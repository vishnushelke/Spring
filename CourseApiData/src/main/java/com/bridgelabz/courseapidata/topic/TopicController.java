package com.bridgelabz.courseapidata.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

	@Autowired
	private TopicService topicService;

	@RequestMapping("/topics")
	public List<Topic> getAllTopic() {
		return topicService.getTopics();

	}
	
	@RequestMapping("/topics/{id}")//{foo}
	public Topic getTopic(@PathVariable String id)//@PathVariable("foo") String id
	{
		return topicService.getTopic(id);
	}
	
	@RequestMapping( method = RequestMethod.POST,value="/topics")
	public void addPost(@RequestBody Topic topic)//will get an instance of a Topic which is already available and add topic into it
	{
		topicService.addTopic(topic);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value="/topics/{id}")//{foo}
	public void deleteTopic(@PathVariable String id)//@PathVariable("foo") String id
	{
		topicService.deleteTopic(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT,value="/topics/{id}")
	public void updatePost(@RequestBody Topic topic,@PathVariable String id)//will get an instance of a Topic which is already available and update topic into it
	{
		topicService.updateTopic(id,topic);
	}

}
