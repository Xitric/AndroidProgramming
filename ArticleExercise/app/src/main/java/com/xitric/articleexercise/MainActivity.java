package com.xitric.articleexercise;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListFragment articleNamesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articleNamesFragment = new ListFragment();
        List<String> articleNames = new ArrayList<>();
        for (Article article: Article.articles) {
            articleNames.add(article.getTitle());
        }
        articleNamesFragment.setListAdapter(new ArrayAdapter<>(this, R.layout.layout_article_cell, articleNames));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.articleListFragment, articleNamesFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        articleNamesFragment.getListView().setOnItemClickListener((parent, view, position, id) -> showArticle(Article.articles[position]));
    }

    private void showArticle(Article article) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ArticleFragment articleFragment = new ArticleFragment();
        articleFragment.setArticle(article);
        if (findViewById(R.id.articleFragment) != null) {
            //Dedicated fragment for article
            transaction.add(R.id.articleFragment, articleFragment);


        } else {
            //List and article share fragment
            transaction.replace(R.id.articleListFragment, articleFragment);
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
