<%--
    a学生一覧表示画面
    機能：入学年度、クラス、在学状況での絞り込みと、該当する学生の一覧表示
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
 
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
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
		<div class="my-2 text-end px-4">
				<a href="SubjectCreate.action">新規登録</a>
			</div>
		<table style="width:100%; table-layout: fixed;" >
		<tr>
		<th style="width: 22%;">科目コード</th>
		<th style="width: 43%;">科目名</th>
		<th style="width: 17%;"></th>
		<th style="width: 13%;"></th>
		</tr>
		<c:forEach var="s" items="${subjects }">
		<tr>
		<td>${s.cd}</td>
		<td>${s.name }</td>
		<td>
		<a href="SubjectUpdate.action?cd=${s.cd}">変更</a>
		</td>
		<td>
		<a href="SubjectDelete.action?cd=${s.cd}">削除</a>
		</td>
		</tr>
		</c:forEach>
		
		</table>
 
	</c:param>
</c:import>
 