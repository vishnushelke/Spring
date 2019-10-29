package com.bridgelabz.fundoo.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.note.dto.AddLabelDto;
import com.bridgelabz.fundoo.note.dto.CreateNoteDto;
import com.bridgelabz.fundoo.note.model.Response;
import com.bridgelabz.fundoo.note.services.ImplLabelService;

@RestController
@RequestMapping("/user/notes")
public class LabelController {
	
	@Autowired
	ImplLabelService service;
	
	@PostMapping
	public ResponseEntity<Response> createLabel(@RequestBody AddLabelDto addLabelDto)
	{
		return new ResponseEntity<Response>(service.createLabel(addLabelDto),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Response> getLabel(@RequestHeader String name)
	{
		return new ResponseEntity<Response>(service.getLabel(name),HttpStatus.OK);
	}
	@PutMapping
	public ResponseEntity<Response> updateLabel(@RequestHeader String name,@RequestHeader String updationName)
	{
		return new ResponseEntity<Response>(service.updateLabel(name, updationName),HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<Response> deleteLabel(@RequestHeader String name)
	{
		return new ResponseEntity<Response>(service.deleteLabel(name),HttpStatus.OK);
	}
}
