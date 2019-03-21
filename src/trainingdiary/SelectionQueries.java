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
	
	public QueryResult getOktNotat(int k) {
		connect();
		int key;
		QueryResult result = new QueryResult();
		String dato, tidspunkt, varighet, form, prestasjon, treningsFormaal, treningsOpplevelse;
		List<String> row;
		
		try {
			System.out.println("dheya");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT *"
										   + "FROM Okt as o "
										   + "LEFT JOIN Notat AS n "
										   + "ON o.OktId = n.OktId "
										   + "ORDER BY o.OktId DESC "
										   + "LIMIT " + k);
		while (rs.next()) {
			row = new ArrayList<>();
			
			key = rs.getInt("OktId");
			dato = rs.getString("OktDato");
			tidspunkt = rs.getString("OktTidspunkt");
			varighet = rs.getString("OktVarighet");
			form = rs.getString("PersonligForm");
			prestasjon = rs.getString("PersonligPrestasjon");
			treningsFormaal = rs.getString("TreningsFormal");
			treningsOpplevelse = rs.getString("TreningsOpplevelse");
			row.add(Integer.toString(key));
			row.add(dato);
			row.add(tidspunkt);
			row.add(varighet);
			row.add(form);
			row.add(prestasjon);
			row.add(treningsFormaal);
			row.add(treningsOpplevelse);
			
			result.insertRow(key, row);
		}
		} catch (Exception e) {
			System.out.println("db error during select of okt= "+e);
		}
		
		return result;
	}
	
	/*public QueryResult getResultatlogg(int dato1, int dato2) {
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
											+ "left join Notat as n on o.OktId = n.OktId"
											+ "order by o.OktId desc limit " + k);
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
	}*/
	
	public QueryResult getOvelseGrupper() {
		connect();
		int key;
		QueryResult result = new QueryResult();
		String navn;
		List<String> row;
		
		try {
			System.out.println("dheya");
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("select *"
            								+ "from Gruppe");
           
            while (rs.next()) {
            	row = new ArrayList<>();
            	
                key =  rs.getInt("GruppeId");
                navn = rs.getString("GruppeNavn");
                row.add(Integer.toString(key));
                row.add(navn);
                
                result.insertRow(key, row);
            }

        } catch (Exception e) {
            System.out.println("db error during select of ovelsegruppe= "+e);
        }
		
		return result;
	}
	
	public static void main(String[] args) {
		SelectionQueries test = new SelectionQueries();
		QueryResult apparater = test.getApparater();
		QueryResult okt = test.getOktNotat(4);
		QueryResult grupper = test.getOvelseGrupper();
		
		for(List<String> row : apparater) {
			for(String col : row) {
				System.out.print(col + " ");
			}
			System.out.print("\n");
		}
		for(List<String> row1 : okt) {
			for(String col : row1) {
				System.out.print(col + " ");
			}
			System.out.print("\n");
		}
		
	}
}
