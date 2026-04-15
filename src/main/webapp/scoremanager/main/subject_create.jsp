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
	<form>
	<div class="mb-3">
	科目コード
      <input type="text" name="cd" placeholder="科目コードを入力して下さい" 
             class="form-control" required>
    </div>
    <div class="mb-3">
	科目名
      <input type="text" name="name" placeholder="科目名を入力してください" 
             class="form-control" required>
    </div>
    <div class="mb-3">
    <input type="submit" value="登録" class="btn btn-primary">
    </div>
	</form>
	<div class="mb-3">
	<a href="SubjectList.action">戻る</a>
	</c:param>

</c:import>
