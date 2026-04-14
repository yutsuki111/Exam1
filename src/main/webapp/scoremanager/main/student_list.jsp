<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>科目管理</title>
    <style>
        body { font-family: sans-serif; }
        .header { background: #eee; padding: 10px; }
        .menu { float: left; width: 180px; background: #f7f7f7; padding: 10px; }
        .content { margin-left: 200px; padding: 20px; }
        table { border-collapse: collapse; width: 600px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
        th { background: #ddd; }
        .footer { margin-top: 40px; font-size: 12px; color: #666; }
    </style>
</head>
<body>

<div class="header">
    <span style="font-size: 20px;">得点管理システム</span>
    <a href="Logout.action">ログアウト</a>
</div>

<div class="menu">
    <h3>メニュー</h3>
    <ul>
        <li><a href="StudentList.action">学生管理</a></li>
        <li><a href="ScoreList.action">成績管理</a></li>
        <li><a href="ScoreEntry.action">成績登録</a></li>
        <li><a href="ScoreView.action">成績参照</a></li>
        <li><a href="SubjectList.action">科目管理</a></li>
    </ul>
</div>

<div class="content">
    <h2>科目管理</h2>

    <p><a href="SubjectAdd.action">新規登録</a></p>

    <table>
        <tr>
            <th>科目コード</th>
            <th>科目名</th>
            <th>変更</th>
            <th>削除</th>
        </tr>

        <!-- subjects はサーブレットやアクションから渡されるリスト -->
        <c:forEach var="sub" items="${subjectList}">
            <tr>
                <td>${sub.code}</td>
                <td>${sub.name}</td>
                <td><a href="SubjectEdit.action?code=${sub.code}">変更</a></td>
                <td><a href="SubjectDelete.action?code=${sub.code}"
                       onclick="return confirm('削除してよろしいですか？');">削除</a></td>
            </tr>
        </c:forEach>
    </table>

</div>

</body>
</html>