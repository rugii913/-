package ArticleExercise.preTest1.controller;

import ArticleExercise.preTest1.container.Container;
import ArticleExercise.preTest1.dto.Article;
import ArticleExercise.preTest1.dto.Member;
import ArticleExercise.preTest1.service.ArticleService;
import ArticleExercise.preTest1.service.MemberService;
import ArticleExercise.preTest1.util.TimeUtil;

import java.util.List;
import java.util.Scanner;

public class ArticleController extends Controller {
    private Scanner sc;
    private String cmd;
    private ArticleService articleService;
    private MemberService memberService;

    public ArticleController(Scanner sc) {
        this.sc = sc;
        articleService = Container.articleService;
        memberService = Container.memberService;
    }

    @Override
    public void doAction(String actionMethodName, String cmd) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "write":
                doWrite();
                break;
            case "list":
                showList();
                break;
            case "detail":
                showDetail();
                break;
            case "modify":
                doModify();
                break;
            case "delete":
                doDelete();
                break;
            default:
                System.out.println("해당 기능은 사용할 수 없습니다.");
                break;
        }
    }

    private void doWrite() {
        int id = articleService.setNewId();
        System.out.print("제목: ");
        String regDate = TimeUtil.getLocalDateTime();
        String title = sc.nextLine();
        System.out.print("내용: ");
        String body = sc.nextLine();

        Article article = new Article(id, regDate, regDate, loginedMember.getId(), title, body);
        articleService.add(article);

        System.out.printf(id + "번 글이 생성되었습니다.");
    }

    private void showList() {
        String searchKeyword = cmd.substring("article list".length()).trim();

        List<Article> listForPrintArticles = articleService.getListForPrintArticles(searchKeyword);

        if (listForPrintArticles.size() == 0) {
            System.out.println("게시글이 없습니다.");
            return;
        }

        System.out.println("  번호  //  제목  //  조회  //  작성자  ");
        for (int i = listForPrintArticles.size() - 1; i >= 0; i--) {
            String writerName = null;

            List<Member> members = memberService.getMembers();
            Article article = listForPrintArticles.get(i);

            for (Member member : members) {
                if (article.getMemberId() == member.getId()) {
                    writerName = member.getName();
                    break;
                }
            }

            System.out.printf("  %d  //  %s  //  %d  //  %s  \n", article.getId(), article.getTitle(), article.getHit(), writerName);
        }
    }

    private void showDetail() {
        String[] cmdDiv = cmd.split(" ");

        if (cmdDiv.length < 3) {
            System.out.println("명령어를 확인해주세요.");
            return;
        }

        int id = Integer.parseInt(cmdDiv[2]);

        Article article = articleService.getArticleById(id);

        if (article == null) {
            System.out.println(id + "번 게시물은 존재하지 않습니다.");
            return;
        }

        String writerName = null;

        List<Member> members = memberService.getMembers();

        for (Member member : members) {
            if (article.getMemberId() == member.getId()) {
                writerName = member.getName();
                break;
            }
        }

        article.setNewHit();

        System.out.println("번호: " + article.getId());
        System.out.println("작성일: " + article.getRegDate());
        System.out.println("수정일: " + article.getUpdateDate());
        System.out.println("작성자: " + writerName);
        System.out.println("제목: " + article.getTitle());
        System.out.println("내용: " + article.getBody());
        System.out.println("조회수: " + article.getHit());
    }

    private void doModify() {
        String[] cmdDiv = cmd.split(" ");

        if (cmdDiv.length != 3) {
            System.out.println("명령어를 확인해주세요.");
            return;
        }

        int id = Integer.parseInt(cmdDiv[2]);

        Article article = articleService.getArticleById(id);

        if (article == null) {
            System.out.println(id + "번 게시물은 존재하지 않습니다.");
            return;
        }

        if (article.getMemberId() != loginedMember.getId()) {
            System.out.println("권한이 없습니다.");
            return;
        }

        System.out.print("새 제목: ");
        String newTitle = sc.nextLine();
        System.out.print("새 내용: ");
        String newBody = sc.nextLine();
        String updateDate = TimeUtil.getLocalDateTime();

        article.setTitle(newTitle);
        article.setBody(newBody);
        article.setUpdateDate(updateDate);

        System.out.println(id + "번 글을 수정했습니다.");
    }

    private void doDelete() {
        String[] cmdDiv = cmd.split(" ");

        if (cmdDiv.length != 3) {
            System.out.println("명령어를 확인해주세요.");
            return;
        }

        int id = Integer.parseInt(cmdDiv[2]);

        Article article = articleService.getArticleById(id);

        if (article == null) {
            System.out.println(id + "번 게시물은 존재하지 않습니다.");
            return;
        }

        if (article.getMemberId() != loginedMember.getId()) {
            System.out.println("권한이 없습니다.");
            return;
        }

        articleService.remove(article);
        System.out.println(id + "번 글을 삭제했습니다.");
    }

    @Override
    public void makeTestData() {
        System.out.println("게시글 테스트 데이터 생성");
        articleService.add(new Article(1, TimeUtil.getLocalDateTime(), TimeUtil.getLocalDateTime(), 3, "제목1", "내용1", 11));
        articleService.add(new Article(2, TimeUtil.getLocalDateTime(), TimeUtil.getLocalDateTime(), 3, "제목2", "내용2", 22));
        articleService.add(new Article(3, TimeUtil.getLocalDateTime(), TimeUtil.getLocalDateTime(), 2, "제목3", "내용3", 33));
    }
}
