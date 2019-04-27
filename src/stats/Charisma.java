package stats;

public class Charisma extends Stat{

	public Charisma(int value) {
		super(value, StatNames.CHARISMA);
		initSubStats();
	}
	
	private void initSubStats() {
		super.getSubstats().add(new SubStat(super.getBonus(), "Deception", false));
		super.getSubstats().add(new SubStat(super.getBonus(), "Intimidation", false));
		super.getSubstats().add(new SubStat(super.getBonus(), "Performance", false));
		super.getSubstats().add(new SubStat(super.getBonus(), "Persuasion", false));
	}
	
}
