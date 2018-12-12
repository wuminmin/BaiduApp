$(document).ready(function () {
    var htmlsum = "";
     var jsonstr = window.MyBrowserAPI.getLocationData("从页面传给手机的message");//拿到本地数据,并可以传给手机一些内容，可选
//    var jsonstr = "[\"/static/img/a1.jpg\", \"/static/img/a2.jpg\", \"/static/img/a2.jpg\", \"/static/img/a3.jpg\"]";
    if (jsonstr !== '[]') {
        var jsonarr = eval(jsonstr);//通过eval执行字符串，结果arr是一个数组，数组内容是"1","2","3","4"
//      var first = true;
        htmlsum = '';
        $.each(jsonarr, function (i, item) {
          	htmlsum += '<div class="mui-slider-item">' +
				'<img src="images/icon_banner02.png" id = "'+ "img_"+ i +'" alt="" class="cj-banner-img-border"/>' +
				'</div>';
		    imgOrientation(item,"#img_"+i);
//          if (first) {
//              htmlsum = "<div class=\"item active\">\n" +
//                  "<img alt=\"image\" class=\"img-responsive\" src=\"" + item + "\">\n" +
//                  "</div>";
//              first = false;
//          } else {
//              htmlsum = htmlsum + "     <div class=\"item \">\n" +
//                  "                                <img alt=\"image\" class=\"img-responsive\" src=\"" + item + "\">\n" +
//                  "                            </div>";
//              console.log(i, item);
//          }
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
    if( str === ''){
//        document.getElementById("dizhi").innerText = '清点击左边按钮选择地址';
//          var jsonstrdizhi = window.getXinxiJsonOne.getLocationData("从页面传给手机的message");//拿到本地数据,并可以传给手机一些内容，可选
        //           var jsonstrdizhi = '[{"rsrp":"-86","rssnr":"0","getAsuLevelCellInfoLte":"54","getLevelCellInfoLte":"4","cqi":"0","getDbmCellInfoLte":"-86","rsrq":"-13","getTimingAdvanceCellInfoLte":"2","eci":"6d3f835","earf":"1825","mcc":"460","mnc":"11","pci":"123","tac":"27953","getAsuLevelCellSignalStrengthCdma":"16","getLevelCellSignalStrengthCdma":"4","getCdmaLevelCellSignalStrengthCdma":"4","getEvdoLevelCellSignalStrengthCdma":"0","dbm":"-64","ecio":"-70","getDbmCellSignalStrengthCdma":"-64","getEvdoDbmCellSignalStrengthCdma":"0","getEvdoEcioCellSignalStrengthCdma":"0","getEvdoSnrCellSignalStrengthCdma":"0","bid":"60051","nid":"18","sid":"14166","weidu":30.657186,"jingdu":117.495127,"pingpai":"Xiaomi","xinghao":"MIX 2","yingjian":"qcom","anzhuo":"8.0.0","wangluo":"LTE","shuju":"LTE","xiaoqu":"LTE","time":"15:38:20","leixing":"安卓","jingque":"300米","fangshi":"GPS","band":3,"enb":"447480","cellid":"53"}]';
//                   var xinxiObj = JSON.parse(jsonstrdizhi);
//                   var firstJson = {};
//                   var first = true;
//                   $.each(xinxiObj, function (i, item) {
//                       if (first === true) {
//                           firstJson = item;
//                           first = false;
//                       }
//                   });
//                   var points = [new BMap.Point(firstJson['jingdu'], firstJson['weidu'])];
//                   var myGeo = new BMap.Geocoder();
//                   //坐标转换完之后的回调函数
//                   translateCallback = function (data) {
//                       if (data.status === 0) {
//                           for (var i = 0; i < data.points.length; i++) {
//                               myGeo.getLocation(new BMap.Point(  data.points[i]['lng'] , data.points[i]['lat']), function (result) {
//                                   if (result) {
//                                        $("#dizhi").val( result.address );
//                                   }
//                               });
//                           }
//                       }
//                   };
//                   setTimeout(function () {
//                       var convertor = new BMap.Convertor();
//                       convertor.translate(points, 1, 5, translateCallback)
//                   }, 1000);
    }else{
        $("#dizhi").val( decodeURIComponent(str) );
    }
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
     window.location.href = "file:///android_asset/shiwai.html";
}

function selectPhoto() {
    var file = Android.selectPhoto();
      window.location.href = "file:///android_asset/shiwai.html";
}

function imgOrientation(img_url, dom_id) {
	var Orientation = 1;
	var imageObj = new Image();
	imageObj.src = img_url;
	imageObj.onload = function() {
		var cvs = document.createElement('canvas');
		var ctx = cvs.getContext('2d');
		cvs.width = this.width;
		cvs.height = this.height; //计算等比缩小后图片宽高
		EXIF.getData(imageObj, function() {
			Orientation = EXIF.getTag(this, "Orientation");
			if(Orientation && Orientation != 1) {
				switch(Orientation) {
					case 6: // 旋转90度
						cvs.width = this.height;
						cvs.height = this.width;
						ctx.rotate(Math.PI / 2);
						// (0,-imgHeight) 从旋转原理图那里获得的起始点
						ctx.drawImage(this, 0, -this.height, this.width, this.height);
						break;
					case 3: // 旋转180度
						ctx.rotate(Math.PI);
						ctx.drawImage(this, this.width, -this.height, this.width, this.height);
						break;
					case 8: // 旋转-90度
						cvs.width = this.height;
						cvs.height = this.width;
						ctx.rotate(3 * Math.PI / 2);
						ctx.drawImage(this, -this.width, 0, this.width, this.height);
						break;
				}
				var newImageData = cvs.toDataURL(this, 1);
			    console.log(JSON.stringify(newImageData));
			    $(dom_id).attr('src', newImageData);
			} else {
				$(dom_id).attr('src', img_url);
			}
		});
	};
}

