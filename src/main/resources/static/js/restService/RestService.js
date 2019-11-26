class RestService {

    urlPrefix;

    constructor(urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    getListByPage(currentPage) {
        let productList;
        let xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === XMLHttpRequest.DONE) {
                if (xmlhttp.status === 200) {
                    productList = JSON.parse(xmlhttp.responseText);
                } else {
                    console.log('getListByPage');
                }
            }
        };

        xmlhttp.open("GET", this.urlPrefix + "?page=" + currentPage, false);
        xmlhttp.send();

        return productList;
    }

    getCountOfPages() {
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

        xmlhttp.open("GET", this.urlPrefix + "/pagination", false);
        xmlhttp.send();

        return countOfPages;
    }
}