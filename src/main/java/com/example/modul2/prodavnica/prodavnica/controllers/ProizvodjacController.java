package com.example.modul2.prodavnica.prodavnica.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.modul2.prodavnica.prodavnica.model.Proizvodjac;
import com.example.modul2.prodavnica.prodavnica.service.ProizvodjacService;

@Controller
@RequestMapping("/Proizvodjaci")
public class ProizvodjacController {

	@Autowired
	private ProizvodjacService proizvodjacService;
	
	@GetMapping
	@ResponseBody
	public HashMap<String, Object> index(){
		
		List<Proizvodjac> proizvodjaci = proizvodjacService.getAll();
		HashMap<String, Object> odgovor = new HashMap<String, Object>();
		odgovor.put("status", "ok");
		odgovor.put("proizvodjaci", proizvodjaci);
		return odgovor;
	}
	
	@PostMapping("/Edit")
	@ResponseBody
	public Map<String, Object> update(
			@RequestParam int id,
			@RequestParam String naziv,
			@RequestParam String sediste){
		
		try {
			if(naziv.equals("")) {
				throw new Exception("Naziv ne sme biti prazan!");
			}
			if(sediste.equals("")) {
				throw new Exception("Naziv ne sme biti prazan!");
			}
			
			Proizvodjac proizvodjac = new Proizvodjac(id, naziv, sediste);
			proizvodjacService.update(proizvodjac);
			
			Map<String, Object> odgovor = new LinkedHashMap<String, Object>();
			odgovor.put("status", "ok");
			return odgovor;	
		} catch (Exception e) {
			String poruka = e.getMessage();
			if (poruka == "") {
				poruka = "Neuspešno dodavanje!";
			}
			
			Map<String, Object> odgovor = new LinkedHashMap<>();
			odgovor.put("status", "greska");
			odgovor.put("poruka", poruka);
			return odgovor;
		}
	}
}
