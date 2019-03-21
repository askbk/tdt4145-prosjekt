package trainingdiary;

import java.sql.*;
import java.util.*;

public class LagNotatCtrl extends DBConn {
	private Notat notat;
	private String muligtreningsformal;
	private String muligtreningsopplevelse;
	
	public LagNotatCtrl() {
        connect();
        // La laging av avtale være en transaksjon
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAutoCommit of LagFriOvelseCtrl="+e);
            return;
        }
	}
	
	public void lagNotat(String treningsformal, String treningsopplevelse) {
		this.notat = new Notat(treningsformal, treningsopplevelse);
		this.muligtreningsformal = treningsformal;
		this.muligtreningsopplevelse = treningsopplevelse;
	}
	
	public void fullforNotat() {
		this.notat.save(conn);
		try {
			conn.commit();
		} catch (SQLException e) {
			System.out.println("DB error during commit of lagNotatCtrl: " + e);
			return;
		}
	}
	public String toString(){
		return muligtreningsformal + 
				"\n" + muligtreningsopplevelse; 
	}
}
