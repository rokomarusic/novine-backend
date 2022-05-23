package is.projekt.is.mapper;

import is.projekt.is.model.Article;
import is.projekt.is.model.Content;
import is.projekt.is.model.Employee;
import is.projekt.is.model.Topic;
import is.projekt.is.repository.ContentRepository;
import is.projekt.is.repository.EmployeeRepository;
import is.projekt.is.repository.TopicRepository;
import is.projekt.is.response.ArticleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleResponseMapper {

    private final TopicResponseMapper topicResponseMapper;
    private final ContentRepository contentRepository;
    private final TopicRepository topicRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ArticleResponseMapper(TopicResponseMapper topicResponseMapper, ContentRepository contentRepository, TopicRepository topicRepository, EmployeeRepository employeeRepository) {
        this.topicResponseMapper = topicResponseMapper;
        this.contentRepository = contentRepository;
        this.topicRepository = topicRepository;
        this.employeeRepository = employeeRepository;
    }

    public ArticleResponse map(Article article){
        ArticleResponse response = new ArticleResponse();
        Content content = contentRepository.getById(article.getId());
        Employee employee = null;
        Topic topic = null;
        if(content.getTopic() != null && topicRepository.existsById(content.getTopic().getId())){
            topic = topicRepository.getById(content.getTopic().getId());
        }
        if(content.getEmployee() != null && employeeRepository.existsById(content.getEmployee().getId())){
            employee = employeeRepository.getById(content.getEmployee().getId());
        }
        if(topic != null){
            response.setTopicResponse(topicResponseMapper.map(topic));
        }
        if(employee != null){
            response.setEmployeeId(employee.getId());
            response.setEmployeeName(employee.getFirstName() + " " + employee.getLastName());
        }
        response.setDate(content.getDate());
        response.setText(content.getText());
        response.setImage(content.getImage());
        response.setName(content.getName());
        response.setId(article.getId());
        response.setKeywords(article.getKeywords());
        return response;
    }

    public List<ArticleResponse> map(List<Article> articles){
        return articles.stream().map(this::map).collect(Collectors.toList());
    }
}
