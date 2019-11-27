class ShoppingCartPageService {

    constructor() {
        this.shoppingCartRestService = new ShoppingCartRestService();
        this.discountRestService = new DiscountRestService("/api/discounts");
    }

    loadTable() {
        let shoppingCart;
        let currentDiscount;
        try {
            shoppingCart = this.shoppingCartRestService.getShoppingCart();
            currentDiscount = this.discountRestService.currentDiscount();
        } catch (e) {
            console.log(e);
            return;
        }

        const htmlTable = this.parser(shoppingCart, currentDiscount);
        document.getElementById("shopping-cart").innerHTML = htmlTable;
    }

    parser(shoppingCart, currentDiscount) {
        let htmlRows = "";


        shoppingCart.positionList.forEach(position => {
            if (position.product.id !== currentDiscount.product.id) {
                htmlRows +=
                    `<tr><td>${position.product.name}</td><td>${position.number}</td><td>${position.product.price} * ${position.number} = ${position.product.price * position.number}  &#8381</td></tr>`;
            } else {
                htmlRows +=
                    `<tr><td style="color: coral;">${position.product.name}</td><td>${position.number}</td><td style="color: coral;">${((100 - currentDiscount.size) / 100 * position.product.price).toFixed(2)} * ${position.number} = ${((100 - currentDiscount.size) / 100 * position.product.price * position.number).toFixed(2)}   &#8381</td></tr>`;
            }
        });
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