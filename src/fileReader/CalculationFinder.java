package fileReader;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.*;

@SuppressWarnings("unused")
public class CalculationFinder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			File inputFile = new File("c:/dev/Agent - live.twb");
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(inputFile);
			
			NodeList nList = doc.getElementsByTagName("calculation");
			
			ArrayList<TableauCalculation> tabCalcs = new ArrayList<TableauCalculation>();
			
			for (int i = 0; i < nList.getLength(); i++ ) {
				
				if (nList.item(i).getParentNode().getParentNode().getNodeName() == "datasource") {
					
					String calcName = nList.item(i).getParentNode().getAttributes().getNamedItem("name").getNodeValue().toString();
					String calcFormula = nList.item(i).getAttributes().getNamedItem("formula").getNodeValue().toString();
					String calcCaption = "NA";
					Node noder = nList.item(i).getParentNode().getAttributes().getNamedItem("caption");
					if (noder != null) {
						calcCaption = noder.getNodeValue().toString();
						//System.out.println(nList.item(i).getParentNode().getAttributes().getNamedItem("caption").getNodeValue());
					}					
					TableauCalculation tableauCalc = new TableauCalculation("workbook", "version", calcName, calcFormula, calcCaption);
					
					tabCalcs.add(tableauCalc);
					tableauCalc.printCalculation();
				}
				
			}
			System.out.println("Registered " + tabCalcs.size() + " total calculations in the workbook.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

	}

}
