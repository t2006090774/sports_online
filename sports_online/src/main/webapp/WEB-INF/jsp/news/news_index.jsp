<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/basePage.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="${basePath}/css/news/amazeui.min.css">
    <link rel="stylesheet" href="${basePath}/css/news/wap.css">
    <title>新闻首页</title>
</head>
<body onload="init()">
<div data-am-widget="gotop" class="am-gotop am-gotop-fixed">
    <a href="#top" title="">
        <img class="am-gotop-icon-custom" src="${basePath}/img/news/goTop.png"/>
    </a>
</div>

<div class="pet_mian" id="top">
    <div data-am-widget="slider" class="am-slider am-slider-a1" data-am-slider='{"directionNav":false}'>
        <ul class="am-slides">
            <li>
                <img src="${basePath}/img/news/zuqiu.png">
                <div class="pet_slider_font">
                    <span class="pet_slider_emoji"> (｡・`ω´･)　</span>
                    <span>各种姿势教你学足球</span>
                </div>
                <div class="pet_slider_shadow"></div>
            </li>
            <li>
                <img src="${basePath}/img/news/gaoxiao.jpg">
                <div class="pet_slider_font">
                    <span class="pet_slider_emoji"> (╭￣3￣)╭♡   </span>
                    <span>搞笑体育图 -赶快戳进来</span>
                </div>
                <div class="pet_slider_shadow"></div>
            </li>
            <li>
                <img src="${basePath}/img/news/fl02.png">
                <div class="pet_slider_font">
                    <span class="pet_slider_emoji"> []~(￣▽￣)~*　</span>
                    <span>人类的魔性好友 -这些狗你见过几种</span>
                </div>
                <div class="pet_slider_shadow"></div>
            </li>
        </ul>
    </div>

    <div class="pet_circle_nav">
        <ul class="pet_circle_nav_list">
            <%--<li><a href="" class="iconfont pet_nav_xinxianshi ">&#xe61e;</a><span>新鲜事</span></li>
            <li><a href="" class="iconfont pet_nav_zhangzhishi ">&#xe607;</a><span>趣闻</span></li>
            <li><a href="" class="iconfont pet_nav_kantuya ">&#xe62c;</a><span>阅读</span></li>
            <li><a href="" class="iconfont pet_nav_mengzhuanti ">&#xe622;</a><span>专题</span></li>
            <li><a href="" class="iconfont pet_nav_meirong ">&#xe629;</a><span>订阅</span></li>
            <li><a href="" class="iconfont pet_nav_yiyuan ">&#xe602;</a><span>专栏</span></li>
            <li><a href="" class="iconfont pet_nav_dianpu ">&#xe604;</a><span>讨论</span></li>--%>
            <li><a href="###" onclick="getNewsDataList(1)" class="pet_nav_nba"></a><span>NBA</span></li>
            <li><a href="###" onclick="getNewsDataList(2)" class="pet_nav_ouguan"></a><span>欧冠</span></li>
            <li><a href="###" onclick="getNewsDataList(3)" class="pet_nav_yingchao"></a><span>英超</span></li>
            <li><a href="###" onclick="getNewsDataList(4)" class="pet_nav_dejia"></a><span>德甲</span></li>
            <li><a href="###" onclick="getNewsDataList(5)" class="pet_nav_guozu"></a><span>国足</span></li>
            <li><a href="###" onclick="getNewsDataList(7)" class="pet_nav_dianjing"></a><span>电竞</span></li>
            <li><a href="###" onclick="getNewsDataList(8)" class="pet_nav_wangqiu"></a><span>网球</span></li>


            <li><a href="javascript:;" class="iconfont pet_nav_gengduo ">&#xe600;</a><span>更多</span></li>
        </ul>
        <div class="pet_more_list">
            <div class="pet_more_list_block">
                <div class="iconfont pet_more_close">×</div>
                <div class="pet_more_list_block">
                    <div class="pet_more_list_block_name">
                        <div class="pet_more_list_block_name_title">其他分类</div>
                        <a class="pet_more_list_block_line" onclick="getNewsDataList(11)">
                            <i class="iconfont pet_nav_cba pet_more_list_block_line_ico">&#xe61e;</i>
                            <div class="pet_more_list_block_line_font">CBA</div>
                        </a>
                        <a class="pet_more_list_block_line" onclick="getNewsDataList(10)">
                            <i class="iconfont pet_nav_fiba pet_more_list_block_line_ico">&#xe607;</i>
                            <div class="pet_more_list_block_line_font">FIBA</div>
                        </a>
                        <a class="pet_more_list_block_line" onclick="getNewsDataList(9)">
                            <i class="iconfont pet_nav_boji pet_more_list_block_line_ico">&#xe62c;</i>
                            <div class="pet_more_list_block_line_font">搏击</div>
                        </a>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="pet_content_main">
        <div data-am-widget="list_news" class="am-list-news am-list-news-default">
            <div class="am-list-news-bd">
                <ul class="am-list">


                </ul>
            </div>
            <div id="spinner" class="spinner" style="display:none">
                <div class="rect1"></div>
                <div class="rect2"></div>
                <div class="rect3"></div>
                <div class="rect4"></div>
                <div class="rect5"></div>
            </div>
        </div>
    </div>

    <div class="pet_article_dowload pet_dowload_more_top_off">
        <div class="pet_article_footer_info"> © 2018 趋势科技. All Rights Reserved <a href="#" target="_blank" title="联系我们">联系我们</a>
        </div>
    </div>

</div>

<script src="${basePath}/common/js/jquery.min.js"></script>
<script src="${basePath}/js/news/news_index.js"></script>
<script src="${basePath}/js/amazeui.min.js"></script>

<script type="text/javascript">

    document.addEventListener("plusready", function() {
        // 注册返回按键事件
        plus.key.addEventListener('backbutton', function() {
            // 事件处理
            plus.nativeUI.confirm("退出程序？", function(event) {
                if (event.index) {
                    plus.runtime.quit();
                }
            }, null, ["取消", "确定"]);
        }, false);
    });

    $(function () {

        // 动态计算新闻列表文字样式
        auto_resize();
        $(window).resize(function () {
            auto_resize();
        });
        $('.am-list-thumb img').load(function () {
            auto_resize();
        });

        $('.am-list > li:last-child').css('border', 'none');

        function auto_resize() {
            $('.pet_list_one_nr').height($('.pet_list_one_img').height());

        }

        $('.pet_nav_gengduo').on('click', function () {
            $('.pet_more_list').addClass('pet_more_list_show');
        });
        $('.pet_more_close').on('click', function () {
            $('.pet_more_list').removeClass('pet_more_list_show');
        });

        //首页内容和图片等高
        $(".pet_list_one_nr").css("height", $(".right_img").height());

        //滑动到底部
        $(window).scroll(function(){
            //判断是否滑动到页面底部
            if($(window).scrollTop() === $(document).height() - $(window).height()){
                // TODO 滑动到底部时可请求下一页的数据并加载，加载可使用append方法
                getNewsDataList(categoryIdPub);
            }
        });
    });

</script>

<script type="text/templete" id="template_id">
   <!--缩略图在标题右边-->
    <li onClick="toSecondPage(@pid@)" class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-right pet_list_one_block">
    <!-- 作者
    <div class="pet_list_one_info">
        <div class="pet_list_one_info_l">
            头像
            <div class="pet_list_one_info_ico"><img src="${basePath}/img/news/a1.png">
            </div>
            <昵称

        </div>
        <div class="pet_list_one_info_r">
            分类
            <div class="pet_list_tag pet_list_tag_xxs">News</div>
        </div>
    </div> -->
    <!-- 内容 -->
    <div class=" am-u-sm-8 am-list-main pet_list_one_nr">
        <h3 class="am-list-item-hd pet_list_one_bt"><a href="###" class="title_a_block">@title@</a>
        </h3>
        <div class="pet_list_one_info_name">更新时间：@time@</div>
        <!-- <div class="am-list-item-text pet_list_one_text left_content">
            @content@
        </div> -->
    </div>
    <!-- 图片 -->
    <div class="am-u-sm-4 am-list-thumb right_img">
        <a href="###" class="">
            <img src="@picUrl@" class="pet_list_one_img" height="70" width="150"
                 onerror='this.src="${basePath}/img/moren.jpg"'/>
        </a>
    </div>
    </li>
</script>
</body>
</html>

