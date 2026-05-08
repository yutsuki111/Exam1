<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4  fw-bold">クラス変更</h2>

            <form action="ClassUpdateExecute.action" method="get" class="mx-4">
                
                <%-- 入学年度 (readonly: 設計書に基づき編集不可) --%>
                <div class="mb-2">
                    <label class="col-form-label ">クラス名</label>
                    <div class="ms-3">
                        <input type="text" class="form-control" name="class_num" placeholder="クラス名を入力して下さい"
                               value="${class_num.class_num}" maxlength="5" required>
                        <input type="hidden" name="old_class_num" value="${class_num.class_num}">
                       <p>${errortext }</p>
                    </div>
                </div>
                <%-- 変更ボタン --%>
                <div class="mt-4">
                    <button type="submit" class="btn btn-primary" name="login">変更</button>
                </div>
            </form>

            <%-- 戻るリンク --%>
            <div class="mx-4 mt-3">
                <a href="ClassList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>