package is.projekt.is.mapper;

import is.projekt.is.model.Article;
import is.projekt.is.response.ArticleResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleResponseMapper {

    public ArticleResponse map(Article article){
        ArticleResponse response = new ArticleResponse();
        response.setContent(article.getContent());
        response.setId(article.getId());
        response.setKeywords(article.getKeywords());
        return response;
    }

    public List<ArticleResponse> map(List<Article> articles){
        return articles.stream().map(this::map).collect(Collectors.toList());
    }
}
