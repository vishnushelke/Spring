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

import com.bridgelabz.fundoo.note.dto.LabelDto;
import com.bridgelabz.fundoo.note.model.Response;
import com.bridgelabz.fundoo.note.services.ImplLabelService;

@RestController
@RequestMapping("/user/labels")
public class LabelController {

	@Autowired
	ImplLabelService service;

	/**
	 * purpose: This method is used for creating a label in a database of a
	 * particular user.
	 * 
	 * @param addLabelDto Data Transfer Object sent while creating a label
	 * @return Response according to the result
	 */
	@PostMapping
	public ResponseEntity<Response> createLabel(@RequestBody LabelDto addLabelDto) {
		return new ResponseEntity<Response>(service.createLabel(addLabelDto), HttpStatus.OK);
	}

	/**
	 * purpose: This method is used for displays label in a database of a particular
	 * user.
	 * 
	 * @param tokenLabelId of the user whose label to be displayed
	 * @return Response according to the result
	 */
	@GetMapping
	public ResponseEntity<Response> getLabel(@RequestHeader String tokenLabelId) {
		return new ResponseEntity<Response>(service.getLabel(tokenLabelId), HttpStatus.OK);
	}

	/**
	 * purpose: This method is used for updating a particular label in a database.
	 * 
	 * @param updateNoteDto Data Transfer Object sent while updating a label,tokenLabelId of a label
	 * @return Response according to the result
	 */
	@PutMapping
	public ResponseEntity<Response> updateLabel(@RequestHeader String tokenLabelId, @RequestBody LabelDto addLabelDto) {
		return new ResponseEntity<Response>(service.updateLabel(tokenLabelId, addLabelDto), HttpStatus.OK);
	}

	/**
	 * purpose: This method is used for deleting a particular label in a database.
	 * 
	 * @param tokenLabelId of the label which is to be deleted
	 * @return Response according to the result
	 */
	@DeleteMapping
	public ResponseEntity<Response> deleteLabel(@RequestHeader String tokenLabelId) {
		return new ResponseEntity<Response>(service.deleteLabel(tokenLabelId), HttpStatus.OK);
	}
}
