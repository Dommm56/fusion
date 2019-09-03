package cs.csf.dfc.poo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.w3c.dom.css.Counter;

import cs.csf.dfc.poo.ActiviteVille;


public class Main {
	public static List<ActiviteVille> lireActivitesVille(XMLStreamReader p_Doc) throws Exception {
		List<ActiviteVille> res = new ArrayList<ActiviteVille>();
		p_Doc.next();
		while (p_Doc.isStartElement()) {
			if (p_Doc.getLocalName().equals("EVT")) {
				res.add(lireActiviteVille(p_Doc));
			}
			p_Doc.next();
			p_Doc.next();
		}
		return res;
	}

	private static ActiviteVille lireActiviteVille(XMLStreamReader p_Doc) throws Exception {
		p_Doc.next();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String codeId =null;
		Date dateDebut = null;
		String description = null;
		
		while (p_Doc.isStartElement()) {
			if (p_Doc.getLocalName().equals("CODEID")){
				p_Doc.next();
				codeId = p_Doc.getText();
				p_Doc.next();
			}
			else if (p_Doc.getLocalName().equals("DT01")) {
				p_Doc.next();
				String dateString = p_Doc.getText();
				dateDebut = formatter.parse(dateString);
				p_Doc.next();
			} else if (p_Doc.getLocalName().equals("TITRE")) {
				p_Doc.next();
				description = p_Doc.getText();
				p_Doc.next();
			} else {
				p_Doc.next();
				p_Doc.next();
			}
			p_Doc.next();
			p_Doc.next();
		}
		return new ActiviteVille(codeId,description, dateDebut);
	}

	public static void main(String[] args) throws Exception {

		//methode pour assembler la liste totale compose des 2 fonctions faireliste
		FaireListeFinale(FaireListe1(),FaireListe2());
	}
	
	private static void FaireListeFinale(List<ActiviteVille> faireListe1, List<ActiviteVille> faireListe2) throws Exception {
		Integer countInteger =0;
		List<ActiviteVille> listelibre = FaireListe1();
		List<ActiviteVille> listelibre2 = FaireListe2();
		listelibre.addAll(listelibre2);
		listelibre.sort((av1, av2) -> av1.get_DateDebut().compareTo(av2.get_DateDebut()));
		listelibre.forEach(System.out::println);
		
	}

	public static List<ActiviteVille> FaireListe1() throws Exception {
		FileReader input = null;
		input = new FileReader(new File(Main.class.getResource("/res/LOISIR_LIBRE_Standard.XML").getFile()));
		XMLStreamReader doc = XMLInputFactory.newInstance().createXMLStreamReader(input);
		// Lecture du XML
		List<ActiviteVille> listelibre = lireActivitesVille(doc);
		// Tri du XML
		listelibre.sort((av1, av2) -> av1.get_DateDebut().compareTo(av2.get_DateDebut()));
		listelibre.forEach(System.out::println);
		return listelibre;
	}
	public static List<ActiviteVille> FaireListe2() throws Exception{
		FileReader input2 = null;
		input2 = new FileReader(new File(Main.class.getResource("/res/LOISIR_PAYANT_Standard.XML").getFile()));
		XMLStreamReader doc2 = XMLInputFactory.newInstance().createXMLStreamReader(input2);
		// Lecture du XML
		List<ActiviteVille> listelibre2 = lireActivitesVille(doc2);
		// Tri du XML
		listelibre2.sort((av1, av2) -> av1.get_DateDebut().compareTo(av2.get_DateDebut()));
		listelibre2.forEach(System.out::println);
		return listelibre2;
	}
	
	public static void FaireListeFinale() throws Exception{
		
	}
}