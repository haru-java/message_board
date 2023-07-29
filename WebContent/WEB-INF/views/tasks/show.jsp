<%--
Lesson 16Chapter 11
show（詳細画面）の作成その２

ビューとなる。

日情報は基本的にオブジェクトの形をしており、単純な文字列ではありません。
そこで <fmt:formatDate> タグで作成日時や更新日時を
pattern 属性で指定した 年-月-日 時:分:秒 の形式で表示するようにしています。


Lesson 16Chapter 12
edit（編集画面）の作成その２
edit（編集画面）から。show.jsp に edit へのリンクを貼りましょう。「一覧へ戻る」リンクの行の下に1行追加

Lesson 16Chapter 15.1
データが無かった場合に表示内容を変えるその１

該当するIDのメッセージデータが無かった場合に お探しのデータは見つかりませんでした。
 と表示させるように変更します。条件分岐をしたいので
<c:choose> および <c:when> 、 <c:otherwise> を使いましょう。

Lesson 16Chapter 15.2
画面の装飾その４cssの続き
タイトルとメッセージ、作成日時および変更日時の表示をテーブルでの表示に変更

 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${task != null}">
                <h2>id : ${task.id} のタスク詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>タスク</th>
                            <td><c:out value="${task.content}" /></td>
                        </tr>
                        <tr>
                            <th>作成日時</th>
                            <td><fmt:formatDate value="${task.created_at}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td><fmt:formatDate value="${task.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        </tr>
                    </tbody>
                </table>

                <p><a href="${pageContext.request.contextPath}/index">一覧に戻る</a></p>
                <p><a href="${pageContext.request.contextPath}/edit?id=${task.id}">このタスクを編集する</a></p>
            </c:when>

            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

    </c:param>
</c:import>