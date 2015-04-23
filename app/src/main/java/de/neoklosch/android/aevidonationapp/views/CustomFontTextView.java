package de.neoklosch.android.aevidonationapp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import de.neoklosch.android.aevidonationapp.R;


/**
 * Class {@link CustomFontTextView} extends {@link TextView} to set the font custom font.
 */
public class CustomFontTextView extends TextView {
    private static final String	LOG_TAG	= CustomFontTextView.class.getSimpleName();

    /**
     * Creates and initializes a new {@link CustomFontTextView} but without the custom font cause of the missing {@link AttributeSet}.
     *
     * @param context
     *            the {@link Context} to be used
     */
    public CustomFontTextView(Context context) {
        super(context);
        setFont(context, null);
    }

    /**
     * Creates and initializes a new {@link CustomFontTextView}.
     *
     * @param context
     *            the {@link Context} to be used
     * @param attrs
     *            the {@link AttributeSet} to be used
     */
    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context, attrs);
    }

    /**
     * Creates and initializes a new {@link CustomFontTextView}.
     *
     * @param context
     *            the {@link Context} to be used
     * @param attrs
     *            the {@link AttributeSet} to be used
     * @param defStyle
     *            the default style to be used
     */
    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont(context, attrs);
    }

    /**
     * Set the font of the {@link CustomFontTextView}. The custom attribute to be used is called <b>fontFamily</b>.
     *
     * @param context
     *            the {@link Context} to be used
     * @param attrs
     *            the {@link AttributeSet} to be used
     */
    public void setFont(Context context, AttributeSet attrs) {
        if(attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomFontTextView);
            String fontFamily = null;
            final int attributesCount = attributes.getIndexCount();
            for(int index = 0; index < attributesCount; ++index) {
                int attribute = attributes.getIndex(index);
                if(attribute == R.styleable.CustomFontTextView_fontFamily) {
                    fontFamily = attributes.getString(attribute);
                }
            }
            attributes.recycle();
            if(!isInEditMode()) {
                try {
                    Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontFamily + ".ttf");
                    setTypeface(tf);
                }
                catch(Exception e) {
                    Log.v(LOG_TAG, "font not found");
                }
            }
        }
    }
}
