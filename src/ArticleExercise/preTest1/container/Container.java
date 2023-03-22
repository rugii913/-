package ArticleExercise.preTest1.container;

import ArticleExercise.preTest1.dao.ArticleDao;
import ArticleExercise.preTest1.dao.MemberDao;
import ArticleExercise.preTest1.service.ArticleService;
import ArticleExercise.preTest1.service.MemberService;

public class Container {
    // 주의 Dao보다 Service 먼저 생성하면 Null Pointer Exception
    public static ArticleDao articleDao = new ArticleDao();
    public static MemberDao memberDao = new MemberDao();
    public static ArticleService articleService = new ArticleService();
    public static MemberService memberService = new MemberService();
}
