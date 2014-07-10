package uk.me.paulgarner.familyhistory.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import uk.me.paulgarner.familyhistory.model.Source;
import uk.me.paulgarner.familyhistory.util.ConnectionFactory;

public class SourceDaoImpl implements SourceDao {

	static Logger logger = Logger.getLogger("uk.me.paulgarner");	

	@Override
	public List<Source> findAllSources() {
		List<Source> sources = new ArrayList<Source>();
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM source ORDER BY title");
			while (rs.next()) {
				sources.add(getSourceFromRow(rs));
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}
		
		ConnectionFactory.closeConnection(connection);
		
		return sources;
	}

	@Override
	public Source findSource(int id) {
		logger.info(String.format("findSource: start of method (%s)", System.currentTimeMillis()));

		logger.info(String.format("findSource: id = %s", id));

		Source source = null;
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM source WHERE id = " + id);

			if (rs.next()) {
				logger.info("findSource: OK");
				source = getSourceFromRow(rs);
			}
			
			rs.close();
			st.close();
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}
		
		ConnectionFactory.closeConnection(connection);
		
		logger.info(String.format("findSource: end of method (%s)", System.currentTimeMillis()));

		return source;
	}
	
	private Source getSourceFromRow(ResultSet rs) throws SQLException {
		Source source = new Source();
		
		source.setAuthor(rs.getString("author"));
		source.setDate(rs.getString("date"));
		source.setId(rs.getInt("id"));
		source.setNotes(rs.getString("notes"));
		source.setPublisher(rs.getString("publisher"));
		source.setTitle(rs.getString("title"));
		source.setType(rs.getString("type"));
		source.setUrl(rs.getString("url"));

		return source;
	}
}
