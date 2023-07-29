
/*

Lesson 16Chapter 11
show（詳細画面）の作成

index ページのID番号に貼ったリンクをクリックすると、
該当のメッセージの詳細情報を表示するページを作成

JDBC3のレッスンの「2.4 Hibernateを使ってDBからデータを取得する」 で
作成したコードと同じ em.find() メソッドを利用
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

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/show")
public class ShowServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        // 該当のIDのメッセージ1件のみをデータベースから取得(どのようなデータもString型のデータとして取得)
        // String型の”1”を整数値の1に変えてから find メソッドの引数に...
        Task t = em.find(Task.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        // メッセージデータをリクエストスコープにセットしてshow.jspを呼び出す
        request.setAttribute("task", t);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/show.jsp");
        rd.forward(request, response);
    }
}