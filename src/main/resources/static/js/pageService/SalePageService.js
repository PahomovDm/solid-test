class SalePageService extends PageService {
    constructor() {
        super(new SaleRestService("/api/sales"), SalePageService.parseJsonToHtmlTable)
    }

    static parseJsonToHtmlTable(productJson) {
        let htmlRows = "";
        productJson.forEach(sale => htmlRows += `<tr><td>${sale.id}</td><td>${new Date(sale.date).toLocaleString()}</td><td>${SalePageService.parsePositionList(sale.positions)}</td><td>Сумма товаров и скидка</td><td></tr>`);
        return htmlRows;
    }

    static parsePositionList(positionList) {
        let positionRows = "";
        positionList.forEach(position => positionRows += `<span>${position.product.name} (${position.number})</span><br>`);
        return positionRows;
    }
}

{
    const saleServicePage = new SalePageService();

    document.addEventListener('DOMContentLoaded', function () {
        saleServicePage.loadTable();
    }, false);

}