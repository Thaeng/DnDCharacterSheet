package actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ControllerInterface;
import io.CharFileChooser;
import io.CharFileDTO;
import io.CharFileWriter;
import stats.ProficiencyBonus;
import stats.Stat;
import view.PopupMessage;

@SuppressWarnings("serial")
public class SaveCharFile extends AbstractAction {

	private List<Stat> stats;
	private ProficiencyBonus proficiencyBonus;
	private ControllerInterface controller;

	public SaveCharFile(List<Stat> stats, ProficiencyBonus proficiencyBonus, ControllerInterface controller) {
		this.stats = stats;
		this.proficiencyBonus = proficiencyBonus;
		this.controller = controller;
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
			controller.reTitleFrame(new File(path).getName());
		} catch (Exception e1) {
			PopupMessage.popException("An error occured during saving the Character", e1);
		}
	}

}
