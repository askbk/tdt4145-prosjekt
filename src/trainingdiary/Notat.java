package trainingdiary;

import java.sql.*;
import java.util.*;


public class Notat extends ActiveDomainObject{
	private int oktId;
	private String treningsFormaal;
	private String treningsOpplevelse;

	public Notat(String treningsFormaal, String treningsOpplevelse) {
		this.treningsFormaal = treningsFormaal;
		this.treningsOpplevelse = treningsOpplevelse;
	}

	public String getTreningsFormaal() {
		return this.treningsFormaal;
	}

	public String getTreningsOpplevelse() {
		return this.treningsOpplevelse;
	}

	@Override
	public void initialize(Connection conn) {
		try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select treningsformal, treningsOpplevelse from Notat where OktId=" + oktId);
            while (rs.next()) {
                treningsFormaal =  rs.getString("treningsformal");
                treningsOpplevelse = rs.getString("treningsopplevelse");
              }

        } catch (Exception e) {
            System.out.println("db error during select of Notat= "+e);
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
            stmt.executeUpdate("insert into Notat(treningsFormal,treningsOpplevelse) values (" + this.treningsFormaal + "," + this.treningsOpplevelse + ")");

        } catch (Exception e) {
            System.out.println("db error during insert of Notat="+e);
            return;
        }
		

	}




}
