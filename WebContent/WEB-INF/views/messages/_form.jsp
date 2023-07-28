<%--
Lesson 16Chapter 9.3
newのビューを作成その２。
フォームの共通レイアウトになる、これ。
src/controllers/NewServlet.javaの変更からの、こちら。

次にビューを作りましょう。ここで使うフォームは edit でも利用したいので、
レイアウトファイルと同じように共通ファイル化しておきます。

タイトルとメッセージのテキストボックスに value="${message.title}" のような記述を入れました。
このあと作成する edit や、入力値エラーがあってフォームのページを再度表示する際に役立ちます。
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<label for="title">タイトル</label><br />
<input type="text" name="title" id="title" value="${message.title}" />
<br /><br />

<label for="content_msg">メッセージ</label><br />
<input type="text" name="content" id="content_msg" value="${message.content}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>