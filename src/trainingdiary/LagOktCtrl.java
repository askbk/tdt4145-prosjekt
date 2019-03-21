package trainingdiary;

import java.sql.*;
import java.util.*;

public class LagOktCtrl extends DBConn {
	private Okt okt;
	private String muligDato;
	private String muligTidspunkt;
	private String muligVarighet;
	private int muligForm;
	private int muligPrestasjon;

	public LagOktCtrl() {
        connect();
        // La laging av avtale være en transaksjon
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAutoCommit of LagOktCtrl="+e);
            return;
        }
	}

	public void lagOkt(String dato, String tidspunkt, String varighet, int form, int prestasjon) {
		this.okt = new Okt(dato, tidspunkt, varighet, form, prestasjon);
		this.muligDato = dato;
		this.muligTidspunkt = tidspunkt;
		this.muligVarighet = varighet;
		this.muligForm = form;
		this.muligPrestasjon = prestasjon;
	}

	public void fullforOkt() {
		this.okt.save(conn);
		try {
			conn.commit();
		} catch (SQLException e) {
			System.out.println("DB error during commit of LagOktCtrl: " + e);
			return;
		}
	}
	public String toString(){
		return muligDato +
				"\n" + muligTidspunkt + 
				"\n" + muligVarighet +
				"\n" + muligForm +
				"\n" + muligPrestasjon;
		}
	}
