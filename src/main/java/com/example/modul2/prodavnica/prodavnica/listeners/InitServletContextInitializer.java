package com.example.modul2.prodavnica.prodavnica.listeners;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import com.example.modul2.prodavnica.prodavnica.controllers.KomponenteController;
import com.example.modul2.prodavnica.prodavnica.model.Komponenta;
import com.example.modul2.prodavnica.prodavnica.model.Proizvodjac;


@Component
public class InitServletContextInitializer implements ServletContextInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("Inicijalizacija konteksta pri ServletContextInitializer...");
		
		HashMap<Integer, Proizvodjac> proizvodjaci = new HashMap<Integer, Proizvodjac>();
		proizvodjaci.put(1, new Proizvodjac(1, "AMD", "Sjedinjene Americke Drzave"));
		proizvodjaci.put(2, new Proizvodjac(2, "Samsung", "Juzna Korea"));
		proizvodjaci.put(3, new Proizvodjac(3, "Gigabyte", "Tajvan"));
		servletContext.setAttribute(KomponenteController.PROIZVODJACI_KEY, proizvodjaci);
		
		HashMap<Long, Komponenta> komponente = new HashMap<Long, Komponenta>();
		komponente.put(1L, new Komponenta(1L, proizvodjaci.get(1), "Ryzen", 13000, true));
		komponente.put(2L, new Komponenta(2L, proizvodjaci.get(3), "B450 Aorus M", 12000, false));
		komponente.put(3L, new Komponenta(3L, proizvodjaci.get(2), "SSD 970 EVO PLUS", 18000, true));
		komponente.put(4L, new Komponenta(4L, proizvodjaci.get(3), "GeForce RTX 2080", 115000, false));
		komponente.put(5L, new Komponenta(5L, proizvodjaci.get(2), "C24FG73", 32000, false));
		servletContext.setAttribute(KomponenteController.KOMPONENTE_KEY, komponente);
		
		int dostupneKopmonente = 0;
		servletContext.setAttribute(KomponenteController.DOSTUPNE_KOMPONENTE_KEY, dostupneKopmonente);
		
		System.out.println("Uspeh ServletContextInitializer!");
		
	}

}
