class ProductToSalePageService {

    pageCount = 1;

    constructor() {
        this.productToSaleRestService = new ProductToSaleRestService("/api/products");
        this.shoppingCartRestService = new ShoppingCartRestService();
        this.shoppingCartPageService = new ShoppingCartPageService();
        this.discointRestService = new DiscountRestService("/api/discounts");
    }

    loadTable() {
        let productList;
        let currentDiscount;
        try {
            productList = this.productToSaleRestService.getListByPage(this.pageCount);
            currentDiscount = this.discointRestService.currentDiscount();
        } catch (e) {
            return;
        }
        const htmlTable = this.parser(productList, currentDiscount);
        document.getElementById("products-to-sale").innerHTML += htmlTable;

        const currentObject = this;

        Array.from(document.getElementsByClassName("to-shopping-cart")).forEach((element) => {
            element.addEventListener('click', function (e) {
                currentObject.sendPositionToCart(this.parentElement.parentElement.childNodes);
            });
        });

        if (this.productToSaleRestService.getCountOfPages() === this.pageCount) {
            document.getElementById("more-sale-product").hidden = true;
        }

        this.pageCount++;
    }

    parser(list, currentDiscount) {
        let htmlRows = "";
        list.forEach(product => {
            if (product.id !== currentDiscount.product.id) {
                htmlRows +=
                    `<tr><td>${product.id}</td><td>${product.name}</td><td>${product.price}  &#8381</td><td><input type="number"></td><td><input type="button" class="to-shopping-cart" value="В корзину"></td></tr>`;
            } else {
                htmlRows +=
                    `<tr><td>${product.id}</td><td style="color: coral;">${product.name}</td><td style="color: coral;">${((100 - currentDiscount.size) / 100 * product.price).toFixed(2)} &#8381</td><td><input type="number"></td><td><input type="button" class="to-shopping-cart" value="В корзину"></td></tr>`;
            }

    });
        return htmlRows;
    }

    sendPositionToCart(positionRow) {
        const productId = positionRow[0].innerText;
        const count = positionRow[3].childNodes[0].value;

        const data = JSON.stringify({
            product: {'id': productId},
            number: count
        });

        this.shoppingCartRestService.sendPositionToShoppingCart(data);
        this.shoppingCartPageService.loadTable();
    }
}

{
    const productToSalePageService = new ProductToSalePageService();

    document.addEventListener('DOMContentLoaded', function () {
        productToSalePageService.loadTable();
    }, false);

    document.getElementById("more-sale-product").addEventListener('click', function () {
        productToSalePageService.loadTable();
    }, false);

}