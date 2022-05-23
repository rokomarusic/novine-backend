package is.projekt.is.service;

import is.projekt.is.exception.NotFoundException;
import is.projekt.is.mapper.ArticleRequestMapper;
import is.projekt.is.model.Article;
import is.projekt.is.model.Content;
import is.projekt.is.model.Employee;
import is.projekt.is.model.Topic;
import is.projekt.is.repository.ArticleRepository;
import is.projekt.is.repository.ContentRepository;
import is.projekt.is.repository.EmployeeRepository;
import is.projekt.is.repository.TopicRepository;
import is.projekt.is.request.ArticleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final TopicRepository topicRepository;
    private final EmployeeRepository employeeRepository;
    private final ContentRepository contentRepository;
    private final ArticleRequestMapper articleRequestMapper;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, TopicRepository topicRepository, EmployeeRepository employeeRepository, ContentRepository contentRepository, ArticleRequestMapper articleRequestMapper) {
        this.articleRepository = articleRepository;
        this.topicRepository = topicRepository;
        this.employeeRepository = employeeRepository;
        this.contentRepository = contentRepository;
        this.articleRequestMapper = articleRequestMapper;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Article with the given id does not exist."));
    }

    public Article createArticle(@Valid ArticleRequest articleRequest) {
        if (!topicRepository.existsById(articleRequest.getTopicId())) {
            throw new NotFoundException("Topic with the given id does not exist.");
        }

        if (!employeeRepository.existsById(articleRequest.getEmployeeId())) {
            throw new NotFoundException("Employee with the given id does not exist.");
        }

        Content content = saveContent(articleRequest, null);
        Article article = articleRequestMapper.map(articleRequest, content.getId());

        article = articleRepository.save(article);
        article.setContent(content);
        return article;
    }

    public Article updateArticle(Long id, @Valid ArticleRequest articleRequest) {
        if (!articleRepository.existsById(id)) {
            throw new NotFoundException("Article with the given id does not exist.");
        }

        if (articleRequest.getTopicId() != null && !topicRepository.existsById(articleRequest.getTopicId())) {
            throw new NotFoundException("Topic with the given id does not exist.");
        }

        if (!employeeRepository.existsById(articleRequest.getEmployeeId())) {
            throw new NotFoundException("Employee with the given id does not exist.");
        }

        Content content = saveContent(articleRequest, id);
        Article article = articleRequestMapper.map(articleRequest, id);

        article = articleRepository.save(article);
        article.setContent(content);
        return article;
    }

    public Long deleteArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new NotFoundException("Article with the given id does not exist.");
        }

        articleRepository.deleteById(id);
        contentRepository.deleteById(id);
        return id;
    }

    public List<Article> getArticleLike(String name){
        return articleRepository.getArticleByContentNameContainingIgnoreCase(name);
    }

    private Content saveContent(ArticleRequest articleRequest, Long id) {
        Content content = new Content();
        Employee employee = new Employee();
        employee.setId(articleRequest.getEmployeeId());
        Topic topic = new Topic();
        topic.setId(articleRequest.getTopicId());
        if (id != null) {
            content.setId(id);
        }
        content.setDate(articleRequest.getDate());
        content.setImage(articleRequest.getImage());
        content.setText(articleRequest.getText());
        content.setName(articleRequest.getName());
        content.setEmployee(employee);
        content.setTopic(topic);
        return contentRepository.save(content);
    }
}
