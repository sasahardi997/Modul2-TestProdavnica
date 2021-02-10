package com.example.modul2.prodavnica.prodavnica.service;

import java.util.List;

import com.example.modul2.prodavnica.prodavnica.model.Proizvodjac;

public interface ProizvodjacService {

	List<Proizvodjac> getAll();
	Proizvodjac update(Proizvodjac proizvodjac);
}
