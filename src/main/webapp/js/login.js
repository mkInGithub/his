$(function(){
	
	$("#loginBtn").click(function(){
		$.ajax({
			type:"post",
			url:"userlogin",
			data:$("#form1").serialize(),
			dataType:"json",
			success:function(data){
				if(data.result=="登录失败"){
					alert(data.result);
				}else{
					var role = $("#role").val();
					if(role=="门诊管理员"){
						location.href="outpatient_register.html";
					}else{
						location.href="doctor_medicalrecord.html";
					}
				}
			}
		});
	})
})


