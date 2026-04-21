<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<%-- ページタイトル --%>
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">

            <div class="container">
				<!-- 見出し -->
                <h2>成績管理</h2>
				<!-- 検索フォーム -->
                <form action="TestRegist.action" method="post">
                	<!-- 全項目を横並びにする flex コンテナ -->
                	<div class="d-flex align-items-center flex-wrap gap-4">
                	
						<!-- 入学年度 -->
	                    <div class="d-flex align-items-center">
	                        <label class="me-2">入学年度</label>
	                        <select name="f1" class="form-select w-auto">
	                        	<option value="0">------</option>
	                        	<c:forEach var="year" items="${ent_year_set }">
	                            	<option value="${year }">${year }</option>
	                            </c:forEach>
	                        </select>
	                    </div>
	                    <!-- クラス -->
		                <div class="d-flex align-items-center">
	                        <label class="me-2">クラス</label>
	                        <select name="f2" class="form-select w-auto">
	                            <option value="0">------</option>
	                            <c:forEach var="classNum" items="${class_num_set }">
	                            	<option value="${classNum }">${classNum }</option>
	                            </c:forEach>
	                        </select>
	                    </div>
						<!-- 科目 -->
	                    <div class="d-flex align-items-center">
	                        <label class="me-2">科目</label>
	                        <select name="f3" class="form-select w-auto">
	                            <option value="0">------</option>
	                            <c:forEach var="subject" items="${subject_set }">
	                            	<option value="${subject.cd }">${subject.name }</option>
	                            </c:forEach>
	                        </select>
	                    </div>
						<!-- 回数 -->
	                    <div class="d-flex align-items-center">
	                        <label class="me-2">回数</label>
	                        <select name="f4" class="form-select w-auto">
	                            <option value="0">------</option>
	                            <c:forEach var="testNo" items="${test_no_set }">
	                            	<option value="${testNo }">${testNo }</option>
	                            </c:forEach>
	                        </select>
	                    </div>
	                    <!-- 検索ボタン -->
	                   	<button type="submit" class="btn btn-primary">検索</button>
                    </div>
                </form>

            </div>

        </section>
        <section class="me-4">

            <div class="container">
                <!-- 登録処理を行うサーブレットにPOST送信 -->
                <form action="RegisterServlet" method="post">

                    <!-- 科目名と回数を表示 -->
                    <div class="mb-3">
                        <div>科目：${subject}（${count}回）</div>
                    </div>

                    <!-- 成績一覧テーブル -->
                    <table class="table table-bordered">
                        <thead class="table-light">
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>点数</th>
                            </tr>
                        </thead>

                        <tbody>
                            <!-- scoreListのデータを1件ずつ取り出して表示 -->
                            <c:forEach var="s" items="${scoreList}">
                                <tr>
                                    <!-- 各学生の情報を表示 -->
                                    <td>${s.year}</td>
                                    <td>${s.classNo}</td>
                                    <td>${s.studentNo}</td>
                                    <td>${s.name}</td>

                                    <td>
                                        <!-- 点数入力欄-->
                                        <input type="text"
                                               name="point_${s.studentNo}"
                                               value="${s.point}"
                                               size="5">
                                    </td>

                                    <!-- 学生番号をサーバに送るためのhidden -->
                                    <input type="hidden" name="student_${s.studentNo}" value="${s.studentNo}">
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <!-- 画面で使っている情報をサーブレットへ送信 -->
                    <input type="hidden" name="subject" value="${subject}">
                    <input type="hidden" name="count" value="${count}">
                    <input type="hidden" name="classNo" value="${classNo}">

                    <div class="mt-3">
                        <!-- 登録ボタン（押すとRegisterServletへ送信） -->
                        <button type="submit" name="regist" class="btn btn-success">
                            登録して終了
                        </button>
                    </div>
                </form>

            </div>

        </section>
    </c:param>
</c:import>
