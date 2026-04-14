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
	<div class="row border mx-3 mb-3 py-2 align-items-center rounded ">
	    <h2 class="mb-4">ログイン</h2>
	
	    <div class="mb-3">
	      <input type="text" name="id" placeholder="id" 
	             class="form-control mx-auto" style="max-width: 250px;" required>
	    </div>
	
	    <div class="mb-3">
	      <input type="password" name="password" id="password-field"
	             placeholder="パスワード"
	             class="form-control mx-auto" style="max-width: 250px;" required>
	    </div>
	
	    <div class="form-check mb-3 d-flex justify-content-center">
	      <input class="form-check-input" type="checkbox" id="chk_ps">
	      <label class="form-check-label ms-2" for="chk_ps">
	        パスワードを表示する
	      </label>
	    </div>
	    <div>
	      <input type="submit" value="ログイン" class="btn btn-primary">
	    </div>
	</div>
</form>
<script>
  const pwInput = document.getElementById('password-field');
  const toggleBtn = document.getElementById('chk_ps');

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
<