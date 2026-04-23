<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <%-- 共通レイアウト base.jsp を読み込み --%>

    <c:param name="title">得点管理システム</c:param>
    <%-- 画面タイトルを base.jsp に渡す --%>

    <c:param name="content">
        <!-- base.jsp のメインコンテンツ領域に埋め込まれる -->

        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>
            <!-- 学生情報変更完了画面 -->

            <div class="text-center" style="background-color: #6EC8B5;">
                変更が完了しました
            </div>
            <!-- 成功メッセージ -->
			<div style="padding-top: 50px;">
            	<a href="StudentList.action">学生一覧</a>
            </div>
            <!-- 学生一覧画面へのリンク -->

        </section>
    </c:param>
</c:import>
