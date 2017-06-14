/**
 * 
 */
package com.cis;

/**
 * @author gouthamjoshi
 *
 */
public final class stringUtils {
	
	private stringUtils(){}
	
	
	public static String actualOutputFileName(FolderDetails folder,String inputFileName){
		System.out.println("outputfilename"+inputFileName.substring(0, 4)+"_"+inputFileName.substring(5,7)+"_"+folder.getSearchText());
		return folder.getDestinationFolder()+inputFileName.substring(0, 4)+"_"+inputFileName.substring(5,7)+"_"+folder.getSearchText()+".pdf";//returning outputfilename
	}
	public static String modifyFolderPath(String folderPath){
		return folderPath.replaceAll("/", "\\");
	}
}
