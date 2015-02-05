package com.example.hetisvandaag4;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<String>
{

	public ArrayList<String> data;
	private final Context context;
	private final String TAG = "Arraylistadapter";

	public MyArrayAdapter(Context context, int textViewResourceId,
			ArrayList<String> data)
	{
		super(context, textViewResourceId, data);
		this.context = context;
		this.data = data;
		

	}

	public void changeData(ArrayList<String> data)
	{
		
		clear();
		addAll(data);
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

		TextView textview = null;
		String item = (String) getItem(position);

		if (convertView == null)
		{
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.list_item_tv, null);
			textview = (TextView) convertView.findViewById(R.id.listitemtv);
			convertView.setTag(new ViewHolder(textview));
			
		} else
		{
			ViewHolder holder = (ViewHolder) convertView.getTag();
			textview = holder.getTextView();
			//textview.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.background1));
			textview.setPadding(15, 0, 0, 4);
		}

		textview.setText(item);

		return convertView;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
