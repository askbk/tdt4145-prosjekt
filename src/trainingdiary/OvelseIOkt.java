package trainingdiary;

import java.sql.Connection;
import java.sql.Statement;

public class OvelseIOkt extends ActiveDomainObject {
	private int oktId, ovelseId, sett = -1;
	private float kilo;
	
	public OvelseIOkt(int oktId, int ovelseId, float kilo, int sett) {
		this.oktId = oktId;
		this.ovelseId = ovelseId;
		this.kilo = kilo;
		this.sett = sett;
	}
	
	public OvelseIOkt(int oktId, int ovelseId) {
		this.oktId = oktId;
		this.ovelseId = ovelseId;
	}

	@Override
	public void initialize(Connection conn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Connection conn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Connection conn) {
		try {    
            Statement stmt = conn.createStatement();
            if (sett == -1) {
            	stmt.executeUpdate("INSERT INTO OvelseIOkt (OktId, OvelseId, Kilo, sett) "
       				 + "VALUES (" + this.oktId + ", " + this.ovelseId + ", " + this.kilo + ", " + this.sett + ")");
            } else {
            	stmt.executeUpdate("INSERT INTO OvelseIOkt (OktId, OvelseId) "
       				 + "VALUES (" + this.oktId + ", " + this.ovelseId + ")");
            }
            

        } catch (Exception e) {
            System.out.println("db error during insert of OvelseGrupper="+e);
            return;
        }
	}
}
