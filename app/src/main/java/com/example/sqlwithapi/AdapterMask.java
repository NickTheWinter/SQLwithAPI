package com.example.sqlwithapi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.util.Base64;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterMask extends BaseAdapter {
    private Context mContext;
    List<Mask> maskList;

    public AdapterMask(Context context, List<Mask> listProduct){
        mContext = context;
        maskList = listProduct;
    }

    @Override
    public int getCount() {
        return maskList.size();
    }

    @Override
    public Object getItem(int i) {
        return maskList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return maskList.get(i).getID();
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view.inflate(mContext,R.layout.item_layout,null);
        TextView AirlineName = v.findViewById(R.id.AirlineName);
        TextView AirlineWebSite = v.findViewById(R.id.AirlineWebSite);
        ImageView imageView = v.findViewById(R.id.imageView);
        Mask mask = maskList.get(i);
        AirlineName.setText(mask.getAirlineName());
        AirlineWebSite.setText(mask.getAirlineWebSite());
        imageView.setImageBitmap(mask.getAirlineImage(mask.getImage()));
        return v;
    }
}
