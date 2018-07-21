var basePath = (window.location+'').split('/')[0]+'//'+(window.location+'').split('/')[2]+'/'+(window.location+'').split('/')[3];
//拼接的html
var firstHtml = "<li><div class='table-responsive'><table class='table table-bordered'><thead><td class='td-name'>";
var secondHtml = "<td><span class='label label-danger'>";
var thirdHtml = "<img src='"+basePath+"/img/cha.png' alt='X' onclick=\"delPlayerPoker(this)\"></span></td>";
var endHtml = "</thead></table></div></li>";
var buttonHtml = "<li><button id='add_user_id' data-toggle='modal' data-target='#myModal' type='button' class='btn btn-theme'>添加新玩家</button></li>";
//定义卡牌花色
var pokerImg = new Array("红桃","方块","黑桃","梅花");
//定义卡牌数字
var pokerNum = new Array("A","2","3","4","5","6","7","8","9","10","J","Q","K");
//卡片描述定义
var pokerDescription = new Array("指定在场任意一名玩家喝一杯酒(本轮必须使用，使用后失效)",
    "此身份牌为“陪酒小姐”。若被其他玩家指定“陪酒”，需说出台词“老板吃好喝好玩开心”并和这名玩家干杯和同样的酒(此身份牌有效期至下张“2”出现)",
    "逛三园（动物园、果园、花园）（游戏结束此牌失效）",
    "免死金牌（持有此牌玩家，可免酒一杯，使用后失效）",
    "“你,我,他”游戏（持有此牌玩家可在任意时间喊出“你我他启动”，此时在场所有人员说话均不可带关键字“你、我、他包括英文（禁止装逼）”，否则罚酒一杯，同时此牌失效）",
    "跳过（持有此牌玩家跳过当前回合,同时此牌失效）",
    "逢“7”过（此牌持有者开始游戏，喊出一个0 - 10的起始数字，后续玩家依次喊出下一相邻数字，如果该数字含7或者是7的倍数，则应喊“过”,否则罚酒一杯，同时此牌失效）",
    "上厕所（持有此牌着可上离场、上厕所，可借，使用后此牌失效）",
    "自罚一杯，罚酒后此牌失效",
    "“神经病”，持有此牌玩家可在任意时间喊出“神经病启动”，此时在场所有人员说话均不可和此牌持有者对话”，否则罚酒一杯，同时此牌失效",
    "持有此牌者左手边玩家喝一杯酒，同时此牌失效",
    "持有此牌者右手边玩家喝一杯酒，同时此牌失效",
    "持有此牌者，指定下一名拿到“K”的玩家要喝的酒的量，如下一张“K”出现，则此牌失效");
//创建卡牌全局变量
var pokerArray = null;
//创建存放玩家的数组
var playerArray = new Array();
//记录当前用户下标
var playerIndex = 0;
//记录全局卡牌
var publicPoker = new Array();

// 初始化
$(document).ready(function(){
    $(".close").click(function(){
        $("#myAlert").alert();
    });

});

//添加一个玩家
function addPlayer() {
    var playerName = $("#add-palyer-name").val();
    var playerSex = $(".radio-inline input:radio:checked").val();
    //check 玩家姓名
    var nameCheckFlag = checkPlayerName(playerName);
    if(nameCheckFlag==1){
        //提示姓名以存在
        alert("该用户已经存在，清重新输入！");
        //$(function () { $('.popover-toggle').popover({html : true });});
        //隐藏模态窗口
        //$('#myModal').modal('hide');
        return;
    }
    if(playerName==null||playerName==undefined||playerName==""){
        alert("请输入用户名！");
        return;
    }

    var player = new Object();
    player.name=playerName;
    player.sex=playerSex;
    player.pokerArr=new Array();
    playerArray.push(player);
    //刷新玩家区域
    flushPlayerArea();
    //刷新游戏区域
    if(playerArray.length>1){
        flushGameArea();
    }
    $('#myModal').modal('hide');
    $("#add-palyer-name").val("");
}

//刷新显示玩家区域
function flushPlayerArea(){
    $("#player-area-id").html(createPlayerHtml());
}

//check玩家姓名
function checkPlayerName(playerName){
    for(var i=0;playerArray!=null&&playerArray!=undefined&&i<playerArray.length; i++){
        if(playerArray[i].name==playerName){
            return 1;
        }
    }
}

//创建牌堆
function createPokerArr(){
    //初始化游戏区域
    initPokerArea();
    //初始化玩家手牌
    initPlayerPoker();
    //清空牌组
    pokerArray = new Array();
    //嵌套循环创建卡牌
    for(var i = 0;i < pokerImg.length; i++){
        for(var j = 0;j < pokerNum.length; j++){
            //check全局牌//如果存在跳过创建
            if(checkPublicPoker(pokerImg[i],pokerNum[j])==1){continue;}
            // pokerArray.push(pokerImg+"-"+pokerNum);
            var poker = new Object();
            poker.pokerImg = pokerImg[i];
            poker.pokerNum = pokerNum[j];
            poker.pokerDescription = pokerDescription[j];
            //标记牌类
            if(poker.pokerNum=='2'||poker.pokerNum=='4'||poker.pokerNum=='8'||poker.pokerNum=='10'||poker.pokerNum=='K'){
                poker.pokerType = 1;
            }else{
                poker.pokerType = 0;
            }
            pokerArray.push(poker);
        }
    }
    pokerArray = shuffleArray(pokerArray);
    console.log(pokerArray);
}

/**
 * 洗牌时check全局牌
 */
function checkPublicPoker(pokerImg,pokerNum){
    for(var i=0;i<publicPoker.length;i++){
        if(publicPoker[i].pokerNum==pokerNum&&publicPoker[i].pokerImg==pokerImg){
            return 1;
        }
    }
}


