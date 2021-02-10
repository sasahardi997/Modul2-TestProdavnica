package com.example.modul2.prodavnica.prodavnica.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.modul2.prodavnica.prodavnica.model.Proizvodjac;

@Repository
public class ProizvodjacDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static class ProizvodjacRowMapper implements RowMapper<Proizvodjac>{

		@Override
		public Proizvodjac mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			int id = rs.getInt(index++);
			String naziv = rs.getString(index++);
			String sediste = rs.getString(index++);
			
			Proizvodjac proizvodjac = new Proizvodjac(id, naziv, sediste);
			return proizvodjac;
		}
		
	}

	public List<Proizvodjac> getAll() {
		String sql = "SELECT id, naziv, sediste FROM proizvodjaci";
		return jdbcTemplate.query(sql, new ProizvodjacRowMapper());
	}

	public void update(Proizvodjac proizvodjac) {
		String sql = "UPDATE proizvodjaci SET naziv = ?, sediste = ? WHERE id = ?";
		jdbcTemplate.update(sql, proizvodjac.getNaziv(), proizvodjac.getSediste(), proizvodjac.getId());
	}

}
