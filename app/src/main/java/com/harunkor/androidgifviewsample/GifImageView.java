package com.harunkor.androidgifviewsample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.net.Uri;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by harunkor on 22.04.2017.
 */

public class GifImageView extends View {
    private InputStream mInputStream;
    private Movie mMovie;
    private int mWidth, mHeight;
    private long mStart;
    private Context mContext;


    public GifImageView(Context context) {
        super(context);
        this.mContext=context;
    }

    public GifImageView(Context context, AttributeSet attributeSet)
    {
        this(context,attributeSet,0);
    }

    public GifImageView(Context context,AttributeSet attributeSet,int defStyleattr)
    {
       super(context,attributeSet,defStyleattr);
        this.mContext=context;
        if(attributeSet.getAttributeName(1).equals("background"))
        {
            int id=Integer.parseInt(attributeSet.getAttributeName(1).substring(1));
            setGifImageResource(id);

        }

    }

    private void init()
    {
        setFocusable(true);
        mMovie=Movie.decodeStream(mInputStream);
        mWidth=mMovie.width();
        mHeight=mMovie.height();
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       long now= SystemClock.uptimeMillis();

        if(mStart==0)
        {
            mStart=now;

        }

        if(mMovie!=null)
        {
            int duration = mMovie.duration();
            if(duration==0)
            {
                duration=1000;
            }
            int relTime=(int) ((now-mStart) % duration);
            mMovie.setTime(relTime);
            mMovie.draw(canvas,0,0);
            invalidate();


        }


    }

    public void setGifImageResource(int id)
    {
        mInputStream=mContext.getResources().openRawResource(id);
        init();
    }

    public void setGifImageUri(Uri uri)
    {
        try {
            mInputStream=mContext.getContentResolver().openInputStream(uri);

            init();


        }catch (FileNotFoundException e)
        {
             e.printStackTrace();
        }
    }





}
