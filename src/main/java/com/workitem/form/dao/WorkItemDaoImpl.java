package com.workitem.form.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.workitem.form.model.User;
import com.workitem.form.model.WorkItem;

@Repository
public class WorkItemDaoImpl implements WorkItemDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public WorkItem findById(Integer id) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);

		String sql = "SELECT * FROM WORKITEM WHERE id=:id";

		WorkItem result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new WorkItemMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}

		/*
		 * User result = namedParameterJdbcTemplate.queryForObject( sql, params,
		 * new BeanPropertyRowMapper<User>());
		 */

		return result;

	}

	@Override
	public List<WorkItem> findAll() {

		String sql = "SELECT * FROM WORKITEM";
		List<WorkItem> result = namedParameterJdbcTemplate.query(sql, new WorkItemMapper());

		return result;

	}

	@Override
	public void save(WorkItem user) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String sql = "INSERT INTO WORKITEM(WORKITEM, FILECHANGE, PEERREVIEWCOMMENTS, IMPACTANALYSIS, MODULE, APPLICATION, REQUESTOR, STATUS, TEAM) "
				+ "VALUES ( :workItem, :fileChange, :peerReviewComments, :impactAnalysis, :module, :application, :requestor, :status, :team)";

		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user), keyHolder);
		user.setId(keyHolder.getKey().intValue());
		
	}

	@Override
	public void update(WorkItem user) {

		String sql = "UPDATE WORKITEM SET WORKITEM=:workItem, FILECHANGE=:fileChange, PEERREVIEWCOMMENTS=:peerReviewComments, " + "IMPACTANALYSIS=:impactAnalysis, MODULE=:module, APPLICATION=:application, "
				+ "REQUESTOR=:requestor, STATUS=:status, TEAM=:team WHERE id=:id";

		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));

	}

	@Override
	public void delete(Integer id) {

		String sql = "DELETE FROM WORKITEM WHERE id= :id";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));

	}

	private SqlParameterSource getSqlParameterByModel(WorkItem workItem) {

		// Unable to handle List<String> or Array
		// BeanPropertySqlParameterSource

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", workItem.getId());
		paramSource.addValue("workItem", workItem.getWorkItem());
		paramSource.addValue("fileChange", workItem.getFileChange());
		paramSource.addValue("peerReviewComments", workItem.getPeerReviewComments());
		paramSource.addValue("impactAnalysis", workItem.getImpactAnalysis());
		paramSource.addValue("module", workItem.getModule());
		paramSource.addValue("application", workItem.getApplication());
		paramSource.addValue("requestor", workItem.getRequestor());
		paramSource.addValue("status", workItem.getStatus());
		paramSource.addValue("team", workItem.getTeam());
		return paramSource;
	}

	private static final class WorkItemMapper implements RowMapper<WorkItem> {

		public WorkItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			WorkItem workItem = new WorkItem();
			workItem.setId(rs.getInt("id"));
			workItem.setWorkItem(rs.getInt("workItem"));
			workItem.setFileChange(rs.getString("fileChange"));
			workItem.setPeerReviewComments(rs.getString("peerReviewComments"));
			workItem.setImpactAnalysis(rs.getString("impactAnalysis"));
			workItem.setModule(rs.getString("module"));
			workItem.setApplication(rs.getString("application"));
			workItem.setRequestor(rs.getString("requestor"));
			workItem.setStatus(rs.getString("status"));
			workItem.setTeam(rs.getString("team"));
			
			return workItem;
		}
	}

	private static List<String> convertDelimitedStringToList(String delimitedString) {

		List<String> result = new ArrayList<String>();

		if (!StringUtils.isEmpty(delimitedString)) {
			result = Arrays.asList(StringUtils.delimitedListToStringArray(delimitedString, ","));
		}
		return result;

	}

	private String convertListToDelimitedString(List<String> list) {

		String result = "";
		if (list != null) {
			result = StringUtils.arrayToCommaDelimitedString(list.toArray());
		}
		return result;

	}

}