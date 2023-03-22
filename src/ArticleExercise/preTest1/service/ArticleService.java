package ArticleExercise.preTest1.service;

import ArticleExercise.preTest1.container.Container;
import ArticleExercise.preTest1.dao.ArticleDao;
import ArticleExercise.preTest1.dto.Article;

import java.util.List;


public class ArticleService {
    private ArticleDao articleDao;

    public ArticleService() {
        this.articleDao = Container.articleDao;
    }

    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }

    public List<Article> getListForPrintArticles(String searchKeyword) {
        return articleDao.getListForPrintArticles(searchKeyword);
    }

    public int setNewId() {
        int id = articleDao.setNewId();
        return id;
    }

    public void add(Article article) {
        articleDao.add(article);
    }

    public void remove(Article article) {
        articleDao.remove(article);
    }
}
