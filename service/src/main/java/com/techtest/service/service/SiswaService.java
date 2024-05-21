package com.techtest.service.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techtest.service.dto.SiswaDTO;
import com.techtest.service.entity.GuruModel;
import com.techtest.service.entity.KelasModel;
import com.techtest.service.entity.SiswaModel;
import com.techtest.service.repository.KelasRepository;
import com.techtest.service.repository.SiswaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SiswaService {
	
	@Autowired
	SiswaRepository repo;
	
	@Autowired
	KelasRepository kelasRepo;
	
	@Transactional
	public String addSiswa(SiswaDTO dto) {
		SiswaModel model = new SiswaModel();
		String result = "";
		
		try {
			KelasModel kelasModel = new KelasModel();
			Optional<KelasModel> existingKelas = kelasRepo.findById(dto.getKodeKelas());
			if (existingKelas.isPresent()) {
				kelasModel = existingKelas.get();
				model.setKelas(kelasModel);
			}else {
				log.error("Kelas " + dto.getKodeKelas() + " Not Found");
			}
			
			model.setNama(dto.getNama());
			model.setNis(dto.getNis());
			model.setTanggalLahir(dto.getTanggalLahir());
			model.setDeleted(false);
			model.setKelas(kelasModel);
			model.setInsertBy(dto.getInsertBy());
			repo.save(model);
			result = dto.getNis();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}
	
	@Transactional
	public void editSiswa(SiswaDTO dto) {
		
		try {
			SiswaModel findSiswa = repo.getById(dto.getNis());
			
			KelasModel kelasModel = new KelasModel();
			Optional<KelasModel> existingKelas = kelasRepo.findById(dto.getKodeKelas());
			if (existingKelas.isPresent()) {
				kelasModel = existingKelas.get();
				findSiswa.setKelas(kelasModel);
			}else {
				log.error("Kelas " + dto.getKodeKelas() + " Not Found");
			}
			
			findSiswa.setNama(dto.getNama());
			findSiswa.setTanggalLahir(dto.getTanggalLahir());
			findSiswa.setDeleted(dto.getDeleted());
			findSiswa.setUpdateBy(dto.getUpdateBy());
			repo.save(findSiswa);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
