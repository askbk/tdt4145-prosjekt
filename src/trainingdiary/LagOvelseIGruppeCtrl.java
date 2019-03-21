package trainingdiary;

import java.sql.SQLException;

public class LagOvelseIGruppeCtrl extends DBConn {
	private OvelseIGruppe ovelseIGruppe;
	private int muligOvelseId = -1, muligGruppeId = -1;
	
	public LagOvelseIGruppeCtrl() {
		connect();
	        // La laging av avtale være en transaksjon
	        try {
	            conn.setAutoCommit(false);
	        } catch (SQLException e) {
	            System.out.println("db error during setAutoCommit of LagOvelseGrupperCtrl="+e);
	            return;
	        }
	}
	
	public void lagOvelseIGruppe(int ovelseId, int gruppeId) {
		this.muligGruppeId = gruppeId;
		this.muligOvelseId = ovelseId;
		this.ovelseIGruppe = new OvelseIGruppe(this.muligGruppeId, this.muligOvelseId);
	}
	
	public void fullforOvelseIGruppe() {
		this.ovelseIGruppe.save(conn);
		try {
			conn.commit();
		} catch (SQLException e) {
			System.out.println("DB error during commit of lagOvelseIGrupperCtrl: " + e);
			return;
		}
	}
	
	public String toString() {
		return "OvelseId:\t" + this.muligOvelseId + "\n"
				+ "GruppeId:\t" + this.muligGruppeId;
	}
}
