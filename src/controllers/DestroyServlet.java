/*

Lesson 16Chapter 14
destroy（削除処理）の作成その２

基本的な流れは UpdateServlet と同様ですが、データベースからデータを削除する際は、
em.find で取得したオブジェクトを引数に入れて em.remove(); を実行し、
さらに em.getTransaction().commit(); でコミットする必要があります。

Lesson 16Chapter 15.3
フラッシュメッセージを出すその４

フラッシュメッセージをセッションスコープに保存し、
index.jsp を呼び出したときにセッションスコープから取り出して表示するようにします
em.getTransaction().commit(); のすぐ下に命令を追記

 */

package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class DestroyServlet
 */
@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            // セッションスコープからメッセージのIDを取得して
            // 該当のIDのメッセージ1件のみをデータベースから取得
            Task t = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));

            em.getTransaction().begin();
            em.remove(t);       // データ削除
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "削除が完了しました。");
            em.close();

            // セッションスコープ上の不要になったデータを削除
            request.getSession().removeAttribute("task_id");

            // indexページへリダイレクト
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }
}