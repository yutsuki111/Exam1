<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通テンプレート（base.jsp）を読み込み、ページ全体のレイアウトを適用 --%>
<c:import url="/common/base.jsp" >
	<%-- ページのタイトルをパラメータとして渡す --%>
	<c:param name="title">得点管理システム</c:param>

	<c:param name="scripts"></c:param>

	<%-- メインコンテンツ部分の定義 --%>
	<c:param name="content">
		<section class="me=4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>
			

			

			<%-- 検索・絞り込みフォーム --%>
			<form method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					
					<%-- 入学年度の選択プルダウン --%>
					<div class="mb-3">
						<label class="form-label">入学年度</label>
						<form action="aaa" method="post">
						<br><input type="nendo" name="ent_year">
							<%-- Actionクラスから渡された ent_year_set（年度リスト）をループで回す --%>
							<c:forEach var="year" items="${ent_year }">
								<%-- 検索後の再表示時、選択していた年度を保持（selected）する --%>
								<option value="${year }" <c:if test="${year == f1 }">selected</c:if>>${year }</option>
							</c:forEach>
						
					</div>
					<br>
					<%-- クラス番号の選択プルダウン --%>
					<div class="mb-3">
						<label class="form-label">学生番号</label>
						<form action="bbb" method="post">
						<br><input type="bango" name="no">
							<%-- Actionから渡された class_num_set（クラス一覧）をループで回す --%>
							<c:forEach var="num" items="${no }">
								<%-- 検索後の再表示時、選択していたクラスを保持（selected）する --%>
								<option value="${num }" <c:if test="${num == f2 }">selected</c:if>>${num }</option>
							</c:forEach>
						
					</div>
					<br>
					<div class="mb-3">
						<label class="form-label">氏名</label>
						<form action="ccc" method="post">
						<br><input type="simei" name="name" style="width: 400px;">
							<%-- Actionから渡された class_num_set（クラス一覧）をループで回す --%>
							<c:forEach var="num" items="${name }">
								<%-- 検索後の再表示時、選択していたクラスを保持（selected）する --%>
								<option value="${num }" <c:if test="${num == f2 }">selected</c:if>>${num }</option>
							</c:forEach>
						
					</div>

					<div class="mb-3">
						<label class="form-label">クラス</label>
						<select name="class_num" class="form-select">
							<option value="0">--------</option>
							<%-- Actionから渡された class_num_set（クラス一覧）をループで回す --%>
							<c:forEach var="num" items="${class_num_set }">
								<%-- 検索後の再表示時、選択していたクラスを保持（selected）する --%>
								<option value="${num }" <c:if test="${num == f2 }">selected</c:if>>${num }</option>
							</c:forEach>
						</select>
					</div>
					
					<%-- 在学中チェックボックス --%>
					<div class="mb-3">
						<label class="form-check-label" for="student-f3-check">在学中
							<%-- パラメーターf3（チェック状態）が存在する場合、チェックを入れたまま（checked）にする --%>
							<input class="form-check-input" type="checkbox"
							id="student-f3-check" name="f3" value="t"
							<c:if test="${!empty f3 }">checked</c:if> />
						</label>
					</div>

					<%-- 絞込み実行ボタン --%>
					<div class="mb-3">
						<button type="submit" class="btn btn-primary">変更</button>
					</div>
			
					<div class="mt-3">
						<a href="StudentList.action">戻る</a>
					</div>
				</div>
			</form>
		</section>
	</c:param>
</c:import>
