package br.com.marcosouza.justamobile.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.marcosouza.justamobile.R;
import br.com.marcosouza.justamobile.model.ClickRecyclerView;
import br.com.marcosouza.justamobile.model.NewsArticles;
import br.com.marcosouza.justamobile.model.NewsResponse;
import br.com.marcosouza.justamobile.ui.adapter.NewsAdapter;
import br.com.marcosouza.justamobile.ui.viewmodels.NewsViewModel;

public class NewsFragment extends Fragment implements ClickRecyclerView {

    private ArrayList<NewsArticles> newsArticleList = new ArrayList <> ();
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;

    private NewsViewModel newsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newsViewModel =
                ViewModelProviders.of(this).get(NewsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = root.findViewById(R.id.rv_news);
        newsViewModel =
                ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.init();
        newsViewModel.getNewsRepository().observe(this, new Observer<NewsResponse>() {
            @Override
            public void onChanged(NewsResponse newsResponse) {
                List<NewsArticles> newsArticles = newsResponse.getArticles();
                newsArticleList.addAll(newsArticles);
                newsAdapter.notifyDataSetChanged();
            }
        });
        loadRecyclerView();

        return root;
    }

    private void loadRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = new NewsAdapter(getActivity(), newsArticleList, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(newsAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            newsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCustomClick(View view, int position, boolean isLongClick) {
        //TODO add webview posteriromente
        Uri uri = Uri.parse(newsArticleList.get(position).getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}