package trainingdiary;

import java.sql.*;

public class Okt extends ActiveDomainObject {
	private int oktId = -1;
	private String dato;
	private String tidspunkt;
	private String varighet;
	private int form;
	private int prestasjon;
	
	public Okt(String dato, String tidspunkt, String varighet, int form, int prestasjon) {
		this.dato = dato;
		this.tidspunkt = tidspunkt;
		this.varighet = varighet;
		this.form = form;
		this.prestasjon = prestasjon;
	}

	@Override
	public void initialize(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select Dato, Tidspunkt, Varighet, Personlig_form, Personlig_prestasjon"
            								+ "from Apparat "
            								+ "where ApparatId=" + this.oktId);
            while (rs.next()) {
                this.dato =  rs.getString("Dato");
                this.tidspunkt = rs.getString("Tidspunkt");
            }

        } catch (Exception e) {
            System.out.println("db error during select of økt= "+e);
            return;
        }
	}

	@Override
	public void refresh(Connection conn) {
		this.initialize(conn);
	}

	@Override
	public void save(Connection conn) {
        try {    
            Statement stmt = conn.createStatement(); 
            stmt.executeUpdate("insert into Økt (Dato, Tidspunkt, Varighet, Personlig_form, Personlig_prestasjon) "
            				+ "values ('" + this.dato + "', '" + this.tidspunkt + "', '" + this.varighet + "', '" + this.form + "', " + this.prestasjon +"')");
        } catch (Exception e) {
            System.out.println("db error during insert of Apparat="+e);
            return;
        }
	}
	
	
}
