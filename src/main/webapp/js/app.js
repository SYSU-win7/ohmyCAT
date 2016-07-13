$(document).ready(function() {

	// 进行cookie监测，如果监测到就自动登录
	(function checkCookie() {
		if ($.cookie("autoLogin") == true) {
			var username = $.cookie("ohmycat-username");
			var password = $.cookie("ohmycat-password");
			var data = {
				username: username,
				password: password
			};
			$.ajax({
				url: "/ohmycat/login",
				type: "post",
				data: data,
				dataType: "json",
				error: function() {
					alert("ERROR!");
				},
				success: function(data) {
					if (data.Status == "Success") {
						window.location.reload();
					} else {
						alert(data.Status);
					}
				}
			});
		}
	})();

	// 是否勾选下次自动登录选项的操作
	$("span[for='auto-login']").click(function() {
		if ($("#auto-login").attr("checked")) {
			$("#auto-login").attr("checked", false);
			$(".check-auto .glyphicon").css("display", "none");
		} else {
			$("#auto-login").attr("checked", true);
			$(".check-auto .glyphicon").css("display", "inline-block");
		}
	});

	// 登录验证用户名和密码
	$("#login-form").submit(function(event) {
		var that = this;
		var username = $(".login-inputs #username").val();
		var password = $(".login-inputs #password").val();
		if (username != "" && password != "") {

			var data = {
				username: username,
				password: password
			};
			if ($("#auto-login").attr("checked")) {
				data.auto = true;
			} else {
				data.auto = false;
			}

			$.ajax({
				url: "/ohmycat/login",
				type: "post",
				data: data,
				dataType: "json",
				error: function() {
					alert("ERROR!");
				},
				success: function(data) {
					$(".login-inputs .errorMsg").css("visibility", "hidden");
					// 处理是否勾选下次自动登录
					if ($("#auto-login").attr("checked") == true) {
						$.cookie("autoLogin",true,{expires:7});
						$.cookie("ohmycat-username",that.username,{expires:7});
						$.cookie("ohmycat-password",that.password,{expires:7});
					} else {
						$.cookie("autoLogin",false,{expires:-1});
						$.cookie("ohmycat-username","",{expires:-1});
						$.cookie("ohmycat-password","",{expires:-1});
					}
					if (data.Status == "Success") {
						window.location.reload();
					} else {
						alert(data.Status);
					}
				}
			});
		} else {
			alert("输入不能为空！");
		}
		event.preventDefault();
		return false;
	});

	// 注册时验证两次输入的密码是否相同
	$(".register-inputs #password").blur(function(event) {
		checkPassword(event);
	});
	$(".register-inputs #password_").blur(function(event) {
		checkPassword(event);
	});
	function checkPassword(event) {
		var password = $(".register-inputs #password").val();
		var password_ = $(".register-inputs #password_").val();
		if (password != "" && password_ != "") {
			if (password != password_) {
				$(".passwordErrorMsg").css("visibility", "visible");
				$(".register-inputs #password_").focus();
			} else $(".passwordErrorMsg").css("visibility", "hidden");
		} else $(".passwordErrorMsg").css("visibility", "hidden");
	}

	// 注册时验证输入的邮箱格式是否正确
	$("#email").blur(function(event) {
		var email = $("#email").val();
		if (email != "") {
			var email_reg = /^[a-z0-9]([a-z0-9]*[-_]?[a-z0-9]+)*@([a-z0-9]*[-_]?[a-z0-9]+)+[\.][a-z]{2,3}([\.][a-z]{2})?$/i;
			if (!email_reg.test(email)) {
				$(".emailErrorMsg").css("visibility", "visible");
			} else $(".emailErrorMsg").css("visibility", "hidden");
		} else $(".emailErrorMsg").css("visibility", "hidden");
	});

	// 注册验证用户名、密码、以及邮箱
	$("#register-submit").click(function(event) {
		var username = $(".register-inputs #username").val();
		var password = $(".register-inputs #password").val();
		var password_ = $(".register-inputs #password_").val();
		var email = $("#email").val();

		var data = {
			username: username,
			password: password,
			email: email
		};

		if (username != "" && password != "" && password_ != "" && email != "") {

			$.ajax({
				url: "/ohmycat/register",
				type: "post",
				data: data,
				dataType: "json",
				error: function() {
					alert("ERROR!");
				},
				success: function(data) {
					if (data.Status == "Success") {
						window.location.reload();
					} else {
						alert(data.Status);
					}
				}
			});

		}
		event.preventDefault();
		return false;
	});
});