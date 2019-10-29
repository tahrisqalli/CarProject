package bba.cars.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JPanel;

import bba.cars.models.voiture;
import bba.cars.xml.VoitureDOMParser;
import bba.cars.xml.XMLNode;

public class FormPanel extends JPanel implements ActionListener {
	JButton b;
	Hashtable<String,LabeledTextField> htable;
	//private int labelWidth=100;

	public FormPanel() 
	{
		
		
	}
	/*
	public void addTextField(String label, int size, int labelWidth)
	{
		LabeledTextField t1 = new LabeledTextField(label, size, labelWidth);
		
	}
	*/
	
	public FormPanel(Class<?> c)
	{
		 b = new JButton("Ajouter");
		 htable=new Hashtable<String,LabeledTextField>();  //
		 
		Field f[] = c.getDeclaredFields();
		for (int i = 0; i < f.length; i++)
		{
			String name = f[i].getName();
			String label;
			int labelWidth;
			int size = 20;
			bba.cars.annotations.Field field= f[i].getAnnotation(bba.cars.annotations.Field.class);
			
		
			
			try {
				label = field.label();
				size = field.size();
				labelWidth=field.labelWidth();
				
				LabeledTextField lb1 =new LabeledTextField(label,size,labelWidth);
				add(lb1);
				htable.put(label,lb1);  //
				
			}catch(Exception e)
			{
				
			}
			
			
			
		}
		add(b);   //
		b.addActionListener(this);  //
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
			voiture e=new voiture();
			e.setId(Integer.parseInt(htable.get("identificateur").getValue()));
			e.setMarque(htable.get("marque").getValue());
			e.setVitesse(Integer.parseInt(htable.get("vitesse").getValue()));
			e.setConsommation(Double.parseDouble(htable.get("consommation").getValue()));
			System.out.println("aaaa"+e);
			System.out.println("*********new**********"+e);
			XMLNode xml1=new XMLNode("resources/voitures.xml");
			xml1.addElement(e.getClass(),e.getId()+"", e.getMarque(), e.getVitesse()+"", e.getConsommation()+"");
			
		
		
		
		
	}


}
