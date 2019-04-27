package controller;

import java.util.List;

import stats.ProficiencyBonus;
import stats.Stat;

public interface ControllerInterface {

	public List<Stat> getStats();
	public ProficiencyBonus getProficiencyBonus();
	public void reTitleFrame(String title);
	
}
