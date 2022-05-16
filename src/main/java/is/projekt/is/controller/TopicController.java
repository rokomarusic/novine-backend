package is.projekt.is.controller;

import is.projekt.is.mapper.TopicResponseMapper;
import is.projekt.is.response.TopicResponse;
import is.projekt.is.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topic")
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


}
