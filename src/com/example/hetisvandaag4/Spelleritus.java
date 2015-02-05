package com.example.hetisvandaag4;

import java.util.ArrayList;

import android.app.Application;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;

public class Spelleritus extends Application
{
	private boolean mTwoPane;
	private MyArrayAdapter gridviewadapter;
	private TextToSpeech texttospeech;
	private SharedPreferences currentpackageidentifier;
	private SharedPreferences currentpackagecontents;
	private ArrayList<String> woordpakket;

	public Spelleritus()
	{
		woordpakket = new ArrayList<String>();
	}

	
	public ArrayList<String> getWoordpakket()
	{
		return woordpakket;
	}




	public void setWoordpakket(ArrayList<String> woordpakket)
	{
		this.woordpakket = woordpakket;
	}




	public SharedPreferences getCurrentpackageidentifier()
	{
		return currentpackageidentifier;
	}




	public void setCurrentpackageidentifier(SharedPreferences currentpackageidentifier)
	{
		this.currentpackageidentifier = currentpackageidentifier;
	}




	public SharedPreferences getCurrentpackagecontents()
	{
		return currentpackagecontents;
	}




	public void setCurrentpackagecontents(SharedPreferences currentpackagecontents)
	{
		this.currentpackagecontents = currentpackagecontents;
	}




	public MyArrayAdapter getGridviewadapter()
	{
		return gridviewadapter;
	}

	public void setGridviewadapter(MyArrayAdapter gridviewadapter)
	{
		this.gridviewadapter = gridviewadapter;
	}

	public boolean ismTwoPane()
	{
		return mTwoPane;
	}

	public void setmTwoPane(boolean mTwoPane)
	{
		this.mTwoPane = mTwoPane;
	}

	public void setSpeechengine(TextToSpeech ttobj)
	{
		this.texttospeech = ttobj;

	}

	public TextToSpeech getTexttospeech()
	{
		return texttospeech;
	}

}
