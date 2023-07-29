<%--
Lesson 16Chapter 12
edit（編集画面）の作成その３
edit（編集画面）から。


_form.jsp で <input type="text" name="title" value="${message.title}" /> のように記述したため、
value="${message.title}" のおかげでデータベースに保存されていたメッセージやタイトルが
初期値としてテキストボックスに格納されます。


Lesson 16Chapter 14
destroy（削除処理）の作成その１

最後に削除機能を作成しましょう。

まず、edit.jsp に削除機能のリンクを貼ります。
一覧へ戻るリンクの下に追記.
削除機能を実行するフォームを作った。

 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>id : ${message.id} のメッセージ編集ページ</h2>

        <form method="POST" action="${pageContext.request.contextPath}/update">
            <c:import url="_form.jsp" />
        </form>

        <p><a href="${pageContext.request.contextPath}/index">一覧に戻る</a></p>

        <p><a href="#" onclick="confirmDestroy();">このメッセージを削除する</a></p>
        <form method="POST" action="${pageContext.request.contextPath}/destroy">
            <input type="hidden" name="_token" value="${_token}" />
        </form>
        <script>
        function confirmDestroy() {
            if(confirm("本当に削除してよろしいですか？")) {
                document.forms[1].submit();
            }
        }
        </script>
<%--
function ～の部分について。削除用に /update とは別のフォームを用意しています。
このフォームはJavaScriptで確認のウィンドウを表示した上で
「OK」がクリックされたらフォームを送信するようにしています。

 --%>

    </c:param>
</c:import>