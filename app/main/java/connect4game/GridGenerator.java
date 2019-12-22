package connect4game;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import fall2018.csc2017.slidingtiles.R;

public class GridGenerator extends BaseAdapter {
    private Context mContext;
    private int nrTiles;
    public GridGenerator(Context c, int nrOfTiles) {
        mContext = c;
        this.nrTiles = nrOfTiles;
    }

    public int getCount() {
        return nrTiles;
    }
    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));

            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(R.drawable.empty_bracket);
        return imageView;
    }
}
