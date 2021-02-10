package com.example.modul2.prodavnica.prodavnica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modul2.prodavnica.prodavnica.dao.KomponentaDAO;
import com.example.modul2.prodavnica.prodavnica.model.Komponenta;
import com.example.modul2.prodavnica.prodavnica.service.KomponentaService;

@Service
public class KomponentaServiceImpl implements KomponentaService {
	
	@Autowired
	private KomponentaDAO komponentaDAO;

	@Override
	public List<Komponenta> getAllBy(String proizvodjac, Double cenaOd, Double cenaDo, Boolean dostupnost) {
		if(proizvodjac == null || proizvodjac.equals("0")) {
			proizvodjac = "";
		}
		if(cenaOd == null) {
			cenaOd = 0.0;
		}
		if(cenaDo == null) {
			cenaDo = Double.MAX_VALUE;
		}
		if(dostupnost == null) {
			dostupnost = false;
		}
		return komponentaDAO.getAllBy(proizvodjac, cenaOd, cenaDo, dostupnost);
	}

	@Override
	public List<Komponenta> getAllBy(String proizvodjac, Double cenaOd, Double cenaDo) {
		if(proizvodjac == null || proizvodjac.equals("0")) {
			proizvodjac = "";
		}
		if(cenaOd == null) {
			cenaOd = 0.0;
		}
		if(cenaDo == null) {
			cenaDo = Double.MAX_VALUE;
		}
		return komponentaDAO.getAllBy(proizvodjac, cenaOd, cenaDo);
	}

}
