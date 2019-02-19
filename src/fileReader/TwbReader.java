package fileReader;

import java.io.IOException;
import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;



public class TwbReader {
	
	static void directoryReader(File theFile, String currentPath) throws IOException {
		
		File[] allItems = theFile.listFiles();
		for (File item: allItems) {
			if(item.isFile() && item.getName().toLowerCase().endsWith(".twb")) {
				
				// get the name of the file we're going to use as the input stream
				String inputFile = item.getParent() + "\\" + item.getName();
				System.out.println(inputFile);
				try {
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(item);
					doc.getDocumentElement().normalize();
					
					NodeList dsList = doc.getElementsByTagName("datasources");
					Element dataSources = (Element) dsList.item(0);  // the actual datasources are always the first element
					
					NodeList dSources = dataSources.getElementsByTagName("datasource");
					
					for (int i = 0; i < dSources.getLength(); i++) {
						Element source = (Element) dSources.item(i);
						NodeList columns = source.getElementsByTagName("column");
						System.out.print("Datasource: " + source.getAttribute("name"));
						System.out.println(" with " + columns.getLength() + " columns.");
					}
					
					/*for (int temp = 0; temp < nList.getLength(); temp++) {
			            Node nNode = nList.item(temp);
			            System.out.println("\nCurrent Element :" + nNode.getNodeName());
			            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			               Element eElement = (Element) nNode;
			               System.out.println("Formula: " + eElement.getAttribute("formula"));
			               System.out.println(nNode.getParentNode().getAttributes().getNamedItem("name"));
			               System.out.println(nNode.getParentNode().getAttributes().getNamedItem("caption"));
			               System.out.println(nNode.getParentNode().getParentNode().getAttributes().getNamedItem("name"));
			               
			               /*System.out.println("First Name : " 
			                  + eElement
			                  .getElementsByTagName("firstname")
			                  .item(0)
			                  .getTextContent());
			            }
			         }	*/				
				} 
				catch (Exception e) { System.out.println("something went wrong"); } 
				finally { }
			}
			if(item.isDirectory()) {
				directoryReader(item, currentPath);
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		
		long startTime;
		long endTime;
		
		startTime = System.currentTimeMillis();
		
		File folder = new File("c:/dev");
		File writedir = new File("c:/dev/dev");
		
		directoryReader(folder, "dev");
		
		endTime = System.currentTimeMillis();
		
		System.out.println(endTime - startTime);
		
		DataSource danny = new DataSource();

	}

}
