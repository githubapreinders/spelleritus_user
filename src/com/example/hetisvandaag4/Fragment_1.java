package com.example.hetisvandaag4;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class Fragment_1 extends SherlockFragment
{
	private ImageView backgroundimage;
	private TransitionDrawable trans;
	private boolean flag;
	private static int PAGEMARKER = 1;
	private boolean instantiated;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		final View pagina1 = inflater.inflate(R.layout.page1, container, false);
		backgroundimage = (ImageView) pagina1.findViewById(R.id.bgimage_frag1);
		trans = (TransitionDrawable) getResources().getDrawable(R.drawable.transition);
		trans.setCrossFadeEnabled(true);
		backgroundimage.setImageDrawable(trans);
		flag = true;
		if(!instantiated)
		{
			handlechange1();
			instantiated = true;
		}
		
		
		GridView gridview = (GridView) pagina1.findViewById(R.id.gridview);
	    gridview.setAdapter(new ImageAdapter(pagina1.getContext(),PAGEMARKER));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	Toast.makeText(pagina1.getContext(), pagina1.getResources().getResourceName((Integer)v.getTag()), Toast.LENGTH_LONG).show();
	        }
	    });
		
		
		return pagina1;
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

	
}
