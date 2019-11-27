class ShoppingCartRestService {

    constructor() {
        this.urlPrefix = "/api/shoppingCart";
    }

    getShoppingCart() {
        let shoppingCart;
        let xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === XMLHttpRequest.DONE) {
                if (xmlhttp.status === 200) {
                    shoppingCart = JSON.parse(xmlhttp.responseText);
                } else if (xmlhttp.status === 204) {
                    throw "ShoppingCart is empty";
                }
                else {
                    console.log('getShoppingCart');
                }
            }
        };

        xmlhttp.open("GET", this.urlPrefix, false);
        xmlhttp.send();

        return shoppingCart;
    }

    sendPositionToShoppingCart(position) {
        let shoppingCart;
        var xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === XMLHttpRequest.DONE) {
                if (xmlhttp.status === 200) {
                    shoppingCart = JSON.parse(xmlhttp.responseText);
                } else {
                    console.log("sendPositionToShoppingCart")
                }
            }
        };

        xmlhttp.open("POST", this.urlPrefix, false);
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xmlhttp.send(position);
    }

    buy() {
        var xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === XMLHttpRequest.DONE) {
                if (xmlhttp.status === 200) {

                } else {
                    console.log("buy");
                }
            }
        };

        xmlhttp.open("POST", this.urlPrefix + "/buy", false);
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xmlhttp.send();
    }
}