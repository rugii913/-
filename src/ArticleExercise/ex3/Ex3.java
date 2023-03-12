package ArticleExercise.ex3;

import ArticleExercise.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//브라우저, DB 없이 명령어를 입력하는 방식으로 콘솔에서 운영되는 게시판이라고 가정
//1. 게시글 관련 기능(Article 혹은 Post), 2. 회원 관련 기능(Member)
public class Ex3 {
    public static void main(String[] args) {
        System.out.println("==프로그램 시작==");

        Scanner sc = new Scanner(System.in);
        // int articleId = lastArticleId + 1; 여기에 넣으면 "1번글이 생성되었습니다."만 나옴
        int lastArticleId = 0;
        List<Article> articles = new ArrayList<>();

        while (true) {
            //List<Article> articles = new ArrayList<>(); // 이게 여기 있으면 articles가 반복문 끝날 때 사라지고 계속 초기화됨
            System.out.print("명령어 >> ");
            String command = sc.nextLine().trim();

            if (command.length() == 0) {
                System.out.println("명령어를 입력해주세요.");
                continue;
            }

            if (command.equals("a66tmp")) {
                if (lastArticleId != 0) {
                    System.out.println("다른 글이 있을 경우 a66tmp는 사용할 수 없습니다.");
                    TmpArticles.isAddedByTempArticles = true;
                    continue;
                } else {
                    System.out.println("테스트용 데이터를 불러옵니다.");
                    TmpArticles.addTempArticles(articles);
                    TmpArticles.isAddedByTempArticles = true;
                    lastArticleId = articles.size();
                    continue;
                }
            }

            if (command.equals("exit")) { // 이런 코드는 따로 빼준다.
                break;
            }


            if (command.equals("article list")) {
                if (articles.size() == 0) {
                    System.out.println("게시글이 없습니다.");
                } else {
                    System.out.println("번호\t//\t제목\t//\t조회\t");
                    for (int i = articles.size() - 1; i>=0; i--) {
                        Article article = articles.get(i);
                        System.out.printf("%d\t//\t%s\t//\t%d\t\n", article.articleId, article.title, article.hits);
                    }
                }


            } else if (command.equals("article write")) {
                int articleId = lastArticleId + 1;
                System.out.print("제목: ");
                String title = sc.nextLine();
                System.out.print("내용: ");
                String body = sc.nextLine();
                String regDate = Util.getNowDateTimeStr();

                Article article = new Article(articleId, regDate, title, body);
                articles.add(article);

                System.out.printf("%d번글이 생성되었습니다.\n", article.articleId);
                lastArticleId++;


            } else if (command.startsWith("article detail")) {
                String[] cmdDiv = command.split(" ");
                if (cmdDiv.length != 3) { // 작성 예시 코드와 다른 부분 시작
                    System.out.println("명령어를 확인해주세요.");
                    continue;
                }
                int id; // 작성 예시 코드에는 없는 부분 시작
                try {
                    id = Integer.parseInt(cmdDiv[2]);
                } catch (NumberFormatException e) {
                    System.out.println("게시물 번호를 숫자로 입력해주세요.");
                    continue;
                } // 작성 예시 코드에는 없는 부분 끝

                // boolean found = false; // found 변수 활용하여 있는 경우, 없는 경우 구현 // foundArticle 들어오면서 필요 없어짐
                Article foundArticle = null;
                // (내가 작성해봤던 코드)int foundIndex = 0;

                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);
                    if (article.articleId == id) {
                        // found = true; // foundArticle 들어오면서 필요 없어짐
                        // (내가 작성해봤던 코드)foundIndex = articles.indexOf(article);
                        foundArticle = article;

                        break; // 찾으면 위 for문을 벗어나도록 해야함
                    }
                }

                if (foundArticle == null) {
                    System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                    continue;
                } // else {}
                // 바로 위 조건문에 continue; 추가해주면 else 날려버릴 수 있다.
                foundArticle.hits++;
                System.out.println("번호: " + foundArticle.articleId);
                System.out.println("날짜: " + foundArticle.regDate);
                System.out.println("수정 날짜: " + foundArticle.modDate);
                System.out.println("조회수: " + foundArticle.hits);
                System.out.println("제목: " + foundArticle.title);
                System.out.println("내용: " + foundArticle.body);
                // (내가 작성해봤던 코드)System.out.println("제목: " + articles.get(foundIndex).title); 이렇게도 가능하지만 낭비가 있는 느낌인 듯


            } else if (command.startsWith("article delete")) {
//            	String[] cmdDiv = command.split(" ");
//            	if (cmdDiv.length != 3) { // 작성 예시 코드와 다른 부분 시작
//            		System.out.println("명령어를 확인해주세요.");
//            		continue;
//            		}
//            	int id;
//				try {
//					id = Integer.parseInt(cmdDiv[2]);
//				} catch (NumberFormatException e) {
//					System.out.println("게시물 번호를 숫자로 입력해주세요.");
//					continue;
//				} // 작성 예시 코드에는 없는 부분 끝
//            	if (id > articles.size() || id <= 0) {
//            		System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
//            	} else {
//            		//
//            		articles.remove(id -1); // 삭제하면 인덱스와 게시물 번호가 불일치하는 문제 시작 // 인덱스 기준으로 작성된 다른 모든 코드 검토해야함
                // 결국 작성 예시코드 따라가야함
//            	}
                String[] cmdDiv = command.split(" ");
                if (cmdDiv.length != 3) { // 작성 예시 코드와 다른 부분 시작
                    System.out.println("명령어를 확인해주세요.");
                    continue;
                }
                int id; // 작성 예시 코드에는 없는 부분 시작
                try {
                    id = Integer.parseInt(cmdDiv[2]);
                } catch (NumberFormatException e) {
                    System.out.println("게시물 번호를 숫자로 입력해주세요.");
                    continue;
                } // 작성 예시 코드에는 없는 부분 끝

                // 작성 예시와 다르게 작성한 코드 시작
//            	Article foundArticle = null;
//
//            	for (int i = 0; i < articles.size(); i++) {
//            		Article article = articles.get(i);
//            		if (article.articleId == id) {
//            			foundArticle = article;
//            			break; // 찾으면 위 for문을 벗어나도록 해야함
//            		}
//            	}
//
//            	if (foundArticle == null) {
//					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
//					continue;
//				}
//            	articles.remove(foundArticle);
//            	System.out.println(foundArticle.articleId + "번 게시물이 삭제되었습니다.");
                // 작성 예시와 다르게 작성한 코드 끝
                int foundIndex = -1;
                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);
                    if (article.articleId == id) {
                        foundIndex = i;
                        break;
                    }
                }

                if (foundIndex == -1) {
                    System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                    continue;
                }

                articles.remove(foundIndex);
                System.out.println(id + "번 게시물이 삭제되었습니다.");


            } else if (command.startsWith("article modify")) {
                String[] cmdDiv = command.split(" ");
                if (cmdDiv.length != 3) { // 작성 예시 코드와 다른 부분 시작
                    System.out.println("명령어를 확인해주세요.");
                    continue;
                }
                int id; // 작성 예시 코드에는 없는 부분 시작
                try {
                    id = Integer.parseInt(cmdDiv[2]);
                } catch (NumberFormatException e) {
                    System.out.println("게시물 번호를 숫자로 입력해주세요.");
                    continue;
                } // 작성 예시 코드에는 없는 부분 끝

                Article foundArticle = null;

                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);
                    if (article.articleId == id) {
                        foundArticle = article;
                        break;
                    }
                }

                if (foundArticle == null) {
                    System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                    continue;
                }

                System.out.print("수정할 제목: ");
                String title = sc.nextLine();
                System.out.print("수정할 내용: ");
                String body = sc.nextLine();
                String modDate = Util.getNowDateTimeStr();
                foundArticle.title = title;
                foundArticle.body = body;
                foundArticle.modDate = modDate;
                System.out.println(foundArticle.articleId + "번 게시물이 수정되었습니다.");

            } else {
                System.out.println("존재하지 않는 명령어입니다.");
            }
        }

        System.out.println("==프로그램 끝==");

        sc.close(); // 자원 사용 종료를 위해 원칙적으로는 꺼줘야함
    }}

