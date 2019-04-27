package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import actions.ProficiencyValueManipulator;
import stats.ProficiencyBonus;

public class PanelProficiency extends JPanel{

	private ProficiencyBonus proficiencyBonus;
	private static final Dimension PREF_SIZE = new Dimension(290, 80);
	
	
	public PanelProficiency(ProficiencyBonus proficiencyBonus) {
		super();
		this.proficiencyBonus = proficiencyBonus;
		buildPanel();
	}

	private void buildPanel() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		JLabel lblName = new JLabel(proficiencyBonus.getName());
		this.add(lblName,gbc);
		gbc.gridx++;
		
		ProficiencyValueManipulator pvm = new ProficiencyValueManipulator(proficiencyBonus);
		
		JButton btnMinus = new JButton("-");
		btnMinus.addActionListener(pvm);
		this.add(btnMinus, gbc);
		
		JLabel lblValue = proficiencyBonus.getValueField();
		this.add(lblValue, gbc);
		
		JButton btnPlus = new JButton("+");
		btnPlus.addActionListener(pvm);
		this.add(btnPlus, gbc);
		
		this.setLayout(new GridBagLayout());		
		this.setPreferredSize(PREF_SIZE);
	}
	
}
