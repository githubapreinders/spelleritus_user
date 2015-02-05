package com.example.hetisvandaag4;

import java.util.ArrayList;

import spelleritus_superuser.woordpakketapi.Woordpakketapi;
import spelleritus_superuser.woordpakketapi.model.Woordpakket;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.luctoretemergo.woordpakketDetailActivity;
import com.example.luctoretemergo.woordpakketDetailFragment;
import com.example.luctoretemergo.woordpakketListActivity;
import com.example.luctoretemergo.woordpakketListFragment;
import com.example.luctoretemergo.woordpakketListFragment.Callbacks;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

import course.examples.UI.FragmentActionBar.QuoteFragment;
import course.examples.UI.FragmentActionBar.TitlesFragment;

public class MainActivity_2 extends SherlockFragmentActivity implements Callbacks
{
	private static int USERID = 40;

	public static String[] TitleArray;
	public static String[] QuoteArray;
	private final TitlesFragment mTitlesFragment = new TitlesFragment();
	private final QuoteFragment mDetailsFragment = new QuoteFragment();
	private MyArrayAdapter gridviewadapter;
	private String TAG = "In Mainactivity";
	private boolean mTwoPane;
	public TextToSpeech ttobj;


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_2);
		createSpeechEngineInstance();
		Tween.registerAccessor(TextView.class, new TextViewAccessor());

		gridviewadapter = new MyArrayAdapter(this, R.layout.list_item_tv, new ArrayList<String>());
		((Spelleritus) getApplication()).setGridviewadapter(gridviewadapter);

		if (findViewById(R.id.fragmentdetailcontainer) != null)
		{
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;
			((Spelleritus) getApplication()).setmTwoPane(true);
			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			// ((woordpakketListFragment)
			// getSupportFragmentManager().findFragmentById(R.id.listcontainer))
			// .setActivateOnItemClick(true);
		}

		ActionBar actionbar = getSupportActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		Drawable d = getResources().getDrawable(android.R.drawable.ic_menu_gallery);
		int iconcolor = android.graphics.Color.argb(255, 100, 20, 150);
		d.setColorFilter(iconcolor, Mode.MULTIPLY);
		actionbar.setIcon(d);
		actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.background6));
		actionbar.setTitle("Spelleritus");

		double height = actionbar.getHeight() * 0.8;
		Drawable e = getResources().getDrawable(android.R.drawable.presence_online);
		e.setBounds((int) height, (int) height, (int) height, (int) height);
		ActionBar.Tab frag1tab = actionbar.newTab().setText("icons").setIcon(e);
		ActionBar.Tab frag2tab = actionbar.newTab().setText("icons2").setIcon(android.R.drawable.presence_away);
		ActionBar.Tab frag3tab = actionbar.newTab().setText("kijk en schrijf").setIcon(android.R.drawable.presence_busy);
		ActionBar.Tab frag4tab = actionbar.newTab().setText("woordjes").setIcon(android.R.drawable.presence_offline);

		frag1tab.setTabListener(new TabListener<Fragment_1>(this, "tab1", Fragment_1.class));
		frag2tab.setTabListener(new TabListener<Fragment_2>(this, "tab2", Fragment_2.class));
		frag3tab.setTabListener(new TabListener<Fragment_3>(this, "tab3", Fragment_3.class));
		frag4tab.setTabListener(new TabListener<Fragment_4>(this, "tab4", Fragment_4.class));

		actionbar.addTab(frag4tab);
		actionbar.addTab(frag3tab);
		actionbar.addTab(frag1tab);
		actionbar.addTab(frag2tab);
		new retrieveWoordpakketAsyncTask(this).execute();

	}

	public class MyTabsListener implements ActionBar.TabListener
	{

		public Fragment fragment;

		public MyTabsListener(Fragment fragment)
		{
			this.fragment = fragment;
		}

		@Override
		public void onTabSelected(Tab tab, android.support.v4.app.FragmentTransaction ft)
		{
			{
				ft.replace(R.id.fragmentcontainer, fragment);
				Log.d(TAG, ft.getClass().toString());
			}
		}

		@Override
		public void onTabUnselected(Tab tab, android.support.v4.app.FragmentTransaction ft)
		{
			ft.remove(fragment);
			Log.d(TAG, "tab UNselected");
		}

		@Override
		public void onTabReselected(Tab tab, android.support.v4.app.FragmentTransaction ft)
		{
			Log.d(TAG, "tab REselected");

		}

	}

	@Override
	public void onItemSelected(String id)
	{
		Log.d(TAG, "item " + id);
	}

	public MyArrayAdapter getGridviewadapter()
	{
		return gridviewadapter;
	}

	public void setGridviewadapter(MyArrayAdapter gridviewadapter)
	{
		this.gridviewadapter = gridviewadapter;
	}

	public void createSpeechEngineInstance()
	{
		this.ttobj = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener()
		{
			@Override
			public void onInit(int status)
			{

				if (status != TextToSpeech.ERROR)
				{
					ttobj.setLanguage(ttobj.getDefaultLanguage());
				}
			}
		});
		ttobj.getDefaultEngine();
		((Spelleritus) getApplication()).setSpeechengine(ttobj);
	}

	
	private class retrieveWoordpakketAsyncTask extends AsyncTask<String, Void, Woordpakket>
	{
		Context context;
		private ProgressDialog pd;

		public retrieveWoordpakketAsyncTask(Context context)
		{
			this.context = context;
		}

		protected void onPreExecute()
		{
			super.onPreExecute();
			pd = new ProgressDialog(context);
			pd.setMessage("Getting the Woordpakket...");
			pd.show();
		}

		protected Woordpakket doInBackground(String... params)
		{
			Woordpakket response = null;
			try
			{
				Woordpakketapi.Builder builder = new Woordpakketapi.Builder(AndroidHttp.newCompatibleTransport(),
						new GsonFactory(), null);
				Woordpakketapi service = builder.build();

				response = service.getwpbyuserid(USERID).execute();
			} catch (Exception e)
			{
				Log.d("Could not fetch Woordpakket", e.getMessage(), e);
			}
			return response;
		}

		protected void onPostExecute(Woordpakket Woordpakket)
		{
			SharedPreferences.Editor prefseditor = getSharedPreferences("currentpackagename", 0).edit();
			prefseditor.putString("currentpackageidentifier", Woordpakket.getIdentifier());
			prefseditor.putString("currentpackagecontents", Woordpakket.getContents());
			((Spelleritus) getApplication()).setCurrentpackageidentifier(getSharedPreferences(
					"currentpackageidentifier", MODE_PRIVATE));
			((Spelleritus) getApplication()).setCurrentpackagecontents(getSharedPreferences("currentpackagecontents",
					MODE_PRIVATE));
			String[] string = (Woordpakket.getContents()).split("[\\s+,\\s+]");
			ArrayList<String> helper = new ArrayList<String>();
			for (int i = 0; i < string.length; i++)
			{
				string[i].trim();
				if (!(string[i].equals("")))
				{
					helper.add(string[i].toLowerCase());
				}
			}
			((Spelleritus) getApplication()).setWoordpakket(helper);
			((Spelleritus) getApplication()).getGridviewadapter().changeData(helper);
			pd.dismiss();
			Toast.makeText(context, "Woordpakket retrieved succesfully", Toast.LENGTH_SHORT).show();

		}
	}

}
