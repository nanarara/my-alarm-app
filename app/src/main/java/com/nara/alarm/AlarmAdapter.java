package com.nara.alarm;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nara.yoon on 2017-08-21.
 */

public class AlarmAdapter extends BaseAdapter{

    private ArrayList<AlarmItem> items = new ArrayList<>();
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int pos) {
        return items.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        Context mContext = parent.getContext();

        if(view ==null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.alarm_list_item, parent, false);
        }

        ImageView imageView = (ImageView)view.findViewById(R.id.alarm_img);
        TextView nameText = (TextView)view.findViewById(R.id.alarm_text);
        TextView contentText = (TextView)view.findViewById(R.id.alarm_content);

        AlarmItem item = (AlarmItem) getItem(pos);

        imageView.setImageDrawable(item.getIcon());
        nameText.setText(item.getName());
        contentText.setText(item.getContent());

        return view;
    }

    public void addItem(Drawable img, String name, String content){
        AlarmItem item = new AlarmItem();

        item.setIcon(img);
        item.setName(name);
        item.setContent(content);

        items.add(item);
    }
}
