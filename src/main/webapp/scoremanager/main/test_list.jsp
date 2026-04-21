<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%-- 共通テンプレート（base.jsp）を読み込み、ページ全体のレイアウトを適用 --%>
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
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績一覧（科目）</h2>
		<table style="width:100%; table-layout: fixed;" >
		<tr>
		<th style="width: 22%;">入学年度</th>
		<th style="width: 23%;">クラス</th>
		<th style="width: 17%;">学生番号</th>
		<th style="width: 13%;">氏名</th>
		<th style="width: 10%;">1回</th>
		<th style="width: 10%;">2回</th>
		</tr>
		<c:forEach var="s" items="${subjects }">
		<tr>
		<td>${s.year}</td>
		<td>${s.cd }</td>
		<td>${s.no}</td>
		<td>${s.name}</td>
		<td>${s.num1}</td>
		<td>${s.num2}</td>
		</tr>
		</c:forEach>
		
		</table>

	</c:param>
</c:import>