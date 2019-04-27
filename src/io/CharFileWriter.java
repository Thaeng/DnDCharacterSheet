package io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import stats.ProficiencyBonus;
import stats.Stat;

public class CharFileWriter {

	private static CharFileWriter instance;
	
	private CharFileWriter() {
		
	}
	
	public static CharFileWriter getInstance() {
		if(instance == null) {
			instance = new CharFileWriter();
		}
		return instance;
	}
	
	public void writeCharFile(String path, CharFileDTO dto) throws Exception {
		Properties properties = new Properties();
		File file = new File(path);
		validateFile(file);

		putPrimaryStatsInProperties(dto.getPrimaryStats(), properties);
		putProficiencyBonusInProperties(dto.getProficiencyBonus(), properties);
		putProficientSkillsInProperties(dto.getProficientSkills(), properties);
		
		properties.store(new FileOutputStream(file), null);
	}
	
	
	private void validateFile(File file) throws Exception {
		if(file.isDirectory()) {
			throw new Exception("The Path to the File is a Directory");
		}
		
		if(!file.exists()) {
			file.createNewFile();
		}
		
	}
	
	private void putPrimaryStatsInProperties(List<Stat> stats, Properties properties) {
		stats.stream().forEach(stat -> properties.setProperty(stat.getName(), ""+stat.getValue()));
	}

	private void putProficiencyBonusInProperties(ProficiencyBonus profBonus, Properties properties) {
		properties.setProperty("Proficiency", ""+profBonus.getValue());
	}
	
	private void putProficientSkillsInProperties(List<String> proficientSkills, Properties properties) {
		StringBuilder sb = new StringBuilder();
		proficientSkills.stream().forEach(skill -> sb.append(skill+";"));
		properties.setProperty("ProficientSkills", sb.toString());
	}
	
}
