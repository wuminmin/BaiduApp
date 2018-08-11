
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

     var json = window.MyBrowserAPI.getLocationData("从页面传给手机的message");//拿到本地数据,并可以传给手机一些内容，可选
      alert(json);
    // var xinxiObj = JSON.parse('[{"pingpai":"小米","xinghao":"MIX2","yingjian":"八核6GB","anzhuo":"8.0","wangluo":"LTE","leixing":"类型","jingdu":"117.30333","weidu":"30.44556","jingdu":"100米","fangshi":"GPS","dizhi":"安徽省池州市贵池区长江中路126号","shuju":"LTE","xiaoqu":"LTE","tac":"6245","pci":"122","eci":"6245","band":"3","earf":"6245","freq":"1","nid":"2","bid":"3","sid":"4","rsrp":"-100","rsrq":"8","snir":"10","dbm":"-80","ecio":"8","time":"09:41:30","tac":"28688","enb":"43017","cellid":"54"},{"pingpai":"小米","xinghao":"MIX2","yingjian":"八核6GB","anzhuo":"8.0","wangluo":"LTE","leixing":"类型","jingdu":"117.30333","weidu":"30.44556","jingdu":"100米","fangshi":"GPS","dizhi":"安徽省池州市贵池区长江中路126号","shuju":"LTE","xiaoqu":"LTE","tac":"6245","pci":"122","eci":"6245","band":"3","earf":"6245","freq":"1","nid":"2","bid":"3","sid":"4","rsrp":"-100","rsrq":"8","snir":"10","dbm":"-80","ecio":"8","time":"09:41:30","tac":"28688","enb":"43017","cellid":"54"},{"pingpai":"小米","xinghao":"MIX2","yingjian":"八核6GB","anzhuo":"8.0","wangluo":"LTE","leixing":"类型","jingdu":"117.30333","weidu":"30.44556","jingdu":"100米","fangshi":"GPS","dizhi":"安徽省池州市贵池区长江中路126号","shuju":"LTE","xiaoqu":"LTE","tac":"6245","pci":"122","eci":"6245","band":"3","earf":"6245","freq":"1","nid":"2","bid":"3","sid":"4","rsrp":"-100","rsrq":"8","snir":"10","dbm":"-80","ecio":"8","time":"09:41:30","tac":"28688","enb":"43017","cellid":"54"},{"pingpai":"小米","xinghao":"MIX2","yingjian":"八核6GB","anzhuo":"8.0","wangluo":"LTE","leixing":"类型","jingdu":"117.30333","weidu":"30.44556","jingdu":"100米","fangshi":"GPS","dizhi":"安徽省池州市贵池区长江中路126号","shuju":"LTE","xiaoqu":"LTE","tac":"6245","pci":"122","eci":"6245","band":"3","earf":"6245","freq":"1","nid":"2","bid":"3","sid":"4","rsrp":"-100","rsrq":"8","snir":"10","dbm":"-80","ecio":"8","time":"09:41:30","tac":"28688","enb":"43017","cellid":"54"},{"pingpai":"小米","xinghao":"MIX2","yingjian":"八核6GB","anzhuo":"8.0","wangluo":"LTE","leixing":"类型","jingdu":"117.30333","weidu":"30.44556","jingdu":"100米","fangshi":"GPS","dizhi":"安徽省池州市贵池区长江中路126号","shuju":"LTE","xiaoqu":"LTE","tac":"6245","pci":"122","eci":"6245","band":"3","earf":"6245","freq":"1","nid":"2","bid":"3","sid":"4","rsrp":"-100","rsrq":"8","snir":"10","dbm":"-80","ecio":"8","time":"09:41:30","tac":"28688","enb":"43017","cellid":"54"},{"pingpai":"小米","xinghao":"MIX2","yingjian":"八核6GB","anzhuo":"8.0","wangluo":"LTE","leixing":"类型","jingdu":"117.30333","weidu":"30.44556","jingdu":"100米","fangshi":"GPS","dizhi":"安徽省池州市贵池区长江中路126号","shuju":"LTE","xiaoqu":"LTE","tac":"6245","pci":"122","eci":"6245","band":"3","earf":"6245","freq":"1","nid":"2","bid":"3","sid":"4","rsrp":"-100","rsrq":"8","snir":"10","dbm":"-80","ecio":"8","time":"09:41:30","tac":"28688","enb":"43017","cellid":"54"}]');
//    var xinxiObj = JSON.parse('[{"rsrp":"-97"},{"rsrp":"-97"},{"rsrp":"-96"},{"rsrp":"-98"},{"rsrp":"-96"},{"rsrp":"-96"},{"rsrp":"-96"}]');
    var xinxiObj = JSON.parse(json);
    var xinxiList = [];
    console.log(xinxiObj);
     var first = true ;
        $.each(xinxiObj, function (i, item) {
            var tmpist = [];
             var rsrp = 0 ;
            if( item['rsrp'] !== '' ){
                rsrp = parseFloat(item['rsrp'])
            }
            tmpist.push(i , rsrp);
            xinxiList.push( tmpist  )
        });
  console.log(  xinxiList  );

    var barData = [[1, 34], [2, 0], [3, 19], [4, 34], [5, 32], [6, 44]] ;
     var obj = JSON.parse('[ { "1":"34"},{ "2": "6"},{"3":"19"},{"4":"34"},{"5":"32"},{"6":"44" }  ]');
    var myString = '[1, 34], [2, 0],  [3, 19],    [4, 34],    [5, 32],   [6, 44] ';
    // console.log(barData.toString());
    // var json=window.MyBrowserAPI.getLocationData("从页面传给手机的message");//拿到本地数据,并可以传给手机一些内容，可选
    // console.log(json);
    $.plot($("#flot-line-chart"), [xinxiList], barOptions);
});
