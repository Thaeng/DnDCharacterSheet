package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import stats.Charisma;
import stats.Constitution;
import stats.Dexterity;
import stats.Intelligence;
import stats.ProficiencyBonus;
import stats.Stat;
import stats.StatNames;
import stats.Strength;
import stats.SubStat;
import stats.Wisdom;

public class CharFileReader {

	private static CharFileReader instance;
	private static final String[] fields = { StatNames.STRENGTH.getName(), StatNames.DEXTERITY.getName(),
			StatNames.CONSTITUTION.getName(), StatNames.INTELLIGENCE.getName(), StatNames.WISDOM.getName(),
			StatNames.CHARISMA.getName(), "Proficiency" };

	private static final String[] SKILLS = { "Acrobatics", "Animal Handling", "Arcana", "Athletics", "Deception",
			"History", "Insight", "Intimidation", "Medicine", "Investigation", "Medicine", "Nature", "Perception",
			"Performance", "Persuasion", "Religion", "Religion", "Sleight of Hand", "Stealth", "Survival" };

	private static final String INTEGER_REGEX = "^\\d+$";

	private CharFileReader() {

	}

	public static CharFileReader getInstance() {
		if (instance == null) {
			instance = new CharFileReader();
		}
		return instance;
	}

	public CharFileDTO readCharFile(String path) throws Exception {
		CharFileDTO charStats = null;

		File charFile = new File(path);

		if (charFile.exists() && charFile.isFile()) {
			Properties properties = openFile(charFile);
			charStats = buildCharFileDTO(properties);
		} else {
			throw new Exception("The File (" + path + ") is either not a File or doesn't Exist");
		}
		return charStats;
	}

	private boolean validateProperties(Properties properties) {
		for (String key : fields) {
			boolean valid = properties.keySet().contains(key) && properties.get(key).toString().matches(INTEGER_REGEX);
			if (!valid) {
				return false;
			}
		}
		return true;

	}

	private Properties openFile(File file) throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(file));
		return props;
	}

	private CharFileDTO buildCharFileDTO(Properties properties) throws Exception {
		if (!validateProperties(properties)) {
			throw new Exception("The File is not a Character File or not correctly formatted");
		}

		List<Stat> primaryStats = extractPrimaryStats(properties);
		ProficiencyBonus proficiencyBonus = extractProficiencyBonus(properties, primaryStats);
		List<String> proficientSkills = extractProficiencies(properties);
		
		CharFileDTO dto = new CharFileDTO(primaryStats, proficientSkills, proficiencyBonus);
		return dto;
	}

	private List<Stat> extractPrimaryStats(Properties properties) {
		List<Stat> primaryStats = new ArrayList<>();
		properties.keySet().stream().forEachOrdered(key -> {
			String statName = (String) key;
			
			if(statName.equals("ProficientSkills")) {
				return;
			}
			
			int value = Integer.parseInt(properties.getProperty(statName).toString());

			// Strength
			if (statName.equals(StatNames.STRENGTH.getName())) {
				primaryStats.add(new Strength(value));
				// Dexterity
			} else if (statName.equals(StatNames.DEXTERITY.getName())) {
				primaryStats.add(new Dexterity(value));
				// Constitution
			} else if (statName.equals(StatNames.CONSTITUTION.getName())) {
				primaryStats.add(new Constitution(value));
				// Intelligence
			} else if (statName.equals(StatNames.INTELLIGENCE.getName())) {
				primaryStats.add(new Intelligence(value));
				// Wisdom
			} else if (statName.equals(StatNames.WISDOM.getName())) {
				primaryStats.add(new Wisdom(value));
				// Charisma
			} else if (statName.equals(StatNames.CHARISMA.getName())) {
				primaryStats.add(new Charisma(value));
			}
		});
		return primaryStats;
	}

	private ProficiencyBonus extractProficiencyBonus(Properties properties, List<Stat> stats) {

		ProficiencyBonus proficiencyBonus = null;

		for (Object key : properties.keySet()) {

			String statName = (String) key;

			if (statName.equals("Proficiency")) {
				int value = Integer.parseInt(properties.getProperty(statName).toString());
				proficiencyBonus = new ProficiencyBonus(stats, value);
			}
		}

		return proficiencyBonus;
	}

	private List<String> extractProficiencies(Properties properties) {
		List<String> proficiencies = Arrays.asList(properties.getProperty("ProficientSkills").split(";"));
		return proficiencies;
	}

}
