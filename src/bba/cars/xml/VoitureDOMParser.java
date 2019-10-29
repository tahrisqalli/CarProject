package bba.cars.xml;

import java.util.Vector;



import bba.cars.models.voiture;

public class VoitureDOMParser {
	
	private Vector<voiture> voitures;
	
	public VoitureDOMParser()
	{
		
		voitures = new Vector<>();
		
	}
	public VoitureDOMParser(String source)
	{
		
		voitures = new Vector<>();
		XMLNode root = new XMLNode(source);
		
		//Vector<XMLNode> list = root.select("benz");
		Vector<XMLNode> list = root.getChildren();
		//Vector<XMLNode> list = root.getChildren("v");
		
		for(XMLNode a:list)
		{
			voiture voiture = new voiture();
			voitures.add(voiture);
			
			voiture.setId(a.getIntAttribute("identificateur"));
			voiture.setMarque(a.getChild("marque").getValue());
			voiture.setVitesse(Integer.parseInt(a.getChild("vitesse").getValue()));
			voiture.setConsommation(Double.parseDouble(a.getChild("consommation").getValue()));
			
			
			
		}
		
	
		
		
	}
	public Vector<voiture> getVoitures() {
		return voitures;
	}
	public void setVoitures(Vector<voiture> voitures) {
		this.voitures = voitures;
	}

}
