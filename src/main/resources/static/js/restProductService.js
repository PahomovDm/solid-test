{
    const urlPrefix = "/api/products";

    function getCountOfPagesFromServer() {
        let countOfPages;
        let xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === XMLHttpRequest.DONE) {
                if (xmlhttp.status === 200) {
                    countOfPages = JSON.parse(xmlhttp.responseText);
                } else {
                    console.log("get count of pages error");
                }
            }
        };

        xmlhttp.open("GET", urlPrefix + "/pagination", false);
        xmlhttp.send();

        return countOfPages;
    }

    function getProductListByNumberPageFromServer(currentPage) {
        let productList;
        let xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === XMLHttpRequest.DONE) {
                if (xmlhttp.status === 200) {
                    productList = JSON.parse(xmlhttp.responseText);
                } else {
                    console.log('loadProductTable');
                }
            }
        };

        xmlhttp.open("GET", "/api/products?" + "page=" + currentPage, false);
        xmlhttp.send();

        return productList;
    }

    function sendAddedProduct(data) {
        var xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === XMLHttpRequest.DONE) {
                if (xmlhttp.status === 200) {

                } else {
                    console.log("sendNewProduct")
                }
            }
        };

        xmlhttp.open("POST", "/api/products/add", false);
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xmlhttp.send(data);
    }

    function sendUpdatedProduct(data) {
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === XMLHttpRequest.DONE) {
                if (xmlhttp.status === 200) {

                } else {
                    alert('sendEditProductToSave');
                }
            }
        };


        xmlhttp.open("POST", "/api/products/edit", false);
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xmlhttp.send(data);
    }

}