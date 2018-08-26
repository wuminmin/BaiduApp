$(document).ready(function () {
    var htmlsum = "";
     var jsonstr = window.MyBrowserAPI.getLocationData("从页面传给手机的message");//拿到本地数据,并可以传给手机一些内容，可选
//    var jsonstr = "[\"/static/img/a1.jpg\", \"/static/img/a2.jpg\", \"/static/img/a2.jpg\", \"/static/img/a3.jpg\"]";
    if (jsonstr !== '[]') {
        var jsonarr = eval(jsonstr);//通过eval执行字符串，结果arr是一个数组，数组内容是"1","2","3","4"
        var first = true;
        $.each(jsonarr, function (i, item) {
            if (first) {
                htmlsum = "<div class=\"item active\">\n" +
                    "<img alt=\"image\" class=\"img-responsive\" src=\"" + item + "\">\n" +
                    "</div>";
                first = false;
            } else {
                htmlsum = htmlsum + "     <div class=\"item \">\n" +
                    "                                <img alt=\"image\" class=\"img-responsive\" src=\"" + item + "\">\n" +
                    "                            </div>";
                console.log(i, item);
            }
        });
        // var html = document.getElementById("myImage").innerHTML;
        //再跟你想追加的代码加到一起插入div中
        document.getElementById("myImage").innerHTML = htmlsum;
    } else {
        var html = document.getElementById("myImage").innerHTML;
        //再跟你想追加的代码加到一起插入div中
        document.getElementById("myImage").innerHTML = html;
    }
        var str = getQueryString("dizhi");

         console.log(decodeURIComponent(str));
         $("#dizhi").val( decodeURIComponent(str) );
});


function getQueryString(name) {
    var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
    if (result == null || result.length < 1) {
        return "";
    }
    return result[1];
}

function showAndroidToast(toast) {
    Android.showToast(toast);
}

function setFilePath(file) {
    document.getElementById('lblpath').innerHTML = file;
    Android.showToast(file);
}

function setFileUri(uri) {
    document.getElementById('lbluri').innerHTML = uri;
    Android.showToast(uri);
}

function choosePhoto() {
    var file = Android.choosePhoto();
    window.alert("file = " + file);
}

function selectPhoto() {
    var file = Android.selectPhoto();
    window.alert("file = " + file);
}

