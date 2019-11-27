class ShoppingCartPageService {

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
        document.getElementById("shopping-cart").innerHTML = htmlTable;
    }

    parser(shoppingCart) {
        let htmlRows = "";
        shoppingCart.positionList.forEach(position => htmlRows +=
            `<tr><td>${position.product.name}</td><td>${position.number}</td><td>${position.product.price} * ${position.number} = ${position.product.price * position.number}  &#8381</td></tr>`);
        return htmlRows;
    }

    buy() {
        this.shoppingCartRestService.buy();
        this.loadTable();
        new SalePageService().loadTable();
    }
}

{
    const shoppingCartServicePage = new ShoppingCartPageService();

    document.addEventListener('DOMContentLoaded', function () {
        shoppingCartServicePage.loadTable();
    }, false);

    document.getElementById('buy').addEventListener('click', function () {
        shoppingCartServicePage.buy();
    }, false);

}