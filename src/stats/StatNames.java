package stats;

public enum StatNames {

	STRENGTH ("Strength"),
	DEXTERITY ("Dexterity"),
	CONSTITUTION ("Constitution"),
	INTELLIGENCE ("Intelligence"),
	WISDOM ("Wisdom"),
	CHARISMA ("Charisma")
	;
	
    private final String statName;

    private StatNames(String statName) {
        this.statName = statName;
    }
    
    public String getName() {
    	return this.statName;
    }
    
}
