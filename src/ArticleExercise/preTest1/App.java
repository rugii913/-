package ArticleExercise.preTest1;

import ArticleExercise.preTest1.controller.ArticleController;
import ArticleExercise.preTest1.controller.Controller;
import ArticleExercise.preTest1.controller.MemberController;

import java.util.Scanner;

public class App {
    public void start() {
        System.out.println("==프로그램 시작==");
        Scanner sc = new Scanner(System.in);

        Controller controller;
        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        articleController.makeTestData();
        memberController.makeTestData();

        while (true) {
            System.out.print("명령어 >>> ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                break;
            }

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력해주세요.");
                continue;
            }

            String[] cmdDiv = cmd.split(" ");
            if (cmdDiv.length == 1) {
                System.out.println("명령어를 확인해주세요.");
                continue;
            }

            String controllerName = cmdDiv[0];
            String actionMethodName = cmdDiv[1];
            String stringForLoginCheck = controllerName + "/" + actionMethodName;

            if (controllerName.equals("article")) {
                controller = articleController;
            } else if (controllerName.equals("member")) {
                controller = memberController;
            } else {
                System.out.println("존재하지 않는 명령어입니다.");
                continue;
            }

            switch (stringForLoginCheck) {
                case "article/write":
                case "article/modify":
                case "article/delete":
                case "member/logout":
                case "member/profile":
                    if (!Controller.isLogined()) {
                        System.out.println("로그인 후 이용해주세요.");
                        continue;
                    }
                    break;
                case "member/login":
                case "member/join":
                    if (Controller.isLogined()) {
                        System.out.println("로그아웃 후 이용해주세요.");
                        continue;
                    }
                    break;
            }

            controller.doAction(actionMethodName, cmd);
        }

        sc.close();
        System.out.println("==프로그램 종료==");
    }
}
