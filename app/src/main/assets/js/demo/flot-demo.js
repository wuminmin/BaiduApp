$(function () {
    var barOptions = {
        series: {
            lines: {
                show: true,
                lineWidth: 2,
                fill: true,
                fillColor: {
                    colors: [{
                        opacity: 0.0
                    }, {
                        opacity: 0.0
                    }]
                }
            }
        },
        xaxis: {
            tickDecimals: 0
        },
        colors: ["#1ab394"],
        grid: {
            color: "#999999",
            hoverable: true,
            clickable: true,
            tickColor: "#D4D4D4",
            borderWidth: 0
        },
        legend: {
            show: false
        },
        tooltip: true,
        tooltipOpts: {
            content: "x: %x, y: %y"
        }
    };
     var jsonstr = window.MyBrowserAPI.getLocationData("从页面传给手机的message");//拿到本地数据,并可以传给手机一些内容，可选
//    var jsonstr = '[{"pingpai":"小米","xinghao":"MIX2","yingjian":"八核6GB","anzhuo":"8.0","wangluo":"LTE","leixing":"类型","jingdu":"117.30333","weidu":"30.44556","jingque":"100米","fangshi":"GPS","dizhi":"安徽省池州市贵池区长江中路126号","shuju":"LTE","xiaoqu":"LTE","tac":"6245","pci":"122","eci":"6245","band":"3","earf":"6245","freq":"1","nid":"2","bid":"3","sid":"4","rsrp":"-100","rsrq":"8","snir":"10","dbm":"-80","ecio":"8","time":"09:41:30","tac":"28688","enb":"43017","cellid":"54"},{"pingpai":"小米","xinghao":"MIX2","yingjian":"八核6GB","anzhuo":"8.0","wangluo":"LTE","leixing":"类型","jingdu":"117.30333","weidu":"30.44556","jingdu":"100米","fangshi":"GPS","dizhi":"安徽省池州市贵池区长江中路126号","shuju":"LTE","xiaoqu":"LTE","tac":"6245","pci":"122","eci":"6245","band":"3","earf":"6245","freq":"1","nid":"2","bid":"3","sid":"4","rsrp":"-100","rsrq":"8","snir":"10","dbm":"-80","ecio":"8","time":"09:41:30","tac":"28688","enb":"43017","cellid":"54"},{"pingpai":"小米","xinghao":"MIX2","yingjian":"八核6GB","anzhuo":"8.0","wangluo":"LTE","leixing":"类型","jingdu":"117.30333","weidu":"30.44556","jingdu":"100米","fangshi":"GPS","dizhi":"安徽省池州市贵池区长江中路126号","shuju":"LTE","xiaoqu":"LTE","tac":"6245","pci":"122","eci":"6245","band":"3","earf":"6245","freq":"1","nid":"2","bid":"3","sid":"4","rsrp":"-100","rsrq":"8","snir":"10","dbm":"-80","ecio":"8","time":"09:41:30","tac":"28688","enb":"43017","cellid":"54"},{"pingpai":"小米","xinghao":"MIX2","yingjian":"八核6GB","anzhuo":"8.0","wangluo":"LTE","leixing":"类型","jingdu":"117.30333","weidu":"30.44556","jingdu":"100米","fangshi":"GPS","dizhi":"安徽省池州市贵池区长江中路126号","shuju":"LTE","xiaoqu":"LTE","tac":"6245","pci":"122","eci":"6245","band":"3","earf":"6245","freq":"1","nid":"2","bid":"3","sid":"4","rsrp":"-100","rsrq":"8","snir":"10","dbm":"-80","ecio":"8","time":"09:41:30","tac":"28688","enb":"43017","cellid":"54"},{"pingpai":"小米","xinghao":"MIX2","yingjian":"八核6GB","anzhuo":"8.0","wangluo":"LTE","leixing":"类型","jingdu":"117.30333","weidu":"30.44556","jingdu":"100米","fangshi":"GPS","dizhi":"安徽省池州市贵池区长江中路126号","shuju":"LTE","xiaoqu":"LTE","tac":"6245","pci":"122","eci":"6245","band":"3","earf":"6245","freq":"1","nid":"2","bid":"3","sid":"4","rsrp":"-100","rsrq":"8","snir":"10","dbm":"-80","ecio":"8","time":"09:41:30","tac":"28688","enb":"43017","cellid":"54"},{"pingpai":"小米","xinghao":"MIX2","yingjian":"八核6GB","anzhuo":"8.0","wangluo":"LTE","leixing":"类型","jingdu":"117.30333","weidu":"30.44556","jingque":"100米","fangshi":"GPS","dizhi":"安徽省池州市贵池区长江中路126号","shuju":"LTE","xiaoqu":"LTE","tac":"6245","pci":"122","eci":"6245","band":"3","earf":"6245","freq":"1","nid":"2","bid":"3","sid":"4","rsrp":"-100","rsrq":"8","snir":"10","dbm":"-80","ecio":"8","time":"09:41:30","tac":"28688","enb":"43017","cellid":"54"}]';
    // var jsonstr = '[{"rsrp":"-98"},{"rsrp":"-102"},{"rsrp":"-97"},{},{},{},{},{},{}]';
    var xinxiObj = JSON.parse(jsonstr);
    var xinxiList = [];
    var firstJson = {};
    var first = true;
    var ceshijiluhtml = "";
    $.each(xinxiObj, function (i, item) {
        var tmpist = [];
        var rsrp = 0;
        if (item['rsrp'] !== '') {
            rsrp = parseFloat(item['rsrp'])
        }
        tmpist.push(i, rsrp);
        xinxiList.push(tmpist);
        if (first === true) {
            firstJson = item;
            first = false;
        }
        ceshijiluhtml = ceshijiluhtml +
            "    <tr>\n" +
            "        <td>" + item['time'] + "</td>\n" +
            "        <td>" + item['pci'] + "</td>\n" +
            "        <td>" + item['rsrp'] + "</td>\n" +
            "        <td>" + item['sinr'] + "</td>\n" +
            "        <td>" + item['dbm'] + "</td>\n" +
            "        <td>" + item['ecio'] + "</td>\n" +
            "    </tr>\n";

    });
    document.getElementById("ceshijilu").innerHTML = ceshijiluhtml;
    document.getElementById("pingpai").innerText = firstJson['pingpai'];
    document.getElementById("xinghao").innerText = firstJson['xinghao'];
    document.getElementById("yingjian").innerText = firstJson['yingjian'];
    document.getElementById("anzhuo").innerText = firstJson['anzhuo'];
    document.getElementById("wangluo").innerText = firstJson['wangluo'];
    document.getElementById("leixing").innerText = firstJson['leixing'];
    document.getElementById("jingdu").innerText = firstJson['jingdu'];
    document.getElementById("weidu").innerText = firstJson['weidu'];
    document.getElementById("jingque").innerText = firstJson['jingque'];
    document.getElementById("fangshi").innerText = firstJson['fangshi'];
    document.getElementById("shuju").innerText = firstJson['shuju'];
    document.getElementById("band").innerText = firstJson['band'];
    document.getElementById("pci").innerText = firstJson['pci'];
    document.getElementById("enb").innerText = firstJson['enb'];
    document.getElementById("cellid").innerText = firstJson['cellid'];
    document.getElementById("earf").innerText = firstJson['earf'];
    document.getElementById("nid").innerText = firstJson['nid'];
    document.getElementById("bid").innerText = firstJson['bid'];
    document.getElementById("sid").innerText = firstJson['sid'];
    document.getElementById("rsrp").innerText = firstJson['rsrp'];
    document.getElementById("rsrq").innerText = firstJson['rsrq'];
    document.getElementById("sinr").innerText = firstJson['sinr'];
    document.getElementById("rx").innerText = firstJson['dbm'];
    document.getElementById("ecio").innerText = firstJson['ecio'];

//      document.getElementById("dizhi").innerText = '清点击左边按钮选择地址';
    // 创建地理编码（地理解析器）实例
    var points = [new BMap.Point(firstJson['jingdu'], firstJson['weidu'])];
    var myGeo = new BMap.Geocoder();
    //坐标转换完之后的回调函数
    translateCallback = function (data) {
        if (data.status === 0) {
            for (var i = 0; i < data.points.length; i++) {
                myGeo.getLocation(new BMap.Point(  data.points[i]['lng'] , data.points[i]['lat']), function (result) {
                    if (result) {
                         document.getElementById("dizhi").innerText = result.address ;
                    }
                });
            }
        }
    };
    setTimeout(function () {
        var convertor = new BMap.Convertor();
        convertor.translate(points, 1, 5, translateCallback)
    }, 1000);


});
