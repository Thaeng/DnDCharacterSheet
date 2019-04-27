package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import stats.ProficiencyBonus;

@SuppressWarnings("serial")
public class ProficiencyValueManipulator extends AbstractAction{

	private ProficiencyBonus proficiencyBonus;
	private static final String INCREASE = "+";
	private static final String DECREASE = "-";

	public ProficiencyValueManipulator(ProficiencyBonus proficiencyBonus) {
		super();
		this.proficiencyBonus = proficiencyBonus;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton pressedButton = evaluateEventToGetButton(e);
		adjustStatValue(getAdjustmentToValue(pressedButton));

	}

	private JButton evaluateEventToGetButton(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			return (JButton) e.getSource();
		}
		return null;
	}

	private int getAdjustmentToValue(JButton btn) {
		int value;

		switch (btn.getText()) {
		case INCREASE:
			value = 1;
			break;
		case DECREASE:
			value = -1;
			break;
		default:
			value = 0;
			break;
		}

		return value;
	}

	private void adjustStatValue(int value) {
		proficiencyBonus.setValue(proficiencyBonus.getValue() + value);
	}
	
}
