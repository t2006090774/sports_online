//初始化数据
function loadData(dataObj){
    console.info(dataObj);
    //title
    $(".pet_article_title").text(dataObj.titleSecond);
    //time
    $(".pet_article_user_time").text(dataObj.newsTimeSecond);
    //内容
    var contentArray = dataObj.contentSecond;
    var pictureArray = dataObj.pictureUrlSecond;

    //内容为空
    if(contentArray==null||contentArray.length==0){
        //图片不为空，将图片循环显示
        if(pictureArray!=null&&pictureArray.length>2){
            for(var j=0;j<pictureArray.length;j++){
                $("#article_id").append("<img src=\""+pictureArray[j]+"\" alt=\"\">");
            }
        }else{
            $("#article_id").append("<br/><p align='center' style='color: #BEBEBE'>-&nbsp;暂无内容&nbsp;-</p>");
        }
        return;
    //内容不为空，图片为空，只显示内容
    }else if(pictureArray==null||pictureArray.length==0){
        for(var i=0;i<contentArray.length;i++) {
            $("#article_id").append("<p>" + contentArray[i] + "</p>");
        }
    //都不为空，按比例显示图片
    }else {
        var notPic="http://inews.gtimg.com/newsapp_bt/0/3868004119/641";
        for(var i=0,j=0;i<contentArray.length;i++) {
            if (i == Math.round(contentArray.length/pictureArray.length)*j&&pictureArray[j++]!=notPic) {
                $("#article_id").append("<img src=\"" + pictureArray[j++] + "\" alt=\"\">");
            }else{}
            $("#article_id").append("<p>" + contentArray[i] + "</p>");
        }
    }

}