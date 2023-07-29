/*
Lesson 16Chapter 4.1参照
サーブレットの作成
Lesson 16Chapter 9.2
indexのビューを作成

Lesson 16Chapter 15.3
フラッシュメッセージを出すその５

セッションスコープに入れっぱなしだと、index にアクセスするたび表示されます。
一度限りの表示とするため、IndexServlet でセッションスコープからリクエストスコープに移し替え、
そのあとセッションスコープから除去。
問い合わせ結果をリクエストスコープにセットする行の下に、追記。


Lesson 16Chapter 15.4
indexの表示件数を減らす（ページネーション）その２
 List<Task> tasks…の部分を変更
 */


package controllers;
import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class IndexServlet
 */
@WebServlet({ "/IndexServlet", "/index" })
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        // 開くページ数を取得（デフォルトは1ページ目）※ここからページ数までChapter 15.4で変更
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) {}

        // 最大件数と開始位置を指定してメッセージを取得
        List<Task> tasks = em.createNamedQuery("getAllTasks", Task.class)
                                   .setFirstResult(15 * (page - 1))
                                   .setMaxResults(15)
                                   .getResultList();

        // 全件数を取得
        long tasks_count = (long)em.createNamedQuery("getTasksCount", Long.class)
                                      .getSingleResult();

        em.close();

        request.setAttribute("tasks", tasks);
        request.setAttribute("tasks_count", tasks_count);     // 全件数
        request.setAttribute("page", page);                         // ページ数

        // フラッシュメッセージがセッションスコープにセットされていたら
        // リクエストスコープに保存する（セッションスコープからは削除）
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/index.jsp");
        rd.forward(request, response);
    }

}