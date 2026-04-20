<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL(coreタグ)を使用するための宣言 -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- 共通レイアウト(base.jsp)の読み込み -->
<c:import url="/common/base.jsp" >

	<!-- 画面タイトルをbase.jspに渡す -->
	<c:param name="title">得点管理システム</c:param>

	<!-- JavaScript用の領域（今回は未使用） -->
	<c:param name="scripts"></c:param>

	<%-- メインコンテンツ部分の定義 --%>
	<c:param name="content">
		<section class="me=4">
			
			<!-- 見出し（Bootstrapのスタイルを使用） -->
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
				成績管理
			</h2>

			<!-- 登録完了メッセージ表示エリア -->
			<div style="
                background-color: #c8e6c9;
                border: 1px solid #8bc34a;
                padding: 10px 15px;
                font-size: 1.2rem;
                margin: 20px 0;">
                
                <!-- 登録成功時のメッセージ -->
                登録が完了しました。
            </div>

			<!-- 前の画面に戻るリンク（遷移先は未設定） -->
			<a href="">戻る</a>

			<!-- 成績参照画面へのリンク（遷移先は未設定） -->
			<a href="">成績参照</a>

		</section>
	</c:param>	
</c:import>