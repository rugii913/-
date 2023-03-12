package ArticleExercise.ex1;

import java.util.ArrayList;
import java.util.Scanner;

//브라우저, DB 없이 명령어를 입력하는 방식으로 콘솔에서 운영되는 게시판이라고 가정
// 1. 게시글 관련 기능(ArticleExercise.ex1.Article 혹은 Post), 2. 회원 관련 기능(ArticleExercise.ex1.Member)

public class ArticleEx1 { // 2023-03-08 19:10 작성 완료


    public static void main(String[] args) {
        System.out.println("==프로그램 시작==");

        Scanner sc = new Scanner(System.in);
        // int articleNum = finalArticleNum + 1; 여기에 넣으면 "1번글이 생성되었습니다."만 나옴
        ArrayList<Article> articles = new ArrayList<Article>();


        while (true) {
            System.out.print("명령어 >> ");
            String command = sc.nextLine();

            if (command.length() == 0) {
                System.out.println("명령어를 입력해주세요.");
                continue;
            }

            if (command.equals("exit")) { // 이런 코드는 따로 빼준다.
                break;
            }

            if (command.equals("article list")) {
                if (articles.size() == 0) {
                    System.out.println("게시글이 없습니다.");
                } else {
                    System.out.println("번호     /    제목");
                    for (int i = articles.size()-1; i>=0; i--){
                        int articleId = articles.get(i).articleId;
                        String title = articles.get(i).title;
                        System.out.printf("  %d     /    %s\n", articleId, title);
                    }
                }
            } else if (command.equals("article write")) {
                Article article = new Article();
                article.articleId = Article.lastArticleId + 1;
                ++Article.lastArticleId;

                System.out.print("제목: ");
                article.title = sc.nextLine();

                System.out.print("내용: ");
                article.body = sc.nextLine();

                articles.add(article);
                System.out.printf("%d번글이 생성되었습니다.\n", article.articleId);
            } else {
                System.out.println("존재하지 않는 명령어입니다.");
            }
        }

        System.out.println("==프로그램 끝==");

        sc.close(); // 자원 사용 종료를 위해 원칙적으로는 꺼줘야함
    }
}

class Article {
    static int lastArticleId = 0;
    int articleId;
    String title;
    String body;
}

class Member {

}