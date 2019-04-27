package stats;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class SubStat {

	private int value;
	private String name;
	private boolean proficient;
	private int proficiencyBonus = 0;
	private JLabel valueField = new JLabel();
	private JCheckBox cbProficient = new JCheckBox();
	
	public SubStat(int value, String name, boolean proficient) {
		this.value = value;
		this.name = name;
		this.proficient = proficient;
		this.update();
	}

	private void update() {
		this.valueField.setText(""+this.getValue());
	}
	
	public int getValue() {
		return this.proficient ? value + proficiencyBonus : value;
	}

	public void setValue(int value) {
		this.value = value;
		this.update();
	}

	public String getName() {
		return name;
	}

	public boolean isProficient() {
		return proficient;
	}
	
	public void setProficient(boolean proficient) {
		this.proficient = proficient;
		this.update();
	}

	public JCheckBox getCbProficient() {
		return cbProficient;
	}

	public void setProficiencyBonus(int proficiencyBonus) {
		this.proficiencyBonus = proficiencyBonus;
		this.update();
	}

	public JLabel getValueField() {
		return valueField;
	}
	
	
}
