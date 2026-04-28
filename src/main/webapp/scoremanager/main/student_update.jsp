<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>

            <form action="StudentUpdateExecute.action" method="post" class="mx-4">
                
                <%-- 入学年度 (readonly: 設計書に基づき編集不可) --%>
                <div class="mb-2">
                    <label class="col-form-label ">入学年度</label>
                    <div class="ms-3">
                        <input type="text" class="form-control-plaintext" name="ent_year" 
                               value="${student.entYear}" readonly>
                    </div>
                </div>

                <%-- 学生番号 (readonly: 設計書に基づき編集不可) --%>
                <div class="mb-3">
                    <label class=" col-form-label ">学生番号</label>
                    <div class="ms-3">
                        <input type="text" class="form-control-plaintext" name="no" 
                               value="${student.no}" readonly>
                    </div>
                </div>

                <%-- 氏名 (必須入力・最大30文字) --%>
                <div class="mb-3">
                    <label for="student-name" class="col-form-label">氏名</label>
                    <div class="mx-auto">
                        <input type="text" class="form-control" id="student-name" name="name" 
                               value="${student.name}" maxlength="30" required 
                               placeholder="氏名を入力してください">
                        <%-- 氏名未入力等のエラー表示 --%>
                        <c:if test="${not empty errors.get('name')}">
                            <div class="text-danger small mt-1">${errors.get("name")}</div>
                        </c:if>
                    </div>
                </div>

                <%-- クラス --%>
                <div class="mb-3">
                    <label for="student-class" class="col-form-label">クラス</label>
                    <div class="mx-auto">
                        <select class="form-select" id="student-class" name="class_num">
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num == student.classNum}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <%-- 在学中チェックボックス --%>
                <div class="mb-4">
                    	<label class="form-check-label " for="student-is-attend">在学中</label>
                            <input style="accent-color: var(--bs-primary);" type="checkbox" id="student-is-attend" 
                                   name="is_attend" value="t" <c:if test="${student.isAttend()}">checked</c:if>>
                </div>

                <%-- 変更ボタン --%>
                <div class="mt-4">
                    <button type="submit" class="btn btn-primary" name="login">変更</button>
                </div>
            </form>

            <%-- 戻るリンク --%>
            <div class="mx-4 mt-3">
                <a href="StudentList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>