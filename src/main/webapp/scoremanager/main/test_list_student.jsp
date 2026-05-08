<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績一覧（学生）</h2>

            <%-- 検索フィルター部分（画像の上半分） --%>
            <%-- 検索フィルター外枠（3画面共通でこのコードを使用します） --%>
			<div class="border mx-3 mb-3 p-3 rounded shadow-sm bg-light">
			    
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
			                    <option value="${s.cd}" <c:if test="${s.cd == f3}">selected</c:if>>${s.name}</option>
			                </c:forEach>
			            </select>
			        </div>
			
			        <%-- 検索ボタン --%>
			        <div class="col-2">
			            <button type="submit" class="btn btn-secondary w-100">検索</button>
			        </div>
			        
			        <%-- 科目検索のエラーメッセージ（オレンジ色に変更） --%>
			        <c:if test="${not empty suberror}">
			            <div class="col-12 text-warning small pt-1">${suberror}</div>
			        </c:if>
			    </form>
			
			    <%-- 2. 学生情報検索フォーム --%>
			    <form action="TestListStudentExecute.action" method="get" class="row align-items-end g-3 pt-2">
			        <div class="col-2 fw-bold text-secondary">学生情報</div>
			        
			        <%-- 学生番号入力 --%>
			        <div class="col-4">
			            <label class="form-label" for="f4">学生番号</label>
			            <input type="text" class="form-control" id="f4" name="f4" 
			                   placeholder="学生番号を入力してください" value="${f4}" required>
			        </div>
			
			        <%-- 検索ボタン --%>
			        <div class="col-2">
			            <button type="submit" class="btn btn-secondary w-100">検索</button>
			        </div>
			    </form>
			</div>
            <%-- 結果表示エリア（画像の下半分・赤枠部分） --%>
            <%-- 結果表示エリア（画像の下半分・赤枠部分） --%>
            <div class="mx-3 mt-4">
                <c:choose>
                    <%-- 学生情報が取得できている場合 --%>
                    <c:when test="${not empty student}">
                        
                        <div class="mb-2 fw-bold">
                            氏名：${student.name}(${student.no})
                        </div>

                        <%-- ★追加：成績リスト（test_list）が空かどうかでさらに分岐させる --%>
                        <c:choose>
                            <c:when test="${not empty test_list}">
                                <%-- 成績がある場合はテーブルを表示 --%>
                                <table class="table table-hover mt-3">
                                    <thead>
                                        <tr class="text-secondary small border-bottom">
                                            <th>科目名</th>   
                                            <th>科目コード</th> 
                                            <th>回数</th>    
                                            <th class="text-end">点数</th> 
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="t" items="${test_list}">
                                            <tr class="border-bottom">
                                                <td>${t.subjectName}</td> 
                                                <td>${t.subjectCd}</td>   
                                                <td>${t.num}</td>        
                                                <td class="text-end">${t.point}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <div>成績情報が存在しませんでした</div>
                            </c:otherwise>
                        </c:choose>

                    </c:when>
                    <c:otherwise>
                        <%-- 検索前やエラー時のメッセージ --%>
                        <div class="mt-4 px-3 text-info">
                            <c:if test="${not empty error}">${error}</c:if>
                            <c:if test="${empty error}">学生情報を入力して検索してください</c:if>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="mx-3 mt-4">
                <c:choose>
                    <c:when test="${not empty student}">
                        <div class="mb-2 fw-bold">
                            氏名：${student.name}(${student.no})
                        </div>

                        <table class="table table-hover mt-3">
                            <thead>
                                <tr class="text-secondary small border-bottom">
                                    <th>科目名</th>   
                                    <th>科目コード</th> 
                                    <th>回数</th>    
                                    <th class="text-end">点数</th> 
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="t" items="${test_list}">
                                    <tr class="border-bottom">
                                        <td>${t.subjectName}</td> 
                                        <td>${t.subjectCd}</td>   
                                        <td>${t.num}</td>        
                                        <td class="text-end">${t.point}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <%-- 検索前やエラー時のメッセージ --%>
                        <div class="mt-4 px-3 text-info">
                            <c:if test="${not empty error}">${error}</c:if>
                            <c:if test="${empty error}">学生情報を入力して検索してください</c:if>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>
    </c:param>
</c:import>