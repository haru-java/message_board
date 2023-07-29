/*
Lesson 16Chapter 10参照
create（挿入処理）の作成


if(_token != null && _token.equals(request.getSession().getId())) { ... }
でCSRF対策のチェックを行っています。_token に値がセットされていなかったり
セッションIDと値が異なったりしたらデータの登録ができないようにしています。

アクセス →「新規メッセージの投稿」→ タイトルとメッセージを入力して「投稿」、
までの一連の流れを試しましょう。

http://localhost:8080/tasklist/index



Lesson 16Chapter 15.3
フラッシュメッセージを出すその２

フラッシュメッセージをセッションスコープに保存し、
index.jsp を呼び出したときにセッションスコープから取り出して表示するようにします
em.getTransaction().commit(); のすぐ下に命令を追記


Lesson 16Chapter 15.5
入力内容のチェック（バリデーション）その２
m.setUpdated_at が記述されている行より下の部分を書き換え


 */


package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import models.validators.TaskValidator;
import utils.DBUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();
            em.getTransaction().begin();

            Task t = new Task();

            String content = request.getParameter("content");
            t.setContent(content);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            // 現在日時の情報を持つ日付型のオブジェクトを取得できます（Javaでは日時情報もオブジェクトで管理します）
            t.setCreated_at(currentTime);
            t.setUpdated_at(currentTime);

         // バリデーションを実行してエラーがあったら新規登録のフォームに戻る
            List<String> errors = TaskValidator.validate(t);
            if(errors.size() > 0) {
                em.close();

                // フォームに初期値を設定、さらにエラーメッセージを送る
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("task", t);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/new.jsp");
                rd.forward(request, response);
            } else {
                // データベースに保存
                em.persist(t);
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "登録が完了しました。");
                em.close();

                // indexのページにリダイレクト
                response.sendRedirect(request.getContextPath() + "/index");
            }
        }
    }
}

