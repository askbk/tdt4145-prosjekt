package trainingdiary;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectionQueries extends DBConn{
	public SelectionQueries() {
		
	}
	
	public QueryResult getApparater() {
		connect();
		int key;
		QueryResult result = new QueryResult();
		String navn, beskrivelse;
		List<String> row;
		
		try {
			System.out.println("dheya");
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("select *"
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
	
	public QueryResult getOktNotat(int n) {
		connect();
		int key;
		QueryResult result = new QueryResult();
		String treningsFormaal;
		String treningsOpplevelse;
		List<String> row;
		
		try {
			System.out.println("dheya");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select*"
											+ "from Okt as o"
											+ "left join Notat as "+ n + " on o.OktId = n.OktId"
											+ "order by o.OktId desc limit " + n);
		while (rs.next()) {
			row = new ArrayList<>();
			
			key = rs.getInt("oktId");
			treningsFormaal = rs.getString("treningsFormaal");
			treningsOpplevelse = rs.getString("treningsOpplevelse");
			row.add(Integer.toString(key));
			row.add(treningsFormaal);
			row.add(treningsOpplevelse);
			
			result.insertRow(key, row);
		}
		} catch (Exception e) {
			System.out.println("db error during select of okt= "+e);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		SelectionQueries test = new SelectionQueries();
		QueryResult apparater = test.getApparater();
		
		for(List<String> row : apparater) {
			for(String col : row) {
				System.out.print(col + " ");
			}
			System.out.print("\n");
		}
		
	}
}
