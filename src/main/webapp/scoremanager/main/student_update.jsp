<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>

            <form action="StudentUpdateExecute.action" method="post" class="mx-4">
                
                <%-- 入学年度 (readonly) --%>
                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label fw-bold">入学年度</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control-plaintext" name="ent_year" 
                               value="${student.entYear}" readonly>
                    </div>
                </div>

                <%-- 学生番号 (readonly) --%>
                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label fw-bold">学生番号</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control-plaintext" name="no" 
                               value="${student.no}" readonly>
                    </div>
                </div>

                <%-- 氏名 --%>
                <div class="mb-3 row">
                    <label for="student-name" class="col-sm-2 col-form-label fw-bold">氏名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="student-name" name="name" 
                               value="${student.name}" maxlength="30" required>
                        <div class="text-warning">${errors.get("name")}</div>
                    </div>
                </div>

                <%-- クラス --%>
                <div class="mb-3 row">
                    <label for="student-class" class="col-sm-2 col-form-label fw-bold">クラス</label>
                    <div class="col-sm-4">
                        <select class="form-select" id="student-class" name="class_num">
                            <c:forEach var="num" items="${class_num_set}">
                                <%-- studentの持つ現在のクラス番号と一致する場合にselected --%>
                                <option value="${num}" <c:if test="${num == student.classNum}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <%-- 在学中チェックボックス --%>
                <div class="mb-4 row">
                    <div class="col-sm-2 fw-bold">在学中</div>
                    <div class="col-sm-10">
                        <div class="form-check">
                            <%-- Student BeanのisAttend()がtrueならchecked --%>
                            <input class="form-check-input" type="checkbox" id="student-is-attend" 
                                   name="is_attend" value="t" <c:if test="${student.isAttend()}">checked</c:if>>
                            <label class="form-check-label" for="student-is-attend">在学中</label>
                        </div>
                    </div>
                </div>

                <div class="mt-4">
                    <button type="submit" class="btn btn-primary" name="login">変更</button>
                </div>
            </form>

            <div class="mx-4 mt-3">
                <a href="StudentList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>