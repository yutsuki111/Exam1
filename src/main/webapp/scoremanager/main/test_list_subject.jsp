<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<<<<<<< HEAD
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

=======
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
 
<%-- 共通テンプレート（base.jsp）を読み込み --%>
<c:import url="/common/base.jsp" >
	<%-- ページのタイトルをパラメータとして渡す --%>
	<c:param name="title">得点管理システム</c:param>
 
	<c:param name="scripts"></c:param>
 
	<%-- メインコンテンツ部分の定義 --%>
	<c:param name="content">
		<section class="me=4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績一覧（科目）</h2>
			
 
			<%-- 検索・絞り込みフォーム --%>
			<form method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					
					<%-- 入学年度の選択プルダウン --%>
					<div class="col-4">
						<label class="form-label" for="student-f1-select">入学年度</label>
						<select class="form-select" id="student-f1-select" name="f1">
							<option value="0">--------</option>
							<%-- Actionクラスから渡された ent_year_set（年度リスト）をループで回す --%>
							<c:forEach var="year" items="${ent_year_set }">
								<%-- 検索後の再表示時、選択していた年度を保持（selected）する --%>
								<option value="${year }" <c:if test="${year == f1 }">selected</c:if>>${year }</option>
							</c:forEach>
						</select>
					</div>
 
					<%-- クラス番号の選択プルダウン --%>
					<div class="col-4">
						<label class="form-label" for="student-f2-select">クラス</label>
						<select class="form-select" id="student-f2-select" name="f2">
							<option value="0">--------</option>
							<%-- Actionから渡された class_num_setをループ --%>
							<c:forEach var="num" items="${class_num_set }">
								<%-- 検索後の再表示時、選択していたクラスを保持 --%>
								<option value="${num }" <c:if test="${num == f2 }">selected</c:if>>${num }</option>
							</c:forEach>
						</select>
					</div>
 
					<%-- 在学中チェックボックス --%>
					<div class="col-2 form-check text-center">
						<label class="form-check-label" for="student-f3-check">在学中
							<%-- パラメーターf3（チェック状態）がある場合、チェックを入れたまま（checked）にする --%>
							<input class="form-check-input" type="checkbox"
							id="student-f3-check" name="f3" value="t"
							<c:if test="${!empty f3 }">checked</c:if> />
						</label>
					</div>
 
					<%-- 絞込み実行ボタン --%>
					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">絞込み</button>
					</div>
 
					<%-- 入力エラーを表示 --%>
					<div class="mt-2 text-warning">${errors.get("f1") }</div>
				</div>
			</form>
 
			<%-- 学生リストの表示判定 --%>
			<c:choose>
				<%-- 1件以上見つかった場合 --%>
				<c:when test="${students.size() > 0 }">
					<div>検索結果：${students.size() }件</div>
					<table class="table table-hover">
						<tr>
							<th>入学年度</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>クラス</th>
							<th class="text-center">在学中</th>
							<th></th>
						</tr>
						<%-- 学生リスト(students)を1つずつstudent変数に取り出して表示 --%>
						<c:forEach var="student" items="${students }">
							<tr>
								<td>${student.entYear }</td>
								<td>${student.no }</td>
								<td>${student.name }</td>
								<td>${student.classNum }</td>
								<td class="text-center">
									<%-- 在学フラグ(isAttend)がtrueなら「◯」、falseなら「×」 --%>
									<c:choose>
										<c:when test="${student.isAttend() }">◯</c:when>
										<c:otherwise>×</c:otherwise>
									</c:choose>
								</td>
								<%-- 学生番号(no)をパラメータとして渡し、編集画面へ遷移 --%>
								<td><a href="StudentUpdate.action?no=${student.no }">変更</a></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				
				<%-- 検索結果が0件だった場合 --%>
				<c:otherwise>
					<div class="alert alert-info">学生情報が存在しませんでした。</div>
				</c:otherwise>
			</c:choose>
		</section>
>>>>>>> branch 'master' of https://github.com/yutsuki111/Exam1.git
	</c:param>
</c:import>