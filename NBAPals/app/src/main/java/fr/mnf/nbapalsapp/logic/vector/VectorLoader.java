package fr.mnf.nbapalsapp.logic.vector;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.wnafee.vector.compat.VectorDrawable;

/**
 * Created by Francois on 01/11/2015.
 */
class VectorLoader extends AsyncTask<Void, Void, VectorDrawable> {

    private final ImageView mImageView;
    private Context mContext;
    private int mDrawable;

    public VectorLoader(ImageView imageView, Context context, int drawable) {
        mImageView = imageView;
        mContext = context;
        mDrawable = drawable;
    }

    @Override
    protected VectorDrawable doInBackground(Void... params) {
        return VectorDrawable.getDrawable(mContext, mDrawable);
    }

    @Override
    protected void onPostExecute(VectorDrawable drawable) {
        mImageView.setImageDrawable(drawable);
    }
}
