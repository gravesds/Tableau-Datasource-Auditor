package fileReader;

import java.util.regex.*;

@SuppressWarnings("unused")
public class TableauCalculation {
	String workbook;
	String tableauVersion;
	String name;
	String formula;
	String caption;
	
	TableauCalculation(String workbook, String tableauVersion, String name, String formula, String caption) {
		this.workbook = workbook;
		this.tableauVersion = tableauVersion;
		this.name = name;
		this.formula = formula;
		this.caption = caption;
	}
	
	boolean containsCalculation() {
		Pattern pattern = Pattern.compile("\\[Calculation_\\d+\\]");
		Matcher m = pattern.matcher(this.formula);
		return m.find();
	}
	
	void printCalculation() {
		System.out.println("Workbook: " + this.workbook);
		System.out.println("Versoin: " + this.tableauVersion);
		System.out.println("Field Name: " + this.name);
		System.out.println("Formula:\n" + this.formula);
		System.out.println("Calculation: " + this.caption);
		if (this.containsCalculation()) {System.out.println("This calculation references another calculation");}
		System.out.println();
		
	}
}
