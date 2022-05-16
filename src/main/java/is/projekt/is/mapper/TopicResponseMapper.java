package is.projekt.is.mapper;

import is.projekt.is.model.Topic;
import is.projekt.is.response.TopicResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TopicResponseMapper {

    public TopicResponse map(Topic topic){
        TopicResponse response = new TopicResponse();
        response.setId(topic.getId());
        response.setName(topic.getName());
        response.setShortName(topic.getShortName());

        return response;
    }

    public List<TopicResponse> map(List<Topic> topicList){
        return topicList.stream().map(this::map).collect(Collectors.toList());
    }
}
