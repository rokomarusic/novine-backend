package is.projekt.is.service;

import is.projekt.is.mapper.TopicRequestMapper;
import is.projekt.is.model.Topic;
import is.projekt.is.repository.TopicRepository;
import is.projekt.is.request.TopicRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicRequestMapper topicRequestMapper;

    public TopicService(TopicRepository topicRepository, TopicRequestMapper topicRequestMapper) {
        this.topicRepository = topicRepository;
        this.topicRequestMapper = topicRequestMapper;
    }

    public List<Topic> getAllTopics(){
        return topicRepository.findAll();
    }

    public Topic getTopicById(Long id){
        return topicRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Topic with the given id does not exist."));
    }

    public Topic createTopic(TopicRequest topicRequest){
        return topicRepository.save(topicRequestMapper.map(topicRequest, null));
    }

    public Topic updateTopic(Long id, TopicRequest topicRequest){
        if(!topicRepository.existsById(id)){
            throw new IllegalArgumentException("Topic with the given id does not exist.");
        }

        return topicRepository.save(topicRequestMapper.map(topicRequest, id));
    }

    public Long deleteTopic(Long id){
        if(!topicRepository.existsById(id)){
            throw new IllegalArgumentException("Topic with the given id does not exist.");
        }

        topicRepository.deleteById(id);
        return id;
    }
}
