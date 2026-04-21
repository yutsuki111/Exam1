<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<!-- ページタイトル -->
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">

            <div class="container">
				<!-- 見出し -->
                <h2>成績管理</h2>
				<!-- 検索フォーム -->
                <form action="SearchServlet" method="post">
                	<!-- 全項目を横並びにする flex コンテナ -->
                	<div class="d-flex align-items-center flex-wrap gap-4">
                	
						<!-- 入学年度 -->
	                    <div class="d-flex align-items-center">
	                        <label class="me-2">入学年度</label>
	                        <select name="f1" class="form-select w-auto">
	                            <option value="0">------</option>
	                        </select>
	                    </div>
	                    <!-- クラス -->
		                <div class="d-flex align-items-center">
	                        <label class="me-2">クラス</label>
	                        <select name="f2" class="form-select w-auto">
	                            <option value="0">------</option>
	                        </select>
	                    </div>
						<!-- 科目 -->
	                    <div class="d-flex align-items-center">
	                        <label class="me-2">科目</label>
	                        <select name="f3" class="form-select w-auto">
	                            <option value="0">------</option>
	                        </select>
	                    </div>
						<!-- 回数 -->
	                    <div class="d-flex align-items-center">
	                        <label class="me-2">回数</label>
	                        <select name="f4" class="form-select w-auto">
	                            <option value="0">------</option>
	                        </select>
	                    </div>
	                    <!-- 検索ボタン -->
	                   	<button type="submit" class="btn btn-primary">検索</button>
                    </div>
                </form>

            </div>

        </section>
    </c:param>
</c:import>
