package is.projekt.is.mapper;

import is.projekt.is.model.Article;
import is.projekt.is.response.ArticleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleResponseMapper {

    private final TopicResponseMapper topicResponseMapper;

    @Autowired
    public ArticleResponseMapper(TopicResponseMapper topicResponseMapper) {
        this.topicResponseMapper = topicResponseMapper;
    }

    public ArticleResponse map(Article article){
        ArticleResponse response = new ArticleResponse();
        response.setTopicResponse(topicResponseMapper.map(article.getContent().getTopic()));
        response.setEmployeeId(article.getContent().getEmployee().getId());
        response.setEmployeeName(article.getContent().getEmployee().getFirstName() + " " +
                article.getContent().getEmployee().getLastName());
        response.setDate(article.getContent().getDate());
        response.setText(article.getContent().getText());
        response.setImage(article.getContent().getImage());
        response.setName(article.getContent().getName());
        response.setId(article.getId());
        response.setKeywords(article.getKeywords());
        return response;
    }

    public List<ArticleResponse> map(List<Article> articles){
        return articles.stream().map(this::map).collect(Collectors.toList());
    }
}
