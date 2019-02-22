package fileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@SuppressWarnings("unused")
public class CalculationFinder {
	
	public static ArrayList<TableauWorkbook> tWorkbooks = new ArrayList<TableauWorkbook>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long startTime;
		long endTime;
		
		
		
		startTime = System.currentTimeMillis();
		
		File folder = new File("c:/dev");
		File writedir = new File("c:/dev/dev");
		
		
		
		
		
		try {
			File inputFile = new File("c:/dev/Agent - live.twb");
			dirReader(folder, "dev");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		endTime = System.currentTimeMillis();
		
		System.out.println(endTime - startTime);
		System.out.println(tWorkbooks.get(0).getWorkbookName());
		System.out.println(tWorkbooks.get(1).getWorkbookName());
		System.out.println(tWorkbooks.get(2).getWorkbookName());
		System.out.println(tWorkbooks.get(0).getCalculations().get(10).formula);
		System.out.println(tWorkbooks.size());
	}
	
	public static ArrayList<TableauCalculation> twbRead(String fileName) {
		
		ArrayList<TableauCalculation> calc = new ArrayList<TableauCalculation>();
		try {
			File inputFile = new File(fileName);

			InputStream iStream = new FileInputStream(inputFile);
			calc = TwbXMLParser.parseFile(iStream);
			iStream.close();
			System.out.println("success");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return calc;
	}
	
	public static ArrayList<TableauCalculation> twbxRead(String fileName) {
		
		ArrayList<TableauCalculation> calc = new ArrayList<TableauCalculation>();
		try {
			ZipFile zipFile = new ZipFile(fileName);
			
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			
			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				String name = entry.getName();
				
				if (name.endsWith(".twb")) {
					InputStream stream = zipFile.getInputStream(entry);
					TwbXMLParser.parseFile(stream);
					stream.close();
					System.out.println(name);
				}
			}
			zipFile.close();
		} catch (Exception e) {
			
		}
		return calc;
	}
	
	static void dirReader(File theFile, String currentPath) throws IOException {
		
		File[] allItems = theFile.listFiles();
		for (File item: allItems) {
			if(item.isFile() && item.getName().toLowerCase().endsWith(".twb")) {
				// get the name of the file we're going to use as the input stream
				String fileWithPath = item.getParent() + "\\" + item.getName();
				TableauWorkbook twb = new TableauWorkbook(item.getName(), "2018.2", twbRead(fileWithPath));
				tWorkbooks.add(twb);
				
			}
			if(item.isFile() && item.getName().toLowerCase().endsWith(".twbx")) {
				
				// get the name of the file we're going to use as the input stream
				String fileWithPath = item.getParent() + "\\" + item.getName();
				TableauWorkbook twb = new TableauWorkbook(item.getName(), "2018.2", twbxRead(fileWithPath));
				tWorkbooks.add(twb);
				
			}
			if(item.isDirectory()) {
				dirReader(item, currentPath);
			}
		}
		
	}
}
