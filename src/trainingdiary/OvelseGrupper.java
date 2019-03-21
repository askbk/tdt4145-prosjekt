package trainingdiary;

import java.sql.*;
import java.util.*;


public class OvelseGrupper extends ActiveDomainObject{
	private int gruppeId;
	private String navn;

	public OvelseGrupper(String navn) {
		this.navn = navn;
	}

	public String getNavn() {
		return this.navn;
	}

	@Override
	public void initialize(Connection conn) {
		try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select gruppenavn from Gruppe where GruppeId=" + gruppeId);
            while (rs.next()) {
                navn =  rs.getString("navn");
              }

        } catch (Exception e) {
            System.out.println("db error during select of OvelseGrupper= "+e);
            return;
        }

	}

	@Override
	public void refresh(Connection conn) {
		initialize (conn);
	}

	@Override
	public void save(Connection conn) {
		try {    
            Statement stmt = conn.createStatement(); 
            stmt.executeUpdate("INSERT INTO Gruppe (GruppeNavn) "
            				 + "VALUES ('" + this.navn + "')");

        } catch (Exception e) {
            System.out.println("db error during insert of OvelseGrupper="+e);
            return;
        }

	}




}
