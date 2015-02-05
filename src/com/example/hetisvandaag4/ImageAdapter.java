package com.example.hetisvandaag4;

import java.lang.reflect.Field;
import java.util.ArrayList;
import android.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * By importing android.R the drawables that are accessed come from the android set; Deleting this import
 * forces the compiler to look in the local drawable directory
 *
 */


public class ImageAdapter extends BaseAdapter
{
	private Context mContext;
	private ArrayList<Integer> drawablearray;
	private Integer[] mThumbIds;
	private int pagemarker;

	public ImageAdapter(Context c, int pagemarker)
	{
		this.pagemarker = pagemarker;
		mContext = c;
		drawablearray = new ArrayList<Integer>();
		loadDrawables(R.drawable.class);
		mThumbIds = new Integer[drawablearray.size()];
		int counter = 0;
		for (Integer i : drawablearray)
		{
			mThumbIds[counter] = i;
			counter++;
		}

	}

	public int getCount()
	{
		return mThumbIds.length;
	}

	public Object getItem(int position)
	{
		return null;
	}

	public long getItemId(int position)
	{
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ImageView imageView;
		if (convertView == null)
		{ // if it's not recycled, initialize some attributes
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} else
		{
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(mThumbIds[position]);
		imageView.setTag(mThumbIds[position]);
		return imageView;
	}

	public void loadDrawables(Class<?> clz)
	{
		final Field[] fields = clz.getDeclaredFields();
		for (Field field : fields)
		{
			final int drawableId;
			try
			{
				drawableId = field.getInt(clz);
			} catch (Exception e)
			{
				continue;
			}
			String resourcename = mContext.getResources().getResourceName(drawableId);
			String marker = null;
			try
			{
				marker = resourcename.substring(17, 26);
			} catch (IndexOutOfBoundsException e)
			{
				e.printStackTrace();
			}

			switch (pagemarker)
			{
			case 1:
				if (marker != null && !marker.equals("ic_action"))
				{
					drawablearray.add((Integer) drawableId);
				}
				break;
			case 2:
				if (marker != null && marker.equals("ic_action"))
				{
					drawablearray.add((Integer) drawableId);
				}
				break;
			}
		}
	}

	// references to our images
	// private Integer[] mThumbIds = { R.drawable.sample_2, R.drawable.sample_3,
	// R.drawable.sample_4, R.drawable.sample_5,
	// R.drawable.sample_6, R.drawable.sample_7, R.drawable.sample_0,
	// R.drawable.sample_1, R.drawable.sample_2,
	// R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5,
	// R.drawable.sample_6, R.drawable.sample_7,
	// R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2,
	// R.drawable.sample_3, R.drawable.sample_4,
	// R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7 };
}
