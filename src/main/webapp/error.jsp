<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">エラー 得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-danger bg-opacity-10 py-2 px-4 text-danger">
                システムエラー
            </h2>

            <div class="mx-4 mt-4">
                <div class="alert alert-danger" role="alert">
                    <h4 class="alert-heading">申し訳ございません。</h4>
                    <p>処理の実行中に予期せぬエラーが発生しました。</p>
                    <hr>
                    <%-- 
                         FrontController や Action から渡された具体的な
                         エラーメッセージがある場合はここに表示する 
                    --%>
                    <p class="mb-0 small">
                        <strong>詳細:</strong> 
                        <c:out value="${errortext}" default="不明なエラーが発生しました。" />
                    </p>
                </div>

                <div class="mt-4 text-center">
                    <a href="${pageContext.request.contextPath}/scoremanager/main/Menu.action" class="btn btn-outline-secondary">
                        メインメニューへ戻る
                    </a>
                </div>
            </div>
        </section>
    </c:param>
</c:import>