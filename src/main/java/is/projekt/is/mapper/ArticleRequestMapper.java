package is.projekt.is.mapper;

import is.projekt.is.model.Article;
import is.projekt.is.model.Content;
import is.projekt.is.model.Employee;
import is.projekt.is.model.Topic;
import is.projekt.is.repository.ContentRepository;
import is.projekt.is.request.ArticleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleRequestMapper {


    @Autowired
    public ArticleRequestMapper() {
    }

    public Article map(ArticleRequest articleRequest, Long id){
        Article article = new Article();
        article.setKeywords(articleRequest.getKeywords());
        article.setId(id);

        return article;
    }
}
