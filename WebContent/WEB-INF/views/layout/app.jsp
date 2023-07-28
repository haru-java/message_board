<%--
Lesson 16Chapter 9.1
レイアウトファイルの作成

画面は index new show edit の4つで必要になります。
各画面で共通している部分（ヘッダーやフッターなど）は
「共通のひな形（サイトデザインの大枠）」として用意しておくと修正が楽になります。

そこで、共通のひな形の役割をもつ「レイアウトファイル」を作成.
内容が大枠となり、 ${param.content} と書かれた部分に各ページのビューの内容が入ります。
 --%>

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