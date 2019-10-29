package bba.cars.swing;

import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bba.cars.models.voiture;
import bba.cars.xml.VoitureDOMParser;

public class TabPannel extends JPanel
{
	JTable t;
	Vector<voiture> voitures;
	VoitureDOMParser vdp;
	Vector<String> intitulee;
	Vector<String> voi;
	private JScrollPane sc;
	
	
	


	public TabPannel(String src) 
	{
		Vector<voiture> voitures = new Vector<>();
		VoitureDOMParser vdp = new VoitureDOMParser(src);
		
		voitures = vdp.getVoitures();
		
		Vector<String> intitulee = new Vector<>();
		
		Vector<Vector<String>> stringVoi = new Vector<>();
		
		
		
		intitulee.add("id");
		intitulee.add("marque");
		intitulee.add("vitesse");
		intitulee.add("consommation");
		
		for (voiture v : voitures)
		{
			Vector<String> voi = new Vector<>();
			
			voi.add(v.getId()+"");
			voi.add(v.getMarque());
			voi.add(v.getVitesse()+"");
			voi.add(v.getConsommation()+"");
			
			
			stringVoi.add(voi);
			
		}
		t= new JTable(stringVoi,intitulee);
		 sc = new JScrollPane(t);
		 add(sc);
		
		
		
	}

}
