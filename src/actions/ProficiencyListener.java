package actions;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import stats.SubStat;

public class ProficiencyListener implements ItemListener{

	private SubStat subStat;
	
	public ProficiencyListener(SubStat subStat) {
		super();
		this.subStat = subStat;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBox box = evaluateEventToGetButton(e);
		
		subStat.setProficient(box.isSelected());
		
	}
	
	private JCheckBox evaluateEventToGetButton(ItemEvent e) {
		if (e.getSource() instanceof JCheckBox) {
			return (JCheckBox) e.getSource();
		}
		return null;
	}

}
