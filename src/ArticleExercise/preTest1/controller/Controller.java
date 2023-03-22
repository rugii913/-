package ArticleExercise.preTest1.controller;

import ArticleExercise.preTest1.dto.Member;

public abstract class Controller {
    protected static Member loginedMember = null;

    public static boolean isLogined() {
        return loginedMember != null;
    }

    public abstract void doAction(String actionMethodName, String command);

    public abstract void makeTestData();
}
