<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">成績参照 | 得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <%-- ヘッダー部分 --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>

            <%-- 検索フィルター外枠 --%>
            <div class="border mx-3 mb-3 p-3 rounded shadow-sm bg-light" id="filter">
                
                <%-- 1. 科目情報検索フォーム --%>
                <form action="TestListSubjectExecute.action" method="get" class="row align-items-end g-3 pb-3 border-bottom mb-3">
                    <div class="col-2 fw-bold text-secondary">科目情報</div>
                    
                    <%-- 入学年度 --%>
                    <div class="col-2">
                        <label class="form-label" for="f1">入学年度</label>
                        <select class="form-select" id="f1" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- クラス --%>
                    <div class="col-2">
                        <label class="form-label" for="f2">クラス</label>
                        <select class="form-select" id="f2" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 科目 --%>
                    <div class="col-4">
                        <label class="form-label" for="f3">科目</label>
                        <select class="form-select" id="f3" name="f3">
                            <option value="0">--------</option>
                            <c:forEach var="s" items="${subjects}">
                                <%-- 修正：cdで比較することで選択状態を保持 --%>
                                <option value="${s.cd}" <c:if test="${s.cd == f3}">selected</c:if>>${s.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 検索ボタン --%>
                    <div class="col-2">
                        <button type="submit" class="btn btn-secondary w-100">検索</button>
                    </div>
                    
                    <%-- 科目検索のエラーメッセージ --%>
                    <c:if test="${not empty suberror}">
                        <div class="col-12 text-danger small pt-1">${suberror}</div>
                    </c:if>
                </form>

                <%-- 2. 学生情報検索フォーム --%>
                <form action="TestListStudentExecute.action" method="get" class="row align-items-end g-3 pt-2">
                    <div class="col-2 fw-bold text-secondary">学生情報</div>
                    
                    <%-- 学生番号入力 --%>
                    <div class="col-4">
                        <label class="form-label" for="f4">学生番号</label>
                        <input type="text" class="form-control" id="f4" name="f4" 
                               placeholder="学生番号を入力してください" value="${f4}">
                    </div>

                    <%-- 検索ボタン --%>
                    <div class="col-2">
                        <button type="submit" class="btn btn-secondary w-100">検索</button>
                    </div>
                </form>
            </div>

            <%-- 戻るボタン --%>
            <div class="mx-4 mt-4">
                <a href="Menu.action" class="btn btn-link p-0 text-decoration-none">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>