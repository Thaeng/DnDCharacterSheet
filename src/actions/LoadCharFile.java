package actions;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.AbstractAction;

import controller.ControllerInterface;
import io.CharFileChooser;
import io.CharFileDTO;
import io.CharFileReader;
import stats.Stat;
import view.PopupMessage;

@SuppressWarnings("serial")
public class LoadCharFile extends AbstractAction{

	
	private ControllerInterface controller;
	
	public LoadCharFile(ControllerInterface controller) {
		super();
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String path = askLoadPath();
		
		if(path != null) {
			CharFileDTO dto = null;
			
			try {
				dto = CharFileReader.getInstance().readCharFile(path);
			} catch (Exception e1) {
				PopupMessage.popException("An Error occured loading the File", e1);
			}
			
			fillView(dto);
		}
	}

	private void fillView(CharFileDTO dto) {
		List<Stat> dtoStats = dto.getPrimaryStats();
		List<Stat> viewStats = controller.getStats();
		
		viewStats.stream().forEach(stat -> stat.setValue(findValueForIn(stat, dtoStats)));
		
		controller.getProficiencyBonus().setValue(dto.getProficiencyBonus().getValue());
		
		selectProficientSkill(viewStats, dto);
	}
	
	private String askLoadPath(){
		String path = null;
		try {
			path = CharFileChooser.getInstance().askForLoadPath();
		} catch (IOException e) {
			PopupMessage.popException("An Error occured loading the File", e);
		}
		return path;
	}
	
	private void selectProficientSkill(List<Stat> stats, CharFileDTO dto) {
		stats.stream().forEach(stat -> stat.getSubstats().stream().forEach(substat -> {
			for(String skill : dto.getProficientSkills()) {
				if(skill.equals(substat.getName())) {
					substat.getCbProficient().setSelected(true);
				}
			}
		}));
	}
	
	private int findValueForIn(Stat stat, List<Stat> stats) {
		int value = stat.getValue();
		
		for(Stat s : stats) {
			if(s.getName().equals(stat.getName())) {
				value = s.getValue();
			}
		}
				
		return value;
	}

	
	
}
