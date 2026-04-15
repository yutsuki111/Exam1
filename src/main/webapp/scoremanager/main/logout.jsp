<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
	    得点管理システム
	</c:param>
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
	<div class="text-center">
		<h2 class="row border mx-3 mb-3 py-2 align-items-center bg-dark bg-opacity-10">ログアウト</h2>
		<label class="row border mx-3 align-items-center" style="background-color: #bdb;">
		<p>ログアウトしました。</p>
		</label>
	</div>
	<a href="Login.action" class="text-center" style="margin-top: 20px;">ログイン</a>
	
	</c:param>

</c:import>
