package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import stats.Stat;

@SuppressWarnings("serial")
public class StatManipulator extends AbstractAction {

	private Stat stat;
	private static final String INCREASE = "+";
	private static final String DECREASE = "-";

	public StatManipulator(Stat stat) {
		super();
		this.stat = stat;
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
		stat.setValue(stat.getValue() + value);
	}

}
