<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- ◆ 共通レイアウト base.jsp を読み込み、title と content を渡す --%>
<c:import url="/common/base.jsp">
    
    <%-- ◆ ページタイトル --%>
    <c:param name="title">得点管理システム</c:param>

    <%-- ◆ メインコンテンツ開始 --%>
    <c:param name="content">
        <section class="me-4">

            <%-- ◆ 見出し --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績登録</h2>

            <%-- ◆ 検索フィルタ部分（入学年度・クラス・科目・回数） --%>
            <form action="TestRegist.action" method="get">
                <div class="row border mx-3 mb-3 py-2 align-items-end rounded shadow-sm">

                    <%-- 入学年度 --%>
                    <div class="col-2">
                        <label class="form-label" for="f1">入学年度</label>
                        <select class="form-select" id="f1" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>
                                    ${year}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- クラス番号 --%>
                    <div class="col-2">
                        <label class="form-label" for="f2">クラス</label>
                        <select class="form-select" id="f2" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="cNum" items="${class_num_set}">
                                <option value="${cNum}" <c:if test="${cNum == f2}">selected</c:if>>
                                    ${cNum}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 科目 --%>
                    <div class="col-3">
                        <label class="form-label" for="f3">科目</label>
                        <select class="form-select" id="f3" name="f3">
                            <option value="0">--------</option>
                            <c:forEach var="sub" items="${subject_set}">
                                <option value="${sub.cd}" <c:if test="${sub.cd == f3}">selected</c:if>>
                                    ${sub.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 回数（第1回・第2回など） --%>
                    <div class="col-2">
                        <label class="form-label" for="f4">回数</label>
                        <select class="form-select" id="f4" name="f4">
                            <c:forEach var="n" items="${num_set}">
                                <option value="${n}" <c:if test="${n == f4}">selected</c:if>>
                                    第${n}回
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 検索ボタン --%>
                    <div class="col-2 text-center">
                        <button class="btn btn-secondary w-100" id="filter-button">検索</button>
                    </div>
                </div>
            </form>

            <%-- ◆ 成績入力フォーム部分（検索結果がある場合） --%>
            <c:choose>
                <c:when test="${not empty tests}">
                    <div class="mx-3 mt-4">

                        <%-- 科目名と回数を表示 --%>
                        <p>科目：${tests[0].subject.name} （第${f4}回）</p>

                        <form action="TestRegistExecute.action" method="post">

                            <%-- 科目コードと回数を送信する hidden --%>
                            <input type="hidden" name="subject_cd" value="${f3}">
                            <input type="hidden" name="num" value="${f4}">

                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>入学年度</th>
                                        <th>クラス</th>
                                        <th>学生番号</th>
                                        <th>氏名</th>
                                        <th style="width: 150px;">点数</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <%-- ◆ 学生ごとに1行ずつ成績入力欄を表示 --%>
                                    <c:forEach var="test" items="${tests}">
                                        <tr>
                                            <td>${test.student.entYear}</td>
                                            <td>${test.student.classNum}</td>
                                            <td>${test.student.no}</td>
                                            <td>${test.student.name}</td>

                                            <td>
                                                <%-- 学生番号を hidden で送信（複数行分） --%>
                                                <input type="hidden" name="student_no" value="${test.student.no}">

                                                <%-- 点数入力欄（未登録なら空欄） --%>
                                                <input type="number"
                                                       name="point"
                                                       class="form-control"
                                                       value="${test.point >= 0 ? test.point : ''}"
                                                       min="0" max="100">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>

                            <%-- 登録ボタン --%>
                            <div class="mt-4">
                                <button type="submit" class="btn btn-secondary px-4">登録して終了</button>
                            </div>
                        </form>
                    </div>
                </c:when>

                <%-- ◆ 検索条件が未入力 or 該当学生なし --%>
                <c:otherwise>
                    <div class="mx-4 mt-4 text-muted">

                        <%-- 条件は選んだが該当学生がいない場合 --%>
                        <c:if test="${not empty f1 and f1 != '0'}">
                            該当する学生が見つかりませんでした。
                        </c:if>

                        <%-- 条件未選択の場合 --%>
                        <c:if test="${empty f1 or f1 == '0'}">
                            条件を選択して検索してください。
                        </c:if>

                    </div>
                </c:otherwise>
            </c:choose>

            <%-- ◆ メニューへ戻るリンク --%>
            <div class="mx-4 mt-3">
                <a href="Menu.action">戻る</a>
            </div>

        </section>
    </c:param>
</c:import>