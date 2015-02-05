package com.example.hetisvandaag4;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class TextViewAdapter extends BaseAdapter
{

	private ArrayList<String> listofitems;
	public HashMap<String,WordPackage> gwendolyn;
	public HashMap<String, Integer> selectedIds;
	public HashMap<Integer, Integer> selectedIds2;
	public ArrayList<Integer> groupheadings;
	private final Context context;
	Drawable img1;
	private final String TAG = "TextViewAdapter";

	public TextViewAdapter(Context context, int textViewResourceId,
			ArrayList<String> group)
	{
		super();
		this.context = context;
		this.listofitems = group;
		if(context instanceof MainActivity_2)
		{
			Collections.sort(group);
		}		
		this.gwendolyn = new HashMap<String,WordPackage>();
		this.selectedIds = new HashMap<String, Integer>();
		this.selectedIds2 = new HashMap<Integer, Integer>();
		this.groupheadings = new ArrayList<Integer>();
		
		img1 = context.getResources().getDrawable(
				R.drawable.background1);

	}

	public void changeData(ArrayList<String> data)
	{
		if(context instanceof MainActivity_2)
		{
			Collections.sort(data);
		}
		this.listofitems = data;
		notifyDataSetChanged();
	}

	public class ViewHolder
	{
		public TextView textView;
		public String label;

		public ViewHolder()
		{
		}

		public ViewHolder(TextView textView)
		{

			this.textView = textView;
		}

		public TextView getTextView()
		{
			return textView;
		}

		public void setTextView(TextView textView)
		{
			this.textView = textView;
		}

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		TextView textview;
		if (convertView == null)
		{ // if it's not recycled, initialize some attributes
			textview = new TextView(context);
			textview.setLayoutParams(new GridView.LayoutParams(85, 85));
			textview.setPadding(8, 8, 8, 8);
		} else
		{
			textview = (TextView) convertView;
		}

	//	textview.setText(listofitems[position]);
		//textview.setTag(mThumbIds[position]);
		return textview;
	}

	@Override
	public int getCount()
	{
		return 0;
	}

	@Override
	public Object getItem(int position)
	{
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
