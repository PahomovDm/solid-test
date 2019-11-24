document.addEventListener('DOMContentLoaded', function () {
    loadProductTable();

    document.getElementById("send-new-product").addEventListener('click', function (e) {
        sendNewProduct();
    });
}, false);

function setOnclickToEditButton() {
    let edits = document.getElementsByClassName("edit");
    for (let i = 0; i < edits.length; i++) {
        edits[i].addEventListener('click', function (e) {
            openEditForm(this.parentElement.parentElement.childNodes);
        });
    }
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
            sendEditProductToSave(this.parentElement.parentElement.childNodes);
        });
    }

    for (let i = 0; i < cancelButtons.length; i++) {
        cancelButtons[i].addEventListener('click', function (e) {
            cancelEditProduct(this.parentElement.parentElement.childNodes, editName, editPrice);
        });
    }
}

function sendEditProductToSave(childNodes) {
    let editId = childNodes[0].outerText;
    let editName = childNodes[1].childNodes[0].value;
    let editPrice = childNodes[2].childNodes[0].value;
    let xmlhttp = new XMLHttpRequest();

    let data = JSON.stringify({
        'id' : editId,
        'name' : editName,
        'price' : editPrice
    });

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === XMLHttpRequest.DONE) {
            if (xmlhttp.status === 200) {
                childNodes[1].innerHTML = editName;
                childNodes[2].innerHTML = editPrice;
                childNodes[3].innerHTML = `<button class="edit">Edit</button>`;
                setOnclickToEditButton();
            } else {
                alert('sendEditProductToSave');
            }
        }
    };

    xmlhttp.open("POST", "/api/products/edit", false);
    xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xmlhttp.send(data);
}

function cancelEditProduct(childNodes, editName, editPrice) {
    childNodes[1].innerHTML = editName;
    childNodes[2].innerHTML = editPrice;
    childNodes[3].innerHTML = `<button class="edit">Edit</button>`;
    setOnclickToEditButton();
}

function loadProductTable() {
    let url = new URL(window.location.href);
    let params = new URLSearchParams(url.search.slice(1));

    if (params.has("page")) {
        currentPage = parseInt(params.get("page"));
    } else {
        currentPage = 1;
    }

    let xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === XMLHttpRequest.DONE) {
            if (xmlhttp.status === 200) {
                document.getElementById("productTable").innerHTML = parseProductJsonToHtmlTable(JSON.parse(xmlhttp.responseText));
                setOnclickToEditButton();
                loadPaginationPanel(currentPage);
            } else {
                alert('loadProductTable');
            }
        }
    };

    xmlhttp.open("GET", "/api/products?" + "page=" + currentPage, false);
    xmlhttp.send();
}

function parseProductJsonToHtmlTable(productJson) {
    let htmlRows = "";
    for (let i = 0; i < productJson.length; i++) {
        htmlRows += `<tr><td>${productJson[i].id}</td><td>${productJson[i].name}</td><td>${productJson[i].price}</td><td><button class="edit">Edit</button></td></tr>`;
    }
    return htmlRows;
}

function getCountOfPages() {
    let countOfPages;
    var xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === XMLHttpRequest.DONE) {
            if (xmlhttp.status === 200) {
                countOfPages = JSON.parse(xmlhttp.responseText);
            } else {
                alert('getCountOfPages');
            }
        }
    };

    xmlhttp.open("GET", "/api/products/pagination", false);
    xmlhttp.send();

    return countOfPages;
}

function loadPaginationPanel(currentPage){
    let countOfPage = getCountOfPages();

    if (currentPage > countOfPage || currentPage === undefined) {
        currentPage = countOfPage;
    }

    let prevElement = ``, currentElement = ``, nextElement = ``;

    let prevPage = currentPage - 1;
    let nextPage = currentPage + 1;

    if (countOfPage > 1 && currentPage !== 1) {
        prevElement = `<li class="page-item" data-page="1">
                   <a class="page-link" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span>
                   </a>
                </li>
                <li class="page-item" data-page="${prevPage}"><a class="page-link">${prevPage}</a></li>`;
    }

    currentElement = `<li class="page-item" data-page="${currentPage}"><a class="page-link">${currentPage}</a></li>`;

    if (countOfPage > 1 && currentPage !== countOfPage) {
        nextElement = `<li class="page-item" data-page="${nextPage}"><a class="page-link">${nextPage}</a></li>
                        <li class="page-item" data-page="${countOfPage}">
                            <a class="page-link" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>`;
    }
    const htmlElement = `<nav aria-label="Page navigation example">
                            <ul class="pagination">
                            ${prevElement}
                            ${currentElement}
                            ${nextElement}
                            </ul>
                        </nav>`;

    document.getElementById("pagination").innerHTML = htmlElement;

    let pageItem = document.getElementsByClassName("page-item");
    for (let i = 0; i < pageItem.length; i++) {
        pageItem[i].addEventListener('click', function (e) {
            reloadTableToPage(this.dataset.page);
        });
    }
}

function sendNewProduct() {
    let newProductName = document.getElementById("new-name").value;
    let newProductPrice = document.getElementById("new-price").value;

    let data = JSON.stringify({
        'name' : newProductName,
        'price' : newProductPrice
    });

    var xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === XMLHttpRequest.DONE) {
            if (xmlhttp.status === 200) {
                document.getElementById("new-name").value = "";
                document.getElementById("new-price").value = "";
                reloadTableToPage()
            } else {
                alert("sendNewProduct")
            }
        }
    };

    xmlhttp.open("POST", "/api/products/add", true);
    xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xmlhttp.send(data);
}

function reloadTableToPage(page) {
    if (page === undefined) {
        loadProductTable();
        return;
    }
    let url = new URL(window.location.href);
    let params = new URLSearchParams(url.search.slice(1));

    params.set("page", page);

    var newUrl = window.location.protocol + "//" + window.location.host + window.location.pathname + '?' + params.toString();
    window.history.pushState({path:newUrl},'',newUrl);
    loadProductTable();
}