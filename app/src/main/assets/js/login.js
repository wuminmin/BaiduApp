$(document).ready(function() {
    var jsonstr = window.getXinxiJsonOne.getLocationData("从页面传给手机的message");//拿到本地数据,并可以传给手机一些内容，可选
	//var jsonstr = '[{"rsrp":"-86","rssnr":"0","getAsuLevelCellInfoLte":"54","getLevelCellInfoLte":"4","cqi":"0","getDbmCellInfoLte":"-86","rsrq":"-13","getTimingAdvanceCellInfoLte":"2","eci":"6d3f835","earf":"1825","mcc":"460","mnc":"11","pci":"123","tac":"27953","getAsuLevelCellSignalStrengthCdma":"16","getLevelCellSignalStrengthCdma":"4","getCdmaLevelCellSignalStrengthCdma":"4","getEvdoLevelCellSignalStrengthCdma":"0","dbm":"-64","ecio":"-70","getDbmCellSignalStrengthCdma":"-64","getEvdoDbmCellSignalStrengthCdma":"0","getEvdoEcioCellSignalStrengthCdma":"0","getEvdoSnrCellSignalStrengthCdma":"0","bid":"60051","nid":"18","sid":"14166","weidu":30.657186,"jingdu":117.495127,"pingpai":"Xiaomi","xinghao":"MIX 2","yingjian":"qcom","anzhuo":"8.0.0","wangluo":"LTE","shuju":"LTE","xiaoqu":"LTE","time":"15:38:20","leixing":"安卓","jingque":"300米","fangshi":"GPS","band":3,"enb":"447480","cellid":"53"}]';
	var xinxiObj = JSON.parse(jsonstr);
	var firstJson = {};
	var first = true;
	$.each(xinxiObj, function(i, item) {
		if(first === true) {
			firstJson = item;
			first = false;
		}
	});
	// 百度地图API功能
	var map = new BMap.Map("l-map"); // 创建Map实例
	var points = [new BMap.Point(firstJson['jingdu'], firstJson['weidu'])];
	var myGeo = new BMap.Geocoder();
	//坐标转换完之后的回调函数
	translateCallback = function(data) {
	if(data.status === 0) {
			for(var i = 0; i < data.points.length; i++) {
				var mPoint = new BMap.Point(data.points[i]['lng'], data.points[i]['lat']);
				map.enableScrollWheelZoom();
				map.centerAndZoom(mPoint, 15);
				var circle = new BMap.Circle(mPoint, 100, {
					fillColor: "blue",
					strokeWeight: 1,
					fillOpacity: 0.3,
					strokeOpacity: 0.3
				});
				map.addOverlay(circle);
				var local = new BMap.LocalSearch(map, {
					renderOptions: {
						map: map,
						panel: "r-result"
					}
				});
				local.searchNearby(["住宅", "公司", "酒店", "高校", "餐饮", "村", "路"], mPoint, 200);
				local.setResultsHtmlSetCallback(function(result) {
					/*返回最近一次检索的结果*/
				$.each($("ol li"), function() {
				$(this).bind("tap", this, function() {
				var flag = getQueryString("flag");							
				if(flag === 'shinei') {
				 var dizhi = $(this)[0]['innerText'] ;
                 var t = dizhi.split('\n');
                 var xiangxidizhi =  encodeURIComponent(t[3]);
                 var jianzu = encodeURIComponent(t[1]);                                                         
                 window.sessionStorage.setItem('jianzhu',jianzu);
                 window.sessionStorage.setItem('xiangxi',xiangxidizhi); 
                 window.history.go(-1);				
		        } else if(flag === 'shiwai'){
	             var dizhi = $(this)[0]['innerText'] ;
                 var t = dizhi.split('\n');
                 var xiangxidizhi =  encodeURIComponent(t[3]);
                 var jianzu = encodeURIComponent(t[1]);                                                         
                 window.sessionStorage.setItem('jianzhu_sw',jianzu);
                 window.sessionStorage.setItem('xiangxi_sw',xiangxidizhi); 
                 window.history.go(-1);	
				
				}else{
		        var dizhi = $(this)[0]['innerText'];
			    var t = dizhi.split('\n');
				var dizhi2 = encodeURIComponent(t[3]);
				var jianzu = encodeURIComponent(t[1]);
				saveDizhi(dizhi);
			    var dizhiUrl = '?' + 'dizhi' + '=' + dizhi2 + '&' + 'jianzu' + '=' + jianzu;
				window.location.href = "file:///android_asset/shineng.html" + dizhiUrl;
							}
						});
					});
				});
			}
		}
	};

	setTimeout(function() {
		var convertor = new BMap.Convertor();
		convertor.translate(points, 1, 5, translateCallback)
	}, 1000);

	function getQueryString(name) {
		var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
		if(result == null || result.length < 1) {
			return "";
		}
		return result[1];
	}

	function saveDizhi(s) {
		// var file = Android.saveDizhi(s);
		// window.alert("file = " + file);
	}

});