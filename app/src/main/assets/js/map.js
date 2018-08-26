$(document).ready(function () {
    var jsonstr = window.getXinxiJsonOne.getLocationData("从页面传给手机的message");//拿到本地数据,并可以传给手机一些内容，可选
//    var jsonstr = '[{"rsrp":"-86","rssnr":"0","getAsuLevelCellInfoLte":"54","getLevelCellInfoLte":"4","cqi":"0","getDbmCellInfoLte":"-86","rsrq":"-13","getTimingAdvanceCellInfoLte":"2","eci":"6d3f835","earf":"1825","mcc":"460","mnc":"11","pci":"123","tac":"27953","getAsuLevelCellSignalStrengthCdma":"16","getLevelCellSignalStrengthCdma":"4","getCdmaLevelCellSignalStrengthCdma":"4","getEvdoLevelCellSignalStrengthCdma":"0","dbm":"-64","ecio":"-70","getDbmCellSignalStrengthCdma":"-64","getEvdoDbmCellSignalStrengthCdma":"0","getEvdoEcioCellSignalStrengthCdma":"0","getEvdoSnrCellSignalStrengthCdma":"0","bid":"60051","nid":"18","sid":"14166","weidu":30.657186,"jingdu":117.495127,"pingpai":"Xiaomi","xinghao":"MIX 2","yingjian":"qcom","anzhuo":"8.0.0","wangluo":"LTE","shuju":"LTE","xiaoqu":"LTE","time":"15:38:20","leixing":"安卓","jingque":"300米","fangshi":"GPS","band":3,"enb":"447480","cellid":"53"}]';
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
                map.addOverlay(mPoint);
                 map.addOverlay(circle);

            }
        }
    };

    setTimeout(function () {
        var convertor = new BMap.Convertor();
        convertor.translate(points, 1, 5, translateCallback)
    }, 1000);

    function getQueryString(name) {
        var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
        if (result == null || result.length < 1) {
            return "";
        }
        return result[1];
    }


});



