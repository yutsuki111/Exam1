<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>

            <form action="StudentUpdateExecute.action" method="post" class="mx-4">

                <%-- 科目コード (必須入力・最大30文字) --%>
                <div class="mb-3 row">
                    <label for="subject-name" class="col-sm-2 col-form-label fw-bold">科目コード</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="subject-name" name="name" 
                               value="${subject.name}" maxlength="30" required 
                               placeholder="科目を入力してください">
                        <%-- 科目未入力等のエラー表示 --%>
                        <c:if test="${not empty errors.get('name')}">
                            <div class="text-danger small mt-1">${errors.get("name")}</div>
                        </c:if>
                    </div>
                </div>

                <%--科目 --%>
                <div class="mb-3 row">
                    <label for="subject-class" class="col-sm-2 col-form-label fw-bold">科目</label>
                    <div class="col-sm-4">
                        <select class="form-select" id="subject-class" name="class_num">
                            <c:forEach var="num" items="${subject_set}">
                                <option value="${num}" <c:if test="${num == subject.classNum}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <%-- 変更ボタン --%>
                <div class="mt-4">
                    <button type="submit" class="btn btn-primary" name="login">変更</button>
                </div>
            </form>

            <%-- 戻るリンク --%>
            <div class="mx-4 mt-3">
                <a href="SubjectList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>