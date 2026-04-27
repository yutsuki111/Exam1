<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%-- 共通レイアウト base.jsp を読み込み、title と content を渡す --%>
<c:import url="/common/base.jsp">
    
    <%-- ページタイトル --%>
    <c:param name="title">得点管理システム</c:param>

    <%-- メインコンテンツ開始 --%>
    <c:param name="content">
        <section class="me-4">

            <%-- 見出し --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績一覧（科目）</h2>

            <%-- 検索フォーム（入学年度・クラス・科目） --%>
            <div class="border mx-3 mb-3 p-3 rounded shadow-sm bg-light">
                <form action="TestListSubjectExecute.action" method="get" class="row align-items-end g-3 pb-3 border-bottom mb-3">

                    <div class="col-1 fw-bold text-secondary text-center">科目情報</div>

                    <%-- 入学年度プルダウン --%>
                    <div class="col-2">
                        <label class="form-label" for="f1">入学年度</label>
                        <select class="form-select" id="f1" name="f1">
                            <option value="0">--------</option>

                            <%-- ent_year_set の年度を選択肢として表示 --%>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>
                                    ${year}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- クラス番号プルダウン --%>
                    <div class="col-2">
                        <label class="form-label" for="f2">クラス</label>
                        <select class="form-select" id="f2" name="f2">
                            <option value="0">--------</option>

                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num == f2}">selected</c:if>>
                                    ${num}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 科目プルダウン --%>
                    <div class="col-4">
                        <label class="form-label" for="f3">科目</label>
                        <select class="form-select" id="f3" name="f3">
                            <option value="0">--------</option>

                            <%-- subjects の科目一覧を表示 --%>
                            <c:forEach var="s" items="${subjects}">
                                <option value="${s.cd}" <c:if test="${s.cd == f3}">selected</c:if>>
                                    ${s.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 検索ボタン --%>
                    <div class="col-2">
                        <button type="submit" class="btn btn-secondary w-100">検索</button>
                    </div>
                </form>
            </div>

            <%-- 検索結果表示エリア --%>
            <div class="mx-3 mt-4">
                <c:choose>

                    <%-- test_list が存在する場合（検索結果あり） --%>
                    <c:when test="${not empty test_list}">
                        
                        <%-- 科目名を表示（リストの先頭から取得） --%>
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
                                <%-- 成績リストを1行ずつ表示 --%>
                                <c:forEach var="t" items="${test_list}">
                                    <tr>
                                        <td>${t.entYear}</td>
                                        <td>${t.classNum}</td>
                                        <td>${t.no}</td>
                                        <td>${t.name}</td>

                                        <%-- 1回目の点数（0以上なら表示、なければ "-"） --%>
                                        <td class="text-end">
                                            <c:choose>
                                                <%-- t.getPoint(1) を呼び出す --%>
                                                <c:when test="${t.getPoint(1) >= 0}">
                                                    ${t.getPoint(1)}
                                                </c:when>
                                                <c:otherwise>-</c:otherwise>
                                            </c:choose>
                                        </td>

                                        <%-- 2回目の点数 --%>
                                        <td class="text-end">
                                            <c:choose>
                                                <%-- t.getPoint(2) を呼び出す --%>
                                                <c:when test="${t.getPoint(2) >= 0}">
                                                    ${t.getPoint(2)}
                                                </c:when>
                                                <c:otherwise>-</c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>

						<tfoot class="bg-light fw-bold border-top">
						    <tr>
						        <td colspan="4" class="text-end">平均点</td>
						        <td class="text-end">
						            <c:choose>
						                <c:when test="${avg1 >= 0}">
						                    
						                    <fmt:formatNumber value="${avg1}" maxFractionDigits="2" minFractionDigits="2" />点
						                </c:when>
						                <c:otherwise>-</c:otherwise>
						            </c:choose>
						        </td>
						        <td class="text-end">
						            <c:choose>
						                <c:when test="${avg2 >= 0}">
						                    
						                    <fmt:formatNumber value="${avg2}" maxFractionDigits="2" minFractionDigits="2" />点
						                </c:when>
						                <c:otherwise>-</c:otherwise>
						            </c:choose>
						        </td>
						    </tr>
						</tfoot>
                        </table>
                    </c:when>

                    <%-- test_list が空の場合（検索結果なし） --%>
                    <c:otherwise>
                        <div class="mt-3 px-3">情報が存在しません。</div>
                    </c:otherwise>

                </c:choose>
            </div>
        </section>
    </c:param>
</c:import>