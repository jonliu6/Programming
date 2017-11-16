package org.freecode.demo.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// @Component("cityDAO")
@Repository("cityDAO")
public class CityDAO {

	private JdbcTemplate jdbcTemp;
	private NamedParameterJdbcTemplate namedParamJdbcTemp;
	
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
	
	public List<City> findCitiesByCountry(String countryName) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("country", countryName);
		
		return namedParamJdbcTemp.query("SELECT * FROM xcity WHERE countryName = :country", params, new RowMapper<City>() {
			public City mapRow(ResultSet rs, int rowNum) throws SQLException {
				City c = new City();
				c.setCityId(rs.getString("cityId"));
				c.setCityName(rs.getString("cityName"));
				c.setCountryName(rs.getString("countryName"));
				return c;
			}
		});
	}
	
	public City findCityByName(String name) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", name);
		return namedParamJdbcTemp.queryForObject("SELECT * FROM xcity WHERE cityName = :name", params, new RowMapper<City>() {
			public City mapRow(ResultSet rs, int rowNum) throws SQLException {
				City c = new City();
				c.setCityId(rs.getString("cityId"));
				c.setCityName(rs.getString("cityName"));
				c.setCountryName(rs.getString("countryName"));
				return c;
			}
		});
	}
	
	public Boolean addCity(String cId, String cName, String country) {
		Boolean result = Boolean.FALSE;
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", cId);
		params.addValue("name", cName);
		params.addValue("country", country);
		
		int num = namedParamJdbcTemp.update("INSERT INTO xcity (cityId, cityName, countryName) VALUES (:id, :name, :country)", params);
		System.out.println(num + " city added.");
		
		return result;
	}
	
	public Boolean addCity2(City c) {
		Boolean result = Boolean.FALSE;
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(c);
		int num = namedParamJdbcTemp.update("INSERT INTO xcity (cityId, cityName, countryName) VALUES (:cityId, :cityName, :countryName)", params);
		System.out.println(num + " city added.");
		
		return result;
	}
	
	public Boolean updateCity(City c) {
		Boolean result = Boolean.FALSE;
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(c);
		int num = namedParamJdbcTemp.update("UPDATE xcity SET cityName = :cityName, countryName = :countryName WHERE cityId = :cityId", params);
		System.out.println(num + " city updated.");
		
		return result;
	}
	
	public Boolean removeCity(String cityId) {
		Boolean result = Boolean.FALSE;
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", cityId);
		int num = namedParamJdbcTemp.update("DELETE FROM xcity WHERE cityId = :id", params);
		System.out.println(num + " city removed.");
		
		return result;
	}
	
	@Transactional("txManager")
	public int[] batchAddCities(List<City> cities) {
		// long way doing
//		List<MapSqlParameterSource> params = new ArrayList<MapSqlParameterSource>();
//		for (City c: cities) {
//			MapSqlParameterSource param = new MapSqlParameterSource();
//			param.addValue("cityId", c.getCityId());
//			param.addValue("cityName", c.getCityName());
//			param.addValue("countryName", c.getCountryName());
//			params.add(param);
//		}
//		MapSqlParameterSource[] paramArray = new MapSqlParameterSource[params.size()];
//		params.toArray(paramArray);
		SqlParameterSource[] paramArray = SqlParameterSourceUtils.createBatch(cities.toArray());
		int[] affectedRowNum = namedParamJdbcTemp.batchUpdate("INSERT INTO xcity (cityId, cityName, countryName) VALUES (:cityId, :cityName, :countryName)", paramArray); 
		return affectedRowNum;
	}

	public JdbcTemplate getJdbcTemp() {
		return jdbcTemp;
	}

	@Autowired
	public void setJdbcTemp(DataSource ds) {
		this.jdbcTemp = new JdbcTemplate(ds);
	}

	public NamedParameterJdbcTemplate getNamedParamJdbcTemp() {
		return namedParamJdbcTemp;
	}

	@Autowired
	public void setNamedParamJdbcTemp(DataSource ds) {
		this.namedParamJdbcTemp = new NamedParameterJdbcTemplate(ds);
	}
	
	
}
