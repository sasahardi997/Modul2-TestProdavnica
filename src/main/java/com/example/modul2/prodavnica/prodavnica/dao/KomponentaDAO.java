package com.example.modul2.prodavnica.prodavnica.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.modul2.prodavnica.prodavnica.model.Komponenta;
import com.example.modul2.prodavnica.prodavnica.model.Proizvodjac;

@Repository
public class KomponentaDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static class KomponentaRowMapper implements RowMapper<Komponenta>{

		@Override
		public Komponenta mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			Long id = rs.getLong(index++);
			int proizvodjacId = rs.getInt(index++);
			String model = rs.getString(index++);
			double cena = rs.getDouble(index++);
			boolean dostupnost = rs.getBoolean(index++);
			
			int idProizvodjaca = rs.getInt(index++);
			String naziv = rs.getString(index++);
			String sediste = rs.getString(index++);
			
			Proizvodjac proizvodjac = new Proizvodjac(idProizvodjaca, naziv, sediste);
			Komponenta komponenta = new Komponenta(id, proizvodjac, model, cena, dostupnost);
			return komponenta;
		}
		
	}

	public List<Komponenta> getAllBy(String proizvodjac, Double cenaOd, Double cenaDo, Boolean dostupnost) {
		proizvodjac = "%" + proizvodjac + "%";
		String sql = "SELECT k.id, k.proizvodjacId, k.model, k.cena, k.dostupnost, p.id, p.naziv, p.sediste\r\n"
				+ "FROM komponente k \r\n"
				+ "LEFT JOIN proizvodjaci p\r\n"
				+ "ON k.proizvodjacId = p.id\r\n"
				+ "WHERE p.naziv LIKE ? AND cena > ? AND cena < ? AND dostupnost = ?";
		return jdbcTemplate.query(sql, new KomponentaRowMapper(), proizvodjac, cenaOd, cenaDo, dostupnost);
	}

	public List<Komponenta> getAllBy(String proizvodjac, Double cenaOd, Double cenaDo) {
		proizvodjac = "%" + proizvodjac + "%";
		String sql = "SELECT k.id, k.proizvodjacId, k.model, k.cena, k.dostupnost, p.id, p.naziv, p.sediste\r\n"
				+ "FROM komponente k \r\n"
				+ "LEFT JOIN proizvodjaci p\r\n"
				+ "ON k.proizvodjacId = p.id\r\n"
				+ "WHERE p.naziv LIKE ? AND cena > ? AND cena < ?";
		return jdbcTemplate.query(sql, new KomponentaRowMapper(), proizvodjac, cenaOd, cenaDo);
	}

}
