package com.example.modul2.prodavnica.prodavnica.model;

public class Proizvodjac {

	private int id;
	private String naziv;
	private String sediste;
	
	public Proizvodjac(String naziv, String sediste) {
		this.naziv = naziv;
		this.sediste = sediste;
	}

	public Proizvodjac(int id, String naziv, String sediste) {
		this.id = id;
		this.naziv = naziv;
		this.sediste = sediste;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proizvodjac other = (Proizvodjac) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSediste() {
		return sediste;
	}

	public void setSediste(String sediste) {
		this.sediste = sediste;
	}

	@Override
	public String toString() {
		return "Proizvodjac [id=" + id + ", naziv=" + naziv + ", sediste=" + sediste + "]";
	}
	
}
