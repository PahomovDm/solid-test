class StatisticPageService extends PageService {

    constructor() {
        super(new StatisticRestService("/api/statistics"), StatisticPageService.parseJsonToHtmlTable);

    }

    static parseJsonToHtmlTable(discountJson) {
        let htmlRows = "";
        discountJson.forEach(discount => htmlRows += `<tr><td></td><td></td><td></td><td></td><td></td></tr>`);
        return htmlRows;
    }
}

{
    const statisticPageService = new StatisticPageService();

    document.addEventListener('DOMContentLoaded', function () {
        statisticPageService.loadTable();
    }, false);

}
