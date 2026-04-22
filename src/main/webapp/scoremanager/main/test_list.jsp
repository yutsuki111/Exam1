<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<form action = "TestListSubjectExecute.action"method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					<div class="col-2">
					科目情報
					</div>
					<%-- 入学年度の選択プルダウン --%>
					<div class="col-2">
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
					<div class="col-2">
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
					
					<%-- 科目名の選択プルダウン --%>
					<div class="col-4">
						<label class="form-label" for="student-f3-select">科目</label>
						<select class="form-select" id="student-f3-select" name="f3">
							<option value="0">--------</option>
							<%-- Actionから渡された class_num_setをループ --%>
							<c:forEach var="s" items="${subjects }">
								<%-- 検索後の再表示時、選択していたクラスを保持 --%>
								<option value="${s.cd }" <c:if test="${s.name == f3 }">selected</c:if>>${s.name }</option>
							</c:forEach>
						</select>
					</div>
					<%-- 絞込み実行ボタン --%>
					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
				
				
			<form action = "TestListStudentExecute.action"method="get">
				<div class="row  align-items-center py-3" id="filter">
					<div class="col-2">
					学生情報
					</div>
					<%-- 入学年度の選択プルダウン --%>
					<div class="col-2">
						<label class="form-label" for="student-f4-select">学生番号</label>
						<input type="text" class="" id="student-f4-select" name="f4">
					</div>
					
					<%-- 絞込み実行ボタン --%>
					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
					<%-- 入力エラーを表示 --%>
					<div class="mt-2 text-warning">${errors.get("f1") }</div>
				</div>
			</form>
		</section>
	</c:param>
</c:import>
 