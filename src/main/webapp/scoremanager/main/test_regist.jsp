<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">

            <div class="container">

                <h2>成績管理</h2>

                <form action="SearchServlet" method="post">

                    <div class="d-flex align-items-center mb-3">
                        <label class="me-2">入学年度</label>
                        <select name="f1" class="form-select w-auto">
                            <option value="">------</option>
                        </select>
                    </div>

                    <div class="d-flex align-items-center mb-3">
                        <label class="me-2">クラス</label>
                        <select name="f2" class="form-select w-auto">
                            <option value="">------</option>
                        </select>
                    </div>

                    <div class="d-flex align-items-center mb-3">
                        <label class="me-2">科目</label>
                        <select name="f3" class="form-select w-auto">
                            <option value="">------</option>
                        </select>
                    </div>

                    <div class="d-flex align-items-center mb-3">
                        <label class="me-2">回数</label>
                        <select name="f4" class="form-select w-auto">
                            <option value="">------</option>
                        </select>
                    </div>

                    <div class="mt-3">
                        <button type="submit" class="btn btn-primary">検索</button>
                    </div>

                </form>

            </div>

        </section>
    </c:param>
</c:import>
