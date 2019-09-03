package cs.csf.dfc.poo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActiviteVille {
	private Date m_DateDebut;
	private String m_codeId;
	public Date get_DateDebut() {
		return m_DateDebut;
	}
	
	public String get_CODEID() {
		return m_codeId;
	}

	public String get_NomActivite() {
		return m_NomActivite;
	}

	private String m_NomActivite;

	public ActiviteVille(String p_codeId,String p_NomActivite, Date p_DateDebut) {
		this.m_NomActivite = p_NomActivite;
		this.m_DateDebut = p_DateDebut;
		this.m_codeId = p_codeId;
	}

	@Override
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return this.get_CODEID()+ " " + formatter.format(this.m_DateDebut) + " " + this.get_NomActivite();
	}
}
