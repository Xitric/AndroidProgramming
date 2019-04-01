package com.xitric.articleexercise;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ArticleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_article, container, false);
    }

    public void setArticle(Article article) {
        View view;

        if ((view = getView()) != null) {
            TextView titleLabel = view.findViewById(R.id.titleLabel);
            titleLabel.setText(article.getTitle());

            TextView contentLabel = view.findViewById(R.id.contentLabel);
            contentLabel.setText(article.getContent());
        }
    }
}
