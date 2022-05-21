package is.projekt.is.service;

import is.projekt.is.exception.AlreadyExistsException;
import is.projekt.is.exception.NotFoundException;
import is.projekt.is.mapper.TopicRequestMapper;
import is.projekt.is.model.Content;
import is.projekt.is.model.Topic;
import is.projekt.is.repository.TopicRepository;
import is.projekt.is.request.TopicRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicRequestMapper topicRequestMapper;

    public TopicService(TopicRepository topicRepository, TopicRequestMapper topicRequestMapper) {
        this.topicRepository = topicRepository;
        this.topicRequestMapper = topicRequestMapper;
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Topic getTopicById(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Topic with the given id does not exist."));
    }

    public Topic createTopic(TopicRequest topicRequest) {
        if(topicRepository.existsByName(topicRequest.getName())){
            throw  new AlreadyExistsException("Topic with the given name already exists");
        }

        return topicRepository.save(topicRequestMapper.map(topicRequest, null));
    }

    public Topic updateTopic(Long id, TopicRequest topicRequest) {
        if (!topicRepository.existsById(id)) {
            throw new NotFoundException("Topic with the given id does not exist.");
        }

        if(topicRepository.existsByNameAndIdNot(topicRequest.getName(), id)){
            throw  new AlreadyExistsException("Topic with the given name already exists");
        }

        return topicRepository.save(topicRequestMapper.map(topicRequest, id));
    }

    public Long deleteTopic(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Topic with the given id does not exist."));

        for(Content c: topic.getContentList()){
            c.setTopic(null);
        }

        topicRepository.save(topic);
        topic.setContentList(new ArrayList<>());
        topicRepository.delete(topic);

        return id;
    }
}
