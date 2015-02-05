package com.example.hetisvandaag4;


import android.widget.ImageView;
import aurelienribon.tweenengine.TweenAccessor;

public class ImageAccessor implements TweenAccessor<ImageView>
{

	public static final int ALPHA = 0;
	public static final int ROTATION = 1;
	public static final int SCALE = 2;
	public static final int POS_XY = 3;
	

	@Override
	public int getValues(ImageView target, int tweentype, float[] returnvalues)
	{
		switch (tweentype)
		{
		case ALPHA:
			returnvalues[0] = target.getAlpha();
			return 1;

		case ROTATION:
			returnvalues[0] = target.getRotation();
			return 1;

		case POS_XY:
			returnvalues[0] = target.getX();
			returnvalues[1] = target.getY();
			return 2;

		case SCALE:
			returnvalues[0] = target.getScaleX();
			returnvalues[1] = target.getScaleY();
			return 2;


		default:
			assert false;
			return -1;
		}
	}

	@Override
	public void setValues(ImageView target, int tweentype, float[] newvalues)
	{
		switch (tweentype)
		{
		case ALPHA:
			target.setAlpha(newvalues[0]);
			break;

		case ROTATION:
			target.setRotation(newvalues[0]);
			break;

		case POS_XY:
			target.setX((int) newvalues[0]);
			target.setY((int)newvalues[1]);
			break;

		case SCALE:
			target.setScaleX(newvalues[0]);
			target.setScaleY(newvalues[1]);
			break;

		default:
			assert false;
		}
	}

	

}
