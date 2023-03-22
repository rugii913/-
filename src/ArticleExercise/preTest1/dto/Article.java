package ArticleExercise.preTest1.dto;

public class Article {
    int id;
    String regDate;
    String updateDate;
    String title;
    String body;
    int hit;
    int memberId;

    public Article(int id, String regDate, String updateDate, int memberId, String title, String body, int hit) {
        this.id = id;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.memberId = memberId;
        this.title = title;
        this.body = body;
        this.hit = hit;
    }

    public Article(int id, String regDate, String updateDate, int memberId, String title, String body) {
        this(id, regDate, updateDate, memberId, title, body, 0);
    }

    public int getId() {
        return id;
    }

    public String getRegDate() {
        return regDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getHit() {
        return hit;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setNewHit() {
        hit++;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
