package stats;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

public abstract class Stat {

	private int value;
	private StatNames name;
	private ArrayList<SubStat> substats = new ArrayList<>();
	private JLabel valueField = new JLabel();
	private JLabel bonusField = new JLabel();
	
	public Stat(int value, StatNames name) {
		this.value = value;
		this.name = name;
		this.update();
	}
	
	public int getBonus() {
		int bonus = -5;
		int iterator = 0;
		
		while(iterator < this.value) {
			iterator += 2;
			if(iterator <= this.value) {
				bonus++;				
			}
		}
		
		return bonus;
	}

	private void update() {
		valueField.setText(""+this.getValue());
		bonusField.setText(""+this.getBonus());
		substats.stream().forEach(s -> s.setValue(getBonus()));
	}
	
	public int getValue() {
		return value;
	}

	public ArrayList<SubStat> getSubstats() {
		return substats;
	}
	
	public String getName() {
		return name.getName();
	}
	
	public void setValue(int value) {
		if(value >= 0) {
			this.value = value;
			this.update();
		}
	}

	public JLabel getValueField() {
		return valueField;
	}

	public JLabel getBonusField() {
		return bonusField;
	}
}
