class DiscountPageService extends PageService {

    constructor() {
        super(new DiscountRestService("/api/discounts"), DiscountPageService.parseJsonToHtmlTable);

    }

    static parseJsonToHtmlTable(discountJson) {
        let htmlRows = "";
        discountJson.forEach(discount => htmlRows += `<tr><td>${discount.id}</td><td>${new Date(discount.startTime).toLocaleString()}</td><td>${discount.product.name}</td><td>${discount.size}%</td><td>${((100 - discount.size) / 100 * discount.product.price).toFixed(2)}</td></tr>`);
        return htmlRows;
    }
}

{
    const discountServicePage = new DiscountPageService();

    document.addEventListener('DOMContentLoaded', function () {
        discountServicePage.loadTable();
    }, false);

}
