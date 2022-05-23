package is.projekt.is.controller;

import is.projekt.is.mapper.TopicResponseMapper;
import is.projekt.is.request.TopicRequest;
import is.projekt.is.response.TopicResponse;
import is.projekt.is.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<List<TopicResponse>> getAllTopics(){
        return ResponseEntity.ok(topicResponseMapper.map(topicService.getAllTopics()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> getTopicById(@PathVariable Long id){
        return ResponseEntity.ok(topicResponseMapper.map(topicService.getTopicById(id)));
    }

    @PostMapping
    public ResponseEntity<TopicResponse> createTopic(@RequestBody @Valid TopicRequest topicRequest){
        return ResponseEntity.ok(topicResponseMapper.map(topicService.createTopic(topicRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicResponse> updateTopic(@PathVariable Long id, @RequestBody TopicRequest topicRequest){
        return ResponseEntity.ok(topicResponseMapper.map(topicService.updateTopic(id, topicRequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTopic(@PathVariable Long id){
        return ResponseEntity.ok(topicService.deleteTopic(id));
    }
}
