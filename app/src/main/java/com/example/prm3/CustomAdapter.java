package com.example.prm3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.net.URL;
import java.util.List;

public class CustomAdapter extends BaseAdapter {


    private List<Article> list;
    Context context;

    public CustomAdapter(@NonNull Context context, @NonNull List<Article> objects) {
        this.context = context;
        this.list = objects;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            ViewHolder viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.single_row, parent, false);
            viewHolder.icon = (ImageView) row.findViewById(R.id.imageView);
            viewHolder.title = (TextView) row.findViewById(R.id.title);
            viewHolder.text = (TextView) row.findViewById(R.id.text);
            row.setTag(viewHolder);
        }


        ViewHolder viewHolder = (ViewHolder) row.getTag();
        viewHolder.text.setText(list.get(position).getText());
        viewHolder.title.setText(list.get(position).getTitle());
        new DownloadImageTask(viewHolder.icon)
                .execute(list.get(position).getIconURL());


        return row;
    }

    public Bitmap getBitmap(String bitmapUrl) {
        try {
            URL url = new URL(bitmapUrl);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (Exception ex) {
            return null;
        }
    }
}
