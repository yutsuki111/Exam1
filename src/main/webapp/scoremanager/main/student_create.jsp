<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 fw-bold">学生情報登録</h2>

            <form action="StudentCreateExecute.action" method="post" class="mx-4">
                
                <%-- 入学年度 --%>
                <div class="mb-3">
                    <label for="ent-year-select" class="form-label">入学年度</label>
                    <select class="form-select" id="ent-year-select" name="ent_year">
                        <option value="0">--------</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <option value="${year}" <c:if test="${year == ent_year}">selected</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                    <%-- 入学年度未選択エラー --%>
                    <c:if test="${not empty errors.get('f1')}">
                        <div class="small mt-1" style="color: orange;">${errors.get("f1")}</div>
                    </c:if>
                </div>

                <%-- 学生番号 --%>
                <div class="mb-3">
                    <label for="student-no" class="form-label">学生番号</label>
                    <input type="text" class="form-control" id="student-no" name="no" 
                           value="${no}" maxlength="10" placeholder="学生番号を入力してください" required>
                    <%-- 学生番号重複エラー --%>
                    <c:if test="${not empty errors.get('no')}">
                        <div class="small mt-1" style="color: orange;">${errors.get("no")}</div>
                    </c:if>
                </div>

                <%-- 氏名 --%>
                <div class="mb-3">
                    <label for="student-name" class="form-label">氏名</label>
                    <input type="text" class="form-control" id="student-name" name="name" 
                           value="${name}" maxlength="30" placeholder="氏名を入力してください" required>
                </div>

                <%-- クラス --%>
                <div class="mb-3">
                    <label for="class-num-select" class="form-label">クラス</label>
                    <select class="form-select" id="class-num-select" name="class_num">
                        <c:forEach var="num" items="${class_num_set}">
                            <option value="${num}" <c:if test="${num == class_num}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>

                <%-- 登録して終了 --%>
                <div class="mt-4">
                    <button type="submit" class="btn btn-secondary" name="end" value="15">登録して終了</button>
                </div>
            </form>

            <%-- 戻るリンク --%>
            <div class="mx-4 mt-3">
                <a href="StudentList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>