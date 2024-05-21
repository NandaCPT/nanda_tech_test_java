package com.techtest.service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techtest.service.dto.GuruDTO;
import com.techtest.service.repository.GuruRepository;
import com.techtest.service.service.GuruService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/guru")
@Slf4j
public class GuruController {
	
	@Autowired
	GuruService service;
	
	@Autowired
	GuruRepository repo;
	
	@PostMapping
	public ResponseEntity<Object> addGuru(@RequestBody GuruDTO dto){
	
		Map<String, Object> result = new HashMap<>();
		try {
		if(repo.existsById(dto.getNip())) {
			result.put("message", "Guru Already Exist!");
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		String nis = service.addGuru(dto);
		if(!nis.equals("")) {
			result.put("message", "Add Guru Success!");
			result.put("data", dto);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		else {
			result.put("message", "Add Guru Failed!");
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}	
	} catch (Exception e) {
		log.error(e.getMessage());
		result.put("status", false);
		result.put("message", "Something Wrong!");
		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

}
