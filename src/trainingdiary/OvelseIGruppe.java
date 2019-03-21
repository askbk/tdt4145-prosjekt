package trainingdiary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OvelseIGruppe extends ActiveDomainObject {
	private int gruppeId, ovelseId;

	public OvelseIGruppe(int ovelseId, int gruppeId) {
		this.ovelseId = ovelseId;
		this.gruppeId = gruppeId;
	}

	@Override
	public void initialize(Connection conn) {
		try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select gruppenavn from Gruppe where GruppeId=" + gruppeId);
            while (rs.next()) {
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
            stmt.executeUpdate("INSERT INTO OvelseIGruppe (GruppeId, OvelseId) "
            				 + "VALUES (" + this.gruppeId + ", " + this.ovelseId + ")");

        } catch (Exception e) {
            System.out.println("db error during insert of OvelseGrupper="+e);
            return;
        }

	}
}
