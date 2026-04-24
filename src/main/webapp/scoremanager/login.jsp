<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
	    得点管理システム
	</c:param>
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
 	<form action="LoginExecute.action" method="post" class="text-center">
		<div class="border mx-auto  mb-3 align-items-center"  style="width:75%;">
		    <h2 class="mb-4 py-2 bg-dark bg-opacity-10 border-top border-3" style="margin-top: 0;">ログイン</h2>
		    <!-- エラーテキスト -->
		    <c:if test="${not empty errortext}">
			    <ul>
			    	<li >
			    		<div class="text-center">${errortext }</div>
			    	</li>
			    </ul>
			</c:if>
			<!-- id -->
		    <div class="mb-3">
		    	
		    	<input type="text" name="id" placeholder="半角でご入力ください" value="${id }"
		             class="form-control mx-auto" style="max-width: 600px; height: 50px; padding-left: 2em;" maxlength="10" required>
		    </div>
		
		    <div class="mb-3">
		    <!-- パスワード -->
		    <input type="password" name="password" id="password-field" value="${password }"
		             placeholder="30文字以内の半角英数字でご入力ください"
		             class="form-control mx-auto" style="max-width: 600px; height: 50px; padding-left: 2em;" maxlength="30" required>
		    </div>
			<!-- パスワードを表示するチェックボックス -->
		    <div class="form-check mb-3 d-flex justify-content-center">
		      <input class="form-check-input" type="checkbox" id="chk_d_ps" name="chk_d_ps">
		      <label class="form-check-label ms-2" for="chk_d_ps">
		        パスワードを表示する
		      </label>
		    </div>
		    <div class="py-2">
		    <!-- ログインボタン -->
		      <input type="submit" value="ログイン" class="btn btn-primary w-25">
		    </div>
		</div>
	</form>
<!-- パスワードを表示の処理 -->
<script>
  const pwInput = document.getElementById('password-field');
  const toggleBtn = document.getElementById('chk_d_ps');

  // チェックボックスの状態が変わったら、typeを切り替える
  toggleBtn.addEventListener('change', function() {
    if (this.checked) {
      pwInput.type = 'text'; // テキストとして表示（見える！）
    } else {
      pwInput.type = 'password'; // 伏せ字に戻す（隠す！）
    }
    
  });
</script>
</c:param>

</c:import>
