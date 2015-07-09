package kr.ac.daegu.app.actionbarpager;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class ViewPgerSampleActivity extends ActionBarActivity {


    private ViewPager mPager;

    private static final int[] mData1 = { R.raw.dokdo_1_13, R.raw.dokdo_1_16, R.raw.dokdo_1_17, R.raw.dokdo_1_18,
            R.raw.dokdo_1_19, R.raw.dokdo_1_20, R.raw.dokdo_2_01, R.raw.dokdo_2_02
    };


    private View.OnClickListener mPagerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String text = ((Button) v).getText().toString();
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
                    .show();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pger_sample);

        final ActionBar bar = getActionBar();

        mPager = (ViewPager) findViewById(R.id.view);
        mPager.setAdapter(new PagerAdapterClass( ViewPgerSampleActivity.this, mData1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_pger_sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }












    private class PagerAdapterClass extends PagerAdapter {

        private LayoutInflater mInfalter;
        private int[] mImage;

        public PagerAdapterClass( Context c, int[] datas) {
            super();
            mInfalter = LayoutInflater.from(c);
            this.mImage = datas;
        }

        @Override
        public int getCount() {
            return mImage.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = null;
            v = mInfalter.inflate(R.layout.page1, null);

            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inSampleSize = 2;
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mImage[position], opts);
            ((ImageView)v.findViewById(R.id.imageView)).setImageBitmap(bitmap);
  //         .setImageResource(mImage[position)
            v.findViewById(R.id.button).setOnClickListener(mPagerListener);
            ((ViewPager)container).addView(v, 0 );

            return v;
        }

        @Override
        public boolean isViewFromObject(View pager, Object obj) {
            return pager == obj;
        }

        @Override
        public void destroyItem(ViewGroup pager, int position, Object view) {
            ((ViewPager) pager).removeView((View)view);
        }
    }










}

