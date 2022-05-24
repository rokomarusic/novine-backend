package is.projekt.is.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import is.projekt.is.exception.AlreadyExistsException;
import is.projekt.is.exception.NotFoundException;
import is.projekt.is.service.TopicService;
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

import static is.projekt.is.TopicGenerator.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
@ActiveProfiles("h2")
public class TopicControllerTest {

    private static final String ENDPOINT = "/topics";
    private static final String ENDPOINT_ID = ENDPOINT + "/1";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicService topicService;

    @Test
    public void getAllTopicsTest() throws Exception {
        when(topicService.getAllTopics()).thenReturn(generateTopicList());

        mockMvc.perform(get(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void getTopicByIdTestSuccessful() throws Exception {
        when(topicService.getTopicById(ArgumentMatchers.any())).thenReturn(generateDefaultTopic());

        mockMvc.perform(get(ENDPOINT + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getTopicByIdNotFound() throws Exception {
        when(topicService.getTopicById(ArgumentMatchers.any())).thenThrow(new NotFoundException());

        mockMvc.perform(get(ENDPOINT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteTopicSuccessful() throws Exception {
        when(topicService.deleteTopic(ArgumentMatchers.any())).thenReturn(1L);

        mockMvc.perform(delete(ENDPOINT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTopicNotFound() throws Exception {
        when(topicService.deleteTopic(ArgumentMatchers.any())).thenThrow(new NotFoundException());

        mockMvc.perform(delete(ENDPOINT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createTopicSuccessful() throws Exception {
        when(topicService.createTopic(ArgumentMatchers.any())).thenReturn(generateDefaultTopic());

        mockMvc.perform(post(ENDPOINT)
                        .content(objectMapper.writeValueAsString(generateTopicRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createTopicAlreadyExists() throws Exception {
        when(topicService.createTopic(ArgumentMatchers.any())).thenThrow(new AlreadyExistsException());

        mockMvc.perform(post(ENDPOINT)
                        .content(objectMapper.writeValueAsString(generateTopicRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateTopicSuccessful() throws Exception {
        when(topicService.updateTopic(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(generateDefaultTopic());

        mockMvc.perform(put(ENDPOINT_ID)
                        .content(objectMapper.writeValueAsString(generateTopicRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTopicAlreadyExists() throws Exception {
        when(topicService.updateTopic(ArgumentMatchers.any(), ArgumentMatchers.any())).thenThrow(new AlreadyExistsException());

        mockMvc.perform(put(ENDPOINT_ID)
                        .content(objectMapper.writeValueAsString(generateTopicRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateTopicIdNotFound() throws Exception {
        when(topicService.updateTopic(ArgumentMatchers.any(), ArgumentMatchers.any())).thenThrow(new NotFoundException());

        mockMvc.perform(put(ENDPOINT_ID)
                        .content(objectMapper.writeValueAsString(generateTopicRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
