package view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JOptionPane;

public class PopupMessage{

	public static void popException(String message, Exception ex) {
		popMessage(message + "\n" + convertExceptionToString(ex));
	}
	
	public static void popMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	
	private static String convertExceptionToString(Exception ex) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    ex.printStackTrace(ps);
	    ps.close();
	    return baos.toString();
	}
}
