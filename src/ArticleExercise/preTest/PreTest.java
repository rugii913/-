package ArticleExercise.preTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ArticleExercise.preTest.Time.getNowDateTimeStr;

public class PreTest {
    static List<Article> articles  = new ArrayList<>();
    static int lastArticleId = 3;


    public static void main(String[] args) {
        System.out.println("===프로그램 시작===");
        Scanner sc = new Scanner(System.in);

        makeTestData();

        while (true) {
            System.out.print("명령어 >>> ");
            String command = sc.nextLine().trim();

            if (command.length() == 0) {
                System.out.println("명령어를 입력해주세요.");
                continue;
            }
            //
            //
            if (command.equals("exit")) {
                break;
            }
            //
            //
            if (command.equals("article write")) {
                System.out.print("제목: ");
                String title = sc.nextLine();
                System.out.print("내용: ");
                String body = sc.nextLine();

                String regDate = "~~~~~~~~~~~~~~~~~"; /////////////////////////////////////////
                int id = ++lastArticleId;

                articles.add(new Article(id, title, body, regDate, regDate));
                System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
                //
                //
            } else if (command.startsWith("article list")) {
                if (articles.size() == 0) {
                    System.out.println("게시글이 없습니다.");
                    continue;
                }

                List<Article> listedArticles = articles;
                String keyword = command.substring("article list".length()).trim();

                if (keyword.length() > 0) {
                    System.out.printf("검색키워드: %s\n", keyword);

                    listedArticles = new ArrayList<>();

                    for (Article article : articles) {
                        if (article.title.contains(keyword)) {
                            listedArticles.add(article);
                        }
                    }

                    if(listedArticles.size() == 0) {
                        System.out.println("검색 결과가 없습니다.");
                        continue;
                    }
                    // ~~~~ 위에서 keyword.length() > 0이 아니라 keyword != ""로 입력하면 NullPointerException 발생
                    // 그대로 keyword != ""로 두고 listedArticles = null;이 아니라 new ArrayList<>();로 바꾸는 걸로 해결 가능하긴 함
                }

                System.out.println(" 번호 // 제목 // 조회수  ");
                for (int i = listedArticles.size()-1; i >= 0; i--) {
                    Article article = listedArticles.get(i);
                    System.out.printf(" %d // %s // %d  \n", article.id, article.title, article.hits);
                }
                //
                //
            } else if (command.startsWith("article detail")) {
                int id = splitAndExtract(command);
                if (id == -1) continue;
                Article foundArticle = getArticleById(id);
                if (foundArticle == null) continue;
                foundArticle.hits++;

                System.out.printf("글번호: %d\n", foundArticle.id);
                System.out.printf("작성날짜: %s\n", foundArticle.regDate);
                System.out.printf("수정날짜: %s\n", foundArticle.modDate);
                System.out.printf("제목: %s\n", foundArticle.title);
                System.out.printf("내용: %s\n", foundArticle.body);
                //
                //
            } else if (command.startsWith("article modify")) {
                int id = splitAndExtract(command);
                Article foundArticle = getArticleById(id);

                System.out.print("수정할 제목: ");
                foundArticle.title = sc.nextLine();
                System.out.println();
                System.out.print("수정할 내용: ");
                foundArticle.body = sc.nextLine();
                System.out.println();
                foundArticle.modDate = "~~~수정일자~~~"; /////////////////////////////////////
                System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
                //
                //
            } else if (command.startsWith("article delete")) {
                int id = splitAndExtract(command);
                int foundArticleIndex = getArticleIndexById(id);
                articles.remove(foundArticleIndex);
                //
                //
            } else if (command.equals("member join")) {
                System.out.println("아직 구현되지 않은 기능");
                //
                //
            } else {
                System.out.println("존재하지 않는 명령어입니다. 명령어를 다시 입력해주세요.");
            }
        }

        sc.close();
        System.out.println("===프로그램 종료===");
    }


    static void makeTestData() {
        System.out.println("테스트 데이터를 불러옵니다.");
        articles.add(new Article(1, getNowDateTimeStr(), getNowDateTimeStr(),"제목1", "제목1", 11));
        articles.add(new Article(2, getNowDateTimeStr(), getNowDateTimeStr(), "제목2", "제목2", 22));
        articles.add(new Article(3, getNowDateTimeStr(), getNowDateTimeStr(), "제목3", "제목3", 33));
    }

    static int splitAndExtract(String command) {
        String[] cmdDiv = command.split(" ");
        int id;

        if (cmdDiv.length != 3) {
            System.out.println("명령어를 다시 입력해주세요.");
            return -1;
        }

        try {
            id = Integer.parseInt(cmdDiv[2]);
        } catch (NumberFormatException e) {
            System.out.println("게시물의 번호를 숫자로 입력해주세요.");
            return -1;
        }

        return id;
    }

    static int getArticleIndexById (int id) {
        int i = 0;
        for (Article article : articles) {
            if (article.id == id) {
                return i;
            }
            i++;
        }
        System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
        return -1;
    }

    static Article getArticleById (int id) {
        int index = getArticleIndexById(id);
        if (index == -1) {
            return null;
        }
        return articles.get(index);
    }
}

class Article {
    int id;
    String title;
    String body;
    String regDate;
    String modDate;
    int hits;

    Article(int id, String title, String body, String regDate, String modDate, int hits) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.regDate = regDate;
        this.modDate = modDate;
        this.hits = hits;
    }

    Article(int id, String title, String body, String regDate, String modDate) {
        this(id, title, body, regDate, modDate, 0);
    }
}

class Member {
    int id;
    String title;
    String body;
    String regDate;
    String modDate;
    int hits;
}

class Time {
    static String getNowDateTimeStr() {
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return formatedNow;
    }
}