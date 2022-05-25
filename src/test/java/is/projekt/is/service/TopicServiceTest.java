package is.projekt.is.service;

import is.projekt.is.exception.AlreadyExistsException;
import is.projekt.is.exception.NotFoundException;
import is.projekt.is.model.Topic;
import is.projekt.is.repository.TopicRepository;
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
import java.util.List;
import java.util.Optional;

import static is.projekt.is.TopicGenerator.*;
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

    @Test
    public void getAllTopics() {
        when(topicRepository.findAll()).thenReturn(generateTopicList());

        List<Topic> topicList = topicService.getAllTopics();

        Assertions.assertEquals(2, topicList.size());
        Assertions.assertEquals(1L, topicList.get(0).getId());
        Assertions.assertEquals(2L, topicList.get(1).getId());
        Assertions.assertEquals("TEST1", topicList.get(0).getName());
        Assertions.assertEquals("TEST2", topicList.get(1).getName());
        Assertions.assertEquals("T1", topicList.get(0).getShortName());
        Assertions.assertEquals("T2", topicList.get(1).getShortName());
    }

    @Test
    public void getTopicByIdSuccessful() {
        when(topicRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(generateDefaultTopic()));

        Topic topic = topicService.getTopicById(1L);
        Assertions.assertEquals(1L, topic.getId());
        Assertions.assertEquals("TEST1", topic.getName());
        Assertions.assertEquals("T1", topic.getShortName());
    }

    @Test
    public void getTopicByIdNotFound() {
        when(topicRepository.existsById(ArgumentMatchers.any())).thenReturn(false);

        Assertions.assertThrows(NotFoundException.class, () -> topicService.getTopicById(1L));
    }

    @Test
    public void createTopicSuccessful(){
        when(topicRepository.existsByName(ArgumentMatchers.any())).thenReturn(false);
        when(topicRepository.save(ArgumentMatchers.any())).thenReturn(generateDefaultTopic());

        Topic topic = topicService.createTopic(generateTopicRequest());
        Assertions.assertEquals(1L, topic.getId());
        Assertions.assertEquals("TEST1", topic.getName());
        Assertions.assertEquals("T1", topic.getShortName());
    }

    @Test
    public void createTopicAlreadyExists(){
        when(topicRepository.existsByName(ArgumentMatchers.any())).thenReturn(true);

        Assertions.assertThrows(AlreadyExistsException.class, () -> topicService.createTopic(generateTopicRequest()));
    }

    @Test
    public void updateTopicSuccessful(){
        when(topicRepository.existsById(ArgumentMatchers.anyLong())).thenReturn(true);
        when(topicRepository.existsByNameAndIdNot(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(false);
        when(topicRepository.save(ArgumentMatchers.any())).thenReturn(generateDefaultTopic());

        Topic topic = topicService.updateTopic(1L, generateTopicRequest());
        Assertions.assertEquals(1L, topic.getId());
        Assertions.assertEquals("TEST1", topic.getName());
        Assertions.assertEquals("T1", topic.getShortName());
    }

    @Test
    public void updateTopicAlreadyExists(){
        when(topicRepository.existsById(ArgumentMatchers.anyLong())).thenReturn(true);
        when(topicRepository.existsByNameAndIdNot(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(true);

        Assertions.assertThrows(AlreadyExistsException.class, () -> topicService.updateTopic(1L, generateTopicRequest()));
    }

    @Test
    public void updateTopicNotFound(){
        when(topicRepository.existsById(ArgumentMatchers.anyLong())).thenReturn(false);

        Assertions.assertThrows(NotFoundException.class, () -> topicService.updateTopic(1L, generateTopicRequest()));
    }

    @Test
    public void deleteTopicNotFound(){
        when(topicRepository.existsById(ArgumentMatchers.anyLong())).thenReturn(false);

        Assertions.assertThrows(NotFoundException.class, () -> topicService.deleteTopic(1L));
    }
}
