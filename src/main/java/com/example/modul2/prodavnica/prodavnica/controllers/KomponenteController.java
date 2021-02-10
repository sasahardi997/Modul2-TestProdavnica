package com.example.modul2.prodavnica.prodavnica.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.modul2.prodavnica.prodavnica.model.Komponenta;
import com.example.modul2.prodavnica.prodavnica.model.Proizvodjac;
import com.example.modul2.prodavnica.prodavnica.service.KomponentaService;

@Controller
@RequestMapping("/Komponente")
public class KomponenteController {

	public static final String KOMPONENTE_KEY = "komponente";
	public static final String PROIZVODJACI_KEY = "proizvodjaci";
	public static final String DOSTUPNE_KOMPONENTE_KEY = "dostupneKomponente";
	public static final String KOMPONENTE_KORISNIKA_KEY = "komponenteKorisnika";
	
	@Autowired
	private ServletContext servletContext;
	private String bURL;
	
	@Autowired
	private KomponentaService komponentaService;
	
	@PostConstruct
	public void init() {
		bURL = servletContext.getContextPath() + "/";
	}
	
	@PostMapping("/Create")
	public void zadatak2(
			@RequestParam Integer proizvodjacId,
			@RequestParam String model,
			@RequestParam Double cena,
			@RequestParam Boolean dostupnost,
			HttpServletResponse response, HttpSession session) throws IOException {
		HashMap<Long, Komponenta> komponente = (HashMap<Long, Komponenta>) servletContext.getAttribute(KomponenteController.KOMPONENTE_KEY);
		HashMap<Integer, Proizvodjac> proizvodjaci = (HashMap<Integer, Proizvodjac>) servletContext.getAttribute(KomponenteController.PROIZVODJACI_KEY);
		List<Komponenta> komponenteKorisnika = (List<Komponenta>) session.getAttribute(KomponenteController.KOMPONENTE_KORISNIKA_KEY);
		
		Proizvodjac proizvodjac = proizvodjaci.get(proizvodjacId);
		
		Komponenta komponenta = new Komponenta(Long.valueOf(komponente.size() + 1),proizvodjac, model, cena, dostupnost);
		komponente.put(komponenta.getId(), komponenta);
		komponenteKorisnika.add(komponenta);
		
		int dostupneKomponente = (int) servletContext.getAttribute(KomponenteController.DOSTUPNE_KOMPONENTE_KEY);
		for(Komponenta k : komponente.values()) {
			System.out.println(k);
			if(k.isDostupnost()) {
				dostupneKomponente ++;
			}
		}
		System.out.println("Broj dostupnih komponenti: " + dostupneKomponente);
		
		response.sendRedirect(bURL + "Komponente/Zadatak3");
	}
	
	@GetMapping("/Zadatak3")
	public String zadatak3(ModelMap modelMap, HttpSession session) {
		
		//Komponente korisnika
		List<Komponenta> komponenteKorisnika = (List<Komponenta>) session.getAttribute(KomponenteController.KOMPONENTE_KORISNIKA_KEY);
		for(Komponenta k : komponenteKorisnika) {
			System.out.println(k);
		}
		
		HashMap<Long, Komponenta> komponente = (HashMap<Long, Komponenta>) servletContext.getAttribute(KomponenteController.KOMPONENTE_KEY);
		List<Komponenta> dostupne = new ArrayList<Komponenta>();
		List<Komponenta> nedostupne = new ArrayList<Komponenta>();
		for(Komponenta k : komponente.values()) {
			if(k.isDostupnost()) {
				dostupne.add(k);
			} else {
				nedostupne.add(k);
			}
		}
		modelMap.addAttribute("dostupne", dostupne);
		modelMap.addAttribute("nedostupne", nedostupne);
		
		//Algoritam za proizvodjaca sa najvise komponenti
		List<String> komponenteProizvodjaca = new ArrayList<String>();
		int brojKomponenti = 0;
		double ukupnaCena =0;
		
		for(Komponenta k : komponente.values()) {
			if(k.isDostupnost()) {
				komponenteProizvodjaca.add(k.getProizvodjac().getNaziv());
			}
		}
		
		//Ime proizvodjaca
		String imeProizvodjaca 
	    = komponenteProizvodjaca.stream()
	          .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
	          .entrySet()
	          .stream()
	          .max(Comparator.comparing(Entry::getValue))
	          .get()
	          .getKey();
		
		//Broj komponenti
		for(String s : komponenteProizvodjaca) {
			if(s.equals(imeProizvodjaca)) {
				brojKomponenti++;
			}
		}
		
		//Ukupna cena
		for(Komponenta k : komponente.values()) {
			if(k.getProizvodjac().getNaziv().equals(imeProizvodjaca)) {
				if(k.isDostupnost()) {
					ukupnaCena += k.getCena();
				}
			}
		}
		
		modelMap.addAttribute("imeProizvodjaca", imeProizvodjaca);
		modelMap.addAttribute("brojKomponenti", brojKomponenti);
		modelMap.addAttribute("ukupnaCena", ukupnaCena);

		return "zadatak3";
	}
	
	@GetMapping("/Zadatak5")
	public String zadatak5(
			@RequestParam(defaultValue = "0") String proizvodjac,
			@RequestParam(defaultValue = "0") Double cenaOd,
			@RequestParam(defaultValue = Double.MAX_VALUE + "") Double cenaDo,
			@RequestParam(defaultValue = "0") String dostupnost,
			ModelMap modelMap) {
		
		List<Komponenta> komponente = new ArrayList<Komponenta>();
		if(dostupnost.equals("0")) {
			komponente = komponentaService.getAllBy(proizvodjac, cenaOd, cenaDo);
		} else {
			boolean dostupnostBool = Boolean.parseBoolean(dostupnost);
			komponente = komponentaService.getAllBy(proizvodjac, cenaOd, cenaDo, dostupnostBool);
		}
		List<Komponenta> dostupne = new ArrayList<Komponenta>();
		List<Komponenta> nedostupne = new ArrayList<Komponenta>();
		for(Komponenta k : komponente) {
			if(k.isDostupnost()) {
				dostupne.add(k);
			} else {
				nedostupne.add(k);
			}
		}
		modelMap.addAttribute("dostupne", dostupne);
		modelMap.addAttribute("nedostupne", nedostupne);
		
		HashMap<Integer, Proizvodjac> proizvodjaci = (HashMap<Integer, Proizvodjac>) servletContext.getAttribute(KomponenteController.PROIZVODJACI_KEY);
		modelMap.addAttribute("proizvodjaci", proizvodjaci.values());
		
		return "zadatak5";
	}
}
