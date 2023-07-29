
/*

Lesson 16Chapter 15.5
入力内容のチェック（バリデーション）その１

最後に、サーブレット/JSP2 の「5 実践：問い合わせフォームの作成」 でも作ったような
入力値チェック（バリデーション）を実装しましょう。

バリデーションには、ブラウザ側に（JavaScriptで）実装する方法と、
サーバ側に（Javaで）実装する方法があります。ブラウザ側の方が単純ですがJavaScriptを扱う必要が生じるので、
サーバ側のバリデーションを作りましょう。

 */

package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Message;

public class MessageValidator {
    // バリデーションを実行する
    public static List<String> validate(Message m) {
        List<String> errors = new ArrayList<String>();

        String title_error = validateTitle(m.getTitle());
        if(!title_error.equals("")) {
            errors.add(title_error);
        }

        String content_error = validateContent(m.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        return errors;
    }

    // タイトルの必須入力チェック
    private static String validateTitle(String title) {
        if(title == null || title.equals("")) {
            return "タイトルを入力してください。";
        }

        return "";
    }

    // メッセージの必須入力チェック
    private static String validateContent(String content) {
        if(content == null || content.equals("")) {
            return "メッセージを入力してください。";
        }

        return "";
    }
}