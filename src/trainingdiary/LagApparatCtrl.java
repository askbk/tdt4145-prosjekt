package trainingdiary;

import java.sql.*;
import java.util.*;

public class LagApparatCtrl extends DBConn {
	private Apparat apparat;
	private String muligNavn;
	private String muligBeskrivelse;
	
	public LagApparatCtrl() {
        connect();
        // La laging av avtale være en transaksjon
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAutoCommit of LagApparatCtrl="+e);
            return;
        }
	}
	
	public void lagApparat(String navn, String beskrivelse) {
		this.apparat = new Apparat(navn, beskrivelse);
		this.muligNavn = navn;
		this.muligBeskrivelse = beskrivelse;
	}
	
	public void fullførApparat() {
		this.apparat.save(conn);
		try {
			conn.commit();
		} catch (SQLException e) {
			System.out.println("DB error during commit of lagApparatCtrl: " + e);
			return;
		}
	}
}
