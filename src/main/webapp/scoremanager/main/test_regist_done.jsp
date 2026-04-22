<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通レイアウトの読み込み --%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            
            <%-- 見出し --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

            <%-- 登録完了メッセージ表示エリア --%>
            <div style="background-color: #94c59c; padding: 12px 20px; margin-bottom: 30px;">
                <span style="color: #333;">登録が完了しました</span>
            </div>

            <%-- 各画面へのリンク --%>
            <div style="display: flex; gap: 50px; padding-left: 10px;">
                <%-- 前の画面に戻るリンク --%>
                <a href="TestRegist.action" style="text-decoration: underline;">戻る</a>
                <%-- 成績参照画面へのリンク --%>
                <a href="TestList.action" style="text-decoration: underline;">成績参照</a>
            </div>

        </section>
    </c:param>
</c:import>