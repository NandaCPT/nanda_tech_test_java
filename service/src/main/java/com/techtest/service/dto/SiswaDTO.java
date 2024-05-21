package com.techtest.service.dto;

import java.time.LocalDate;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiswaDTO {
	
	private String nis;
	private String nama;
	private LocalDate tanggalLahir;
	private String kodeKelas;
	private String namaKelas;
	private String waliKelas;
	private Boolean deleted;
	private String insertBy;
	private String updateBy;

}
