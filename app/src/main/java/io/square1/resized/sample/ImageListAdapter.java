package io.square1.resized.sample;

import android.content.Context;
import android.media.Image;
import android.os.Parcel;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by roberto on 22/03/2016.
 */
public class ImageListAdapter extends BaseAdapter {

    private static class ImageItem implements AsymmetricItem {

        private static Creator<ImageItem> CREATOR = new Creator<ImageItem>() {
            @Override
            public ImageItem createFromParcel(Parcel source) {
                return null;
            }

            @Override
            public ImageItem[] newArray(int size) {
                return new ImageItem[0];
            }
        };

        private int mColumnSpan;
        private int mRowSpan;
        private String mUrl;

        public ImageItem(String url, int columnSpan, int rowSpan){
            mUrl = url;
            mColumnSpan = columnSpan;
            mRowSpan = rowSpan;
        }

        @Override
        public int getColumnSpan() {
            return mColumnSpan;
        }

        @Override
        public int getRowSpan() {
            return mRowSpan;
        }

        public String getUrl(){
            return mUrl;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }
    }

    private List<ImageItem> mItems;
    private BaseGlideUrlLoader mBaseGlideUrlLoader;

    private Context mApplicationContext;

    public ImageListAdapter(Context context, BaseGlideUrlLoader loader) {
        super();

        mApplicationContext = context.getApplicationContext();
        mBaseGlideUrlLoader = loader;
        mItems = new ArrayList<>();

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels  / 2;


        for(int index = 0; index < 100; index ++ ){
            mItems.add( new ImageItem("http://digital-photography-school.com/wp-content/uploads/flickr/5661878892_15fba42846_o.jpg", rand(dpWidth) , rand(dpWidth)));
          //  mItems.add( new ImageItem("http://7-themes.com/data_images/out/37/6895404-green-landscape.jpg", rand(dpWidth) , rand(dpWidth)));

        }

    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public ImageItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {

            convertView = LayoutInflater.
                    from(parent.getContext())
                    .inflate(R.layout.image, parent, false);

        }

        ImageItem imageItem = mItems.get(position);

        convertView.setLayoutParams( new ListView.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,imageItem.getRowSpan()));
        ImageView imageView = (ImageView)convertView.findViewById(R.id.image);
        Glide.with(mApplicationContext).using(mBaseGlideUrlLoader).load(imageItem.getUrl()).into(imageView);

        return convertView;
    }


    public static int rand(float max) {

        int min = 0;

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt(((int)max - min) + 1) + min;

        return randomNum;
    }
}

