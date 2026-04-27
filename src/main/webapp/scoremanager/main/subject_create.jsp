<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
	    得点管理システム
	</c:param>
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
	<h2 class="bg-dark bg-opacity-10">科目情報登録</h2>
	<form action="SubjectCreateExecute.action" method="get">
	<div class="mb-3">
	<!-- 科目コード（3文字以下） -->
	<label class="form-label">科目コード</label>
      <input type="text" name="cd" placeholder="科目コードを入力してください" 
             class="form-control" maxlength="3" value="${cd}" required>
    </div>
    <!-- エラー文（ないときは表示されない） -->
    <p style="color: orange;">${ lenerrortext}</p>
    <p style="color: orange;">${duperrortext }</p>
    <div class="mb-3">
    <!-- 科目名 -->
	<label class="form-label">科目名</label>
      <input type="text" name="name" placeholder="科目名を入力してください" 
             class="form-control" maxlength="20" value="${name }" required>
    </div>

    <div class="mb-3">
    <!-- 登録ボタン -->
    <input type="submit" value="登録" class="btn btn-primary">
    </div>
	</form>
	<div class="mb-3">
	<!-- 科目一覧に戻る -->
	<a href="SubjectList.action">戻る</a>
	</c:param>

</c:import>
