package trainingdiary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FriOvelse extends Ovelse {
	private String beskrivelse;
	
	public FriOvelse(String navn,String beskrivelse) {
		this.navn = navn;	
		this.beskrivelse = beskrivelse;
	}
	
	public String getBeskrivelse() {
		return beskrivelse;
	}
	public String getNavn() {
		return this.navn;
	}


	@Override
	public void initialize(Connection conn) {
		try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select navn, beskrivelse"
            								+ "from FriOvelse "
            								+ "where ovelsesId=" + this.ovelsesId);
            while (rs.next()) {
            	this.navn = rs.getString("navn");
            	this.beskrivelse = rs.getString("beskrivelse");
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
	            int ovelseId = stmt.executeUpdate("insert into Øvelse (Navn) values ('" + this.navn + "')", Statement.RETURN_GENERATED_KEYS);
	            stmt.executeUpdate("insert into FriOvelse (ØvelseId, FriØvelseBeskrivelse) values ('" + ovelseId + "', '" + this.beskrivelse + "')");
	        } catch (Exception e) {
	            System.out.println("db error during insert of FriOvelse="+e);
	            return;
	        }		
	}

}
