$(function () {
    //点击search图标获取患者信息和收费列表
    $("#search").click(function () {
        $.ajax({
            url:"",
            type:"post",
            data:{},
            dataType:"json",
            success:function (data) {
                //...


                // 追加检验申请的记录
                for (var i = 0;i<data.iirList.length;i++){

                }
             },
        })
    })

    
})