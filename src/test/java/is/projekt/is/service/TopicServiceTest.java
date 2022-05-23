package is.projekt.is.service;

import is.projekt.is.exception.NotFoundException;
import is.projekt.is.model.Topic;

import is.projekt.is.repository.TopicRepository;
import is.projekt.is.request.TopicRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("h2")
public class TopicServiceTest {

    @Autowired
    private TopicService topicService;

    @MockBean
    private TopicRepository topicRepository;

    private static final String TOPIC_INSERTS = "classpath:db/topic_inserts.sql";

    private static final Long TEST_ID = 1L;

    @Test
    @Sql(TOPIC_INSERTS)
    public void testDeleteTopicExists(){
        Long id = topicService.deleteTopic(TEST_ID);
        Assertions.assertEquals(TEST_ID, id);
        Assertions.assertEquals(0, topicService.getAllTopics().size());
    }

    @Test
    public void testDeleteTopicDoesNotExist(){
        Assertions.assertThrows(NotFoundException.class,  () -> topicService.deleteTopic(TEST_ID));
    }

    @Test
    public void testCreateTopic(){
        TopicRequest request = generateTopicRequest();
    }

    public TopicRequest generateTopicRequest(){
        TopicRequest request = new TopicRequest();
        request.setName("TEST TOPIC");
        request.setShortName("TEST");
        return request;
    }
}
