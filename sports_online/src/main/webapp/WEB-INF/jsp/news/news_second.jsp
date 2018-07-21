<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/base/basePage.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="${basePath}/css/news/amazeui.min.css">
    <link rel="stylesheet" href="${basePath}/css/news/wap.css">
    <title>新闻详情</title>
</head>
<body onload="init()" style="background:#ececec">
<div class="pet_mian">
    <%--<div class="pet_head">
        <header data-am-widget="header"
                class="am-header am-header-default pet_head_block">
            <div class="am-header-left am-header-nav ">
                <a href="#" onclick="javascript:history.back(-1);" class="iconfont pet_head_jt_ico">&#xe601;</a>
            </div>
        </header>
    </div>--%>

    <div class="pet_content">
        <div class="pet_content_block">
            <article id="article_id" data-am-widget="paragraph" class="am-paragraph am-paragraph-default pet_content_article"
                     data-am-paragraph="{ tableScrollable: true, pureview: true }">
                <h1 class="pet_article_title">-</h1>
                <div class="pet_article_user_time">发表于：-</div>

            </article>
            <ul class="like_share_block">

<%--            分享
                <li><a class="link_share_button" href="###"><i class="iconfont share_ico_link">&#xe62f;</i>1460</a></li>
                <li><a class="link_share_button" href="###"><i class="iconfont share_ico_wx">&#xe630;</i>微信</a></li>
                <li><a class="link_share_button" href="###"><i class="iconfont share_ico_pyq">&#xe62e;</i>朋友圈</a></li>--%>
            </ul>
        </div>

        <div class="pet_article_footer_info"> © 2018 趋势科技. All Rights Reserved <a href="#" target="_blank" title="联系我们">联系我们</a>
        </div>
    </div>
</div>

<script src="${basePath}/common/js/jquery.min.js"></script>
<script src="${basePath}/js/news/news_second.js"></script>
<script src="${basePath}/js/amazeui.min.js"></script>
<script>
    var pageData = '${requestScope.pageData}';
    //初始化数据
    function init() {
        var dataObj = JSON.parse(pageData);
        loadData(dataObj);
    }

    document.addEventListener("plusready", function() {
        // 注册返回按键事件
        plus.key.addEventListener('backbutton', function() {
            // 事件处理
            window.history.back();
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
        $('.pet_article_like li:last-child').css('border', 'none');

        function auto_resize() {
            $('.pet_list_one_nr').height($('.pet_list_one_img').height());
            // alert($('.pet_list_one_nr').height());
        }

        $('.pet_article_user').on('click', function () {
            if ($('.pet_article_user_info_tab').hasClass('pet_article_user_info_tab_show')) {
                $('.pet_article_user_info_tab').removeClass('pet_article_user_info_tab_show').addClass('pet_article_user_info_tab_cloes');
            } else {
                $('.pet_article_user_info_tab').removeClass('pet_article_user_info_tab_cloes').addClass('pet_article_user_info_tab_show');
            }
        });

        $('.pet_head_gd_ico').on('click', function () {
            $('.pet_more_list').addClass('pet_more_list_show');
        });
        $('.pet_more_close').on('click', function () {
            $('.pet_more_list').removeClass('pet_more_list_show');
        });
    });

</script>
</body>
</html>