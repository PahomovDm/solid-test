{
    const productRestService = new ProductRestService("/api/products");
    const paginationService = new PaginationService();

    document.addEventListener('DOMContentLoaded', function () {
        loadTable();

        document.getElementById("send-new-product").addEventListener('click', function (e) {
            addProduct();
        });
    }, false);

    function setOnclickToEditButton() {
        Array.from(document.getElementsByClassName("edit")).forEach((element) => {
            element.addEventListener('click', function (e) {
                openEditForm(this.parentElement.parentElement.childNodes);
            });
        });
    }

    function openEditForm(childNodes) {
        const editName = childNodes[1].outerText;
        const editPrice = childNodes[2].outerText;

        childNodes[1].innerHTML = `<input id="editName" value='${editName}'/>`;
        childNodes[2].innerHTML = `<input id="editPrice" value='${editPrice}'/>`;
        childNodes[3].innerHTML = `<button class="sendEdit">Save</button><button class="cancel">Cancel</button>`;

        const editButtons = document.getElementsByClassName("sendEdit");
        const cancelButtons = document.getElementsByClassName("cancel");

        for (let i = 0; i < editButtons.length; i++) {
            editButtons[i].addEventListener('click', function (e) {
                updateProduct(this.parentElement.parentElement.childNodes);
            });
        }

        for (let i = 0; i < cancelButtons.length; i++) {
            cancelButtons[i].addEventListener('click', function (e) {
                cancelEditFormProduct(this.parentElement.parentElement.childNodes, editName, editPrice);
            });
        }
    }

    function updateProduct(childNodes) {
        let editId = childNodes[0].outerText;
        let editName = childNodes[1].childNodes[0].value;
        let editPrice = childNodes[2].childNodes[0].value;

        let data = JSON.stringify({
            'id': editId,
            'name': editName,
            'price': editPrice
        });

        productRestService.updateProduct(data);
        cancelEditFormProduct(childNodes, editName, editPrice);
    }

    function cancelEditFormProduct(childNodes, editName, editPrice) {
        childNodes[1].innerHTML = editName;
        childNodes[2].innerHTML = editPrice;
        childNodes[3].innerHTML = `<button class="edit">Edit</button>`;

        setOnclickToEditButton();
    }

    function loadTable() {
        const url = new URL(window.location.href);
        const params = new URLSearchParams(url.search.slice(1));
        let currentPage;

        if (params.has("page")) {
            try {
                currentPage = parseInt(params.get("page"));
            } catch (e) {
                console.log(e);
                page = 1;
            }
        } else {
            currentPage = 1;
        }

        document.getElementById("tableBody").innerHTML = parseJsonToHtmlTable(productRestService.getListByPage(currentPage));
        setOnclickToEditButton();
        loadPagination(currentPage);
    }

    function parseJsonToHtmlTable(productJson) {
        let htmlRows = "";
        productJson.forEach(product => htmlRows += `<tr><td>${product.id}</td><td>${product.name}</td><td>${product.price}</td><td><button class="edit">Edit</button></td></tr>`);
        return htmlRows;
    }

    function loadPagination(currentPage) {
        const countOfPage = productRestService.getCountOfPages();
        document.getElementById("pagination").innerHTML = paginationService.getPaginationPanelHtml(currentPage, countOfPage);

        Array.from(document.getElementsByClassName("page-item")).forEach((element) => {
            element.addEventListener('click', function (e) {
                reloadTableToPage(this.dataset.page);
            })
        });
    }

    function addProduct() {
        const newProductName = document.getElementById("new-name").value;
        const newProductPrice = document.getElementById("new-price").value;

        const data = JSON.stringify({
            'name': newProductName,
            'price': newProductPrice
        });

        productRestService.addProduct(data);

        document.getElementById("new-name").value = "";
        document.getElementById("new-price").value = "";
        reloadTableToPage()

    }

    function reloadTableToPage(page) {
        if (page === undefined) {
            loadTable();
            return;
        }
        const url = new URL(window.location.href);
        const params = new URLSearchParams(url.search.slice(1));

        params.set("page", page);

        const newUrl = window.location.protocol + "//" + window.location.host + window.location.pathname + '?' + params.toString();
        window.history.pushState({path: newUrl}, '', newUrl);
        loadTable();
    }
}