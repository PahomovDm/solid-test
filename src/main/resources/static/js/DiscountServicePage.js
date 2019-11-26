class DiscountPageService extends PageService {

    constructor() {
        super(new DiscountRestService("/api/discounts"), DiscountPageService.parseJsonToHtmlTable);

    }

    static parseJsonToHtmlTable(discountJson) {
        let htmlRows = "";
        discountJson.forEach(discount => htmlRows += `<tr><td>${discount.id}</td><td>${discount.startTime}</td><td>${discount.product.name}</td><td>${discount.size}</td><td>Считать</td></tr>`);
        return htmlRows;
    }
}

{
    const discountServicePage = new DiscountPageService();

    document.addEventListener('DOMContentLoaded', function () {
        discountServicePage.loadTable();
    }, false);

}
