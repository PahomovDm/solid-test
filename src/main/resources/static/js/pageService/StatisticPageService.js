class StatisticPageService extends PageService {

    constructor() {
        super(new StatisticRestService("/api/statistics"), StatisticPageService.parseJsonToHtmlTable);

    }

    static parseJsonToHtmlTable(statisticsJson) {
        let htmlRows = "";
        statisticsJson.forEach(statistic => {
                if (statistic.numberOfSales === 0) {
                    const temp = 0;
                    htmlRows +=
                        `<tr>` +
                        `<td>${new Date(statistic.startingTime).toLocaleString()}</td>` +
                        `<td>${temp}</td>` +
                        `<td>${temp}</td>` +
                        `<td>${temp}</td>` +
                        `<td>${temp}</td>` +
                        `<td>${temp}</td>` +
                        `<td>${temp}</td>` +
                        `</tr>`
                } else {
                    htmlRows +=
                        `<tr>` +
                        `<td>${new Date(statistic.startingTime).toLocaleString()}</td>` +
                        `<td>${statistic.numberOfSales}</td>` +
                        `<td>${statistic.amountValueOfChecks + statistic.discountAmount}</td>` +
                        `<td>${(statistic.amountValueOfChecks + statistic.discountAmount) / statistic.numberOfSales}</td>` +
                        `<td>${statistic.discountAmount}</td>` +
                        `<td>${statistic.amountValueOfChecks}</td>` +
                        `<td>${statistic.amountValueOfChecks / statistic.numberOfSales}</td>` +
                        `</tr>`
                }
            }
        );
        return htmlRows;
    }
}

{
    const statisticPageService = new StatisticPageService();

    document.addEventListener('DOMContentLoaded', function () {
        statisticPageService.loadTable();
    }, false);

}
