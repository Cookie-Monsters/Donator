package de.neoklosch.android.aevidonationapp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.widget.ImageView;

import de.neoklosch.android.aevidonationapp.R;


/**
 * Class {@link ColorizedImageView} extends {@link ImageView} with predefined color filter.
 */
public class ColorizedImageView extends ImageView {
	/**
	 * Creates and initializes a new {@link ColorizedImageView} but without the color filter cause of the missing {@link AttributeSet}.
	 *
	 * @param context
	 *            the {@link Context} to be used
	 */
	public ColorizedImageView(Context context) {
		super(context);
		setImageColor(context, null);
	}

	/**
	 * Creates and initializes a new {@link ColorizedImageView}.
	 *
	 * @param context
	 *            the {@link Context} to be used
	 * @param attrs
	 *            the {@link AttributeSet} to be used
	 */
	public ColorizedImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setImageColor(context, attrs);
	}

	/**
	 * Creates and initializes a new {@link ColorizedImageView}.
	 *
	 * @param context
	 *            the {@link Context} to be used
	 * @param attrs
	 *            the {@link AttributeSet} to be used
	 * @param defStyle
	 *            the default style to be used
	 */
	public ColorizedImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setImageColor(context, attrs);
	}

	/**
	 * Set the color of the image.
	 *
	 * @param context
	 *            the {@link Context} to be used
	 * @param attrs
	 *            the {@link AttributeSet} to be used
	 */
	public void setImageColor(Context context, AttributeSet attrs) {
		if(attrs != null) {
			TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ColorizedImageView);
            if (attributes != null) {
                int colorResource = attributes.getResourceId(R.styleable.ColorizedImageView_colorFilter, 0);
                attributes.recycle();
                if (colorResource != 0) {
                    int color = getResources().getColor(colorResource);
                    PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                    setColorFilter(colorFilter);
                }
            }
		}
	}

	/**
	 * Set the color of the image.
	 *
	 * @param colorValue
	 *            String with color value with same format like for the {@link Color#parseColor(String)}
	 */
	public void setImageColor(String colorValue) {
		int color = Color.parseColor(colorValue);
		PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP);
		setColorFilter(colorFilter);
	}
}
