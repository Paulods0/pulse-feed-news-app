package com.example.pulsefeed;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsRecyclerViewHolder> {

    List<Article> articleList;

    public NewsRecyclerAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public NewsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_row, parent, false);
        return  new NewsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.titleViewTxt.setText(article.getTitle());
        holder.sourceTxt.setText(article.getSource().getName());
        Picasso.get()
                .load(article.getUrlToImage())
                .error(R.drawable.no_image_icon)
                .placeholder(R.drawable.no_image_icon)
                .into(holder.imageView);

        holder.itemView.setOnClickListener((v)-> {
            Intent intent = new Intent(v.getContext(), NewsFullActivity.class);
            intent.putExtra("url", article.getUrl());
            v.getContext().startActivity(intent);
        });
    }

    void updateData(List<Article> data){
        articleList.clear();
        articleList.addAll(data);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class NewsRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView sourceTxt, titleViewTxt;
        ImageView imageView;

        public NewsRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            sourceTxt = itemView.findViewById(R.id.article_source);
            titleViewTxt = itemView.findViewById(R.id.article_title);
            imageView = itemView.findViewById(R.id.image_article_view);
        }
    }
}
