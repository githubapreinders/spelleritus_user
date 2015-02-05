package com.example.hetisvandaag4;

import java.util.ArrayList;
import java.util.HashMap;

import spelleritus_superuser.woordpakketapi.Woordpakketapi;
import spelleritus_superuser.woordpakketapi.model.Woordpakket;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.luctoretemergo.woordpakketListFragment;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

public class Fragment_3 extends ListFragment implements woordpakketListFragment.Callbacks
{
	private ImageView backgroundimage;
	private TransitionDrawable trans;
	private boolean flag;
	private boolean instantiated;
	private Woordpakket  pack1, pack2,pack3, pack4;
	MyArrayAdapter adapter;
	MyArrayAdapter gridadapter;
	View pagina3;
	private TextToSpeech tts;
	SharedPreferences prefs1;
	SharedPreferences prefs2;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		
		
		pack1 = new Woordpakket();
		pack1.setContents("appels peren bananen pruimen dadels");
		pack1.setIdentifier("fruit");
		
		pack2 = new Woordpakket();
		pack2.setContents("eik beuk populier berk acacia kokospalm");
		pack2.setIdentifier("bomen");
		
		pack3 = new Woordpakket();
		pack3.setContents("fiets vliegtuig auto skis skeelers skateboard");
		pack3.setIdentifier("vervoer");
		
		pack4 = new Woordpakket();
		
		
		pagina3 = inflater.inflate(R.layout.page3, container, false);
		backgroundimage = (ImageView) pagina3.findViewById(R.id.bgimage_frag3);
		trans = (TransitionDrawable) getResources().getDrawable(R.drawable.transition3);
		trans.setCrossFadeEnabled(true);
		backgroundimage.setImageDrawable(trans);
		flag = true;
		if (!instantiated)
		{
			handlechange1();
			instantiated = true;
		}
		this.tts = ((Spelleritus)getActivity().getApplication()).getTexttospeech();
		return pagina3;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		 ArrayList<String> packages = new ArrayList<String>();
		packages.add(pack1.getIdentifier());
		packages.add(pack2.getIdentifier());
		packages.add(pack3.getIdentifier());

		final HashMap<String, Woordpakket> packshashmap = new HashMap<String, Woordpakket>();
		packshashmap.put(pack1.getIdentifier(), pack1);
		packshashmap.put(pack2.getIdentifier(), pack2);
		packshashmap.put(pack3.getIdentifier(), pack3);

//		adapter = new MyArrayAdapter(getActivity(), R.layout.list_item, packages);
//		list = getListView();
//		setListAdapter(adapter);
//		list.setAdapter(adapter);
//		list.setOnItemClickListener(new OnItemClickListener()
//		{
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//			{
//				String packname = (String) list.getItemAtPosition(position);
//				gridadapter.changeData(packshashmap.get(packname).getArrayList());
//			}
//		});
		
		GridView gridview = (GridView) pagina3.findViewById(R.id.gridview);
		gridadapter = ((Spelleritus)getActivity().getApplication()).getGridviewadapter();
		gridview.setAdapter(gridadapter);
		gridview.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
				tts.speak(((TextView) v).getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
				Toast.makeText(pagina3.getContext(), ((TextView) v).getText(), Toast.LENGTH_LONG).show();
			}
		});

	}

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

	@Override
	public void onItemSelected(String id)
	{
		// TODO Auto-generated method stub

	}

		
}
