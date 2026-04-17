<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">

            <div class="container">

                <h2>成績管理</h2>

                <form action="RegisterServlet" method="post">

                    <div class="mb-3">
                        <div>科目：${subject}（${count}回）</div>
                    </div>

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
                            <c:forEach var="s" items="${scoreList}">
                                <tr>
                                    <td>${s.year}</td>
                                    <td>${s.classNo}</td>
                                    <td>${s.studentNo}</td>
                                    <td>${s.name}</td>

                                    <td>
                                        <input type="text"
                                               name="point_${s.studentNo}"
                                               value="${s.point}"
                                               size="5">
                                    </td>

                                    <input type="hidden" name="student_${s.studentNo}" value="${s.studentNo}">
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <input type="hidden" name="subject" value="${subject}">
                    <input type="hidden" name="count" value="${count}">
                    <input type="hidden" name="classNo" value="${classNo}">

                    <div class="mt-3">
                        <button type="submit" name="regist" class="btn btn-success">
                            登録して終了
                        </button>
                    </div>
                </form>

            </div>

        </section>
    </c:param>
</c:import>
