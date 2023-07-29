/*
Lesson 16Chapter 12
edit（編集画面）の作成その２
edit（編集画面）から。


リクエストスコープにメッセージのIDを入れた場合、このあと作成する /update へデータを送信する際に
 <input type="hidden"> を使ってメッセージIDの情報をフォームに追加する必要があります。
 しかし今回はセッションスコープへメッセージのIDの情報を保存して、
/update へ渡すことにしました。


Lesson 16Chapter 15.1
データが無かった場合に表示内容を変えるその３

request.getSession().setAttribute("message_id", m.getId());
の1行で、該当するIDのメッセージデータがない場合に
NullPointerExceptionが出るため、例外を回避するための修正。
「        // メッセージIDをセッションスコープに登録
        request.getSession().setAttribute("message_id", m.getId());」を変更
行を if で囲う。

→クエリ・パラメータとなる id=? の ? を「存在していないメッセージIDの数値」にしてアクセスします。
その結果「お探しのデータは見つかりませんでした」と表示される。
http://localhost:8080/message_board/show?id=999
みたいな感じ。
 */

package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        // 該当のIDのメッセージ1件のみをデータベースから取得
        Message m = em.find(Message.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        // メッセージ情報とセッションIDをリクエストスコープに登録
        request.setAttribute("message", m);
        request.setAttribute("_token", request.getSession().getId());

        // メッセージデータが存在しているときのみ
        // メッセージIDをセッションスコープに登録
        if(m != null) {
            request.getSession().setAttribute("message_id", m.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/edit.jsp");
        rd.forward(request, response);
    }
}