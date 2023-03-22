package ArticleExercise.preTest1.dao;

import ArticleExercise.preTest1.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleDao extends Dao{
    private List<Article> articles;

    public ArticleDao() {
        articles = new ArrayList<>();
    }

    @Override
    public int getLastId() {
        return lastId;
    }

    public int setNewId() { // 이거 필요한가???????? -- 필요함...
        return lastId + 1;
    }

    public int getArticleIndexById(int id) {
        int i = 0;
        for (Article article : articles) {
            if (article.getId() == id) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public Article getArticleById(int id) {
        int index = getArticleIndexById(id);
        if (index == -1) {
            return null;
        }
        return articles.get(index);
    }

    public List<Article> getListForPrintArticles(String searchKeyword) {
        if (searchKeyword.length() != 0 && searchKeyword != null) {
            System.out.println("searchKeyword: " + searchKeyword);

            List<Article> listForPrintArticles = new ArrayList<>();

            for (Article article : articles) {
                if (article.getTitle().contains(searchKeyword)) {
                    listForPrintArticles.add(article);
                }
            }
            return listForPrintArticles;
        }
        return articles;
    }

    public void add(Article article) {
        articles.add(article);
        lastId++;
    }

    public void remove(Article article) {
        articles.remove(article);
    }
}