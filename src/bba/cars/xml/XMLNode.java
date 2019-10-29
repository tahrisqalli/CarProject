package bba.cars.xml;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLNode 
{
	private Node node;
	private NamedNodeMap attributte;
	private Document document;
	
	

	public XMLNode(Node node) 
	{
		super();
		this.node=node;
		this.attributte=node.getAttributes();
		
	}
	public XMLNode(String source)
	{
		/*pour récuperer une instance de la classe
		 *"DocumentBuilderFactory", une ligne suffit:
		 * 
		 * 
		 */
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		 
		 try
		 {
			 /* creation du parseur
			  */
			 DocumentBuilder builder = factory.newDocumentBuilder();
			 document = builder.parse(source);
			 node = document.getFirstChild();
			 //test
			 final Element racine = document.getDocumentElement();
			 System.out.println("la racine est: "+racine.getNodeName());
			 
			 while(node.getNodeType() !=Node.ELEMENT_NODE)
			 {
				 node=node.getNextSibling();
			 }
			 
		 }
		 catch(Exception e)
		 {
			 System.out.println("err:" + e.getMessage());
			 
		 }
		 
	}
	
	public Vector<XMLNode> getChildren()
	{
		Vector<XMLNode> children = new Vector<>();
		
		NodeList list = node.getChildNodes();
		
		for (int i = 0; i < list.getLength(); i++)
		{
			Node a = list.item(i);
		
			
			if(a.getNodeType()==Node.ELEMENT_NODE)
			{
				children.add(new XMLNode(a));
			}
			
		}
		return children;
	}
	public Vector<XMLNode> getChildren(String name)
	{
		Vector<XMLNode> children = new Vector<>();
		
		NodeList list = node.getChildNodes();
		
		for (int i = 0; i < list.getLength(); i++)
		{
			Node a = list.item(i);
		
			
			if(a.getNodeName().equals(name))
			{
				children.add(new XMLNode(a));
			}
			
		}
		return children;
	}
	
	
	
	
	
	
	
	public XMLNode getChild(String name)
	{
		NodeList list = node.getChildNodes();
		for (int i=0; i<list.getLength(); i++)
		{
			Node a = list.item(i);
			
			if(a.getNodeName().equals(name))
			{
				return new XMLNode(a);
			}
		}
		return null;
				
	}
	
	
	
	
	
	
	public String getAttribute(String name)
	{
		return attributte.getNamedItem(name).getNodeValue();
		//NamedNodeMap attribute.getNamedItem --> le contenu de l'attribut
    }
	
	
	
	
	
	
	
	
	public int getIntAttribute(String name)
	{
		String a = getAttribute(name);
		
		try
		{
			return Integer.parseInt(a);
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	public double getDoubleAttribute(String name)
	{
		String a = getAttribute(name);
		
		try
		{
			return Double.parseDouble(a);
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	
	public String getValue()
	{
		Node n = node.getFirstChild();
		if(n==null) return "";
		else return n.getNodeValue();
		
	}
	
	public boolean contains(String keyword)
	{
		if(getValue().toLowerCase().contains(keyword))
		{
			return true;
			
		}
		
		Vector<XMLNode> children = getChildren();
		for (XMLNode child : children)
		{
			if(child.contains(keyword))
				return true;	
		}
		
		return false;
	}
	
	public Vector<XMLNode> select(String keyword)
	{
		keyword=keyword.toLowerCase();
		Vector<XMLNode> children = new Vector<>();
		NodeList list = node.getChildNodes();
		
		for (int i = 0; i < list.getLength(); i++)
		{
			Node a= list.item(i);
			
			if(a.getNodeType()==Node.ELEMENT_NODE)
			{
				XMLNode n = new XMLNode(a);
				
				if(n.contains(keyword))
				{
					children.add(n);
				}
			}
			
			
		}
		
		return children;
		
	}
	public void addElement( Class<?> element, String ...value) 
	{
		Element rootElement=document.createElement(element.getSimpleName());//créé element à la fin du document
		Field[] fields=element.getDeclaredFields();
		Vector<String> labels=new Vector<String>();
		int i=0;
		for (Field field : fields) {
			bba.cars.annotations.Field a=field.getAnnotation(bba.cars.annotations.Field.class);
			labels.add(a.label());
		}
		for (String val : value) 
		{
			Element valu=document.createElement(labels.get(i));	
			valu.setTextContent(val);
			rootElement.appendChild(valu);
			i++;
		}
		node.appendChild(rootElement);
		
		try
		{
			
			Transformer trans=TransformerFactory.newInstance().newTransformer();
			trans.transform(new DOMSource(node), new StreamResult(new FileOutputStream("resources/voitures.xml")));
		} catch (Exception e) {System.out.println("erreur de stockage");}	
	}

}
