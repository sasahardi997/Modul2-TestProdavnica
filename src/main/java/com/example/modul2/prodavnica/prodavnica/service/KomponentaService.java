package com.example.modul2.prodavnica.prodavnica.service;

import java.util.List;

import com.example.modul2.prodavnica.prodavnica.model.Komponenta;

public interface KomponentaService {

	List<Komponenta> getAllBy(String proizvodjac, Double cenaOd, Double cenaDo, Boolean dostupnost);

	List<Komponenta> getAllBy(String proizvodjac, Double cenaOd, Double cenaDo);
}
