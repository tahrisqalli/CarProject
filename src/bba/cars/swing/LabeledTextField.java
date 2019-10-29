package bba.cars.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabeledTextField extends JPanel{
	
	

	public LabeledTextField(String label, int size)
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		if(!label.contains(":")) label=label+":";
		JLabel l1=new JLabel(label);
		JTextField t1=new JTextField(size);
		add(l1);
		add(t1);
		
		
	}
	public LabeledTextField(String label, int size, int labelWidth)
	{
		this(label,size);
		JLabel l1 =(JLabel)getComponent(0);
		
		l1.setPreferredSize(new Dimension(labelWidth, l1.getPreferredSize().height));
		
		
	}
	public String getValue()
	{
		
		return ((JTextField)getComponent(1)).getText();
	}
	public void setValue(Object value) 
	{
			((JTextField)getComponent(1)).setText(value.toString());
	}
	

}
