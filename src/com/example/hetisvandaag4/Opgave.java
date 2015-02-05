package com.example.hetisvandaag4;

public class Opgave
{
	private int userid;
	private int woordpakket;
	private String question;
	private String useranswer;
	private boolean iscorrect;
	private long time;

	public Opgave()
	{
	}
	
	public Opgave(String question)
	{
		this.question = question;
		this.time = System.currentTimeMillis();
	}

	public String getUseranswer()
	{
		return useranswer;
	}

	public void setUseranswer(String useranswer)
	{
		this.useranswer = useranswer;
		if(this.question.equals(useranswer))
		{
			setIscorrect(true);
		}
		else
		{
			setIscorrect(false);
		}
		this.time = System.currentTimeMillis() - time;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public boolean isIscorrect()
	{
		return iscorrect;
	}

	public void setIscorrect(boolean iscorrect)
	{
		this.iscorrect = iscorrect;
	}
	
	
	
	
	

}
