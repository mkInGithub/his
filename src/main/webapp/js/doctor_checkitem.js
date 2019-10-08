$(function(){
	//初始化查询系统中所有检查项目
	$.ajax({
		type:"post",
		url:"getAllCheckItem",
		data:{},
		dataType:"json",
		success:function(data){
			console.log(data);
			for(var i=0;i<data.length;i++){
				$("#checkitem").append("<option value='"+data[i].id+"'  data-price='"+data[i].price+"'   >"+data[i].itemname+"</option>");
			}
		}
	});
	
	//根据病历号查询患者
	$("#search").click(function(){
		var pid = $("#pid").val();
		if(pid==""){
			alert("请输入要查询的病历号");
		}else if(isNaN(pid)){
			alert("病历号必须为数字");
		}else{
			$.ajax({
				type:"post",
				url:"getPatientByNo/"+pid,
				data:{},
				dataType:"json",
				success:function(data){
					console.log(data);
					if(data.pid==0){
						alert("系统中不存在该患者");
					}else{
						$("#pno").val(data.pid);
						$("#pname").val(data.pname);
						if(data.sex=="男"){
							$("#sex1")[0].checked=true;
						}else{
							$("#sex0")[0].checked=true;
						}
						$("#createdate").val(data.createDate);
						$("#idcard").val(data.idcard);
						$("#level").val(data.level.levelname);
						if(data.pstatus=="未看诊" || data.pstatus=="已退号"){
							$("#pstatus").html("否");
						}else{
							$("#pstatus").html("是");
						}
						if(data.pstatus=="已退号"){
							$("#status").html("已退号");
						}else{
							$("#status").html("正常");
						}
						$("#deptname").val(data.dept.deptname);
						$("#dname").val(data.doc.dname);
						if(data.pstatus=="未看诊"){
							$("#refund")[0].disabled=false;
						}
					}
				}
			});
		}
	})
	
	var num=0;
	
	//选择菜单中的检查项目，变更页面表格数据
	$("#checkitem").change(function(){
		var selectedOption = $("#checkitem option:selected");
		
		//如果选择的是请选择，则方法执行结束
		if(selectedOption[0].value!=-1){
			//如果表格中已经有该检查项目，则不进行追加
			var tds = $("#item_data tr").find("td:eq(0)");
			//默认该选项没被选择过
			var flag=false;
			for(var k=0;k<tds.length;k++){
				if($(tds[k]).html()==selectedOption[0].text){
					flag = true;
					break;
				}
			}
			//表格追加tr
			if(flag==false){
				$("#item_data").append("<tr><td>"+selectedOption[0].text+"</td><td><input type='hidden' name='cirList["+num+"].cid'   value='"+selectedOption[0].value+"' ><input type='text' name='cirList["+num+"].amount'   class='amount'   value='1' >" +
						"</td><td>"+selectedOption.attr("data-price")+"</td>" +
								"<td><img src='img/rubbish.png' class='rubbish'    ></td></tr>");
				num++;
			}
		}
		
	})
	
	//点击垃圾桶，删除所在行
	$(document).on("click",".rubbish",function(){
		$(this).parent().parent().remove();
	})
	
	//变更数量，计算总金额
	$(document).on("blur",".amount",function(){
		var amount = $(this).val();
		if(parseInt(amount)==0 || isNaN(amount)){
			alert("请输入正确的数量");
		}else{
			var price = $(this).parent().next().html();
			$(this).parent().next().html(parseInt(price)*parseInt(amount));
		}
	})
	
	//提交检查申请
	$("#submitItem").click(function(){
		$.ajax({
				type:"post",
				url:"saveCheckItemRecord/"+$("#pid").val(),
				data:$("#form1").serialize(),
				dataType:"json",
				success:function(data){
					console.log(data);
					alert(data.result);
					location.href="doctor_medicalrecord.html";
				}
			});
	})
	
	
	
})

