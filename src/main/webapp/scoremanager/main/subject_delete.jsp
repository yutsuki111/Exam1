<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
<%-- 共通レイアウト base.jsp を読み込み --%>
    <c:param name="title">得点管理システム</c:param>
    <%-- 画面タイトルを base.jsp に渡す --%>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>

            <form action="SubjectDeleteExecute.action" method="post" class="mx-4">

                <%-- 削除メッセージ --%>
                <div class="mb-3 row">
                    <label for="cd" class="col-sm-2 col-form-label fw-bold"></label>
                    <div>
                       <p>「${subject_name }(${subject_cd })」を削除してもよろしいですか</p>
                    </div>
                </div>
                <input type="hidden" name="subject_cd" value="${subject_cd}">
                <input type="hidden" name="subject_name" value="${subject_name}">

                <%-- 削除ボタン --%>
                <div>
                    <button type="submit" class="btn btn-danger">削除</button>
                </div>
            </form>
			
            <%-- 戻るリンク --%>
            <div style="padding-top: 50px;">
                <a href="SubjectList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>