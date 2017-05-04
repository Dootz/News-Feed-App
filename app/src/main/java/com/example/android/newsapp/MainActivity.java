package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.y;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final int NEWS_LOADER_ID = 1;
    private final String GUARDIAN_REQUEST_URL = "http://content.guardianapis.com/search?q=debates&api-key=test";
    private NewsAdapter mAdapter;
    Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView newsListView = (ListView) findViewById(R.id.list);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News clickedNews = mAdapter.getItem(position);
                Uri newsUri = Uri.parse(clickedNews.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteIntent);
            }
        });

        mAdapter = new NewsAdapter(this, new ArrayList<News>());
        newsListView.setAdapter(mAdapter);
        updateNews();
        updateButton = (Button) findViewById(R.id.updateBT);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNews();
            }
        });
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        mAdapter.clear();
        if (news != null && !news.isEmpty())
            mAdapter.addAll(news);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mAdapter.clear();
    }

    private void updateNews() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            if(loaderManager.getLoader(NEWS_LOADER_ID) != null){
                loaderManager.restartLoader(NEWS_LOADER_ID, null, this);
            }
            else {
                loaderManager.initLoader(NEWS_LOADER_ID, null, this);
            }
        }
    }
}
