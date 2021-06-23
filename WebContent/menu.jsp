<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/LibraryManagementSystem/CSS/styleMenu.css">
</head>
<body>
  <header>
    <a href="menu.jsp"><img src="logo.png" alt="ヘッダ背景"></a>
    ささき図書館
  </header>
<h1>メニュー</h1>
<h2>会員管理</h2>
<hr>
<a href="./memberManager/registerNewMember.jsp">新規会員登録</a>
<a href="./memberManager/searchMemberMember.jsp">検索（更新・削除）</a>
<h2>資料管理</h2>
<hr>
<a href="./documentManager/enterISBN.jsp">新規登録</a>
<a href="./documentManager/searchDocument.jsp">更新・廃棄処理</a>
<h2>貸出・返却</h2>
<a href="./lendRet/searchMemberLendRet.jsp">貸出・返却</a>
<a href="/LibraryManagementSystem/LendRetServlet?action=overdue">延滞者一覧</a>
<hr>
</body>
</html>
