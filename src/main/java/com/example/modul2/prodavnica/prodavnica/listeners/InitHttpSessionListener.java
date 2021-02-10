package com.example.modul2.prodavnica.prodavnica.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;

import com.example.modul2.prodavnica.prodavnica.controllers.KomponenteController;
import com.example.modul2.prodavnica.prodavnica.model.Komponenta;

@Component
public class InitHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("Inicijalizacija sesisje HttpSessionListener...");
		
		HttpSession session  = se.getSession();
		System.out.println("Session id korisnika je "+ session.getId());
		
		List<Komponenta> komponenteKorisnika = new ArrayList<Komponenta>();
		session.setAttribute(KomponenteController.KOMPONENTE_KORISNIKA_KEY, komponenteKorisnika);
		
		System.out.println("Uspeh HttpSessionListener!");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("Brisanje sesisje HttpSessionListener...");
		System.out.println("Uspeh HttpSessionListener!");
	}

}
