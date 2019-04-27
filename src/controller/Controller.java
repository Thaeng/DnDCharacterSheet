package controller;

import java.util.ArrayList;
import java.util.List;

import actions.LoadCharFile;
import actions.SaveCharFile;
import stats.Charisma;
import stats.Constitution;
import stats.Dexterity;
import stats.Intelligence;
import stats.ProficiencyBonus;
import stats.Stat;
import stats.Strength;
import stats.SubStat;
import stats.Wisdom;
import view.DnDMenu;
import view.PanelProficiency;
import view.View;

public class Controller implements ControllerInterface{

	private View view;
	private ArrayList<Stat> stats = new ArrayList<>();
	private ProficiencyBonus proficiencyBonus;
	
	private PanelProficiency panelProficiency;
	private DnDMenu dndMenu;
	
	private static final int INIT_VALUE = 12;
	private static final String VIEW_TITLE_PREFIX = "Dnd Statsheet";
	
	
	public Controller() {
		init();
	}
	
	private void init() {
		createStats();
		createProficiencyBonus();
		createPanels();
		createMenu();
		
		this.view = new View(stats, panelProficiency, dndMenu);
		view.setTitle(VIEW_TITLE_PREFIX);
	}
	
	private void createPanels() {
		panelProficiency = new PanelProficiency(proficiencyBonus);
	}
	
	private void createMenu() {
		dndMenu = new DnDMenu();
		dndMenu.getLoad().addActionListener(new LoadCharFile((ControllerInterface)this));
		dndMenu.getSave().addActionListener(new SaveCharFile(stats, proficiencyBonus));
	}
	
	private void createStats() {
		stats.add(new Strength(INIT_VALUE));
		stats.add(new Dexterity(INIT_VALUE));
		stats.add(new Constitution(INIT_VALUE));
		stats.add(new Intelligence(INIT_VALUE));
		stats.add(new Wisdom(INIT_VALUE));
		stats.add(new Charisma(INIT_VALUE));
	}
	
	private void createProficiencyBonus() {
		proficiencyBonus = new ProficiencyBonus(stats, 0);
	}

	@Override
	public List<Stat> getStats() {
		return this.stats;
	}

	@Override
	public ProficiencyBonus getProficiencyBonus() {
		return this.proficiencyBonus;
	}
}
