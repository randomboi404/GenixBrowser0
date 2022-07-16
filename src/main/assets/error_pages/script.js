function getParameterByName(name, url = window.location.href) {
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}

var statusCode = getParameterByName('code');
var title = getParameterByName('title');
var info = getParameterByName('info');

document.getElementById("statusCode").innerHTML = statusCode;
document.getElementById("title").innerHTML = title;
document.getElementById("info").innerHTML = info;
