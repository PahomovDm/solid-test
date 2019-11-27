class ProductToSalePageService {

    pageCount = 1;

    constructor() {
        this.productToSaleRestService = new ProductToSaleRestService("/api/products");
        this.shoppingCartRestService = new ShoppingCartRestService();
        this.shoppingCartPageService = new ShoppingCartPageService();
    }

    loadTable() {
        let productList;
        try {
            productList = this.productToSaleRestService.getListByPage(this.pageCount);
        } catch (e) {
            return;
        }
        const htmlTable = this.parser(productList);
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

    parser(list) {
        let htmlRows = "";
        list.forEach(product => htmlRows +=
            `<tr><td>${product.id}</td><td>${product.name}</td><td>${product.price}  &#8381</td><td><input type="number"></td><td><input type="button" class="to-shopping-cart" value="В корзину"></td></tr>`);
        return htmlRows;
    }

    sendPositionToCart(positionRow) {
        const productId = positionRow[0].innerText;
        const count = positionRow[3].childNodes[0].value;

        const data = JSON.stringify({
            product:{'id': productId},
            number : count
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