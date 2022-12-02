package ui;

import java.awt.Desktop;
import java.io.IOException;

public class DisplayPDF extends Object {
	public static String pathFilePDF = "";
	public DisplayPDF(String pathFile) {
		pathFilePDF = pathFile;
		java.io.File file = new java.io.File(pathFile);
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.open(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		DisplayPDF display = new DisplayPDF(pathFilePDF);
	}

}
