package fr.mnf.nbapalsapp.logic.vector;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import fr.mnf.nbapalsapp.R;
import fr.mnf.nbapalsapp.logic.utils.NBATeamsVc;

/**
 * Created by Francois on 01/11/2015.
 */
public class VectorAdapter extends BaseAdapter {

    private List<NBATeamsVc> mVectors;
    private Context mContext;

    public VectorAdapter(List<NBATeamsVc> vectors, Context context) {
        mVectors = vectors;
        mContext = context;
    }
    @Override
    public int getCount() {
        return mVectors.size();
    }

    @Override
    public Object getItem(int position) {
        return mVectors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        VectorViewHolder viewHolder;
        Log.d("", "Item : " + position + " - " + mVectors.get(position).name());
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.vector_raw, null);
            viewHolder = new VectorViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (VectorViewHolder) v.getTag();
        }
        new VectorLoader(viewHolder.mImageView, mContext, mVectors.get(position).getDrawable()).execute();
        //viewHolder.mImageView.setImageDrawable(VectorDrawable.getDrawable(mContext, mVectors.get(position).getDrawable()));
        return v;
    }

    class VectorViewHolder {

        public ImageView mImageView;

        public VectorViewHolder(View base) {
            mImageView = (ImageView) base.findViewById(R.id.vector_drawable);
        }
    }
}
