<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績一覧（学生）</h2>

            <div class="border mx-3 mb-3 p-3 rounded shadow-sm bg-light">
                <%-- 科目情報検索フォーム --%>
                <form action="TestListSubjectExecute.action" method="get" class="row align-items-end g-3 pb-3 border-bottom mb-3">
                    <div class="col-1 fw-bold text-secondary text-center">科目情報</div>
                    <div class="col-2"><label class="form-label">入学年度</label><select class="form-select"><option>--------</option></select></div>
                    <div class="col-2"><label class="form-label">クラス</label><select class="form-select"><option>--------</option></select></div>
                    <div class="col-4"><label class="form-label">科目</label><select class="form-select"><option>--------</option></select></div>
                    <div class="col-2"><button type="submit" class="btn btn-secondary w-100">検索</button></div>
                </form>

                <%-- 学生情報検索フォーム：入力値を保持 --%>
                <form action="TestListStudentExecute.action" method="get" class="row align-items-end g-3 pt-2">
                    <div class="col-1 fw-bold text-secondary text-center">学生情報</div>
                    <div class="col-4">
                        <label class="form-label" for="f4">学生番号</label>
                        <input type="text" class="form-control" id="f4" name="f4" value="${f4}" required>
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
                        <div class="mb-2 small">
                            氏名：${test_list[0].studentName} (${f4})
                        </div>
                        <table class="table table-hover border-top">
                            <thead>
                                <tr class="text-secondary small">
                                    <th style="width: 40%;">科目名</th> [cite: 45]
                                    <th style="width: 20%;">科目コード</th> [cite: 45]
                                    <th class="text-center" style="width: 20%;">回数</th> [cite: 45]
                                    <th class="text-end" style="width: 20%;">点数</th> [cite: 45]
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="t" items="${test_list}">
                                    <tr>
                                        <td>${t.subjectName}</td>
                                        <td>${t.subjectCd}</td> [cite: 45]
                                        <td class="text-center">${t.num}</td> [cite: 45]
                                        <td class="text-end">${t.point}</td> [cite: 45]
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>

                    <%-- 結果がない場合：画像 image_6364e4.png ② --%>
                    <c:otherwise>
                        <c:if test="${not empty f4}">
                             <div class="mb-2 small">氏名：${student_name} (${f4})</div>
                             <div class="mt-1 px-1">成績情報が存在しませんでした</div>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>
    </c:param>
</c:import>