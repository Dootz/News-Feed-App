package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 1 on 04.05.2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, List<News> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }
        News currentNews = getItem(position);
        TextView sectionView = (TextView) listItemView.findViewById(R.id.selectionTextView);
        TextView titleView = (TextView) listItemView.findViewById(R.id.titleTextView);
        sectionView.setText(currentNews.getSection());
        titleView.setText(currentNews.getTitle());
        return listItemView;
    }
}
