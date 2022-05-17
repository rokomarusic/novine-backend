package is.projekt.is.mapper;

import is.projekt.is.model.Topic;
import is.projekt.is.request.TopicRequest;
import org.springframework.stereotype.Component;

@Component
public class TopicRequestMapper {

    public Topic map(TopicRequest topicRequest, Long id){
        Topic topic = new Topic();
        if(id != null){
            topic.setId(id);
        }
        topic.setName(topicRequest.getName());
        topic.setShortName(topicRequest.getShortName());

        return topic;
    }

}
