package trainingdiary;

import java.sql.*;
import java.util.*;

public class LagApparatOvelseCtrl extends DBConn {
	private ApparatOvelse apparatovelse;
	private String muligNavn;
	private int muligApparatId;
	
	public LagApparatOvelseCtrl() {
        connect();
        // La laging av avtale være en transaksjon
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAutoCommit of LagApparatOvelseCtrl="+e);
            return;
        }
	}
	
	public void lagApparatOvelse(String navn, int apparatId) {
		this.apparatovelse = new ApparatOvelse(navn, apparatId);
		this.muligNavn = navn;
		this.muligApparatId = apparatId;
	}
	
	public void fullforApparatOvelse() {
		this.apparatovelse.save(conn);
		try {
			conn.commit();
		} catch (SQLException e) {
			System.out.println("DB error during commit of lagApparatOvelseCtrl: " + e);
			return;
		}
	}
	
	@Override
	public String toString(){
		return muligNavn + "\n" + muligApparatId; 
	}
}
