/******************************************************************************
*  Purpose: This is a controller class of label
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   30-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.user.note.dto.LabelDto;
import com.bridgelabz.fundoo.user.note.service.ImplLabelService;
import com.bridgelabz.fundoo.user.response.Response;


@RestController
@CrossOrigin
@RequestMapping("/user/labels")
public class LabelController {

	@Autowired
	private ImplLabelService service;

	/**
	 * purpose: This method is used for creating a label in a database of a
	 * particular user.
	 * 
	 * @param addLabelDto Data Transfer Object sent while creating a label
	 * @return Response according to the result
	 */
	@PostMapping
	public ResponseEntity<Response> createLabel(@RequestBody LabelDto addLabelDto,@RequestHeader String tokenUserId) {
		System.out.println("ahklj");
		return new ResponseEntity<Response>(service.createLabel(addLabelDto,tokenUserId), HttpStatus.OK);
	}

	/**
	 * purpose: This method is used for displays label in a database of a particular
	 * user.
	 * 
	 * @param tokenLabelId of the user whose label to be displayed
	 * @return Response according to the result
	 */
	@GetMapping
	public ResponseEntity<Response> getLabel(@RequestHeader String tokenUserId) {
		return new ResponseEntity<Response>(service.getLabel(tokenUserId), HttpStatus.OK);
	}

	/**
	 * purpose: This method is used for updating a particular label in a database.
	 * 
	 * @param updateNoteDto Data Transfer Object sent while updating a label,tokenLabelId of a label
	 * @return Response according to the result
	 */
	@PutMapping
	public ResponseEntity<Response> updateLabel(@RequestHeader int labelId, @RequestBody LabelDto addLabelDto,@RequestHeader String tokenUserId) {
		return new ResponseEntity<Response>(service.updateLabel(labelId, addLabelDto,tokenUserId), HttpStatus.OK);
	}

	/**
	 * purpose: This method is used for deleting a particular label in a database.
	 * 
	 * @param tokenLabelId of the label which is to be deleted
	 * @return Response according to the result
	 */
	@DeleteMapping
	public ResponseEntity<Response> deleteLabel(@RequestHeader int labelId,@RequestHeader String tokenUserId) {
		return new ResponseEntity<Response>(service.deleteLabel(labelId,tokenUserId), HttpStatus.OK);
	}
	@GetMapping("/notes")
	public ResponseEntity<Response> getNotesOfLabel(@RequestHeader String tokenUserId,@RequestHeader int labelId){
		return new ResponseEntity<Response>(service.getNotesOFLabel(labelId, tokenUserId),HttpStatus.OK);
	}
}
