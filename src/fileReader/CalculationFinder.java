package fileReader;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@SuppressWarnings("unused")
public class CalculationFinder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			File inputFile = new File("c:/dev/book1.twb");
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(inputFile);
			
			NodeList nList = doc.getElementsByTagName("calculation");
			
			for (int i = 0; i < nList.getLength(); i++ ) {
				
				if (nList.item(i).getParentNode().getParentNode().getNodeName() == "datasource") {
					System.out.println(nList.item(i).getParentNode().getAttributes().getNamedItem("name"));
					System.out.println(nList.item(i).getAttributes().getNamedItem("formula"));
					System.out.println(nList.item(i).getParentNode().getAttributes().getNamedItem("caption"));
					System.out.println(nList.item(i).getParentNode().getNodeName());
					System.out.println(nList.item(i).getParentNode().getParentNode().getNodeName() + "\n");
				}
				
			}
			
			
			
			System.out.println(nList.getLength());
			
			
			System.out.println("success");
			
		} catch (Exception e) {
			
		}
		

	}

}
