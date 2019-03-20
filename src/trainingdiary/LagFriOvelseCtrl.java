package trainingdiary;

import java.sql.*;
import java.util.*;

public class LagFriOvelseCtrl extends DBConn {
	private FriOvelse friovelse;
	private String muligNavn;
	private String muligBeskrivelse;
	
	public LagFriOvelseCtrl() {
        connect();
        // La laging av avtale være en transaksjon
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAutoCommit of LagFriOvelseCtrl="+e);
            return;
        }
	}
	
	public void lagApparatOvelse(String navn, String beskrivelse) {
		this.friovelse = new FriOvelse(navn, beskrivelse);
		this.muligNavn = navn;
		this.muligBeskrivelse = beskrivelse;
	}
	
	public void fullforApparatOvelse() {
		this.friovelse.save(conn);
		try {
			conn.commit();
		} catch (SQLException e) {
			System.out.println("DB error during commit of lagFriOvelseCtrl: " + e);
			return;
		}
	}
	public String toString(){
		return muligNavn + 
				"\n" + muligBeskrivelse; 
	}
}
