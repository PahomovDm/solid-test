class StatisticPageService extends PageService {

    constructor() {
        super(new StatisticRestService("/api/statistics"), StatisticPageService.parseJsonToHtmlTable);

    }

    static parseJsonToHtmlTable(statisticsJson) {
        let htmlRows = "";
        statisticsJson.forEach(statistic => {
            const nextHour = new Date(statistic.startingTime);
            nextHour.setHours(nextHour.getHours() + 1);
            nextHour.setMinutes(0);
            nextHour.setSeconds(0);
            const dateTd = `${new Date(statistic.startingTime).toLocaleString()} - ${nextHour.toLocaleTimeString()}`;
                if (statistic.numberOfSales === 0) {
                    const temp = 0;
                    htmlRows +=
                        `<tr>` +
                        `<td>${dateTd}</td>` +
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
                        `<td>${dateTd}</td>` +
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
