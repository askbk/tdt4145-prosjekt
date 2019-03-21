package trainingdiary;

import java.sql.*;
import java.util.*;

public class LagOvelseGrupperCtrl extends DBConn {
	private OvelseGrupper ovelsegrupper;
	private String muligNavn;
	
	public LagOvelseGrupperCtrl() {
        connect();
        // La laging av avtale være en transaksjon
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAutoCommit of LagOvelseGrupperCtrl="+e);
            return;
        }
	}
	
	public void lagOvelseGrupper(String navn) {
		this.ovelsegrupper = new OvelseGrupper(navn);
		this.muligNavn = navn;

	}
	
	public void fullforOvelseGrupper() {
		this.ovelsegrupper.save(conn);
		try {
			conn.commit();
		} catch (SQLException e) {
			System.out.println("DB error during commit of lagOvelseGrupperCtrl: " + e);
			return;
		}
	}
	public String toString(){
		return muligNavn; 
	}
}
