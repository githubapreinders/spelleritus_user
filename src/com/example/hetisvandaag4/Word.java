package com.example.hetisvandaag4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Word
{
	private String word;
	
	public Word(String word)
	{
		this.word = word;
	}

	/**
	 * 
	 * splits the word into the characters it consists of, removing possible whitespaces
	 */
	public char[] splitWord()
	{
			return word.replaceAll("\\W", "").toCharArray();	
	}
	
	
	
	/**
	 *shuffles the letters of the original word  
	 */
	
	public String hussleWord()
	{
		ArrayList<String> wd = new ArrayList<String>(); 
		
		for (int i=0; i<word.length();i++ ) 
		{
			wd.add(Character.toString(word.charAt(i)));
		}
		Collections.shuffle(wd);
		StringBuilder str = new StringBuilder();
		for(String s : wd)
		{
			str.append(s);
		}
		return str.toString();
	}
	
	
	public String getWord()
	{
		return word;
	}

	public void setWord(String word)
	{
		this.word = word;
	}

	public String toString()
	{
		return word.toString();
	}
	
	
}
