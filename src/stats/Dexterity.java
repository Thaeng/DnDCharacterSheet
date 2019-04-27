package stats;

public class Dexterity extends Stat{

	public Dexterity(int value) {
		super(value, StatNames.DEXTERITY);
		initSubStats();
	}
	
	private void initSubStats() {
		super.getSubstats().add(new SubStat(super.getBonus(), "Acrobatics", false));
		super.getSubstats().add(new SubStat(super.getBonus(), "Sleight of Hands", false));
		super.getSubstats().add(new SubStat(super.getBonus(), "Stealth", false));
	}
	
}