//随机排序数据
function shuffleArray(array) {
    for (var i = array.length - 1; i > 0; i--) {
        var j = Math.floor(Math.random() * (i + 1));
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    return array;
}

/**
 * 拼接玩家区域数据
 */
function createPlayerHtml(){
    var html = "";
    for(var i=0;i<playerArray.length;i++){
        //拼接头
        html+=firstHtml;
        //拼接姓名//拼接secondHtml
        html+=playerArray[i].name+"</td>";
        //若玩家手牌不为空，拼接花色
        if(playerArray[i].pokerArr!=null&&playerArray[i].pokerArr!=undefined){
            var pokerArr = playerArray[i].pokerArr;
            for(var j=0;j<pokerArr.length;j++){
                //判断为保留牌才拼接
                if(pokerArr[j]!=undefined&&pokerArr[j].pokerType==1){
                    html+=secondHtml+pokerArr[j].pokerImg+pokerArr[j].pokerNum+thirdHtml;
                }else{
                    continue;
                }
            }
        }
        //拼接最后的html
        html+=endHtml;
    }
    return html+buttonHtml;
}

/**
 * 发牌
 */
function dealPoker(){
    if(pokerArray==null||pokerArray==undefined){
        alert("请先洗牌！");
        return;
    }else if(pokerArray.length==0){
        alert("牌以发完，请重新洗牌！");
        return;
    }
    //check最少2名玩家
    if(playerArray.length<2){
        alert("最少2名玩家，才能开始游戏！");
        return;
    }
    //获取一张牌并从牌堆中删除此牌
    var poker = pokerArray.pop();
    //获取当前用户并插入扑克
    playerArray[playerIndex].pokerArr.push(poker);
    //刷新当前玩家区域
    flushGameArea();
    //刷新玩家列表区域
    flushPlayerArea();
    //刷新纸牌显示区域
    flushPokerArea(poker);
    //标记改为下一玩家
    if(playerIndex==playerArray.length-1){
        playerIndex=0;
    }else{
        playerIndex++;
    }

}

/**
 * 刷新当前玩家区域
 */
function flushGameArea(){
    $("#now-player-id").text(playerArray[playerIndex].name);     //当前玩家
    $("#left-player-id").text(playerArray[playerIndex==0?(playerArray.length-1):playerIndex-1].name);    //左侧玩家
    $("#right-player-id").text(playerArray[playerIndex==(playerArray.length-1)?0:playerIndex+1].name);   //右侧玩家
}

/**
 * 刷新纸牌显示区域
 */
function flushPokerArea(poker) {
    if(poker==undefined||poker==null){
        initPokerArea();
        return;
    }
    var src = basePath+"/img/"+poker.pokerImg+".png";
    var pokerNum = poker.pokerNum;
    var pokerDescription = poker.pokerDescription;

    $("#poker-img-id").attr("src",src);
    $("#poker-Num-id").text(pokerNum);
    $("#poker-description-id").text(pokerDescription);
}

/**
 * 初始化纸牌显示区域
 */
function initPokerArea(){
    $("#poker-img-id").attr("src",basePath+"/img/红桃.png");
    $("#poker-Num-id").text("A");
    $("#poker-description-id").text("请至少添加俩名玩家并洗牌后开始游戏");
}

/**
 * 初始化玩家手牌,保留8
 */
function initPlayerPoker(){
    //重新加载全局牌
    publicPoker = new Array();
    for(var i=0;i<playerArray.length;i++){
        var pokerArr = playerArray[i].pokerArr;
        if(pokerArr!=undefined&&pokerArr!=null){
            var tempPokerArr = new Array();
            for(var j=0;j<pokerArr.length;j++){
                if(pokerArr[j]!=undefined&&pokerArr[j].pokerNum==8){
                    tempPokerArr.push(pokerArr[j]);
                }
            }
            //清空用户手牌赋予保留牌
            playerArray[i].pokerArr = tempPokerArr;
            Array.prototype.push.apply(publicPoker,tempPokerArr);
            //publicPoker.concat(tempPokerArr);
        }
    }
    flushPlayerArea();
}

/**
 * 删除手牌
 */
function delPlayerPoker(imgDom){
    var playerName = $(imgDom).parent().parent().parent().children("td:first-child").text();
    var pokerName = $(imgDom).parent().text();
    //删除对应用户的手牌
    delPlayerPokerFunction(playerName,pokerName);
}

/**
 * 删除玩家手牌
 */
function delPlayerPokerFunction(playerName,pokerName){
    for(var i=0;i<playerArray.length;i++){
        if(playerArray[i].name!=undefined&&playerArray[i].name!=playerName){
            continue;
        }else{
            var pokerArr = playerArray[i].pokerArr;
            if(pokerArr!=undefined&&pokerArr!=null){
                var tempPokerArr = new Array();
                var tempPokerPublicArr = new Array();
                for(var j=0;j<pokerArr.length;j++){
                    if(pokerArr[j]!=undefined&&pokerArr[j].pokerImg+pokerArr[j].pokerNum!=pokerName){
                        tempPokerArr.push(pokerArr[j]);
                    }
                }
                for(var j=0;j<publicPoker.length;j++){
                    if(pokerArr[j]!=undefined&&pokerArr[j].pokerImg+pokerArr[j].pokerNum!=pokerName){
                        tempPokerPublicArr.push(publicPoker[j]);
                    }
                }
                //清空用户手牌赋予保留牌
                playerArray[i].pokerArr = tempPokerArr;
                publicPoker = tempPokerPublicArr;
            }
        }

    }
    flushPlayerArea();
}

