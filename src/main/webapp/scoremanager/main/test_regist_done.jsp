<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp" >
	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<%-- メインコンテンツ部分の定義 --%>
	<c:param name="content">
		<section class="me=4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
				<div style="
	                background-color: #c8e6c9;
	                border: 1px solid #8bc34a;
	                padding: 10px 15px;
	                font-size: 1.2rem;
	                margin: 20px 0;">
	                登録が完了しました。
            	</div>

				<a href="">戻る</a>
				<a href="">成績参照</a>
		</section>
	</c:param>	
</c:import>