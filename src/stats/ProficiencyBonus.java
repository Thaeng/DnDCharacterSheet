package stats;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

public class ProficiencyBonus {

	private String name = "Proficiency Bonus";
	private int value = 0;
	private JLabel valueField = new JLabel();
	private List<SubStat> substats;
	
	public ProficiencyBonus(List<Stat> stats, int value) {
		super();
		this.substats = new ArrayList<>();
		this.value = value;
		appendSubstatsFromStats(stats);
		update();
	}
	
	private void appendSubstatsFromStats(List<Stat> stats){
		stats
			.stream()
			.forEach(stat -> stat.getSubstats()
					.stream()
					.forEach(substat -> this.substats.add(substat)));
	}
	
	private void update() {
		valueField.setText(""+getValue());
		substats.stream().forEach(s -> s.setProficiencyBonus(getValue()));
	}
	
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		if(value >= 0) {
			this.value = value;
			update();
		}
	}
	public String getName() {
		return name;
	}
	public JLabel getValueField() {
		return valueField;
	}
}
