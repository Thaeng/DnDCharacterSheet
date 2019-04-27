package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DnDMenu extends JMenuBar{

	private JMenu file = new JMenu("File...");
	
	private JMenuItem load = new JMenuItem("Load");
	private JMenuItem save = new JMenuItem("Save");
	
	
	public DnDMenu() {
		super();
		buildMenu();		
	}
	
	private void buildMenu() {
		
		file.add(load);
		file.add(save);
		
		this.add(file);
	}

	public JMenuItem getLoad() {
		return load;
	}

	public JMenuItem getSave() {
		return save;
	}
	
	
}
