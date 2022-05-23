package is.projekt.is.service;

import is.projekt.is.model.Topic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TopicServiceTest {

    @Autowired
    private TopicService topicService;

    private static final String TOPIC_INSERTS = "classpath:db/topic_inserts.sql";

    private static final Long TEST_ID = 1L;

    @Test
    @Sql(TOPIC_INSERTS)
    public void testDelete(){
        Topic topic = topicService.getTopicById(TEST_ID);
        Long id = topicService.deleteTopic(TEST_ID);
        Assertions.assertEquals(id, TEST_ID);
    }
}
