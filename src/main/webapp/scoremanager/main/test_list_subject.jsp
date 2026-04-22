<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績一覧（科目）</h2>

            <%-- 検索フィルター --%>
            <div class="border mx-3 mb-3 p-3 rounded shadow-sm bg-light">
                <form action="TestListSubjectExecute.action" method="get" class="row align-items-end g-3 pb-3 border-bottom mb-3">
                    <div class="col-1 fw-bold text-secondary text-center">科目情報</div>
                    
                    <div class="col-2">
                        <label class="form-label" for="f1">入学年度</label>
                        <select class="form-select" id="f1" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-2">
                        <label class="form-label" for="f2">クラス</label>
                        <select class="form-select" id="f2" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-4">
                        <label class="form-label" for="f3">科目</label>
                        <select class="form-select" id="f3" name="f3">
                            <option value="0">--------</option>
                            <c:forEach var="s" items="${subjects}">
                                <option value="${s.cd}" <c:if test="${s.cd == f3}">selected</c:if>>${s.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-2">
                        <button type="submit" class="btn btn-secondary w-100">検索</button>
                    </div>
                    
                    <c:if test="${not empty suberror}">
                        <div class="col-12 px-5 py-1" style="color: orange; font-size: 0.9rem;">
                            入学年度とクラスと科目を選択してください
                        </div>
                    </c:if>
                </form>

                <form action="TestListStudentExecute.action" method="get" class="row align-items-end g-3 pt-2">
                    <div class="col-1 fw-bold text-secondary text-center">学生情報</div>
                    <div class="col-4">
                        <label class="form-label" for="f4">学生番号</label>
                        <input type="text" class="form-control" id="f4" name="f4" placeholder="学生番号を入力してください">
                    </div>
                    <div class="col-2">
                        <button type="submit" class="btn btn-secondary w-100">検索</button>
                    </div>
                </form>
            </div>

            <%-- 結果表示エリア --%>
            <div class="mx-3 mt-4">
                <c:choose>
                    <c:when test="${not empty test_list}">
                        <p class="mb-2 small">科目：${test_list[0].subjectName}</p>
                        <table class="table table-hover border-top">
                            <thead>
                                <tr class="text-secondary small">
                                    <th>入学年度</th>
                                    <th>クラス</th>
                                    <th>学生番号</th>
                                    <th>氏名</th>
                                    <th class="text-end">1回</th>
                                    <th class="text-end">2回</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="t" items="${test_list}">
                                    <tr>
                                        <td>${t.entYear}</td>
                                        <td>${t.classNum}</td>
                                        <td>${t.no}</td>
                                        <td>${t.name}</td>
                                        <td class="text-end">
                                            <c:choose>
                                                <c:when test="${t.points.get(1) >= 0}">${t.points.get(1)}</c:when>
                                                <c:otherwise>-</c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="text-end">
                                            <c:choose>
                                                <c:when test="${t.points.get(2) >= 0}">${t.points.get(2)}</c:when>
                                                <c:otherwise>-</c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${not empty f1 and f1 != '0'}">
                            <div class="mt-3 px-3">学生情報が存在しませんでした</div>
                        </c:if>
                        <c:if test="${empty f1 or f1 == '0'}">
                            <div class="mt-3 px-3 text-info" style="font-size: 0.85rem;">
                                科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
                            </div>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>
    </c:param>
</c:import>