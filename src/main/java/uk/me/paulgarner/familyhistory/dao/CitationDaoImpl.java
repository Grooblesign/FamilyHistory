package uk.me.paulgarner.familyhistory.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import uk.me.paulgarner.familyhistory.model.Citation;
import uk.me.paulgarner.familyhistory.util.ConnectionFactory;

public class CitationDaoImpl implements CitationDao {
	static Logger logger = Logger.getLogger("uk.me.paulgarner");	

	@Override
	public Citation findCitation(int id) {
		
		Citation citation = null;
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(String.format("SELECT * FROM citation WHERE id=%s", id));
			if (rs.next()) {
				citation = new Citation();
				
				citation.setId(rs.getInt("id"));
				citation.setDetails(rs.getString("details").trim());
				citation.setSourceId(rs.getInt("sourceid"));
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
			logger.info(ex.getMessage());
		}
		
		ConnectionFactory.closeConnection(connection);
		
		return citation;
	}

}
