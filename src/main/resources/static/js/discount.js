const urlPrefix = "/api/discounts";

function send() {
    let xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === XMLHttpRequest.DONE) {
            if (xmlhttp.status === 200) {

            } else {
                console.log("send");
            }
        }
    };

    xmlhttp.open("POST", urlPrefix, false);
    xmlhttp.send();
}

function get() {
    let xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === XMLHttpRequest.DONE) {
            if (xmlhttp.status === 200) {
                console.log(JSON.parse(xmlhttp.responseText));
            } else {
                console.log("send");
            }
        }
    };

    xmlhttp.open("GET", urlPrefix, false);
    xmlhttp.send();
}