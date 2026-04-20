<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 宣言 -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- base.jspを読み込む -->
<c:import url="/common/base.jsp">

    <!-- ページタイトル -->
    <c:param name="title">
        得点管理システム
    </c:param>

    
    <c:param name="scripts"></c:param>

    <!-- ページ固有のコンテンツを base.jsp に渡す -->
    <c:param name="content">

        <!-- ログイン画面へのリンク -->
        <a href="Login.action" class="text-center">ログイン</a>

    </c:param>

</c:import>