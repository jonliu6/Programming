package org.freecode.demo.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component("cityDAO")
public class CityDAO {

	private JdbcTemplate jdbcTemp;
	
	public List<City> findCities() {
		return jdbcTemp.query("SELECT * FROM xcity", new RowMapper<City>(){
			public City mapRow(ResultSet rs, int rowNum) throws SQLException {
				City ct = new City();
				ct.setCityId(rs.getString("cityId"));
				ct.setCityName(rs.getString("cityName"));
				ct.setCountryName(rs.getString("countryName"));
				return ct;
			}
		});
	}

	public JdbcTemplate getJdbcTemp() {
		return jdbcTemp;
	}

	@Autowired
	public void setJdbcTemp(DataSource ds) {
		this.jdbcTemp = new JdbcTemplate(ds);
	}
	
	
}
