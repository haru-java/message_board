/*
Lesson 16Chapter 4.1
サーブレットの作成
Lesson 16Chapter 9.2
indexのビューを作成

http://localhost:8080/message_board/index

Lesson 16Chapter 15.3
フラッシュメッセージを出すその５

セッションスコープに入れっぱなしだと、index にアクセスするたび表示されます。
一度限りの表示とするため、IndexServlet でセッションスコープからリクエストスコープに移し替え、
そのあとセッションスコープから除去。
問い合わせ結果をリクエストスコープにセットする行の下に、追記。

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

import models.Message;
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

        List<Message> messages = em.createNamedQuery("getAllMessages", Message.class).getResultList();

        em.close();

        request.setAttribute("messages", messages);

        // フラッシュメッセージがセッションスコープにセットされていたら
        // リクエストスコープに保存する（セッションスコープからは削除）
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/index.jsp");
        rd.forward(request, response);
    }

}
