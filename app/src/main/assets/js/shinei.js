$(document).ready(function() {
	$(document).on('tap', '#floor_select', function() {
		var floorPicker = new mui.PopPicker({
			layer: 2
		});
		floorPicker.setData(floorData);
		floorPicker.show(function(items) {
			$("#floor_select").html((items[0].text == 0 ? "":items[0].text) + items[1].text)
		});
	})

//	  var list = ['images/0F165F9E140FBA77E68B998D0B628C53.jpg',
//	              'images/IMG_20181210_090359_gaitubao_com_.jpg',
//	              'images/icon_banner02.png']
//	  var htmlsum = '';
//	  var oW = new Worker('js/sub_js.js');
//		for (var i = 0; i < list.length; i++) {
//			htmlsum += '<div class="mui-slider-item">' +
//					'<img src="images/icon_banner01.png" id = "'+ "img_"+ i +'" alt="" class="cj-banner-img-border"/>' +
//					'</div>';
//			imgOrientation(list[i],"#img_"+i);
//		}
//		document.getElementById("myImage").innerHTML = htmlsum;
//		
//	return;

	var htmlsum = "";
	var jsonstr = window.MyBrowserAPI.getLocationData("从页面传给手机的message"); //拿到本地数据,并可以传给手机一些内容，可选
	//    var jsonstr = "[\"/static/img/a1.jpg\", \"/static/img/a2.jpg\", \"/static/img/a2.jpg\", \"/static/img/a3.jpg\"]";
	if(jsonstr !== '[]') {
		var jsonarr = eval(jsonstr); //通过eval执行字符串，结果arr是一个数组，数组内容是"1","2","3","4"
		//      var first = true;
		htmlsum = '';
		$.each(jsonarr, function(i, item) {
			htmlsum += '<div class="mui-slider-item">' +
				'<img src="images/icon_banner01.png" id = "' + "img_" + i + '" alt="" class="cj-banner-img-border"/>' +
				'</div>';
			imgOrientation(item , "#img_" + i);
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

	var dizhi = getQueryString("dizhi", 1);
	var jianzu = getQueryString("jianzu", 1);
	console.log(jianzu)
	if(dizhi === '') {} else {
		$("#shineidizhi").val(decodeURIComponent(dizhi));
	}

	if(jianzu == '') {} else {
		jianzu = jianzu.replace("详情»", "")
		$("#shineijianzu").val(decodeURIComponent(jianzu));
	}

	$('.demo1').click(function() {
		swal({
			title: "欢迎使用SweetAlert",
			text: "Sweet Alert 是一个替代传统的 JavaScript Alert 的漂亮提示效果。"
		});
	});

	$('.demo2').click(function() {
		swal({
			title: "太帅了",
			text: "小手一抖就打开了一个框",
			type: "success"
		});
	});

	$('.demo3').click(function() {
		swal({
			title: "您确定要删除这条信息吗",
			text: "删除后将无法恢复，请谨慎操作！",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "删除",
			closeOnConfirm: false
		}, function() {
			swal("删除成功！", "您已经永久删除了这条信息。", "success");
		});
	});

	$('.demo4').click(function() {
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
			function(isConfirm) {
				if(isConfirm) {
					Android.deletePhoto();
					var a = "  <div class=\"item active\">\n" +
						"                        <img alt=\"image\" class=\"img-responsive\" src=\"file:///android_asset/img/hehua.jpg\">\n" +
						"                    </div>";
					document.getElementById("myImage").innerHTML = a;
					var jsonstrgetXinxiJsonOne = window.getXinxiJsonOne.getLocationData("从页面传给手机的message"); //拿到本地数据,并可以传给手机一些内容，可选
					var xinxiObjgetXinxiJsonOne = JSON.parse(jsonstrgetXinxiJsonOne);
					var firstJsongetXinxiJsonOne = {};
					var firstgetXinxiJsonOne = true;
					$.each(xinxiObjgetXinxiJsonOne, function(i, item) {
						if(firstgetXinxiJsonOne === true) {
							firstJsongetXinxiJsonOne = item;
							firstgetXinxiJsonOne = false;
						}
					});
					$.ajax({
						cache: false,
						type: "",
						url: "http://wgyd.wuminmin.top/api",
						data: {
							jingdu: 111,
							weidu: 222
						},
						scriptCharset: "utf-8",
						async: true,
						success: function(data) {
							if(data && data.resultcode == '200') {
								swal("上传成功！", "您已经上传室内场景采集数据。", "success");
							}
						},
						error: function() {
							swal("上传失败！", "网络连接失败。", "error");
						}
					});
				} else {
					swal("已取消", "您取消了操作！", "error");
				}
			});
	});
});

function getQueryString(name, i) {
	var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
	if(result == null || result.length < 1) {
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
	console.log("拍照调用")
	var file = Android.choosePhoto();
	window.alert("file = " + file);
}

function selectPhoto() {
	var file = Android.selectPhoto();
	window.alert("file = " + file);
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