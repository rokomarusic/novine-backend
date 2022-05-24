package is.projekt.is.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import is.projekt.is.exception.NotFoundException;
import is.projekt.is.model.Article;
import is.projekt.is.repository.ContentRepository;
import is.projekt.is.repository.EmployeeRepository;
import is.projekt.is.repository.TopicRepository;
import is.projekt.is.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static is.projekt.is.ArticleGenerator.*;
import static is.projekt.is.EmployeeGenerator.generateEmployee;
import static is.projekt.is.TopicGenerator.generateDefaultTopic;
import static is.projekt.is.mapper.ArticleResponseMapper.generateDefaultArticleRequest;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
@ActiveProfiles("h2")
public class ArticleControllerTest {

    private static final String ENDPOINT = "/articles";
    private static final String ENDPOINT_ID = ENDPOINT + "/1";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @MockBean
    private ContentRepository contentRepository;

    @MockBean
    private TopicRepository topicRepository;

    @MockBean
    private EmployeeRepository employeeRepository;

    private void setupResponse() {
        when(employeeRepository.getById(ArgumentMatchers.anyLong())).thenReturn(generateEmployee());
        when(contentRepository.getById(ArgumentMatchers.anyLong())).thenReturn(generateDefaultContent());
        when(topicRepository.getById(ArgumentMatchers.anyLong())).thenReturn(generateDefaultTopic());
    }

    @Test
    public void getAllArticlesTest() throws Exception {
        setupResponse();

        when(articleService.getAllArticles()).thenReturn(generateArticleList());

        mockMvc.perform(get(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void searchArticlesTest() throws Exception {
        setupResponse();

        when(articleService.getArticleLike(ArgumentMatchers.anyString())).thenReturn(generateArticleList());

        mockMvc.perform(get(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void getArticleByIdTestSuccessful() throws Exception {
        setupResponse();

        when(articleService.getArticleById(ArgumentMatchers.anyLong())).thenReturn(generateDefaultArticle());

        mockMvc.perform(get(ENDPOINT_ID)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void getArticleByIdNotFound() throws Exception {
        when(articleService.getArticleById(ArgumentMatchers.anyLong())).thenThrow(new NotFoundException());

        mockMvc.perform(get(ENDPOINT_ID)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void deleteArticleByIdTestSuccessful() throws Exception {
        setupResponse();

        when(articleService.deleteArticle(ArgumentMatchers.anyLong())).thenReturn(1L);

        mockMvc.perform(delete(ENDPOINT_ID)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void deleteArticleByIdNotFound() throws Exception {
        when(articleService.deleteArticle(ArgumentMatchers.anyLong())).thenThrow(new NotFoundException());

        mockMvc.perform(delete(ENDPOINT_ID)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void createArticleTestSuccessful() throws Exception {
        setupResponse();

        when(articleService.createArticle(ArgumentMatchers.any())).thenReturn(generateDefaultArticle());

        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(generateDefaultArticleRequest()))
        ).andExpect(status().isOk());
    }

    @Test
    public void createArticleTopicOrEmployeeNotFound() throws Exception {
        when(articleService.createArticle(ArgumentMatchers.any())).thenThrow(new NotFoundException());

        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(generateDefaultArticleRequest()))
        ).andExpect(status().isNotFound());
    }

    @Test
    public void updateArticleTestSuccessful() throws Exception {
        setupResponse();

        when(articleService.updateArticle(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(generateDefaultArticle());

        mockMvc.perform(put(ENDPOINT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(generateDefaultArticleRequest()))
        ).andExpect(status().isOk());
    }

    @Test
    public void updateArticleNotFound() throws Exception {
        when(articleService.updateArticle(ArgumentMatchers.any(), ArgumentMatchers.any())).thenThrow(new NotFoundException());

        mockMvc.perform(put(ENDPOINT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(generateDefaultArticleRequest()))
        ).andExpect(status().isNotFound());
    }
}
