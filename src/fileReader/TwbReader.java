package fileReader;

import java.io.IOException;
import java.io.File;
import java.util.Arrays;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;


public class TwbReader {
	
	static void directoryReader(File theFile, String currentPath) throws IOException {
		
		File[] allItems = theFile.listFiles();
		

		for (File item: allItems) {
			if(item.isFile() && item.getName().toLowerCase().endsWith(".twb")) {
				
				FileInputStream in = null;
				FileOutputStream out = null;
				
				
				
				// get the name of the file we're going to use as the input stream
				String inputFile = item.getParent() + "\\" + item.getName();
				System.out.println(inputFile);
				try {
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(item);
					doc.getDocumentElement().normalize();
					
					System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					NodeList nList = doc.getElementsByTagName("calculation");
					
					
					for (int temp = 0; temp < nList.getLength(); temp++) {
			            Node nNode = nList.item(temp);
			            System.out.println("\nCurrent Element :" + nNode.getNodeName());
			            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			               Element eElement = (Element) nNode;
			               System.out.println("Formula: " + eElement.getAttribute("formula"));
			               System.out.println(nNode.getParentNode().getAttributes().getNamedItem("name"));
			               System.out.println(nNode.getParentNode().getAttributes().getNamedItem("caption"));
			               System.out.println(nNode.getParentNode().getParentNode().getAttributes().getNamedItem("name"));
			               /*
			               System.out.println("First Name : " 
			                  + eElement
			                  .getElementsByTagName("firstname")
			                  .item(0)
			                  .getTextContent());
			               System.out.println("Last Name : " 
			                  + eElement
			                  .getElementsByTagName("lastname")
			                  .item(0)
			                  .getTextContent());
			               System.out.println("Nick Name : " 
			                  + eElement
			                  .getElementsByTagName("nickname")
			                  .item(0)
			                  .getTextContent());
			               System.out.println("Marks : " 
			                  + eElement
			                  .getElementsByTagName("marks")
			                  .item(0)
			                  .getTextContent());*/
			            }
			         }
					
					
					
					
				} catch (Exception e) {
					System.out.println("something went wrong");
				} finally {
					
				}
				
				//TODO:  figure out how to get the name for the output stream

				//String[] dirBreakout = item.getParent().split("\\\\", -1);
				//String[] newarr = Arrays.copyOfRange(dirBreakout,1,dirBreakout.length - 1);
				//System.out.print(dirBreakout[dirBreakout.length - 1] + "/");
				//System.out.println(item.getName());
				
				//for(String dir: newarr) { System.out.println(dir);}
				
			}
			if(item.isDirectory()) {
				directoryReader(item, currentPath);
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		
		long startTime;
		long endTime;
		
		//FileInputStream in = null;
		//FileOutputStream out = null;
		
		startTime = System.currentTimeMillis();
		
		/*try {
			in = new FileInputStream("c:/dev/book1.twb");
			out = new FileOutputStream("c:/dev/book1.xml");
			
			int c;
			
			while((c = in.read()) != -1) {
				out.write(c);
			}
		} finally {
			if(in != null) {
				in.close();
			}
			if(out != null) {
				out.close();
			}
		}*/
				
		File folder = new File("c:/dev");
		File writedir = new File("c:/dev/dev");
		
		directoryReader(folder, "dev");
		
		endTime = System.currentTimeMillis();
		
		System.out.println(endTime - startTime);
	}

}
