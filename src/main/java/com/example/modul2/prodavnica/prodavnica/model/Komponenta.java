package com.example.modul2.prodavnica.prodavnica.model;

public class Komponenta {

	private Long id;
	private Proizvodjac proizvodjac;
	private String model;
	private double cena;
	private boolean dostupnost;
	
	public Komponenta(Proizvodjac proizvodjac, String model, double cena, boolean dostupnost) {
		this.proizvodjac = proizvodjac;
		this.model = model;
		this.cena = cena;
		this.dostupnost = dostupnost;
	}

	public Komponenta(Long id, Proizvodjac proizvodjac, String model, double cena, boolean dostupnost) {
		this.id = id;
		this.proizvodjac = proizvodjac;
		this.model = model;
		this.cena = cena;
		this.dostupnost = dostupnost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Komponenta other = (Komponenta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Proizvodjac getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(Proizvodjac proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public boolean isDostupnost() {
		return dostupnost;
	}

	public void setDostupnost(boolean dostupnost) {
		this.dostupnost = dostupnost;
	}

	@Override
	public String toString() {
		return "Komponenta [id=" + id + ", proizvodjac=" + proizvodjac + ", model=" + model + ", cena=" + cena
				+ ", dostupnost=" + dostupnost + "]";
	}

}
