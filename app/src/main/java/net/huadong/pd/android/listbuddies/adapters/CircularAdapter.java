package net.huadong.pd.android.listbuddies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jpardogo.listbuddies.lib.adapters.CircularLoopAdapter;
import com.squareup.picasso.Picasso;

import net.huadong.pd.android.R;
import net.huadong.pd.android.listbuddies.Utils.ScaleToFitWidhtHeigthTransform;

import java.util.ArrayList;
import java.util.List;

public class CircularAdapter extends CircularLoopAdapter {
    private static final String TAG = CircularAdapter.class.getSimpleName();

    private List<String> mItems = new ArrayList<String>();
    private Context mContext;
    private int mRowHeight;

    public CircularAdapter(Context context, int rowHeight, List<String> imagesUrl) {
        mContext = context;
        mRowHeight = rowHeight;
        mItems = imagesUrl;
    }

    @Override
    public String getItem(int position) {
        return mItems.get(getCircularPosition(position));
    }

    @Override
    protected int getCircularCount() {
        return mItems.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listbuddy_item_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.image.setMinimumHeight(mRowHeight);

        Picasso.with(mContext).load(getItem(position)).transform(new ScaleToFitWidhtHeigthTransform(mRowHeight, true)).skipMemoryCache().into(holder.image);

        return convertView;
    }

    static class ViewHolder {
        ImageView image;

        public ViewHolder(View convertView) {
            image = (ImageView) convertView.findViewById(R.id.image);
        }
    }
}
