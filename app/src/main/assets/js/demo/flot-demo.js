
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
    var xinxiObj = JSON.parse( json );
    var xinxiList = []
    console.log(xinxiObj);
     var first = true ;
        $.each(xinxiObj, function (i, item) {
            var tmpist = []
             var rsrp = 0 ;
            if( item['rsrp'] !== '' ){
                rsrp = parseFloat(item['rsrp'])
            }
            tmpist.push(i , rsrp)
            xinxiList.push( tmpist  )
        });
  console.log(  xinxiList  );

    var barData = {
        label: "bar",
        // data: arr,
        data: [[1, 34], [2, 0], [3, 19], [4, 34], [5, 32], [6, 44]]
    };
    // console.log(barData.toString());
    // var json=window.MyBrowserAPI.getLocationData("从页面传给手机的message");//拿到本地数据,并可以传给手机一些内容，可选
    // console.log(json);
    $.plot($("#flot-line-chart"), [xinxiList], barOptions);
});




