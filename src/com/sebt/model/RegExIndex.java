package com.sebt.model;

import java.util.ArrayList;
import java.util.List;

public class RegExIndex {
	
	private Integer index;
	
	private List<Character> character;
	
	private Integer nbOcurrence;

	public static List<RegExIndex> listReg = new ArrayList<>();
	
	private static boolean isInit = false;
	
	public RegExIndex() {
		this.index = 0;
		this.character = new ArrayList<>();
		initListReg();
		
	}
	public RegExIndex(RegExIndex reg) {
		
		this.index = reg.index;
		this.character = reg.character;
		this.nbOcurrence=0;
		initListReg();
		
		
	}
	
	public void initListReg() {
		if(!isInit) {
			isInit = true;
			char[] caraUpper = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
			char[] caraLower = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
//			char[] specialCara = {'é','è','ç','ï','ê','ë','ù','£','¤','§','ö','ô','`','~','²','°'};
//			char[] otherCara = {',','.',':',';','"','\'','\\','/','?','!','%','*','-','+','&','(',')','[',']','{','}','=','@','#','$','_','<','>',' ','|'};
			char[] otherCara = {',','.',':',';','"','\'','\\','?','!','%','-','+','&','(',')','[',']','{','}','=','@','#','_','<','>','|'};
			char[] numberCara = {'0','1','2','3','4','5','6','7','8','9'};
			
			Integer i = 1;
			
			for(int j = 0; j < caraUpper.length ;j++) {
				RegExIndex reg = new RegExIndex();
				reg.getCharacter().add(caraUpper[j]);
				reg.getCharacter().add(caraLower[j]);
				reg.setIndex(i);
				listReg.add(reg);
				i++;
			}
			
//			for(int j = 0; j < specialCara.length ;j++) {
//				RegExIndex reg = new RegExIndex();
//				reg.getCharacter().add(specialCara[j]);
//				reg.setIndex(i);
//				listReg.add(reg);
//				i++;
//			}
			
			for(int j = 0; j < numberCara.length ;j++) {
				RegExIndex reg = new RegExIndex();
				reg.getCharacter().add(numberCara[j]);
				reg.setIndex(i);
				listReg.add(reg);
				i++;
			}
			
			for(int j = 0; j < otherCara.length ;j++) {
				RegExIndex reg = new RegExIndex();
				reg.getCharacter().add(otherCara[j]);
				reg.setIndex(i);
				listReg.add(reg);
				i++;
			}
			
		}
	
	}
	
	public List<RegExIndex> getCloneList(){
		List<RegExIndex> list = new ArrayList<RegExIndex>();
		for(RegExIndex reg : listReg) {
			list.add(new RegExIndex(reg));
		}
		return list;
		
	}
	
	public void addOccurence() {
		this.nbOcurrence += 1;
	}
	
	
	/*
	 * GETTER & SETTER
	 */
	public Integer getNbOcurrence() {
		return nbOcurrence;
	}

	public Integer getIndex(Character character) {
		for(RegExIndex reg : listReg) {
			if(reg.character.contains(character)) {
				return reg.index - 1;
			}
		}
		return -1;
		
	}
	
	public Integer getIndex(String character) {
		for(RegExIndex reg : listReg) {
			if(reg.character.contains(character.charAt(0))) {
				return reg.index - 1;
			}
		}
		return -1;
		
	}
	
	public Integer getIndex() {
		return index;
	}

	private void setIndex(Integer index) {
		this.index = index;
	}

	public List<Character> getCharacter() {
		return character;
	}


}
