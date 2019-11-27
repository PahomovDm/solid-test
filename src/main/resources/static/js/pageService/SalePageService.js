class SalePageService extends PageService {
    constructor() {
        super(new SaleRestService("/api/sales"), SalePageService.parseJsonToHtmlTable)
    }

    static parseJsonToHtmlTable(productJson) {
        let htmlRows = "";
        productJson.forEach(sale => htmlRows += `<tr><td>${sale.id}</td><td>${sale.date}</td><td>Функция для отображения товаров</td><td>Сумма товаров и скидка</td><td></tr>`);
        return htmlRows;
    }
}

{
    const saleServicePage = new SalePageService();

    document.addEventListener('DOMContentLoaded', function () {
        saleServicePage.loadTable();
    }, false);

}