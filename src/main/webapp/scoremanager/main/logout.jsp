<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <%-- 共通レイアウト base.jsp を読み込み--%>

    <c:param name="title">
        得点管理システム
    </c:param>
    <%-- 画面タイトルを base.jsp に渡す --%>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <div>
            <h2 class="bg-dark bg-opacity-10">ログアウト</h2>
            <!-- 見出し色-->

            <div class="text-center" style="background-color: #6EC8B5;">
                <p>ログアウトしました。</p>
                <!-- ログアウト完了メッセージ -->
            </div>
        </div>

        <a href="../Login.action" class="text-center" style="margin-top: 20px;">
            ログイン
        </a>
        <!-- ログイン画面へのリンク -->

    </c:param>

</c:import>
