<%--
Lesson 16Chapter 9.3
newのビューを作成その２。
フォームの共通レイアウトになる。

次にビューを作りましょう。ここで使うフォームは edit でも利用したいので、
レイアウトファイルと同じように共通ファイル化しておきます。

タイトルとメッセージのテキストボックスに value="${message.title}" のような記述を入れました。
このあと作成する edit や、入力値エラーがあってフォームのページを再度表示する際に役立ちます。


Lesson 16Chapter 15.5
入力内容のチェック（バリデーション）その4
フォーム画面が表示された際にエラー内容を表示するように変更を加えましょう。
JSTLタグを利用しているため
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> の追加

 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>

<label for="content_msg">タスク</label><br />
<input type="text" name="content" id="content_msg" value="${task.content}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>

