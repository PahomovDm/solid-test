class ShoppingCartServicePage {

    constructor() {
        this.shoppingCartRestService = new ShoppingCartRestService();
    }

    loadTable() {
        let shoppingCart;
        try {
            shoppingCart = this.shoppingCartRestService.getShoppingCart();
        } catch (e) {
            console.log(e);
            return;
        }

        const htmlTable = this.parser(shoppingCart);
        document.getElementById("products-to-sale").innerHTML += htmlTable;
    }

    parser(shoppingCart) {
        let htmlRows = "";
        list.positions.forEach(position => htmlRows +=
            `<tr><td>${position.product.name}</td><td>${position.number}</td><td>${position.product.price} * ${position.number} = ${position.product.price * position.number}</td></tr>`);
        return htmlRows;
    }
}

{
    const shoppingCartServicePage = new ShoppingCartServicePage();

    document.addEventListener('DOMContentLoaded', function () {
        shoppingCartServicePage.loadTable();
    }, false);

}