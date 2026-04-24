<%-- index　ログイン有無に関わらず共通 --%>
<%-- base.jspの　ログインチェックを削除 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<title>得点管理システム</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
	<div id="wrapper" class="container">
		<header
			class="d-flex flex-wrap justify-content-center py-3 px-5 mb-4 border-bottom border-2 bg-primary bg-opacity-10 bg-gradient">
			<div class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
				<h1 class="fs-1">得点管理システム</h1>
			</div>
		</header>

		<div class="row justify-content-center">
            <main class="col-9 border-start"> 
            	<p class="text-center"><a href="Login.action">ログイン</a><p>
            </main>
		</div>
		<footer class="py-2 my-4 bg-dark bg-opacity-10 border-top border-3 align-bottom">
			<c:import url="/common/footer.jsp" />
		</footer>

	</div>
</body>
</html>
