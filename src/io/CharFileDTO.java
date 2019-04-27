package io;

import java.util.List;

import stats.ProficiencyBonus;
import stats.Stat;
import stats.SubStat;

public class CharFileDTO {

	private List<Stat> primaryStats;
	private ProficiencyBonus proficiencyBonus;
	private List<String> proficientSkills;
	
	public CharFileDTO(List<Stat> primaryStats, ProficiencyBonus proficiencyBonus) {
		this(primaryStats, null, proficiencyBonus);
	}
	public CharFileDTO(List<Stat> primaryStats, List<String> proficientSkills, ProficiencyBonus proficiencyBonus) {
		super();
		this.primaryStats = primaryStats;
		this.proficientSkills = proficientSkills;
		this.proficiencyBonus = proficiencyBonus;
	}
	public List<Stat> getPrimaryStats() {
		return primaryStats;
	}
	public void setPrimaryStats(List<Stat> primaryStats) {
		this.primaryStats = primaryStats;
	}
	
	public List<String> getProficientSkills() {
		return proficientSkills;
	}
	public void setProficientSkills(List<String> proficientSkills) {
		this.proficientSkills = proficientSkills;
	}
	public ProficiencyBonus getProficiencyBonus() {
		return proficiencyBonus;
	}
	public void setProficiencyBonus(ProficiencyBonus proficiencyBonus) {
		this.proficiencyBonus = proficiencyBonus;
	}
	
	
	
}
