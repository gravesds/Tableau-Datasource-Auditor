package fileReader;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.util.*;

@SuppressWarnings("unused")

public class TwbXMLParser {
	public static ArrayList<TableauCalculation> parseFile(InputStream iStream) {
		
		ArrayList<TableauCalculation> tabCalcs = new ArrayList<TableauCalculation>();
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(iStream));
			NodeList nList = doc.getElementsByTagName("calculation");
			
			for (int i = 0; i < nList.getLength(); i++ ) {
				
				if (nList.item(i).getParentNode().getParentNode().getNodeName() == "datasource" && nList.item(i).getAttributes().getNamedItem("formula") != null) {
					
					String calcName = nList.item(i).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
					System.out.println("past calcname");
					String calcFormula = nList.item(i).getAttributes().getNamedItem("formula").getNodeValue();
					System.out.println("past formula");
					String calcCaption = "NA";
					Node noder = nList.item(i).getParentNode().getAttributes().getNamedItem("caption");
					if (noder != null) { calcCaption = noder.getNodeValue().toString();}	
					System.out.println("past caption");				
					TableauCalculation tableauCalc = new TableauCalculation("workbook", "version", calcName, calcFormula, calcCaption);
					
					tabCalcs.add(tableauCalc);
					tableauCalc.printCalculation();
				}
			}
			System.out.println("Registered " + tabCalcs.size() + " total calculations in the workbook.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return(tabCalcs);
	}

}
