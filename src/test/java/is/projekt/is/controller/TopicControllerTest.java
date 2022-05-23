package is.projekt.is.controller;

import is.projekt.is.model.Topic;
import is.projekt.is.service.TopicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class TopicControllerTest {

    private static final String ENDPOINT = "/topics";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicService topicService;

    @Test
    public void getAllTopicsTest() throws Exception {
        when(topicService.getAllTopics()).thenReturn(generateTopicList());

    }

    private List<Topic> generateTopicList(){
        List<Topic> topicList = new ArrayList<>();
        topicList.add(generateTopic(1L, "FIRST TOPIC MOCK", "T1"));
        topicList.add(generateTopic(2L, "SECOND TOPIC MOCK", "T2"));

        return topicList;
    }

    private Topic generateTopic(Long id, String name, String shortName){
        Topic topic = new Topic();
        topic.setId(id);
        topic.setName(name);
        topic.setShortName(shortName);

        return topic;
    }

}
