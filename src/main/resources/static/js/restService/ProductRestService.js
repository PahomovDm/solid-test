class ProductRestService extends RestService {

    constructor(urlPrefix) {
        super(urlPrefix);
    }

    addProduct(data) {
        var xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === XMLHttpRequest.DONE) {
                if (xmlhttp.status === 200) {

                } else {
                    console.log("sendNewProduct")
                }
            }
        };

        xmlhttp.open("POST", this.urlPrefix + "/add", false);
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xmlhttp.send(data);
    }

    updateProduct(data) {
        var xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === XMLHttpRequest.DONE) {
                if (xmlhttp.status === 200) {

                } else {
                    alert('updateProduct');
                }
            }
        };


        xmlhttp.open("POST", this.urlPrefix + "/edit", false);
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xmlhttp.send(data);
    }
}