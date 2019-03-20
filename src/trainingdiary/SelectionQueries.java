package trainingdiary;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectionQueries extends DBConn{
	public SelectionQueries() {
		
	}
	
	public QueryResult getApparater() {
		int key;
		QueryResult result = new QueryResult();
		String navn, beskrivelse;
		List<String> row;
		
		try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select ApparatId, ApparatNavn, ApparatBeskrivelse"
            								+ "from Apparat");
            while (rs.next()) {
            	row = new ArrayList<>();
            	
                key =  rs.getInt("ApparatId");
                navn = rs.getString("ApparatNavn");
                beskrivelse = rs.getString("ApparatBeskrivelse");
                
                row.add(Integer.toString(key));
                row.add(navn);
                row.add(beskrivelse);
                
                result.insertRow(key, row);
            }

        } catch (Exception e) {
            System.out.println("db error during select of okt= "+e);
        }
		
		return result;
	}
}
