package com.techtest.service.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techtest.service.dto.GuruDTO;
import com.techtest.service.entity.GuruModel;
import com.techtest.service.repository.GuruRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GuruService {
	
	@Autowired
	GuruRepository repo;
	
	@Transactional
	public String addGuru(GuruDTO dto) {
		GuruModel model = new GuruModel();
		String result = "";
		
		try {
			model.setNip(dto.getNip());
			model.setNama(dto.getNama());
			model.setHpNo(dto.getHpNo());
			model.setDeleted(false);
			model.setInsertBy(dto.getInsertBy());
			
			repo.save(model);
			result = dto.getNip();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}

}
