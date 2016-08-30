<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="./include/header.jsp"%>
<style>
.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}
.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}
.form-signin .checkbox {
	font-weight: normal;
}
.form-signin .form-control {
	position: relative;
	font-size: 16px;
	height: auto;
	padding: 10px;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}
.form-signin .form-control:focus {
	z-index: 2;
}
.form-signin input[type="text"] {
	margin-bottom: -1px;
	border-bottom-left-radius: 0;
	border-bottom-right-radius: 0;
}
.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
.account-wall {
	margin-top: 20px;
	padding: 40px 0px 20px 0px;
	background-color: #f7f7f7;
	-moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	-webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}
.login-title {
	color: #555;
	font-size: 18px;
	font-weight: 400;
	display: block;
}
a:hover {
	text-decoration: none;
}
.modal.modal-center {
	text-align: center;
}
.modal.modal-center:before {
	display: inline-block;
	vertical-align: middle;
	content: " ";
	height: 100%;
}
.modal-dialog.modal-center {
	display: inline-block;
	text-align: left;
	vertical-align: middle;
}
</style>
<div id="wrap_content" style="min-height: 100%; position: relative;">
	<div id="content">
		<div id="login">
			<div class="col-sm-6 col-md-4 col-md-offset-4" style="margin-top: 100px;">
				<c:choose>
					<c:when test="${ error == 'loginerror' }">
						<font class="text-center login-title" style="font-size: 8pt; color: red;">
							입력하신 정보가 올바르지 않습니다. 다시 시도해 주세요.
						</font>
					</c:when>
					<c:otherwise>
						<font class="text-center login-title" style="font-size: 8pt">
							아이디 비밀번호를 입력하신 후, 로그인 버튼을 클릭해 주세요.
						</font>
					</c:otherwise>
				</c:choose>
				<div class="account-wall">
					<form id="loginfrm" name="loginfrm" class="form-signin" action="/login" method="post">
						<input type="text" id="member_id" name="member_id" class="form-control" placeholder="ID" required autofocus>
						<input type="password" id="member_pw" name="member_pw" class="form-control" placeholder="Password" required>
						<button class="btn btn-lg btn-danger btn-block" type="button" onclick="loginCheck();">로그인</button>
					</form>
				</div>
				<div style="text-align: center; margin-top: 10px; margin-bottom: 100px;">
					<a href="/member" class="new-account">회원가입</a> / 
					<a href="/user/idSearch" class="find-id">아이디찾기</a> / 
					<a href="/user/pwSearch" class="find-pw">비밀번호찾기</a>
				</div>
			</div>
		</div>
	</div>
	<div class="modal modal-center fade" id="myModal" role="dialog" style="margin: 0 auto;">
		<div class="modal-dialog modal-sm modal-center">
			<div class="modal-content">
				<div class="modal-body">
					<form role="form">
						<label id="msg"></label>
					</form>
				</div>
				<div class="modal-footer" style="border: 0px;">
					<button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function loginCheck() {
		if ($("#member_id").val() == null || $("#member_id").val() == "") {
			$("#msg").html("아이디를 입력하세요.");
			$('#myModal').modal('show');
			$("#member_id").focus();
		} else if ($("#member_pw").val() == null || $("#member_pw").val() == "") {
			$("#msg").html("비밀번호를 입력하세요.");
			$('#myModal').modal('show');
			$("#member_pw").focus();
		} else {
			$("#loginfrm").submit();
		}
	}
</script>
<% session.setAttribute("error", null);%>
<%@include file="./include/footer.jsp"%>