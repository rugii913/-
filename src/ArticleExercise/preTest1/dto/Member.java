package ArticleExercise.preTest1.dto;

public class Member {
    int id;
    String regDate;
    String updateDate;
    String loginId;
    String loginPw;
    String name;

    public Member(int id, String regDate, String updateDate, String loginId, String loginPw, String name) {
        this.id = id;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getLoginPw() {
        return loginPw;
    }

    public String getName() {
        return name;
    }
}
