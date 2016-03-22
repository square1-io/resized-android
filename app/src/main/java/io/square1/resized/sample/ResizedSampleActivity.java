package io.square1.resized.sample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;

import io.square1.resized.Resized;

public class ResizedSampleActivity extends AppCompatActivity {


    private static class GliderLoader extends BaseGlideUrlLoader<String> {

        private Resized mResized;

        public GliderLoader(Context context, Resized resized) {
            super(context);
            mResized = resized;
        }


        @Override
        protected String getUrl(String model, int width, int height) {
            return mResized.process(model, width, height);
        }
    }



    private GliderLoader mGliderLoader;
    private ListView mListView;
    private ImageListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_resizer_sample_standard_listview);


        mGliderLoader = new GliderLoader(this ,
                //secret-53202acf7e8982c2c48af3ba809ddc7b4a1fd3b8
                Resized.init("appdev" ,"secret-53202acf7e8982c2c48af3ba809ddc7b4a1fd3b8"));


        mListView = (ListView) findViewById(R.id.listView);


        setupListView(mListView);
       // setupAsymmetricGridView((AsymmetricGridView)mListView);
    }

    private void setupAsymmetricGridView(AsymmetricGridView listView) {



        // Choose your own preferred column width
         listView.setRequestedColumnWidth(Utils.dpToPx(this, 120));

        // initialize your items array
        mListAdapter = new ImageListAdapter(this, mGliderLoader);

         AsymmetricGridViewAdapter asymmetricAdapter =
                 new AsymmetricGridViewAdapter<>(this, listView, mListAdapter);

        listView.setAdapter(asymmetricAdapter);

    }

    private void setupListView(ListView listView){

        mListAdapter = new ImageListAdapter(this, mGliderLoader);
        listView.setAdapter(mListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resizer_sample, menu);
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
}
