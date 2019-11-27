class ProductPageService extends PageService{

    constructor() {
        super(new ProductRestService("/api/products"), ProductPageService.parseJsonToHtmlTable);
    }

    loadTable() {
        super.loadTable();
        this.setOnclickToEditButton();
    }

    loadPagination(currentPage) {
        super.loadPagination(currentPage);
        this.setOnclickToEditButton();
    }

    setOnclickToEditButton() {
        const thisObject = this;

        Array.from(document.getElementsByClassName("edit")).forEach((element) => {
            element.addEventListener('click', function (e) {
                thisObject.openEditForm(this.parentElement.parentElement.childNodes);
            });
        });
    }

    static parseJsonToHtmlTable(productJson) {
        let htmlRows = "";
        productJson.forEach(product => htmlRows += `<tr><td>${product.id}</td><td>${product.name}</td><td>${product.price}  &#8381</td><td><button class="edit btn btn-primary">Edit</button></td></tr>`);
        return htmlRows;
    }

    openEditForm(childNodes) {
        const editName = childNodes[1].outerText;
        const editPrice = childNodes[2].outerText;

        childNodes[1].innerHTML = `<input id="editName" value='${editName}'/>`;
        childNodes[2].innerHTML = `<input id="editPrice" value='${editPrice}'/>`;
        childNodes[3].innerHTML = `<button class="sendEdit btn btn-primary">Save</button><button class="cancel btn btn-primary">Cancel</button>`;

        const editButtons = document.getElementsByClassName("sendEdit");
        const cancelButtons = document.getElementsByClassName("cancel");
        const thisObject = this;

        for (let i = 0; i < editButtons.length; i++) {
            editButtons[i].addEventListener('click', function (e) {
                thisObject.updateProduct(this.parentElement.parentElement.childNodes);
            });
        }

        for (let i = 0; i < cancelButtons.length; i++) {
            cancelButtons[i].addEventListener('click', function (e) {
                thisObject.cancelEditFormProduct(this.parentElement.parentElement.childNodes, editName, editPrice);
            });
        }
    }

    cancelEditFormProduct(childNodes, editName, editPrice) {
        childNodes[1].innerHTML = editName;
        childNodes[2].innerHTML = editPrice;
        childNodes[3].innerHTML = `<button class="edit btn btn-primary">Edit</button>`;

        this.setOnclickToEditButton();
    }

    addProduct() {
        const newProductName = document.getElementById("new-name").value;
        const newProductPrice = document.getElementById("new-price").value;

        const data = JSON.stringify({
            'name': newProductName,
            'price': newProductPrice
        });

        this.restService.addProduct(data);

        document.getElementById("new-name").value = "";
        document.getElementById("new-price").value = "";
        super.reloadTableToPage();
    }

    updateProduct(childNodes) {
        let editId = childNodes[0].outerText;
        let editName = childNodes[1].childNodes[0].value;
        let editPrice = childNodes[2].childNodes[0].value;

        let data = JSON.stringify({
            'id': editId,
            'name': editName,
            'price': editPrice
        });

        this.restService.updateProduct(data);
        this.cancelEditFormProduct(childNodes, editName, editPrice);
    }
}

{
    const productService = new ProductPageService();

    document.addEventListener('DOMContentLoaded', function () {
        productService.loadTable();

        document.getElementById("send-new-product").addEventListener('click', function (e) {
            productService.addProduct();
        });
    }, false);
}