class Article {
    int articleId;
    String regDate;
    String modDate = "수정이력 없음";
    String title;
    String body;
    int hits;

    Article(int articleId, String regDate, String title, String body) {
        this.articleId = articleId;
        this.regDate = regDate;
        this.title = title;
        this.body = body;
    }

    public Article(int articleId, String regDate, String modDate, String title, String body, int hits) {
        this.articleId = articleId;
        this.regDate = regDate;
        this.modDate = modDate;
        this.title = title;
        this.body = body;
        this.hits = hits;
    }
}

class TmpArticles {
    static boolean isAddedByTempArticles = false;

    public static void addTempArticles(List articles) {
        articles.add(new Article(1, "2023-01-01 12:12:12", "수정이력 없음", "1번글 제목", "1번글 내용", 178));
        articles.add(new Article(2, "2023-01-02 12:12:12", "수정이력 없음", "2번글 제목", "2번글 내용", 16));
        articles.add(new Article(3, "2023-01-04 12:12:12", "2023-01-16 12:12:12", "3번글 제목", "3번글 내용", 135));
        articles.add(new Article(4, "2023-01-07 12:12:12", "수정이력 없음", "4번글 제목", "4번글 내용", 77));
        articles.add(new Article(5, "2023-01-07 12:12:12", "수정이력 없음", "5번글 제목", "5번글 내용", 122));
        articles.add(new Article(6, "2023-01-10 12:12:12", "수정이력 없음", "6번글 제목", "6번글 내용", 1054));
        articles.add(new Article(7, "2023-01-15 12:12:12", "수정이력 없음", "7번글 제목", "7번글 내용", 522));
        articles.add(new Article(8, "2023-01-16 12:12:12", "2023-01-25 12:12:12", "8번글 제목", "8번글 내용", 43));
        articles.add(new Article(9, "2023-01-18 12:12:12", "수정이력 없음", "9번글 제목", "9번글 내용", 20));
        articles.add(new Article(10, "2023-01-20 12:12:12", "2023-01-25 12:12:12", "10번글 제목", "10번글 내용", 70));
        articles.add(new Article(11, "2023-01-25 12:12:12", "수정이력 없음", "11번글 제목", "11번글 내용", 195));
        articles.add(new Article(12, "2023-01-31 12:12:12", "수정이력 없음", "12번글 제목", "12번글 내용", 372));
        articles.add(new Article(13, "2023-02-01 12:12:12", "수정이력 없음", "13번글 제목", "13번글 내용", 66));
    }
}