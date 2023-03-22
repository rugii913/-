package ArticleExercise.preTest1.controller;

import ArticleExercise.preTest1.container.Container;
import ArticleExercise.preTest1.dto.Member;
import ArticleExercise.preTest1.service.MemberService;
import ArticleExercise.preTest1.util.TimeUtil;

import java.util.Scanner;

public class MemberController extends Controller{
    private Scanner sc;
    private MemberService memberService;

    public MemberController(Scanner sc) {
        memberService = Container.memberService;
        this.sc = sc;
    }

    @Override
    public void doAction(String actionMethodName, String cmd) {
        switch (actionMethodName) {
            case "join":
                doJoin();
                break;
            case "login":
                doLogin();
                break;
            case "profile":
                showProfile();
                break;
            case "logout":
                doLogout();
                break;
            default:
                System.out.println("해당 기능은 사용할 수 없습니다.");
                break;
        }
    }

    private void doJoin() {
        int id = memberService.setNewId();
        String loginId = null;
        while (true) {
            System.out.println("로그인 아이디: ");
            loginId = sc.nextLine();

            if (!memberService.isJoinableLoginId(loginId)) {
                System.out.println("이미 사용 중인 아이디입니다.");
                continue;
            }
            break;
        }

        String loginPw = null;
        String loginPwConfirm = null;

        while (true) {
            System.out.print("로그인 비밀번호: ");
            loginPw = sc.nextLine();
            System.out.print("로그인 비밀번호 확인:");
            loginPwConfirm = sc.nextLine();

            if (!loginPw.equals(loginPwConfirm)) {
                System.out.println("비밀번호를 확인해주세요.");
                continue;
            }
            break;
        }

        System.out.print("이름: ");
        String name = sc.nextLine();

        String regDate = TimeUtil.getLocalDateTime();
        Member member = new Member(id, regDate, regDate, loginId, loginPw, name);
        memberService.add(member);

        System.out.println(id + "번 회원이 가입되었습니다.");

    }

    private void doLogin() {
        Member member = null;
        String loginId = null;
        String loginPw = null;

        while (true) {
            System.out.print("로그인 아이디: ");
            loginId = sc.nextLine();

            if (loginId.length() == 0) {
                System.out.println("아이디를 입력해주세요.");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("로그인 비밀번호: ");
            loginPw = sc.nextLine();

            if (loginPw.length() == 0) {
                System.out.println("비밀번호를 입력해주세요.");
                continue;
            }
            break;
        }

        member = memberService.getMemberByLoginId(loginId);

        if (member == null) {
            System.out.println("일치하는 회원이 없습니다.");
            return;
        }

        if (!member.getLoginPw().equals(loginPw)) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }

        loginedMember = member;
        System.out.println("로그인 성공!");
    }

    private void showProfile() {
        System.out.println("==현재 로그인 한 회원의 정보==");
        System.out.println("나의 회원번호: " + loginedMember.getId());
        System.out.println("로그인 아이디: " + loginedMember.getLoginId());
        System.out.println("이름: " + loginedMember.getName());
    }

    private void doLogout() {
        loginedMember = null;
        System.out.println("로그아웃 되었습니다.");
    }

    @Override
    public void makeTestData() {
        System.out.println("테스트 회원 데이터 생성");
        memberService.add(new Member(1, TimeUtil.getLocalDateTime(), TimeUtil.getLocalDateTime(), "test1", "test1", "1철수"));
        memberService.add(new Member(2, TimeUtil.getLocalDateTime(), TimeUtil.getLocalDateTime(), "test2", "test2", "2철수"));
        memberService.add(new Member(3, TimeUtil.getLocalDateTime(), TimeUtil.getLocalDateTime(), "test3", "test3", "3철수"));
    }
}
