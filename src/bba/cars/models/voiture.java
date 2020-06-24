package bba.cars.models;

import bba.cars.annotations.Field;

public class voiture {
	//it has to be changed by english names!!
	//it has to be changed by english names2!!
	@Field(label="identificateur")
	int id;
	
	@Field(label="marque")
	String marque;
	
	@Field(label="vitesse")
	int vitesse;
	
	@Field(label="consommation")
	double consommation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "voiture [marque=" + marque + ", vitesse=" + vitesse + ", consommation=" + consommation + "]";
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public double getConsommation() {
		return consommation;
	}

	public void setConsommation(double consommation) {
		this.consommation = consommation;
	}

	public voiture()
	{
		
	}

}
//bba modif 6-24-2020
