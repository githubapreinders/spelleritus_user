package com.example.hetisvandaag4;

import java.util.ArrayList;

public class Quiz1
{
	static final int DEFAULT_CONFIG = 1;
	static final int EASY_CONFIG = 2;

	private ArrayList<String> wordlist = new ArrayList<String>();

	private int animation_speed;
	private int keyboard_highlighting;
	private int howmuchopgaven = 0;
	private int corrects = 0;
	private int errors = 0;
	private long starttime;
	private long quiztime;
	private Opgave currentopgave;
	private boolean quizruns;
	private int wordlistcounter = 0;

	public Quiz1()
	{
		quizruns = false;
	}

	public Quiz1(int config)
	{
		quizruns = false;

		switch (config)
		{
		case 1:
			this.animation_speed = 0;
			this.keyboard_highlighting = 0;
			break;
		case 2:
			this.animation_speed = 0;
			this.keyboard_highlighting = 1;
		}
	}

	public String startQuiz()
	{
		this.starttime = System.currentTimeMillis();
		quizruns = true;
		return makeOpgave();
	}

	public String makeOpgave()
	{
		currentopgave = new Opgave(wordlist.get(wordlistcounter));
		wordlistcounter++;
		if (wordlistcounter > wordlist.size() - 1)
		{
			wordlistcounter = 0;
		}
		return currentopgave.getQuestion();
	}

	public void submitOpgave(String useranswer)
	{
		this.currentopgave.setUseranswer(useranswer);
		if (this.currentopgave.getQuestion().equals(useranswer))
		{
			this.corrects++;
		}
		else
		{
			this.errors++;
		}
		this.howmuchopgaven++;
		// TODO opgave to database;
	}

	public void stopQuiz()
	{
		this.quiztime = starttime - System.currentTimeMillis();
	}

	public int getAnimation_speed()
	{
		return animation_speed;
	}

	public void setAnimation_speed(int animation_speed)
	{
		this.animation_speed = animation_speed;
	}

	public int getKeyboard_highlighting()
	{
		return keyboard_highlighting;
	}

	public void setKeyboard_highlighting(int keyboard_highlighting)
	{
		this.keyboard_highlighting = keyboard_highlighting;
	}

	public void setWordlist(ArrayList<String> wordlist)
	{
		this.wordlist = wordlist;
	}

	public Opgave getCurrentopgave()
	{
		return currentopgave;
	}

	public void setCurrentopgave(Opgave currentopgave)
	{
		this.currentopgave = currentopgave;
	}

}
