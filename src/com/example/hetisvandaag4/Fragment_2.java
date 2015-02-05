package com.example.hetisvandaag4;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockFragment;

public class Fragment_2 extends SherlockFragment
{
	private ImageView backgroundimage;
	private TransitionDrawable trans;
	private Handler hand;
	private boolean flag;
	private static int PAGEMARKER = 2;
	private boolean instantiated;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		final View pagina2 = inflater.inflate(R.layout.page2, container, false);
		
		backgroundimage = (ImageView) pagina2.findViewById(R.id.bgimage_frag2);
		trans = (TransitionDrawable) getResources().getDrawable(R.drawable.transition2);
		trans.setCrossFadeEnabled(true);
		backgroundimage.setImageDrawable(trans);
		flag = true;
		hand = new Handler();
		if(!instantiated)
		{
			handlechange1();
			instantiated = true;
		}
		//hand.postDelayed(new TransManager(), 8000);
		GridView gridview = (GridView) pagina2.findViewById(R.id.gridview);
	    gridview.setAdapter(new ImageAdapter_local(pagina2.getContext(),PAGEMARKER));
	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	Toast.makeText(pagina2.getContext(), pagina2.getResources().getResourceName((Integer)v.getTag()), Toast.LENGTH_LONG).show();
	        }
	    });
		
		return pagina2;
	}

	void handlechange1()
	{
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

	
	
	
}
