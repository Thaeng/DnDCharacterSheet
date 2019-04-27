package stats;

public class Intelligence extends Stat{

	public Intelligence(int value) {
		super(value, StatNames.INTELLIGENCE);
		initSubStats();
	}
	
	private void initSubStats() {
		super.getSubstats().add(new SubStat(super.getBonus(), "Arcana", false));
		super.getSubstats().add(new SubStat(super.getBonus(), "History", false));
		super.getSubstats().add(new SubStat(super.getBonus(), "Investigation", false));
		super.getSubstats().add(new SubStat(super.getBonus(), "Nature", false));
		super.getSubstats().add(new SubStat(super.getBonus(), "Religion", false));
	}
	
}
