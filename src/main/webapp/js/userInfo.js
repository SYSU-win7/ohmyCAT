$(document).ready(function() {

	var submit_flag = true;

	// 设置信息时，在个人资料和密码设置中切换
	$("#personal-msg").click(function(event) {
		$("#personal-msg").toggleClass("active");
		$(".personal-info").css("display", "block");
		$("#set-password").toggleClass("active");
		$(".reset-password").css("display", "none");
	});

	$("#set-password").click(function(event) {
		$("#personal-msg").toggleClass("active");
		$(".personal-info").css("display", "none");
		$("#set-password").toggleClass("active");
		$(".reset-password").css("display", "block");
	});

	// 点击更改头像并选择一张图片作为头像时，将头像更改为当前选择的图片
	/*$("#reset-image").change(function() {
		if ($("#reset-image").val() != "") {
			$("#info-image").attr("src", $("#reset-image").val());
		}
	});*/

	// 修改个人资料时验证输入的邮箱格式是否正确
	$("#info-email").blur(function(event) {
		var email = $("#info-email").val();
		if (email != "") {
			var email_reg = /^[a-z0-9]([a-z0-9]*[-_]?[a-z0-9]+)*@([a-z0-9]*[-_]?[a-z0-9]+)+[\.][a-z]{2,3}([\.][a-z]{2})?$/i;
			if (!email_reg.test(email)) {
				$(".emailError").html("输入的邮箱格式不正确");
				$(".emailError").css("visibility", "visible");
				submit_flag = false;
			} else {
				$(".emailError").css("visibility", "hidden");
				submit_flag = true;
			}
		} else {
			$(".emailError").css("visibility", "hidden");
			submit_flag = false;
		}
	});

	// 提交个人资料修改
	$("#personal-info-submit").click(function(event) {
		var email = $("#info-email").val();
		var assign = $("#info-assign").val();
		var data = {
			email: email,
			assign: assign
		};
		if (email != "" && submit_flag) {
			$.ajax({
				url: "/user/changeInfo",
				type: "post",
				async: false,
				contentType: "application/json;charset=utf-8",
				processData: false,
				data: JSON.stringify(data),
				dataType: "json",
				error: function(xhr, textstatus, errorThrown) {
					alert(xhr.status + '/n' + Thrown + '/n' + errorThrown);
				},
				success: function(data) {
					console.log("success");
				}
			});
		} else if (email == "") {
			$(".emailError").html("邮箱不能为空");
			$(".emailError").css("visibility", "visible");
		} else if (!submit_flag) $("#info-email").focus();
		event.preventDefault();
		return false;
	});

	// 当前密码失去焦点时判断是否为空
	$("#old-password").blur(function(event) {
		if ($("#old-password").val() == "") {
			$(".currentPasswordError").html("当前密码不能为空");
			$(".currentPasswordError").css("visibility", "visible");
		} else {
			$(".currentPasswordError").css("visibility", "hidden");
		}
	})

	// 修改密码时验证两次输入的密码是否相同
	$(".info-input #password").blur(function(event) {
		checkResetPassword(event);
	});
	$(".info-input #password_").blur(function(event) {
		checkResetPassword(event);
	});
	function checkResetPassword(event) {
		var password = $(".info-input #password").val();
		var password_ = $(".info-input #password_").val();
		if (password != "" && password_ != "") {
			if (password != password_) {
				$(".resetPasswordError").css("visibility", "visible");
				$(".info-input #password_").focus();
			} else $(".resetPasswordError").css("visibility", "hidden");
		} else $(".resetPasswordError").css("visibility", "hidden");
	}

	// 提交密码修改
	$("#reset-password-submit").click(function(event) {
		var old_password = $("#old-password").val();
		var new_password = $("#password").val();
		var password_ = $("#password_").val();
		var data = {password: new_password};
		if (old_password != "" && new_password != "" && password_ != "") {

			$.ajax({
				url: "",
				type: "post",
				async: false,
				contentType: "application/json;charset=utf-8",
				processData: false,
				data: JSON.stringify(data),
				dataType: "json",
				error: function(xhr, textstatus, errorThrown) {
					alert(xhr.status + '/n' + Thrown + '/n' + errorThrown);
					$(".currentPasswordError").html("当前密码输入不正确");
					$(".currentPasswordError").css("visibility", "visible");
				},
				success: function(data) {
					console.log("success");
					$(".currentPasswordError").css("visibility", "hidden");
				}
			});
			
		} else if (old_password == "") {
			$(".currentPasswordError").html("当前密码不能为空");
			$(".currentPasswordError").css("visibility", "visible");
			$("#old-password").focus();
		}
		event.preventDefault();
		return false;
	});
});