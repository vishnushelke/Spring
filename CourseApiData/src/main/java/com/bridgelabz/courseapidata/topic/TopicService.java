package com.bridgelabz.courseapidata.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

//	private List<Topic> topics = new ArrayList<>(Arrays.asList(
//
//			new Topic("Spring", "Spring framework", "Spring farmework Description"),
//			new Topic("history", "history framework", "history farmework Description"),
//			new Topic("math", "math framework", "math farmework Description")
//
//	));

	public List<Topic> getTopics() {
		ArrayList<Topic> topics = new ArrayList<Topic>();
		// ???
		topicRepository.findAll().forEach(topics::add);
		return topics;
	}

	public Topic getTopic(String id) {
		return topicRepository.findById(id).orElse(null);
	}

	public void addTopic(Topic topic) {
		topicRepository.save(topic);

	}

	public void updateTopic(String id, Topic topic) {
		topicRepository.save(topic);

	}

	public void deleteTopic(String id) {
		topicRepository.deleteById(id);
	}

}
