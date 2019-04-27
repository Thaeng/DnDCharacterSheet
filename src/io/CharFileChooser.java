package io;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class CharFileChooser extends JFileChooser{

	private static CharFileChooser instance;
	private static final String FILE_EXTENSION = "char";
	
	private String defaulPath;
	
	private CharFileChooser() {
		defaulPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		JOptionPane.showMessageDialog(null, defaulPath);
		this.setCurrentDirectory(new File(defaulPath));
	}
	
	public static CharFileChooser getInstance() {
		if(instance == null) {
			instance = new CharFileChooser();
			FileNameExtensionFilter fnef = new FileNameExtensionFilter("CharacterFile *." + FILE_EXTENSION, FILE_EXTENSION);
			instance.setFileSelectionMode(JFileChooser.FILES_ONLY);
			instance.addChoosableFileFilter(fnef);
			instance.setFileFilter(fnef);
		}
		return instance;
	}
	
	public String askForSavePath() {
		int returnVal = instance.showSaveDialog(null);
		String path = null;
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			path = adjustPath(instance.getSelectedFile().getPath());
		}
		return path;
	}
	
	public String askForLoadPath() throws IOException {
		int returnVal = instance.showOpenDialog(null);
		String path = null;
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			path = instance.getSelectedFile().getPath();
			
			if(!validFilePath(path)) {
				throw new IOException("The selected File is not a CharacterFile");
			}
		}
		
		return path;
	}
	
	private boolean validFilePath(String path) {
		boolean valid = false;
		
		File file = new File(path);
		if(file.exists() && file.isFile()) {
			valid = true;
		}
		
		return valid;
	}
	
	private String adjustPath(String path) {
		if(path.endsWith("."+FILE_EXTENSION)) {
			return path;
		}else {
			return path + "."+FILE_EXTENSION;
		}
	}
	
	
}
