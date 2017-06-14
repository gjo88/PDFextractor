/**
 * 
 */
package com.cis;

/**
 * @author gouthamjoshi
 *
 */
public class FolderDetails {
	private String sourceFolder;
	private String destFolder;
	private String searchText;
	
	public FolderDetails(String sourceFolder, String destFolder, String searchText) {
		this.sourceFolder=sourceFolder;
		this.destFolder=destFolder;
		this.searchText=searchText;
	}
	public String getSourceFolder(){
		return sourceFolder;
	}
	public void setSourceFolder(String sourceFolder) {
		this.sourceFolder = sourceFolder;
	}
	public String getDestinationFolder() {
		return destFolder;
	}
	public void setDestinationFolder(String destinationFolder) {
		this.destFolder = destinationFolder;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
