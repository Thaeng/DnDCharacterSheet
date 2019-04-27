package stats;

public class Strength extends Stat {
	
	public Strength(int value) {
		super(value, StatNames.STRENGTH);
		initSubStats();
	}
	
	private void initSubStats() {
		super.getSubstats().add(new SubStat(super.getBonus(), "Athletics", false));
	}
}
