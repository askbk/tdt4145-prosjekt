package trainingdiary;

import java.sql.*;

public class Okt extends ActiveDomainObject {
	private int oktId = -1;
	private String dato;
	private String tidspunkt;
	private String varighet;
	private int form;
	private int prestasjon;
	private String treningsFormaal = null;
	private String treningsOpplevelse = null;
	
	public Okt(String dato, String tidspunkt, String varighet, int form, int prestasjon) {
		this.dato = dato;
		this.tidspunkt = tidspunkt;
		this.varighet = varighet;
		this.form = form;
		this.prestasjon = prestasjon;
	}
	
	public Okt(String dato, String tidspunkt, String varighet, int form, int prestasjon, String treningsFormaal, String treningsOpplevelse) {
		this.dato = dato;
		this.tidspunkt = tidspunkt;
		this.varighet = varighet;
		this.form = form;
		this.prestasjon = prestasjon;
		this.treningsFormaal = treningsFormaal;
		this.treningsOpplevelse = treningsOpplevelse;
	}

	@Override
	public void initialize(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select Dato, Tidspunkt, Varighet, Personlig_form, Personlig_prestasjon"
            								+ "from Okt "
            								+ "where OktId=" + this.oktId);
            while (rs.next()) {
                this.dato =  rs.getString("Dato");
                this.tidspunkt = rs.getString("Tidspunkt");
            }

        } catch (Exception e) {
            System.out.println("db error during select of okt= "+e);
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
            oktId = stmt.executeUpdate("insert into Okt (oktDato, oktTidspunkt, oktVarighet, PersonligForm, PersonligPrestasjon) "
            				+ "values ('" + this.dato + "', '" + this.tidspunkt + "', '" + this.varighet + "', '" + this.form + "', '" + this.prestasjon +"')", Statement.RETURN_GENERATED_KEYS);
           if (treningsFormaal != null) {
        	
       			stmt.executeUpdate("INSERT INTO Notat (oktId, treningsFormal, treningsOpplevelse) "
       										+ "VALUES (" + oktId + ", '"+ this.treningsFormaal + "', '" + this.treningsOpplevelse + "')");
       	}
        
        } catch (Exception e) {
            System.out.println("db error during insert of Okt="+e);
            return;
        }
        
	}
	
}
