package bba.cars.xml;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bba.cars.models.voiture;
import bba.cars.swing.FormPanel;
import bba.cars.swing.TabPannel;

public class TestCar extends JFrame {

	public TestCar() 
	{
		//Modification bba
		//exp01();
		exp02();
		exp01_2();
		
	}

	void exp01()
	{
		VoitureDOMParser parser = new VoitureDOMParser("resources/voitures.xml");
		Vector<voiture> voitures = parser.getVoitures();
		for (voiture author : voitures)
		{
			System.out.println(author);
		}
	}
	void exp01_2() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();// selon ce qu'on veut
		//setResizable(true);//Tricher
		setSize(500,500);
		setLocation(450,150);
		
	}
	void exp02()
	{
		
		JPanel container=new JPanel();
		TabPannel tp = new TabPannel("resources/voitures.xml");
		container.add(tp);
		
		FormPanel fp = new FormPanel(bba.cars.models.voiture.class);
		container.add(fp);
		setContentPane(container);
	}
	void exp03()
	{
		
	}
	public static void main(String[] args) {
		new TestCar();
	}
}
