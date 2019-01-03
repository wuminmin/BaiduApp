//上传参数
var img_url;
var now_position;
var all_floor;
var buliding_name;
var detail_adress;
var xinghao_2g_dx;
var xinghao_2g_lt;
var xinghao_2g_yd;
var xinghao_4g_dx;
var xinghao_4g_lt;
var xinghao_4g_yd;
var explanation;
var phone_num;

$(document).ready(function() {
	if(sessionStorage.getItem("jianzhu")){
	           buliding_name = sessionStorage.getItem("jianzhu");
               detail_adress = sessionStorage.getItem("xiangxi");
	           buliding_name =  decodeURIComponent(buliding_name);
	           detail_adress =  decodeURIComponent(detail_adress);
	            buliding_name = buliding_name.replace("详情»", "");
	            console.log("addevent"+detail_adress+"---"+buliding_name);
	           document.getElementById('build_name').value = buliding_name;
	           document.getElementById('detail_address').value = detail_adress;	
	}
	           

	$(document).on('tap', '#floor_select', function() {
		var floorPicker = new mui.PopPicker({
			layer: 2
		});
		floorPicker.setData(floorData);
		floorPicker.show(function(items) {
			$("#floor_select").val((items[0].text == 0 ? "" : items[0].text) + items[1].text)
		});
	})
	$(document).on('tap', '#position_select', function() {
		var positionPicker = new mui.PopPicker({
			layer: 2
		});
		positionPicker.setData(floorData);
		positionPicker.show(function(items) {
			$("#position_select").val((items[0].text == 0 ? "" : items[0].text) + items[1].text)
		});
	})

	$(document).on('tap', '#build_name', function() {
 	 window.location.href = "file:///android_asset/login.html?flag=shinei";
 //window.location.href = "login.html?flag=shinei";
	})

	$(document).on('tap', '#xin_hao_2g_dx', function() {
		var arr = new Array();
		arr = document.getElementById("xin_hao_2g_dx").getAttribute("class");
		var reg = RegExp(/list-hover2/);
		if(arr.match(reg)) {
			arr = arr.replace("list-hover2", "");
			document.getElementById("xin_hao_2g_dx").setAttribute("class", arr);
		} else {
			arr = arr.concat("list-hover2");
			document.getElementById("xin_hao_2g_dx").setAttribute("class", arr);
		}
	})
	$(document).on('tap', '#xin_hao_2g_lt', function() {
		var arr = new Array();
		arr = document.getElementById("xin_hao_2g_lt").getAttribute("class");
		var reg = RegExp(/list-hover2/);
		if(arr.match(reg)) {
			arr = arr.replace("list-hover2", "");
			document.getElementById("xin_hao_2g_lt").setAttribute("class", arr);
		} else {
			arr = arr.concat("list-hover2");
			document.getElementById("xin_hao_2g_lt").setAttribute("class", arr);
		}
	})

	$(document).on('tap', '#xin_hao_2g_yd', function() {

		var arr = new Array();
		arr = document.getElementById("xin_hao_2g_yd").getAttribute("class");
		var reg = RegExp(/list-hover2/);
		if(arr.match(reg)) {
			arr = arr.replace("list-hover2", "");
			document.getElementById("xin_hao_2g_yd").setAttribute("class", arr);
		} else {
			arr = arr.concat("list-hover2");
			document.getElementById("xin_hao_2g_yd").setAttribute("class", arr);
			
		}
	})

	$(document).on('tap', '#xin_hao_4g_dx', function() {
		var arr = new Array();
		arr = document.getElementById("xin_hao_4g_dx").getAttribute("class");
		var reg = RegExp(/list-hover2/);
		if(arr.match(reg)) {
			arr = arr.replace("list-hover2", "");
			document.getElementById("xin_hao_4g_dx").setAttribute("class", arr);
		} else {
			arr = arr.concat("list-hover2");
			document.getElementById("xin_hao_4g_dx").setAttribute("class", arr);
		}
	})

	$(document).on('tap', '#xin_hao_4g_lt', function() {
		var arr = new Array();
		arr = document.getElementById("xin_hao_4g_lt").getAttribute("class");
		var reg = RegExp(/list-hover2/);
		if(arr.match(reg)) {
			arr = arr.replace("list-hover2", "");
			document.getElementById("xin_hao_4g_lt").setAttribute("class", arr);
		} else {
			arr = arr.concat("list-hover2");
			document.getElementById("xin_hao_4g_lt").setAttribute("class", arr);
		}
	})

	$(document).on('tap', '#xin_hao_4g_yd', function() {
		var arr = new Array();
		arr = document.getElementById("xin_hao_4g_yd").getAttribute("class");
		var reg = RegExp(/list-hover2/);
		if(arr.match(reg)) {
			arr = arr.replace("list-hover2", "");
			document.getElementById("xin_hao_4g_yd").setAttribute("class", arr);
		} else {
			arr = arr.concat("list-hover2");
			document.getElementById("xin_hao_4g_yd").setAttribute("class", arr);
		}
	})

	$('#demo').click(function() {
		var btnArray = ['取消上传', '确认上传'];
		mui.confirm('您确定要上传室内场景采集数据吗？', '请在上传前，检查数据是否完整 ', btnArray, function(e) {
			if(e.index == 1) {
				var reg = RegExp(/list-hover2/);
				var classval_2g_dx = document.getElementById("xin_hao_2g_dx").getAttribute("class");
				if(classval_2g_dx.match(reg)) {
					xinghao_2g_dx = 0;
				} else {
					xinghao_2g_dx = 1;
				}

				var classval_2g_lt = document.getElementById("xin_hao_2g_lt").getAttribute("class");
				if(classval_2g_lt.match(reg)) {
					xinghao_2g_lt = 0;
				} else {
					xinghao_2g_lt = 1;
				}

				var classval_2g_yd = document.getElementById("xin_hao_2g_yd").getAttribute("class");
				if(classval_2g_yd.match(reg)) {
					xinghao_2g_yd = 0;
				} else {
					xinghao_2g_yd = 1;
				}

				var classval_4g_dx = document.getElementById("xin_hao_4g_dx").getAttribute("class");
				if(classval_4g_dx.match(reg)) {
					xinghao_4g_dx = 0;
				} else {
					xinghao_4g_dx = 1;
				}

				var classval_4g_lt = document.getElementById("xin_hao_4g_lt").getAttribute("class");
				if(classval_4g_lt.match(reg)) {
					xinghao_4g_lt = 0;
				} else {
					xinghao_4g_lt = 1;
				}

				var classval_4g_yd = document.getElementById("xin_hao_4g_yd").getAttribute("class");
				if(classval_4g_yd.match(reg)) {
					xinghao_4g_yd = 0;
				} else {
					xinghao_4g_yd = 1;
				}

				var jsonstr = window.MyBrowserAPI.getLocationData("从页面传给手机的message");
				if(jsonstr !== '[]') {
					img_url = eval(jsonstr);
				}
				now_position = $("#position_select").html();
				all_floor = $("#floor_select").html();
				buliding_name = $("#build_name").val();
				detail_adress = $("#detail_address").val();
				explanation = $("#explanation").val();
				phone_num = $("#phone_num").val();

				var param = {
					"img_url": img_url,
					"now_position": now_position,
					"all_floor": all_floor,
					"buliding_name": buliding_name,
					"detail_adress": detail_adress,
					"xinghao_2g_dx": xinghao_2g_dx,
					"xinghao_2g_lt": xinghao_2g_lt,
					"xinghao_2g_yd": xinghao_2g_yd,
					"xinghao_4g_dx": xinghao_4g_dx,
					"xinghao_4g_lt": xinghao_4g_lt,
					"xinghao_4g_yd": xinghao_4g_yd,
					"explanation": explanation,
					"phone_num": phone_num,
				};

				var paramStr = JSON.stringify(param);
				console.log(paramStr + '传参');
		
				
				 var jsonStr = Android.upload_data(paramStr);  
	 //var jsonStr = "{\"code\":\"error\",\"msg\":\"错误详情\"}";
	 var obj = JSON.parse(jsonStr);   
     
     if(obj.code ==="ok"){
     	 mui.toast("上传成功"); 
     }else{
     	mui.toast(obj.msg); 
     }

			} else {

			}
		})
	})
//return;
	var htmlsum = "";
	var jsonstr = window.MyBrowserAPI.getLocationData("从页面传给手机的message"); //拿到本地数据,并可以传给手机一些内容，可选
	if(jsonstr !== '[]') {
		var jsonarr = eval(jsonstr); //通过eval执行字符串，结果arr是一个数组，数组内容是"1","2","3","4"
		//      var first = true;
		htmlsum = '';
		$.each(jsonarr, function(i, item) {
			htmlsum += '<div class="mui-slider-item">' +
				'<img src="images/icon_banner01.png" id = "' + "img_" + i + '" alt="" class="cj-banner-img-border"/>' +
				'</div>';
			imgOrientation(item, "#img_" + i);
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

	$('#demo').click(function() {
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