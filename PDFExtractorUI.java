package com.cis;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.itextpdf.text.DocumentException;

import javax.swing.JButton;
/**
 * @author gouthamjoshi
 *
 */
public class PDFExtractorUI {
	
	final static Logger logger=Logger.getLogger(PDFExtractorUI.class);
	private JFrame frame;
	private JTextField textField_sourceDir;
	private JTextField textField_destDir;
	private JLabel lblSearchText;
	private JTextField textField_searchText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PDFExtractorUI window = new PDFExtractorUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public PDFExtractorUI() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("PDF Extractor");
		frame.setBounds(100, 100, 816, 343);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSourceFolder = new JLabel("Source Folder");
		lblSourceFolder.setBounds(30, 41, 89, 29);
		frame.getContentPane().add(lblSourceFolder);
		
		textField_sourceDir = new JTextField();
		textField_sourceDir.setBounds(135, 45, 540, 20);
		frame.getContentPane().add(textField_sourceDir);
		textField_sourceDir.setColumns(10);
		
		JLabel lblDestinationFolder = new JLabel("Destination Folder");
		lblDestinationFolder.setBounds(30, 91, 89, 14);
		frame.getContentPane().add(lblDestinationFolder);
		
		textField_destDir = new JTextField();
		textField_destDir.setBounds(135, 88, 540, 20);
		frame.getContentPane().add(textField_destDir);
		textField_destDir.setColumns(10);
		
		lblSearchText = new JLabel("Search Text");
		lblSearchText.setBounds(30, 136, 89, 14);
		frame.getContentPane().add(lblSearchText);
		
		textField_searchText = new JTextField();
		textField_searchText.setBounds(135, 133, 540, 20);
		frame.getContentPane().add(textField_searchText);
		textField_searchText.setColumns(10);
		
		JButton btnGeneratePdfs = new JButton("Generate PDFs");
		btnGeneratePdfs.setBounds(187, 221, 121, 23);
		frame.getContentPane().add(btnGeneratePdfs);
		btnGeneratePdfs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField_sourceDir.getText().isEmpty()||textField_destDir.getText().isEmpty()||textField_searchText.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Data Missing");
				else
					JOptionPane.showMessageDialog(null, "Data will be processed");
				FolderDetails folder=new FolderDetails(stringUtils.modifyFolderPath(textField_sourceDir.getText()),stringUtils.modifyFolderPath(textField_destDir.getText()),textField_searchText.getText());
				try {
					SelectPages.generatePDfs(folder);
				} catch (IOException | DocumentException e1) {
					logger.error(e1.getStackTrace());
					System.out.println(e1.getStackTrace());
				}
				JOptionPane.showMessageDialog(null, "PDFs Successfully Processed");
			}
		} );
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(512, 221, 89, 23);
		frame.getContentPane().add(btnClear);
		btnClear.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				textField_sourceDir.setText(null);
				textField_destDir.setText(null);
				textField_searchText.setText(null);
			}
		});
	}
}
