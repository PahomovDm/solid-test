class ProductToSalePageService {

    pageCount = 1;

    constructor() {
        this.productToSaleRestService = new ProductToSaleRestService("/api/products");
    }


}