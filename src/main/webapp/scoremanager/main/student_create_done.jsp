<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2>学生情報登録</h2>

            <p class="alert alert-success">登録が完了しました</p>

            <p><a href="student_register.jsp">戻る</a></p>

            <p><a href="StudentList.action">学生一覧</a></p>

        </section>
    </c:param>
</c:import>