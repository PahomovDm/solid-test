{
    const discountRestService = new DiscountRestService("/api/discounts");
    const paginationService = new PaginationService();

    document.addEventListener('DOMContentLoaded', function () {
        loadTable();
    }, false);

    function loadTable() {
        const url = new URL(window.location.href);
        const params = new URLSearchParams(url.search.slice(1));
        let currentPage;

        if (params.has("page")) {
            try {
                currentPage = parseInt(params.get("page"));
            } catch (e) {
                console.log(e);
                page = 1;
            }
        } else {
            currentPage = 1;
        }

        document.getElementById("tableBody").innerHTML = parseJsonToHtmlTable(discountRestService.getListByPage(currentPage));
        loadPagination(currentPage);
    }

    function parseJsonToHtmlTable(discountJson) {
        let htmlRows = "";
        discountJson.forEach(discount => htmlRows += `<tr><td>${discount.id}</td><td>${discount.startTime}</td><td>${discount.product.name}</td><td>${discount.size}</td><td>Считать</td></tr>`);
        return htmlRows;
    }

    function loadPagination(currentPage) {
        const countOfPage = discountRestService.getCountOfPages();
        document.getElementById("pagination").innerHTML = paginationService.getPaginationPanelHtml(currentPage, countOfPage);

        Array.from(document.getElementsByClassName("page-item")).forEach((element) => {
            element.addEventListener('click', function (e) {
                reloadTableToPage(this.dataset.page);
            })
        });
    }

    function reloadTableToPage(page) {
        if (page === undefined) {
            loadTable();
            return;
        }
        const url = new URL(window.location.href);
        const params = new URLSearchParams(url.search.slice(1));

        params.set("page", page);

        const newUrl = window.location.protocol + "//" + window.location.host + window.location.pathname + '?' + params.toString();
        window.history.pushState({path: newUrl}, '', newUrl);
        loadTable();
    }
}
