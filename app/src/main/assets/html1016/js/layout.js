// JavaScript Document
//折线图TAB标签效果
 $(function(){
//页面一打开就执行，放入ready是为了layer所需配件（css、扩展模块）加载完毕
$('.popA').on('click', function(){
   //官网欢迎页
    layer.open({
        type: 2,
        //skin: 'layui-layer-lan',
        title: '预约单交易信息',
        fix: false,
        shadeClose: false,
        maxmin: false, 
        area: ['1200px','520px'],
        content:'bqlb-xq.html',  
    });   
});
});		 
	