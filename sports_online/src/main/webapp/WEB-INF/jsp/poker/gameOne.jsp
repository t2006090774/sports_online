<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/base/basePage.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%-- <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="${basePath}/img/favicon.png">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="${basePath}/css/siimple/bootstrap.css">
    <link rel="stylesheet" href="${basePath}/css/siimple/bootstrap-theme.css">
    <%--<link href="${basePath}/css/siimple/bootstrap.css" rel="stylesheet">
    <link href="${basePath}/css/siimple/bootstrap-theme.css" rel="stylesheet">--%>
    <!-- siimple style -->
    <link href="${basePath}/css/siimple/style.css" rel="stylesheet">

    <title>金陵十三钗</title>
</head>

<body>

<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button id="refresh_poker_id" type="button" class="btn btn_refresh" onclick="createPokerArr()">洗牌</button>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a class="navbar-brand">金陵十三钗</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul id="player-area-id" class="nav navbar-nav navbar-right">
                <li>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <td class="td-name">当前无玩家请添加</td>
                            </thead>
                        </table>
                    </div>
                </li>

                <%--<li><a id="add_user_id" data-toggle="modal" data-target="#myModal">添加新玩家</a></li>--%>
                <li>
                    <button id="add_user_id" data-toggle="modal" data-target="#myModal" type="button"
                            class="btn btn-theme">添加新玩家
                    </button>
                </li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>

<div id="header">
    <div class="poker-show-top"><br></div>
    <div class="poker-show">
        <img id="poker-img-id" src="${basePath}/img/红桃.png">
        <div id="poker-Num-id">A</div>
        <h4 id="poker-description-id">请至少添加俩名玩家并洗牌后开始游戏</h4>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <h2 class="subtitle">左侧玩家<span class="label label-playname" id="left-player-id">-</span></h2>

                <h1>当前玩家<span class="label label-playname" id="now-player-id">-</span></h1>

                <h2 class="subtitle">右侧玩家<span class="label label label-playname" id="right-player-id">-</span></h2>

                <form class="form-inline signup" role="form">
                    <%--<div class="form-group">
                        <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter your email address">
                    </div>--%>
                    <button type="button" class="btn btn-theme" onclick="dealPoker()">发牌</button>

                </form>
            </div>
        </div>
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加新玩家</h4>
            </div>
            <br>
            <div class="input-group">
                <span class="input-group-addon">玩家姓名</span>
                <input id="add-palyer-name" type="text" maxlength="5" class="form-control popover-toggle"
                       title="Popover title"
                       data-container="body" data-toggle="popover" data-placement="bottom"
                       data-content="底部的 Popover 中的一些内容">
            </div>
            <%--<br>
            <div class="input-group">
                <span class="input-group-addon">玩家性别</span>

                <div>
                    <label class="radio-inline">
                        <input type="radio" name="optionsRadiosinline" id="optionsRadios3" value="1" checked>男
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="optionsRadiosinline" id="optionsRadios4"  value="2">女
                    </label>
                </div>

                <input id="add-palyer-sex" type="text" class="form-control">
            </div>--%>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-add" onclick="addPlayer()">添加玩家</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div id="copyDiv1" style="display: none">
    <img src="${basePath}/img/红桃.png">
</div>
<button id="copyBtn1">
    点击生成图片222
</button>
<div id="copyDiv">

</div>

<script src="${basePath}/common/js/jquery-3.3.1.min.js"></script>
<script src="${basePath}/js/poker/gameOne.js"></script>
<script src="${basePath}/js/html2canvas.min.js"></script>
<script src="${basePath}/common/js/bootstrap.min.js"></script>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>

<script type="text/javascript">
    //截图
    $("#copyBtn").click(function () {
        //直接选择要截图的dom，就能截图，但是因为canvas的原因，生成的图片模糊
        html2canvas(document.querySelector('body')).then(function(canvas) {
            document.body.appendChild(canvas);
            $("#copyDiv").append(canvas);
        })
        /*html2canvas(jQuery("body"), {
            onrendered: function (canvas) {
                //设置图片
                $("#copyDiv").append(canvas);
            }
        });*/
    })


    $("#copyBtn1").click(function () {
        $("#copyDiv1").show();
        $("#copyBtn1").hide();
        var w = $("body").width();
        var h = $("body").height();
        var canvas = document.createElement("canvas");
        canvas.width = w * 2;
        canvas.height = h * 2;
        canvas.style.width = w + "px";
        canvas.style.height = h + "px";
        var context = canvas.getContext("2d");
        //然后将画布缩放，将图像放大两倍画到画布上
        context.scale(2,2);


        /* //创建一个新的canvas
        var canvas2 = document.createElement("canvas");
        let _canvas = document.querySelector('body');
        var w = parseInt(window.getComputedStyle(_canvas).width);
        var h = parseInt(window.getComputedStyle(_canvas).height);
        //将canvas画布放大若干倍，然后盛放在较小的容器内，就显得不模糊了
        canvas2.width = w * 4;
        canvas2.height = h * 4;
        canvas2.style.width = w + "px";
        canvas2.style.height = h + "px";
        //可以按照自己的需求，对context的参数修改,translate指的是偏移量
        //  var context = canvas.getContext("2d");
        //  context.translate(0,0);
        var context = canvas2.getContext("2d");
        context.scale(2,2);*/
        html2canvas(document.querySelector('body'),{canvas:canvas}).then(function(canvas) {
            //document.body.appendChild(canvas);
            //canvas转换成url，然后利用a标签的download属性，直接下载，绕过上传服务器再下载
            //document.querySelector(".down").setAttribute('href',canvas.toDataURL());

            $("#copyDiv").append(canvas);
        });
    })
</script>
</body>
</html>
