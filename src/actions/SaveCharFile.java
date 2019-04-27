package actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import io.CharFileChooser;
import io.CharFileDTO;
import io.CharFileWriter;
import javafx.stage.Popup;
import stats.ProficiencyBonus;
import stats.Stat;
import view.PopupMessage;

@SuppressWarnings("serial")
public class SaveCharFile extends AbstractAction {

	private List<Stat> stats;
	private ProficiencyBonus proficiencyBonus;

	public SaveCharFile(List<Stat> stats, ProficiencyBonus proficiencyBonus) {
		this.stats = stats;
		this.proficiencyBonus = proficiencyBonus;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String path = CharFileChooser.getInstance().askForSavePath();

		if (path != null) {
			saveCharFile(path);
			PopupMessage.popMessage("Character Stats saved to:\n" + path);
		}
	}

	private void saveCharFile(String path) {
		List<String> proficientSkills = new ArrayList<>();

		stats.stream().forEach(stat -> stat.getSubstats().stream().forEach(substat -> {
			if (substat.isProficient()) {
				proficientSkills.add(substat.getName());
			}
		}));

		CharFileDTO dto = new CharFileDTO(stats, proficientSkills, proficiencyBonus);

		try {
			CharFileWriter.getInstance().writeCharFile(path, dto);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
