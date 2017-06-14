package com.cis;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
/**
 * @author gouthamjoshi
 *
 */
public class SelectPages {
	final static Logger logger = Logger.getLogger(SelectPages.class);
	private static final String SRC = "M:/Allgemein 5 - CLO Aufarbeitung/__US Deals/Wells Fargo Bank/Madison Park Funding IX/Madison Park IX - 2013/";
	public static final String DEST = "H:/Work/PDFs/";
	//public static final String SEARCH_TEXT="Portfolio Assets:  Purchase & Sale Activity";
	public static final String SEARCH_TEXT="Purchases of Collateral Debt Securities";
    public static boolean doPagesExist=true;
    //
    /**
     * Main method.
     * @param args no arguments needed
     * @throws DocumentException 
     * @throws IOException
     */
    public static void generatePDfs(FolderDetails folder) throws IOException, DocumentException {
         File directory = new File(folder.getSourceFolder());
        
         //get all the files from a directory
         File[] fList = directory.listFiles();
         logger.debug(fList.length+"---> Files from the ---> "+folder.getSourceFolder()+" will be processed");
         for (File file : fList){
             if (file.isFile()){
            	 logger.info("Processing file:"+file.getName());
            	 if (!file.getName().contains("Thumbs")){
            		 readFiles(file,folder);
				}
             }
         }
    }
   
	public static void readFiles(File file,FolderDetails folder) throws IOException{
    	logger.debug("Getting values");
    	//PdfReader reader = new PdfReader(filename.toString());
    	PdfReader reader = new PdfReader(folder.getSourceFolder()+file.getName());
    	doPagesExist=true;
    	try{
        reader.selectPages(determineRangeBySearchtext(file,folder));
        System.out.println("Do pages Exist:"+doPagesExist);
        logger.debug("Do pages Exist in the"+file.getName()+"--->"+doPagesExist);
        if(doPagesExist){
        manipulateWithCopy(reader,folder,file.getName());
        reader.close();
        }
        }
    	catch (IOException iox) {
    		logger.error("Exception Occured:"+iox);
		}
    	catch (DocumentException dex) {
    		logger.error("Exception Occured:"+dex);
		}
    	finally{
    		reader.close();
    	}
    }
    /**
     * Creates a new PDF based on the one in the reader
     * @param reader a reader with a PDF file
     * @throws IOException
     * @throws DocumentException
     */
    private static void manipulateWithCopy(PdfReader reader,FolderDetails folder,String inputFileName)
        throws IOException, DocumentException {
        int n = reader.getNumberOfPages();
        Document document = new Document();
        PdfCopy copy = new PdfCopy(document, new FileOutputStream(stringUtils.actualOutputFileName(folder,inputFileName)));
        document.open();
        for (int i = 0; i < n;) {
            copy.addPage(copy.getImportedPage(reader, ++i));
        }
        document.close();
    }
    public static String determineRangeBySearchtext(File file,FolderDetails folder) throws IOException
	{   
	    PdfReader reader = new PdfReader(folder.getSourceFolder()+file.getName());
	    StringBuilder range=new StringBuilder();
	    List<Integer> splitPages = new ArrayList<>();
	    for(int i=1;i<=reader.getNumberOfPages();i++)
	    {
	        String txt = PdfTextExtractor.getTextFromPage(reader, i);
	        if(txt.contains(folder.getSearchText()))
	        {
	            splitPages.add(i);
	        }
	    }
	    System.out.println(splitPages.size());
	    logger.debug("Pages with the "+folder.getSearchText()+"present--->"+splitPages.size());
	    if(splitPages.size()==0){
	    	doPagesExist=false;
	    }
	    if (splitPages != null && !splitPages.isEmpty()){
	    if(splitPages.size()<=2){
		range.append(splitPages.get(1));
	    }
	    else{
	    //generates range for the 2nd to the last element. ex:30-35
	    range.append(splitPages.get(1)).append("-").append(splitPages.get(splitPages.size()-1));
	    logger.debug("The pages ranging "+range+"will be in a new PDF in --->"+folder.getDestinationFolder());
	    }
	    }
	    System.out.println(range.toString());
		return range.toString();
	}
}