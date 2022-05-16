package is.projekt.is.service;

import is.projekt.is.mapper.ArticleRequestMapper;
import is.projekt.is.model.Article;
import is.projekt.is.repository.ArticleRepository;
import is.projekt.is.repository.ContentRepository;
import is.projekt.is.repository.EmployeeRepository;
import is.projekt.is.repository.TopicRepository;
import is.projekt.is.request.ArticleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (articleRepository.existsById(id)) {
            return articleRepository.getById(id);
        } else {
            return null;
        }
    }

    public Article createArticle(ArticleRequest articleRequest) {
        if (!topicRepository.existsById(articleRequest.getTopicId())) {
            throw new IllegalArgumentException("Topic with the given id does not exist.");
        }

        if (!employeeRepository.existsById(articleRequest.getEmployeeId())) {
            throw new IllegalArgumentException("Employee with the given id does not exist.");
        }

        Article article = articleRequestMapper.map(articleRequest, null);

        return articleRepository.save(article);
    }

    public Article updateArticle(Long id, ArticleRequest articleRequest) {
        if (!articleRepository.existsById(id)){
            throw new IllegalArgumentException("Article with the given id already exists.");
        }

        if (!topicRepository.existsById(articleRequest.getTopicId())) {
            throw new IllegalArgumentException("Topic with the given id does not exist.");
        }

        if (!employeeRepository.existsById(articleRequest.getEmployeeId())) {
            throw new IllegalArgumentException("Employee with the given id does not exist.");
        }

        Article article = articleRequestMapper.map(articleRequest, id);

        return articleRepository.save(article);
    }

    public Long deleteArticle(Long id){
        if (!articleRepository.existsById(id)){
            throw new IllegalArgumentException("Article with the given id already exists.");
        }

        articleRepository.deleteById(id);
        contentRepository.deleteById(id);
        return id;
    }
}
