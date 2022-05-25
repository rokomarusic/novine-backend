package is.projekt.is.service;

import is.projekt.is.exception.NotFoundException;
import is.projekt.is.model.Article;
import is.projekt.is.repository.ArticleRepository;
import is.projekt.is.repository.ContentRepository;
import is.projekt.is.repository.EmployeeRepository;
import is.projekt.is.repository.TopicRepository;
import is.projekt.is.request.ArticleRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static is.projekt.is.ArticleGenerator.*;
import static is.projekt.is.EmployeeGenerator.generateEmployee;
import static is.projekt.is.TopicGenerator.generateDefaultTopic;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("h2")
public class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @MockBean
    private ArticleRepository articleRepository;

    @MockBean
    private ContentRepository contentRepository;

    @MockBean
    private TopicRepository topicRepository;

    @MockBean
    private EmployeeRepository employeeRepository;

    /*private void setupResponse() {
        when(employeeRepository.getById(ArgumentMatchers.anyLong())).thenReturn(generateEmployee());
        when(contentRepository.getById(ArgumentMatchers.anyLong())).thenReturn(generateDefaultContent());
        when(topicRepository.getById(ArgumentMatchers.anyLong())).thenReturn(generateDefaultTopic());
    }*/

    @Test
    public void getAllArticlesTest(){
        when(articleRepository.findAll()).thenReturn(generateArticleList());

        List<Article> articleList = articleService.getAllArticles();

        Assertions.assertEquals(2, articleList.size());
        Assertions.assertEquals(articleList.get(0).getContent().getName(), "TEST1");
        Assertions.assertEquals(articleList.get(1).getContent().getName(), "TEST2");
        Assertions.assertEquals(articleList.get(0).getContent().getText(), "TEST TEXT 1");
        Assertions.assertEquals(articleList.get(1).getContent().getText(), "TEST TEXT 2");
    }
    @Test
    public void searchArticlesTest(){
        when(articleRepository.searchByName(ArgumentMatchers.any())).thenReturn(new ArrayList<>(generateArticleList()));

        List<Article> articleList = articleService.getArticleLike("%tEst%");

        Assertions.assertEquals(2, articleList.size());
        Assertions.assertEquals(articleList.get(0).getContent().getName(), "TEST1");
        Assertions.assertEquals(articleList.get(1).getContent().getName(), "TEST2");
        Assertions.assertEquals(articleList.get(0).getContent().getText(), "TEST TEXT 1");
        Assertions.assertEquals(articleList.get(1).getContent().getText(), "TEST TEXT 2");
    }

    @Test
    public void getArticleByIdSuccessful(){
        when(articleRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(generateDefaultArticle()));

        Article article = articleService.getArticleById(1L);

        Assertions.assertEquals(article.getContent().getName(), "TEST1");
        Assertions.assertEquals(article.getContent().getText(), "TEST TEXT 1");
    }

    @Test
    public void getArticleNotFound(){
        when(articleRepository.getById(ArgumentMatchers.any())).thenThrow(new NotFoundException());

        Assertions.assertThrows(NotFoundException.class, () -> articleService.getArticleById(1L));
    }

    @Test
    public void deleteArticleNotFound(){
        when(articleRepository.existsById(ArgumentMatchers.any())).thenThrow(new NotFoundException());

        Assertions.assertThrows(NotFoundException.class, () -> articleService.deleteArticle(1L));
    }

    @Test
    public void createArticleSuccessful(){
        when(articleRepository.save(ArgumentMatchers.any())).thenReturn(generateDefaultArticle());
        when(employeeRepository.existsById(ArgumentMatchers.anyLong())).thenReturn(true);
        when(topicRepository.existsById(ArgumentMatchers.anyLong())).thenReturn(true);
        when(contentRepository.save(ArgumentMatchers.any())).thenReturn(generateDefaultContent());

        Article article = articleService.createArticle(generateArticleRequest());

        Assertions.assertEquals(article.getContent().getName(), "TEST1");
        Assertions.assertEquals(article.getContent().getText(), "TEST TEXT 1");
    }

    @Test
    public void createArticleEmployeeNotFound(){
        when(topicRepository.existsById(ArgumentMatchers.anyLong())).thenReturn(true);
        when(employeeRepository.existsById(ArgumentMatchers.anyLong())).thenReturn(false);

        Assertions.assertThrows(NotFoundException.class, () -> articleService.createArticle(generateArticleRequest()));
    }

    @Test
    public void updateArticleSuccessful(){
        when(articleRepository.save(ArgumentMatchers.any())).thenReturn(generateDefaultArticle());
        when(articleRepository.existsById(ArgumentMatchers.anyLong())).thenReturn(true);
        when(employeeRepository.existsById(ArgumentMatchers.anyLong())).thenReturn(true);
        when(topicRepository.existsById(ArgumentMatchers.anyLong())).thenReturn(true);
        when(contentRepository.save(ArgumentMatchers.any())).thenReturn(generateDefaultContent());

        Article article = articleService.updateArticle(1L, generateArticleRequest());

        Assertions.assertEquals(article.getContent().getName(), "TEST1");
        Assertions.assertEquals(article.getContent().getText(), "TEST TEXT 1");
    }

    @Test
    public void updateArticleNotFound(){
        when(articleRepository.existsById(ArgumentMatchers.anyLong())).thenReturn(false);

        Assertions.assertThrows(NotFoundException.class, () -> articleService.updateArticle(1L, generateArticleRequest()));
    }

}
