class PaginationService {
    getPaginationPanelHtml(currentPage, countOfPage) {
        if (currentPage > countOfPage || currentPage === undefined) {
            currentPage = countOfPage;
        }

        let prevElement = ``, currentElement = ``, nextElement = ``;

        let prevPage = currentPage - 1;
        let nextPage = currentPage + 1;

        if (countOfPage > 1 && currentPage !== 1) {
            prevElement = `<li class="page-item" data-page="1">
                   <a class="page-link" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span>
                   </a>
                </li>
                <li class="page-item" data-page="${prevPage}"><a class="page-link">${prevPage}</a></li>`;
        }

        currentElement = `<li class="page-item" data-page="${currentPage}"><a class="page-link">${currentPage}</a></li>`;

        if (countOfPage > 1 && currentPage !== countOfPage) {
            nextElement = `<li class="page-item" data-page="${nextPage}"><a class="page-link">${nextPage}</a></li>
                        <li class="page-item" data-page="${countOfPage}">
                            <a class="page-link" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>`;
        }
        const htmlElement = `<nav aria-label="Page navigation example">
                            <ul class="pagination">
                            ${prevElement}
                            ${currentElement}
                            ${nextElement}
                            </ul>
                        </nav>`;
        return htmlElement;
    }
}
