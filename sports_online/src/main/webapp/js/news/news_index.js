var basePath = (window.location+'').split('/')[0]+'//'+(window.location+'').split('/')[2]+'/'+(window.location+'').split('/')[3];
var pageNumber = 1;
var pageSize = 10;
var categoryIdPub = 1;

//初始化
function init(){
    getNewsDataList(categoryIdPub);
}


//异步请求新闻数据

function getNewsDataList(categoryId){
    //显示loading加载
    $("#spinner").show();
    if(categoryId==10||categoryId==9){
        alert("暂无数据！");
        return;
    }
    if(categoryId!=categoryIdPub){
        categoryIdPub = categoryId;
        //置空显示区域
        $(".am-list").html("");
        //显示第一页数据
        pageNumber = 1;
    }else{
        //加载其他页数据
        pageNumber += 1;
    }
    var url = basePath+"/dataPage/getPageData";
    $.ajax({
        type: 'POST',
        url: url,
        dataType: "json",
        data: {
            'categoryId' : categoryId,
            'pageNumber' : pageNumber,
            'pageSize' : pageSize
        },
        success: function(result) {
            var templateStr  = document.getElementById('template_id').innerHTML;
            var temp = [];
            result.forEach(function(element) {

                temp.push(formatHtml(templateStr,element))

            }, this);
            //隐藏loading加载
            $("#spinner").hide()
            //追加模板消息
            $(".am-list").append(temp);
            $(".pet_list_one_nr").css("height", $(".right_img").height());
        },
        error:function(msg){
            alert("请求失败");
            console.info(msg);
        },

    });
}

/**
 * 请求跳转二级页面
 */
function toSecondPage(pid){
    var url = basePath+"/news/second/"+pid;
    $(location).attr('href', url);
    //$.get(url);
}

//格式化
function formatHtml(str,obj){
    console.info(obj);
    return str.replace('@time@',obj.newsTime).replace('@title@',obj.title).replace('@title@',obj.title).replace('@picUrl@',obj.pictureUrl).replace('@pid@',obj.pid);
}

