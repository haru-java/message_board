<%--
Lesson 16Chapter 9.1
レイアウトファイルの作成

画面は index new show edit の4つで必要になります。
各画面で共通している部分（ヘッダーやフッターなど）は
「共通のひな形（サイトデザインの大枠）」として用意しておくと修正が楽になります。

そこで、共通のひな形の役割をもつ「レイアウトファイル」を作成.
内容が大枠となり、 ${param.content} と書かれた部分に各ページのビューの内容が入ります。

Lesson 16Chapter 15.2
画面の装飾その１
CSSを追加して、もう少し見栄えのよいWebページにしましょう。

 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>メッセージボード</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>メッセージボード アプリケーション</h1>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                by Taro Kirameki.
            </div>
        </div>
    </body>
</html>










<%--
Lesson 16Chapter 9.1のところ
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>メッセージボード</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>メッセージボード アプリケーション</h1>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                by Taro Kirameki.
            </div>
        </div>
    </body>
</html>
 --%>