package stats;

public class Wisdom extends Stat{

	public Wisdom(int value) {
		super(value, StatNames.WISDOM);
		initSubStats();
	}
	
	private void initSubStats() {
		super.getSubstats().add(new SubStat(super.getBonus(), "Animal Handling", false));
		super.getSubstats().add(new SubStat(super.getBonus(), "Insight", false));
		super.getSubstats().add(new SubStat(super.getBonus(), "Medicine", false));
		super.getSubstats().add(new SubStat(super.getBonus(), "Perception", false));
		super.getSubstats().add(new SubStat(super.getBonus(), "Survival", false));
	}
	
}
