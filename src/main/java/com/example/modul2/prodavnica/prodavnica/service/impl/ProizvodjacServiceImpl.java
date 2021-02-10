package com.example.modul2.prodavnica.prodavnica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modul2.prodavnica.prodavnica.dao.ProizvodjacDAO;
import com.example.modul2.prodavnica.prodavnica.model.Proizvodjac;
import com.example.modul2.prodavnica.prodavnica.service.ProizvodjacService;

@Service
public class ProizvodjacServiceImpl implements ProizvodjacService {
	
	@Autowired
	private ProizvodjacDAO proizvodjacDAO;

	@Override
	public List<Proizvodjac> getAll() {
		return proizvodjacDAO.getAll();
	}

	@Override
	public Proizvodjac update(Proizvodjac proizvodjac) {
		proizvodjacDAO.update(proizvodjac);
		return proizvodjac;
	}

}
