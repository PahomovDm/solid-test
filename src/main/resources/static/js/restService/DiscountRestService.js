class DiscountRestService extends RestService {

    constructor(urlPrefix) {
        super(urlPrefix);
    }

    currentDiscount() {
        let currentDiscount;
        let xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === XMLHttpRequest.DONE) {
                if (xmlhttp.status === 200) {
                    currentDiscount = JSON.parse(xmlhttp.responseText);
                } else {
                    console.log('currentDiscount');
                }
            }
        };

        xmlhttp.open("GET", this.urlPrefix + "/current", false);
        xmlhttp.send();

        return currentDiscount;
    }

}
