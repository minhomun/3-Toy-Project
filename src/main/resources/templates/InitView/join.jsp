<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<!-- 반응형 웹에 사용하는 메타태그 -->
<link rel="stylesheet" href="css/bootstrap.css">
<!-- 참조  -->
<title>TOYPROJECT</title>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="navbar-header">
		<!-- 홈페이지의 로고 -->
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expand="false">
			<span class="icon-bar"></span>
			<!-- 줄였을때 옆에 짝대기 -->
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="main.jsp">TOYPROJECT</a>
	</div>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li><a href="announcemnetBoard.jsp">공지사항</a></li>
			<li><a href="freeBoard.jsp">자유게시판</a></li>
			<li><a href="faqBoard.jsp">FAQ</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false">접속하기<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li class="active"><a href="login.jsp">로그인</a></li>
					<li><a href="join.jsp">회원가입</a></li>
				</ul></li>
		</ul>
	</div>
	</nav>

	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<form method="post" action="joinAction.jsp">
					<h3 style="text-align: center;">회원가입</h3>
					<h5 style="text-align: left;">아이디</h5>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디를 입력해주세요"
							name="userID" maxlength='20'>
					</div>
					<h5 style="text-align: left;">비밀번호</h5>
					<div class="form-group">
						<input type="password" class="form-control"
							placeholder="비밀번호는 8자 ~ 15자까지 입력해주세요 ." name="userPassword"
							maxlength='20'>
					</div>
					<h5 style="text-align: left;">비밀번호 확인</h5>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호는 8자 ~ 15자까지 입력해주세요 ."
							name="userPassword" maxlength='20'>
					</div>
					<h5 style="text-align: left;">이름</h5>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름을 입력해주세요 ."
							name="userName" maxlength='20'>
					</div>
					<h5 style="text-align: left;">휴대폰</h5>
					<div class="form-group">
						<input type="tel" class="form-control" placeholder="숫자만 입력해주세요 "
							name="userEmail" maxlength='20'>
					</div>
					<input type="submit" class="btn btn-primary form-control"
						value="회원가입">
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>