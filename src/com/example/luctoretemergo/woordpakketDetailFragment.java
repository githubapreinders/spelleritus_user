package com.example.luctoretemergo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.hetisvandaag4.R;
import com.example.luctoretemergo.dummy.DummyContent;

/**
 * A fragment representing a single woordpakket detail screen. This fragment is
 * either contained in a {@link woordpakketListActivity} in two-pane mode (on
 * tablets) or a {@link woordpakketDetailActivity} on handsets.
 */
public class woordpakketDetailFragment extends SherlockFragment
{
	public String TAG = " detailfragment ";
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 *
	 * The dummy content this fragment is presenting.
	 */
	private DummyContent.DummyItem mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public woordpakketDetailFragment()
	{
		Log.d(TAG, "detailfragment");
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID))
		{
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_woordpakket_detail, container,false);
		// Show the dummy content as text in a TextView.
		if (mItem != null)
		{
			((TextView) rootView.findViewById(R.id.woordpakket_detail)).setText(mItem.content);
		}

		return rootView;
	}
}
