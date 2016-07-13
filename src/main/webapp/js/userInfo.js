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
	$.fn.extend({
	    uploadPreview: function (opts) {
	        var _self = this,
	            _this = $(this);
	        opts = jQuery.extend({
	            Img: "ImgPr",
	            Width: 100,
	            Height: 100,
	            ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
	            Callback: function () {}
	        }, opts || {});
	        _self.getObjectURL = function (file) {
	            var url = null;
	            if (window.createObjectURL != undefined) {
	                url = window.createObjectURL(file)
	            } else if (window.URL != undefined) {
	                url = window.URL.createObjectURL(file)
	            } else if (window.webkitURL != undefined) {
	                url = window.webkitURL.createObjectURL(file)
	            }
	            return url
	        };
	        _this.change(function () {
	            if (this.value) {
	                if (!RegExp("\.(" + opts.ImgType.join("|") + ")$", "i").test(this.value.toLowerCase())) {
	                    alert("选择文件错误,图片类型必须是" + opts.ImgType.join("，") + "中的一种");
	                    this.value = "";
	                    return false
	                }
	                if ($.support.msie) {
	                    try {
	                        $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
	                    } catch (e) {
	                        var src = "";
	                        var obj = $("#" + opts.Img);
	                        var div = obj.parent("div")[0];
	                        _self.select();
	                        if (top != self) {
	                            window.parent.document.body.focus()
	                        } else {
	                            _self.blur()
	                        }
	                        src = document.selection.createRange().text;
	                        document.selection.empty();
	                        obj.hide();
	                        obj.parent("div").css({
	                            'filter': 'progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)',
	                            'width': opts.Width + 'px',
	                            'height': opts.Height + 'px'
	                        });
	                        div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src
	                    }
	                } else {
	                    $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
	                }
	                opts.Callback();
	            }
	        })
	    }
	});
	$("#reset-image").uploadPreview({ Img: "info-image", Width: 140, Height: 140 });
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
		if (email != "" && submit_flag) {
			
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
				url: "/user/updatePassword",
				type: "post",
				data: data,
				dataType: "json",
				error: function(xhr, textstatus, errorThrown) {
					alert(xhr.status + '/n' + Thrown + '/n' + errorThrown);
					$(".currentPasswordError").html("当前密码输入不正确");
					$(".currentPasswordError").css("visibility", "visible");
				},
				success: function(data) {
					alert("密码修改成功");
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