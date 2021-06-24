<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="/LibraryManagementSystem/CSS/styleMenu.css">
  </head>
  <body>
    <header>
      <a href="/LibraryManagementSystem/menu.jsp"><img src="/LibraryManagementSystem/logo.png" alt="ヘッダ背景"></a>
      ささき図書館
    </header>
  <h1>会員情報を更新</h1>
  <table border="1">
    <tr>
      <td>ID</td><td>名前</td><td>住所</td><td>電話番号</td><td>メールアドレス</td><td>生年月日</td>
    </tr>
    <tr>
    <td>${member.id}</td><td>${member.name}</td><td>${member.address}</td><td>${member.tel}</td><td>${member.email}</td><td>${member.birthday}</td>
    </tr>
  </table>
  <hr>
    <form  action="/LibraryManagementSystem/MemberServlet?action=confirmMemberUpdate" method="post">
      <div class="textBox">
        <label class="ef">
      名前：　　　　　<input type="text" name="name" value=${member.name}><br>
      住所：　　　　　<input type="text" name="address" value=${member.address}><br>
      電話番号：　　　<input type="text" name="tel" value=${member.tel}><br>
      メールアドレス：<input type="text" name="email" value=${member.email}><br>
      生年月日：　　　<input type="text" class="dateText" name="year" value=${birthday[0]}>年<input type="text" class="dateText" name="month" value=${birthday[1]}>月<input type="text" class="dateText" name="date" value=${birthday[2]}>日<br>
    </label>
  </div>
      <input type="submit" class="button" value="確認画面へ">
    </form>
    <form action="/LibraryManagementSystem/memberManager/searchMemberResult.jsp" method="post">
      <input type="submit" class="button" value="戻る">
    </form>
</body>
</html>
