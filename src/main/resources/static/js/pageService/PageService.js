class PageService {

    constructor(restService, parser) {
        this.restService = restService;
        this.parser = parser;
    }

    loadTable() {
        const url = new URL(window.location.href);
        const params = new URLSearchParams(url.search.slice(1));
        let currentPage;

        if (params.has("page")) {
            try {
                currentPage = parseInt(params.get("page"));
            } catch (e) {
                console.log(e);
                currentPage = 1;
            }
        } else {
            currentPage = 1;
        }
        if (currentPage < 1) {
            currentPage = 1;
        }

        this.setPageParamToUrl(currentPage);

        const tableContent = this.restService.getListByPage(currentPage);
        if (tableContent !== undefined) {
            document.getElementById("tableBody").innerHTML = this.parser(tableContent);
        }
        this.loadPagination(currentPage);
    }

    loadPagination(currentPage) {
        const countOfPage = this.restService.getCountOfPages();
        document.getElementById("pagination").innerHTML = PaginationService.getPaginationPanelHtml(currentPage, countOfPage);

        const thisObject = this;
        Array.from(document.getElementsByClassName("page-item")).forEach((element) => {
            element.addEventListener('click', function (e) {
                thisObject.reloadTableToPage(this.dataset.page);
            })
        });
    }

    reloadTableToPage(page) {
        if (page === undefined) {
            this.loadTable();
            return;
        }
        this.setPageParamToUrl(page);
        this.loadTable();
    }

    setPageParamToUrl(pageNumber) {
        if (pageNumber !== 1) {
            const url = new URL(window.location.href);
            const params = new URLSearchParams(url.search.slice(1));

            params.set("page", pageNumber);

            const newUrl = window.location.protocol + "//" + window.location.host + window.location.pathname + '?' + params.toString();
            window.history.pushState({path: newUrl}, '', newUrl);
        }
    }
}