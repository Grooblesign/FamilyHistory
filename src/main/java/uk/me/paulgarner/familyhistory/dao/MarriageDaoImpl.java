package uk.me.paulgarner.familyhistory.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uk.me.paulgarner.familyhistory.model.Marriage;
import uk.me.paulgarner.familyhistory.util.ConnectionFactory;

public class MarriageDaoImpl implements MarriageDao {

	@Override
	public List<Marriage> findAllMarriagesForPerson(int personId) {
		
		List<Marriage> marriages = new ArrayList<Marriage>();
		
		Connection connection = null;
		
		try {
			connection = ConnectionFactory.getConnection();
		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(String.format("SELECT * FROM marriage WHERE husbandid=%s OR wifeId=%s ORDER BY id", personId, personId));
			while (rs.next()) {
				Marriage marriage = new Marriage();
				
				marriage.setId(rs.getInt("id"));
				marriage.setHusbandId(rs.getInt("husbandid"));
				marriage.setWifeId(rs.getInt("wifeid"));
				marriage.setDate(rs.getString("date"));
				marriage.setLocation(rs.getString("location"));
				
				marriages.add(marriage);
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
			
		}
		
		ConnectionFactory.closeConnection(connection);
		
		return marriages;
	}

}
