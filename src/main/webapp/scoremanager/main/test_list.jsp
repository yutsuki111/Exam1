<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- JSTLを使うための宣言 -->
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!-- 共通レイアウト(base.jsp)を読み込む -->
<c:import url="/common/base.jsp">
    
    <!-- 画面タイトルをbase.jspに渡す -->
    <c:param name="title">得点管理システム</c:param>

    <!-- メインコンテンツ部分 -->
    <c:param name="content">
        <section class="me-4">

            <div class="container">

                <!-- 見出し -->
                <h2>成績管理</h2>

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