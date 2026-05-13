<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通テンプレート（base.jsp）を読み込み --%>
<c:import url="/common/base.jsp" >
	<%-- ページのタイトルをパラメータとして渡す --%>
	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<%-- メインコンテンツ部分の定義 --%>
	<c:param name="content">
	
		<style>
        	table {border-collapse: collapse;}
        	th, td {border-bottom: 1px solid #ccc;padding: 8px;}
    	</style>
	
		<section class="me=4">
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス管理</h2>
		<!-- 新規登録リンク -->
		<div class="my-2 text-end px-4">
				<a href="ClassCreate.action">新規登録</a>
		</div>
		<!-- クラステーブル -->
		<table style="width:100%; table-layout: fixed;" >
		<tr>
		<th style="width: 75%;">クラス名</th>
		<th style="width: 17%;"></th>
		</tr>
		<c:forEach var="c" items="${class_list }">
		<tr>
		<td>${c}</td>
		<td>
		<!-- 変更リンク -->
		<a href="ClassUpdate.action?class_num=${c}">変更</a>
		</td>
		</tr>
		</c:forEach>
		</table>
	</c:param>
</c:import>