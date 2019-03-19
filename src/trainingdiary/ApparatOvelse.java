package trainingdiary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ApparatOvelse extends Ovelse{

private int apparatId;
	
	public ApparatOvelse(String navn,int apparatId) {
		this.navn = navn;		
		this.apparatId = apparatId;
	}
	
	public int getApparatId() {
		return apparatId;
	}
	public String getNavn() {
		return this.navn;
	}


	@Override
	public void initialize(Connection conn) {
		try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select navn, apparatId"
            								+ "from ApparatOvelse "
            								+ "where ovelsesId=" + this.ovelsesId);
            while (rs.next()) {
            	this.navn = rs.getString("navn");
            	this.apparatId = rs.getInt("apparatId");
            }

        } catch (Exception e) {
            System.out.println("db error during select of ovelse= "+e);
            return;
        }
	}


	@Override
	public void refresh(Connection conn) {
		this.initialize(conn);
		
	}

	@Override
	public void save(Connection conn) {
		  try {    
	            Statement stmt = conn.createStatement(); 
	            stmt.executeUpdate("insert into ApparatOvelse (Navn, apparatId) values ('" + this.navn + "', '" + this.apparatId + "')");
	        } catch (Exception e) {
	            System.out.println("db error during insert of ApparatOvelse="+e);
	            return;
	        }		
	}


}
