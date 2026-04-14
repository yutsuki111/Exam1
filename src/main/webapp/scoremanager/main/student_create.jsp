<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>学生情報登録</h2>

<form action="studentInsert" method="post">

    
    <label>入学年度</label><br>

   
    <select name="ent_year">
        <c:forEach var="y" items="${entYearList}">
            <option value="${y}">${y}</option>
        </c:forEach>
    </select>
    <br><br>

    <label>学生番号</label><br>

    <input type="text"
           name="no"
           value="${no}"
           placeholder="学生番号を入力してください">
    <br><br>

    <label>氏名</label><br>

    <input type="text"
           name="name"
           value="${name}"
           placeholder="氏名を入力してください">
    <br><br>

    <label>クラス</label><br>

    <select name="class_num">
        <c:forEach var="c" items="${classNumList}">
            <option value="${c}">${c}</option>
        </c:forEach>
    </select>
    <br><br>

    <button type="submit" name="end" value="1">登録して終了</button>

</form>

<a href="studentList">戻る</a>