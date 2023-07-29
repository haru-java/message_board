<%--
Lesson 16Chapter 9.2
indexのビューを作成

<c:import> を使うことで、url 属性に指定したファイルの内容をその位置で読み込むことができます。
<c:param name="content"> と書いたタグの中の記述内容が、
app.jsp の ${param.content} のところに当てはまります。

このあたりを復習したい方は サーブレット/JSP2の
「2.1 主なJSTLタグまとめ」にある「HTMLのテンプレート化」 を参照してください。


Lesson 16Chapter 15.3
フラッシュメッセージを出すその１
「登録が完了しました」のような文言を表示したい。
その文言を表示する場所を、リダイレクト先の index とし、index.jsp の「メッセージ一覧」と書かれた場所の上に、
以下の <c:if> タグを追記。
→そのあと/create、/update、/destroy の各サーブレットも修正しにいこう。
src/controllers/CreateServlet.javaへ。

Lesson 16Chapter 15.4
indexの表示件数を減らす（ページネーション）その３
「新規メッセージの投稿」の上に、以下の<div> タグを追記

 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">

    <c:if test="${flush != null}">
        <div id="flush_success">
             <c:out value="${flush}"></c:out>
        </div>
    </c:if>
<%--
文言を表示する場所
 --%>

        <h2>メッセージ一覧</h2>
        <ul>
            <c:forEach var="message" items="${messages}">
                <li>
                    <a href="${pageContext.request.contextPath}/show?id=${message.id}">
                        <c:out value="${message.id}" />
                    </a>
                    ：<c:out value="${message.title}"></c:out> &gt; <c:out value="${message.content}" />
                </li>
            </c:forEach>
        </ul>

        <%--
        ここから追加Lesson 16Chapter 15.4
         --%>
         <div id="pagination">
            （全 ${messages_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((messages_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/index?page=${i}"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <p><a href="${pageContext.request.contextPath}/new">新規メッセージの投稿</a></p>

    </c:param>
</c:import>