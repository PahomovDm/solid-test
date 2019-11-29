class ProductPageService extends PageService {

    constructor() {
        super(new ProductRestService("/api/products"), ProductPageService.parseJsonToHtmlTable);
    }

    loadTable() {
        super.loadTable();
        this.setEvents();
    }

    loadPagination(currentPage) {
        super.loadPagination(currentPage);
        this.setEvents();
    }

    setEvents() {
        const thisObject = this;

        Array.from(document.getElementsByClassName("edit")).forEach((element) => {
            element.addEventListener('click', function (e) {
                thisObject.openEditForm(this.parentElement.parentElement.childNodes);
            });
        });

        Array.from(document.getElementsByClassName("information")).forEach((element) => {
            element.addEventListener('mouseover', function (e) {
                thisObject.showInformationByProductId(this);
            });
        });
    }

    static parseJsonToHtmlTable(productJson) {
        let htmlRows = "";
        productJson.forEach(product => htmlRows +=
            `<tr><td>${product.id}</td><td class="information">${product.name}</td><td>${product.price}</td><td><button class="edit btn btn-primary btn-sm">Edit</button></td></tr>`);
        return htmlRows;
    }

    openEditForm(childNodes) {
        const editName = childNodes[1].outerText;
        const editPrice = childNodes[2].outerText;

        childNodes[1].innerHTML = `<input id="editName" value='${editName}'/>`;
        childNodes[2].innerHTML = `<input id="editPrice" value='${editPrice}'/>`;
        childNodes[3].innerHTML = `<button class="sendEdit btn btn-primary btn-sm">Save</button><button class="cancel btn btn-primary btn-sm">Cancel</button>`;

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
        childNodes[3].innerHTML = `<button class="edit btn btn-primary btn-sm">Edit</button>`;

        this.setEvents();
    }

    addProduct() {
        const newProductName = document.getElementById("new-name").value;
        const newProductPrice = document.getElementById("new-price").value;
        let isValid = true;

        if (newProductName === '') {
            document.getElementById("new-name").style.borderColor = "#ce8cb0";
            isValid = false;
        } else {
            document.getElementById("new-name").style.borderColor = "";
        }
        if (newProductPrice <= 0) {
            document.getElementById("new-price").style.borderColor = "#ce8cb0";
            isValid = false;
        } else {
            document.getElementById("new-price").style.borderColor = "";
        }

        if (!isValid) {
            return;
        }

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

        let isValid = true;

        if (editName === '') {
            document.getElementById("editName").style.borderColor = "#ce8cb0";
            isValid = false;
        } else {
            document.getElementById("editName").style.borderColor = "";
        }
        if (editPrice <= 0) {
            document.getElementById("editPrice").style.borderColor = "#ce8cb0";
            isValid = false;
        } else {
            document.getElementById("editPrice").style.borderColor = "";
        }

        if (!isValid) {
            return;
        }

        let data = JSON.stringify({
            'id': editId,
            'name': editName,
            'price': editPrice
        });

        this.restService.updateProduct(data);
        this.cancelEditFormProduct(childNodes, editName, editPrice);
    }

    showInformationByProductId(element) {
        let productInformation;
        let htmlTitle;
        try {
            productInformation = this.restService.informationByProductId(element.parentElement.childNodes[0].innerText);
            htmlTitle = `Кол-во проданных товаров - ${productInformation.number}\nПоследняя продажа - ${new Date(productInformation.lastSale).toLocaleString()}`;
        } catch (e) {
            htmlTitle = `Информации о продажах нет`;
        }
        element.title = htmlTitle;
    }
}

{
    const productPageService = new ProductPageService();

    document.addEventListener('DOMContentLoaded', function () {
        productPageService.loadTable();

        document.getElementById("send-new-product").addEventListener('click', function (e) {
            productPageService.addProduct();
        });
    }, false);
}