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

    var dizhi = getQueryString("dizhi",1);
    var jianzu = getQueryString("jianzu",1);
    console.log(jianzu)
    if( dizhi === ''){
    }else{
        $("#shineidizhi").val( decodeURIComponent(dizhi) );
    }

    if(jianzu == ''){
    }else{
        jianzu = jianzu.replace("详情»", "")
        $("#shineijianzu").val( decodeURIComponent( jianzu) );
    }

    
    $('.demo1').click(function () {
                swal({
                    title: "欢迎使用SweetAlert",
                    text: "Sweet Alert 是一个替代传统的 JavaScript Alert 的漂亮提示效果。"
                });
            });

    $('.demo2').click(function () {
        swal({
            title: "太帅了",
            text: "小手一抖就打开了一个框",
            type: "success"
        });
    });

    $('.demo3').click(function () {
        swal({
            title: "您确定要删除这条信息吗",
            text: "删除后将无法恢复，请谨慎操作！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            closeOnConfirm: false
        }, function () {
            swal("删除成功！", "您已经永久删除了这条信息。", "success");
        });
    });

    $('.demo4').click(function () {
        swal({
                title: "您确定要上传室内场景采集数据吗？",
                text: "请在上传前，检查数据是否完整",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "是的，我要上传！",
                cancelButtonText: "让我再考虑一下…",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function (isConfirm) {
                if (isConfirm) {
                    Android.deletePhoto();

                          var a = "  <div class=\"item active\">\n" +
            "                        <img alt=\"image\" class=\"img-responsive\" src=\"file:///android_asset/img/hehua.jpg\">\n" +
            "                    </div>";
                     document.getElementById("myImage").innerHTML = a;
                    swal("上传成功！", "您已经上传室内场景采集数据。", "success");

                } else {
                    swal("已取消", "您取消了操作！", "error");
                }
            });
    });


});


function getQueryString(name,i) {
    var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
    if (result == null || result.length < 1) {
        return "";
    }
    return result[i];
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

