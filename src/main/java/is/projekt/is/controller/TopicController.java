package is.projekt.is.controller;

import is.projekt.is.mapper.TopicResponseMapper;
import is.projekt.is.request.TopicRequest;
import is.projekt.is.response.TopicResponse;
import is.projekt.is.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;
    private final TopicResponseMapper topicResponseMapper;

    @Autowired
    public TopicController(TopicService topicService, TopicResponseMapper topicResponseMapper) {
        this.topicService = topicService;
        this.topicResponseMapper = topicResponseMapper;
    }

    @GetMapping
    public List<TopicResponse> getAllTopics(){
        return topicResponseMapper.map(topicService.getAllTopics());
    }

    @GetMapping("/{id}")
    public TopicResponse getTopicById(@PathVariable Long id){
        return topicResponseMapper.map(topicService.getTopicById(id));
    }

    @PostMapping
    public TopicResponse createTopic(@RequestBody TopicRequest topicRequest){
        return topicResponseMapper.map(topicService.createTopic(topicRequest));
    }

    @PutMapping("/{id}")
    public TopicResponse updateTopic(@PathVariable Long id, @RequestBody TopicRequest topicRequest){
        return topicResponseMapper.map(topicService.updateTopic(id, topicRequest));
    }

    @DeleteMapping("/{id}")
    public Long deleteTopic(@PathVariable Long id){
        return topicService.deleteTopic(id);
    }
}
