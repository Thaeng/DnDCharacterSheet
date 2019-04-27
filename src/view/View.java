package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import actions.ProficiencyListener;
import actions.StatManipulator;
import stats.Stat;

@SuppressWarnings("serial")
public class View extends JFrame{
	
	private ArrayList<JButton> btnStatIncrease = new ArrayList<>();
	
	private static Dimension dimBtn = new Dimension(45, 22);
	private static Font fontBtn = new Font("Serif", Font.BOLD, 20);
	private static final int HEIGHT = 110;
	
	public View(ArrayList<Stat> stats, PanelProficiency panelProficiency, DnDMenu dndMenu) {
		super();
		init(stats, panelProficiency, dndMenu);
	}
	
	public void init(ArrayList<Stat> stats, PanelProficiency panelProficiency, DnDMenu dndMenu) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		this.setJMenuBar(dndMenu);
		
		this.setLayout(new GridBagLayout());
		
		this.add(createMainStats(stats),gbc);
		
		gbc.gridx++;
		this.add(createSkills(stats),gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		this.add(panelProficiency, gbc);
		
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public JPanel createMainStats(ArrayList<Stat> stats) {
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		
		JPanel panel = new JPanel(new GridBagLayout());
		stats.stream().forEachOrdered(stat -> {
			panel.add(createMainStat(stat),gbc);
			gbc.gridy++;
		});
		return panel;		
	}
	
	public JPanel createMainStat(Stat stat) {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		JPanel panel = new JPanel(new GridBagLayout());		
		JLabel lblName = new JLabel(stat.getName());
		JLabel lblValue = stat.getValueField();		
		JLabel lblBonus = stat.getBonusField();	
		
		StatManipulator sm = new StatManipulator(stat);
		
		JButton btnPlus = new JButton("+");
		btnPlus.setPreferredSize(dimBtn);
		btnPlus.setFont(fontBtn);
		btnPlus.addActionListener(sm);
		
		JButton btnMinus = new JButton("-");
		btnMinus.setPreferredSize(dimBtn);
		btnMinus.setFont(fontBtn);
		btnMinus.addActionListener(sm);
		
		btnStatIncrease.add(btnPlus);
		btnStatIncrease.add(btnMinus);
		
		gbc.gridwidth = 2;
		panel.add(lblName, gbc);
		
		gbc.gridy++;
		panel.add(lblValue, gbc);
		
		gbc.gridy++;
		gbc.gridwidth = 1;
		panel.add(btnMinus, gbc);
		
		gbc.gridx++;
		panel.add(btnPlus, gbc);
		
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridy++;
		panel.add(lblBonus, gbc);
		
		panel.setBorder(new LineBorder(Color.BLACK, 1));
		
		panel.setPreferredSize(new Dimension(120, HEIGHT));
		
		return panel;		
	}
	
	public JPanel createSkills(ArrayList<Stat> stats) {
		
		GridBagConstraints gbcUber = new GridBagConstraints();
		gbcUber.anchor = GridBagConstraints.NORTHWEST;
		gbcUber.gridx = 0;
		gbcUber.gridy = 0;
		
		JPanel panel = new JPanel(new GridBagLayout());
		
		stats.stream().forEachOrdered(stat -> {
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			JPanel tmpPanel = new JPanel(new GridBagLayout());
			
			stat.getSubstats().stream().forEachOrdered(substat -> {
				JCheckBox cbProficient = substat.getCbProficient();
				cbProficient.addItemListener(new ProficiencyListener(substat));
				JLabel lblTotalBonus = substat.getValueField();
				JLabel lblName = new JLabel(substat.getName());
				
				gbc.weightx = -1;
				gbc.gridx = 0;
				tmpPanel.add(cbProficient,gbc);
				
				gbc.gridx++;
				tmpPanel.add(lblTotalBonus,gbc);
				
				gbc.gridx++;
				gbc.weightx = 100;
				tmpPanel.add(lblName,gbc);
				
				
				gbc.gridy++;
			});
			tmpPanel.setPreferredSize(new Dimension(170, HEIGHT));
			tmpPanel.setBorder(new LineBorder(Color.BLACK, 1));
			panel.add(tmpPanel,gbcUber);
			gbcUber.gridy++;
		});
		panel.setPreferredSize(new Dimension(170, HEIGHT*6));
		return panel;
	}
}
