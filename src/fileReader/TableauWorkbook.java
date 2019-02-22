package fileReader;

import java.util.ArrayList;

public class TableauWorkbook {
	private String workbookName;
	private String version;
	ArrayList<TableauCalculation> calculations;// = new ArrayList<TableauCalculations>();;
	
	TableauWorkbook() {
		this.workbookName = "N/A";
		this.version = "N/A";
	}
	
	TableauWorkbook(String workbookName, String version) {
		this.workbookName = workbookName;
		this.version = version;
	}
	
	TableauWorkbook(String workbookName, String version, ArrayList<TableauCalculation> calculations) {
		this.workbookName = workbookName;
		this.version = version;
		this.calculations = calculations;
	}
	
	public void setWorkbookName(String workbookName ) {
		this.workbookName = workbookName;
	}
	
	public String getWorkbookName() {
		return this.workbookName;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getVersion() {
		return this.version;
	}
	
	public void setCalculations(ArrayList<TableauCalculation> calculations) {
		this.calculations = calculations;
	}
	
	public ArrayList<TableauCalculation> getCalculations() {
		return this.calculations;
	}
}
