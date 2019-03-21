package trainingdiary;

import java.sql.SQLException;

public class LagOvelseIOktCtrl extends DBConn {
	private OvelseIOkt ovelseIOkt;
	private int muligOvelseId = -1, muligOktId = -1;
	
	public LagOvelseIOktCtrl() {
		connect();
	        // La laging av avtale være en transaksjon
	        try {
	            conn.setAutoCommit(false);
	        } catch (SQLException e) {
	            System.out.println("db error during setAutoCommit of LagOvelseoktrCtrl="+e);
	            return;
	        }
	}
	
	public void lagOvelseIOkt(int ovelseId, int oktId) {
		this.muligOktId = oktId;
		this.muligOvelseId = ovelseId;
		this.ovelseIOkt = new OvelseIOkt(this.muligOvelseId, this.muligOktId);
	}
	
	public void fullforOvelseIokt() {
		this.ovelseIOkt.save(conn);
		try {
			conn.commit();
		} catch (SQLException e) {
			System.out.println("DB error during commit of lagOvelseIoktrCtrl: " + e);
			return;
		}
	}
	
	public String toString() {
		return "OvelseId:\t" + this.muligOvelseId + "\n"
				+ "oktId:\t" + this.muligOktId;
	}
}
