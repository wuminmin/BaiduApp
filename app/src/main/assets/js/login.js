$(document).ready(function () {
    var jsonstr = window.getXinxiJsonOne.getLocationData("从页面传给手机的message");//拿到本地数据,并可以传给手机一些内容，可选
     console.log( "从页面传给手机的message" );
     console.log( jsonstr );
    var xinxiObj = JSON.parse(jsonstr);
    var firstJson = {};
    var first = true;
    $.each(xinxiObj, function (i, item) {
        if (first === true) {
            firstJson = item;
            first = false;
        }
    });

// 百度地图API功能
    var map = new BMap.Map("l-map");            // 创建Map实例
       console.log( "测试经纬度1111111111111111111");
    console.log( firstJson['jingdu']  );
     console.log( firstJson['weidu']  );

    var points = [new BMap.Point(firstJson['jingdu'], firstJson['weidu'])];
    var myGeo = new BMap.Geocoder();

    //坐标转换完之后的回调函数
    translateCallback = function (data) {
        if (data.status === 0) {
            for (var i = 0; i < data.points.length; i++) {

                var mPoint = new BMap.Point(data.points[i]['lng'], data.points[i]['lat']);
                map.enableScrollWheelZoom();
                map.centerAndZoom(mPoint, 15);
                var circle = new BMap.Circle(mPoint, 500, {
                    fillColor: "blue",
                    strokeWeight: 1,
                    fillOpacity: 0.3,
                    strokeOpacity: 0.3
                });
                map.addOverlay(circle);

                var local = new BMap.LocalSearch(map, {
                    renderOptions: {map: map, panel: "r-result"}
                });
                local.searchNearby(["住宅", "机构", "酒店", "超市", "餐饮", "村", "路"], mPoint, 500);
                local.setResultsHtmlSetCallback(function (result) {
                    /*返回最近一次检索的结果*/
                    $.each($("ol li"), function () {
                        console.log(this);
                        $(this).bind("click",this,function () {
                            console.log( $(this)[0]['innerText'] );
                              saveDizhi( $(this)[0]['innerText'] );
                        });
                    });
                });
            }
        }
    };

    setTimeout(function () {
        var convertor = new BMap.Convertor();
        convertor.translate(points, 1, 5, translateCallback)
    }, 1000);

    function saveDizhi(s) {
    var file = Android.saveDizhi(s);
    window.alert("file = " + file);
}

});



