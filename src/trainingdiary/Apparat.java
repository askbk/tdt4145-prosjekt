package trainingdiary;

import java.sql.*;
import java.util.*;

public class Apparat extends ActiveDomainObject {
	private int apparadId = -1;
	private String navn;
	private String beskrivelse;
	
	public Apparat(String navn, String beskrivelse) {
		this.navn = navn;
		this.beskrivelse = beskrivelse;
	}
	
	public String getNavn() {
		return this.navn;
	}
	
	public String getBeskrivelse() {
		return this.beskrivelse;
	}

	@Override
	public void initialize(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select navn, beskrivelse from Apparat where ApparatID=" + apparatId);
            while (rs.next()) {
                this.navn =  rs.getString("navn");
                this.beskrivelse = rs.getString("beskrivelse");
            }

        } catch (Exception e) {
            System.out.println("db error during select of apparat= "+e);
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
            stmt.executeUpdate("insert into Apparat (navn, beskrivelse) values (" + this.navn + ", " + this.beskrivelse + ")");
        } catch (Exception e) {
            System.out.println("db error during insert of Apparat="+e);
            return;
        }
	}
}
