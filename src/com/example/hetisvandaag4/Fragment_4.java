package com.example.hetisvandaag4;

import java.util.ArrayList;

import android.graphics.Typeface;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.actionbarsherlock.app.SherlockFragment;

public class Fragment_4 extends SherlockFragment
{
	private ImageView backgroundimage;
	private TransitionDrawable trans;
	private View pagina4;
	private boolean flag;
	private boolean instantiated;
	private TextView questiontext;
	private TextView answertext;
	private ImageView startbutton, pausebutton,  forwardbutton, clearbutton;
	public boolean isAnimationRunning;
	private TweenManager tweenmanagerFrag4;
	private Display display;
	private int screenwidth;
	private int screenheight;
	private int wordlistcounter;
	private String TAG = "Fragment_4 part of MainActivity_2";
	private TextToSpeech tts;
	private Quiz1 quiz;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		pagina4 = inflater.inflate(R.layout.page4a, container, false);

		// initialisation of the moving background image
		backgroundimage = (ImageView) pagina4.findViewById(R.id.bgimage_frag4);
		trans = (TransitionDrawable) getResources().getDrawable(R.drawable.transition4);
		trans.setCrossFadeEnabled(true);
		backgroundimage.setImageDrawable(trans);
		if (!instantiated)
		{
			handlechange1();
			instantiated = true;
		}
		flag = true;

		// handlers for buttons and textviews
		answertext = (TextView) pagina4.findViewById(R.id.textView2);
		answertext.addTextChangedListener(new answertextChangeListener());
		questiontext = (TextView) pagina4.findViewById(R.id.textView1);
		questiontext.setOnClickListener(new questiontextClickListener());
		clearbutton = (ImageView) pagina4.findViewById(R.id.clearbutton);
		clearbutton.setOnClickListener(new clearbuttonClickListener());
		startbutton = (ImageView) pagina4.findViewById(R.id.startbutton);
		startbutton.setOnClickListener(new startbuttonClickListener());
		pausebutton = (ImageView) pagina4.findViewById(R.id.pausebutton);
		pausebutton.setOnClickListener(new pausebuttonClickListener());
		forwardbutton = (ImageView) pagina4.findViewById(R.id.forwardbutton);
		forwardbutton.setOnClickListener(new forwardbuttonClickListener());
		//backbutton = (ImageView) pagina4.findViewById(R.id.backbutton);
		//backbutton.setOnClickListener(new backbuttonClickListener());

		// initialisation of the question text animation
		WindowManager wm = (WindowManager) (getActivity().getSystemService("window"));
		display = wm.getDefaultDisplay();
		screenwidth = display.getWidth();
		screenheight = display.getHeight();
		tweenmanagerFrag4 = new TweenManager();
		isAnimationRunning = true;
		Timeline.createSequence()
				.beginSequence()
				.push(Tween.set(questiontext, TextViewAccessor.POS_XY).target(-questiontext.getWidth(),
						questiontext.getY()))
				.push(Tween.to(questiontext, TextViewAccessor.POS_XY, 25).target(screenwidth, questiontext.getY())
						.ease(TweenEquations.easeNone)).end().repeat(Tween.INFINITY, 0).start(tweenmanagerFrag4);
		tweenmanagerFrag4.pause();

		// starting a new quiz item
		quiz = new Quiz1(Quiz1.DEFAULT_CONFIG);
		wordlistcounter = 0;

		changeTypefaces();
		this.tts = ((Spelleritus) getActivity().getApplication()).getTexttospeech();
		return pagina4;
	}

	// makes the questiontext clickable to get a spoken text
	private class questiontextClickListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			tts.speak(questiontext.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
		}
	}

	// evaluates the input text field to contain a valid or invalid value
	private class answertextChangeListener implements TextWatcher
	{

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after)
		{
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
			
		}

		@Override
		public void afterTextChanged(Editable s)
		{
			Log.i(TAG, "aftertextchanged; Editable :" + s);
			String answer = s.toString();
			if (questiontext.getText().toString().length() == answer.length())
			{
				quiz.submitOpgave(answer);
				questiontext.setText(quiz.makeOpgave());
				answertext.setText("");
			}

		}

	}

	private class startbuttonClickListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			quiz.setWordlist(((Spelleritus) getActivity().getApplication()).getWoordpakket());
			answertext.setText("");
			questiontext.setText(quiz.startQuiz());
			tweenmanagerFrag4.resume();
		}

	}

	private class forwardbuttonClickListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			oneWordForward();
		}

	}

	public void oneWordForward()
	{
		wordlistcounter++;
		if (wordlistcounter > ((Spelleritus) getActivity().getApplication()).getWoordpakket().size() - 1)
		{
			wordlistcounter = 0;
		}
		questiontext.setText(((Spelleritus) getActivity().getApplication()).getWoordpakket().get(wordlistcounter));
	}

	private class backbuttonClickListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			wordlistcounter--;
			if (wordlistcounter < 0)
			{
				wordlistcounter = ((Spelleritus) getActivity().getApplication()).getWoordpakket().size() - 1;
			}
			questiontext.setText(((Spelleritus) getActivity().getApplication()).getWoordpakket().get(wordlistcounter));
		}

	}

	private class pausebuttonClickListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			tweenmanagerFrag4.pause();
		}
	}

	public void changeTypefaces()
	{

		Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "schoolschrift.ttf");
		ArrayList<Button> letterbuttons = new ArrayList<Button>();
		Button b;
		b = (Button) pagina4.findViewById(R.id.buttona);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttonb);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttonc);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttond);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttone);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttonf);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttong);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttonh);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttoni);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttonj);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttonk);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttonl);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttonm);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttonn);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttono);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttonp);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttonq);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttonr);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttons);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttont);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttonu);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttonv);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttonw);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttonx);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttony);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttonz);
		letterbuttons.add(b);
		b = (Button) pagina4.findViewById(R.id.buttona);
		letterbuttons.add(b);
		for (final Button but : letterbuttons)
		{
			but.setTypeface(tf);
			but.setTextSize(30);
			but.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View v)
				{
					String letter = but.getText().toString();
					answertext.setText(answertext.getText() + letter);

				}
			});
		}
		answertext.setTypeface(tf);
		// questiontext.setTypeface(tf);

	}

	// handles the transition of the background image
	void handlechange1()
	{
		Handler hand = new Handler();
		hand.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				change();
			}

			private void change()
			{
				if (flag)
				{
					trans.startTransition(8000);
					flag = false;
				} else
				{
					trans.reverseTransition(8000);
					flag = true;
				}
				handlechange1();
			}
		}, 8000);

	}

	// deletes a letter from the input text field
	private class clearbuttonClickListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			if (answertext.getText().length() > 0)
			{
				answertext.setText(answertext.getText().toString().substring(0, answertext.getText().length() - 1));
			}
		}

	}

	// lets the tween animation update on the main thread
	@Override
	public void onResume()
	{
		super.onResume();
		new Thread(new Runnable()
		{
			private long lastMillis = -1;

			@Override
			public void run()
			{
				while (isAnimationRunning)
				{
					if (lastMillis > 0)
					{
						long currentMillis = System.currentTimeMillis();
						final float delta = (currentMillis - lastMillis) / 1000f;
						getActivity().runOnUiThread(new Runnable()
						{
							@Override
							public void run()
							{
								tweenmanagerFrag4.update(delta);
							}
						});
						lastMillis = currentMillis;
					} else
					{
						lastMillis = System.currentTimeMillis();
					}
					try
					{
						Thread.sleep(1000 / 60);
					} catch (InterruptedException ex)
					{
					}
				}
			}
		}).start();
	}
}
