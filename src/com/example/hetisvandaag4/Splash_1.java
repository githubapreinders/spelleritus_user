package com.example.hetisvandaag4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

/**
 * Making a splash screen in pure android with Tween engine :
 * Make an imageaccessor file as required by tween engine. Check for a field that you can control and which
 * is accisible by a method of -in this case- ImageView
 * Make a separate runnable thread for the tween wherein the manager can update
 * Dont forget to register the new activity in the manifest file and to add a callback with an intent.
 * An ImageView has to be positioned after the system has made the layout;so in the xml make the imageviews
 * wrap_content in a relative layout; position them afterwards in the view.postRunnable method;
 */

public class Splash_1 extends Activity
{
	TweenManager tweenmanager1;
	TweenManager tweenmanager2;
	ImageView splash;
	ImageView splashtext;
	ImageView beachball;
	Display display;
	int screenwidth;
	int screenheight;
	private boolean isAnimationRunning = true;
	private float initialposX;
	private float initialposY;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		

		WindowManager wm = (WindowManager) (getSystemService("window"));
		display = wm.getDefaultDisplay();
		screenwidth = display.getWidth();
		screenheight = display.getHeight();
		
		splash = (ImageView) findViewById(R.id.imageView1);
		splashtext = (ImageView) findViewById(R.id.imageView2);
		beachball = (ImageView)findViewById(R.id.imageView3);

		splashtext.post(new Runnable()
		{
			
			@Override
			public void run()
			{
				splashtext.setPivotX(splashtext.getWidth()/2);
				splashtext.setPivotY(splashtext.getHeight()/2);
				splashtext.setX(screenwidth/2-(splashtext.getWidth()/2));
				splashtext.setY((display.getHeight()-getActionBar().getHeight())/2-(splashtext.getHeight()/2));				
			}
		});
		
		beachball.post(new Runnable()
		{
			@Override
			public void run()
			{
				beachball.setPivotX(beachball.getWidth()/2);
				beachball.setPivotY(beachball.getHeight()/2);
				makeTimeline();
			}
		});
		
		tweenmanager1 = new TweenManager();
		tweenmanager2 = new TweenManager();
		Tween.registerAccessor(ImageView.class, new ImageAccessor());
		
		Timeline.createSequence()
		.beginSequence()
		.push(Tween.set(splash, ImageAccessor.ALPHA).target(0))
		.push(Tween.set(splashtext, ImageAccessor.ALPHA).target(0))
		.push(Tween.set(splashtext,ImageAccessor.SCALE ).target(0.0f, 0.0f))
		
		.beginParallel()
		.push(Tween.to(splash, ImageAccessor.ALPHA, 2).target(1).ease(TweenEquations.easeNone))
		.push(Tween.to(splashtext, ImageAccessor.ALPHA, 2).target(1).ease(TweenEquations.easeOutCubic))
		.push(Tween.to(splashtext, ImageAccessor.ROTATION, 2).target(1440).ease(TweenEquations.easeInCubic))
		.push(Tween.to(splashtext, ImageAccessor.SCALE, 2).target(1.0f,1.0f).ease(TweenEquations.easeInCubic))
		.end()
		
		.beginParallel()
		.push(Tween.to(splashtext, ImageAccessor.ALPHA, 2).target(0))
		.push(Tween.to(splash, ImageAccessor.ALPHA, 2).target(0)).setCallback(new TweenCallback()
		{
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1)
			{
				isAnimationRunning = false;
				Intent i = new Intent(Splash_1.this, MainActivity_2.class);
				startActivity(i);
			}
		})
		.end()
		.end()
		//.repeat(Tween.INFINITY, 0)
		.start(tweenmanager1);
			
	}
	
	public void makeTimeline()
	{
		int posy = screenheight-getStatusBarHeight()-getActionBar().getHeight()-beachball.getHeight();
		Timeline.createSequence()
		.beginSequence()
		.push(Tween.set(beachball, ImageAccessor.POS_XY).target(screenwidth,posy))
		.pushPause(2.0f)
		.beginParallel()
		.push(Tween.to(beachball, ImageAccessor.POS_XY, 2).target(0-beachball.getWidth()-50,posy).ease(TweenEquations.easeNone))
		.push(Tween.to(beachball, ImageAccessor.ROTATION, 2).target(-1440).ease(TweenEquations.easeNone))
		.end()
		.end()
		//.repeat(Tween.INFINITY, 0)
		.start(tweenmanager2);
	}
	
	
	
	
	@Override
	protected void onResume()
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
						runOnUiThread(new Runnable()
						{
							@Override
							public void run()
							{
								tweenmanager1.update(delta);
								tweenmanager2.update(delta);
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
		})
		.start();

	}

	public int getStatusBarHeight() { 
	      int result = 0;
	      int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
	      if (resourceId > 0) {
	          result = getResources().getDimensionPixelSize(resourceId);
	      } 
	      return result;
	} 
	
}
