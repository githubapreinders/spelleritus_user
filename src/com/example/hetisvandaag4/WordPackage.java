package com.example.hetisvandaag4;

import java.util.ArrayList;

public class WordPackage
{
	private ArrayList<Word> woordenlijst;
	private String naamvanwoordenlijst;

	public WordPackage(String naamvanwoordenlijst)
	{
		this.woordenlijst = new ArrayList<Word>();
		this.naamvanwoordenlijst = naamvanwoordenlijst;
	}

	public WordPackage(ArrayList<String> wordsfromuser)
	{
		this.woordenlijst = new ArrayList<Word>();
		this.naamvanwoordenlijst = "default name";
		for(String str : wordsfromuser)
		{
			this.woordenlijst.add(new Word(str));
		}
	}
	
	public WordPackage(String[] wordsfromuser)
	{
		this.woordenlijst = new ArrayList<Word>();
		this.naamvanwoordenlijst = "default name";
		for(String str : wordsfromuser)
		{
			this.woordenlijst.add(new Word(str));
		}
	}
	
	public void addWord(String word)
	{
		woordenlijst.add(new Word(word));
	}
	
	public void addWord(Word word)
	{
		woordenlijst.add(word);
	}

	public void removeWord(Word word)
	{
		woordenlijst.remove(word);
	}

	public void modifyWord(Word oldword, Word newWord)
	{
		if(woordenlijst.contains(oldword))
				{
					woordenlijst.remove(oldword);
					woordenlijst.add(newWord);
				}
	}

	public ArrayList<String> getArrayList()
	{
		ArrayList<String> words = new ArrayList<String>();
		for(Word w : woordenlijst)
		{
			words.add(w.toString());
		}
		return words;
	}
	
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(Word word : woordenlijst)
		{
			sb.append(word.toString()+", ");
		}
		sb.delete(sb.lastIndexOf(","), sb.length());
		return sb.toString();
	}

	public ArrayList<Word> getWoordenlijst()
	{
		return woordenlijst;
	}

	public void setWoordenlijst(ArrayList<Word> woordenlijst)
	{
		this.woordenlijst = woordenlijst;
	}

	public String getNaamvanwoordenlijst()
	{
		return naamvanwoordenlijst;
	}

	public void setNaamvanwoordenlijst(String naamvanwoordenlijst)
	{
		this.naamvanwoordenlijst = naamvanwoordenlijst;
	}
	
	
}
