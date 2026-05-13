<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
	    得点管理システム
	</c:param>
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
	<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 fw-bold">クラス登録</h2>
	<form action="ClassCreateExecute.action" method="get">
	<div class="mb-3">
	<!-- クラス名-->
	<label class="form-label">クラス名</label>
      <input type="text" name="class_num" placeholder="クラス名を入力してください" 
             class="form-control" maxlength="3" value="${num}" required>
    </div>
    <!-- エラー文（ないときは表示されない） -->
    <p style="color: orange;">${errors }</p>

    <div class="mb-3">
    <!-- 登録ボタン -->
    <input type="submit" value="登録" class="btn btn-primary">
    </div>
	</form>
	<div class="mb-3">
	<!-- クラス一覧に戻る -->
	<a href="ClassList.action">戻る</a>
	</c:param>

</c:import>
