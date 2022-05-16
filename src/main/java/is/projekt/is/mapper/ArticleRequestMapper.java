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

    private final ContentRepository contentRepository;

    @Autowired
    public ArticleRequestMapper(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Article map(ArticleRequest articleRequest, Long id){
        Article article = new Article();
        Content content = new Content();
        Employee employee = new Employee();
        employee.setId(articleRequest.getEmployeeId());
        Topic topic = new Topic();
        topic.setId(articleRequest.getTopicId());
        if(id != null){
            content.setId(id);
        }
        content.setDate(articleRequest.getDate());
        content.setImage(articleRequest.getImage());
        content.setText(articleRequest.getText());
        content.setName(articleRequest.getName());
        content.setEmployee(employee);
        content.setTopic(topic);
        content = contentRepository.save(content);
        //article.setContent(content);
        article.setKeywords(articleRequest.getKeywords());
        article.setId(content.getId());

        return article;
    }
}
