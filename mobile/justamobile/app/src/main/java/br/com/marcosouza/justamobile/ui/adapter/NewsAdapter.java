package br.com.marcosouza.justamobile.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.marcosouza.justamobile.R;
import br.com.marcosouza.justamobile.model.NewsArticles;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private Context context;
    ArrayList<NewsArticles> newsArticles;

    public NewsAdapter(Context context, ArrayList<NewsArticles> newsArticles) {
        this.context = context;
        this.newsArticles = newsArticles;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.mTextViewTitle.setText(newsArticles.get(position).getTitle());
        holder.mTextViewDescription.setText(newsArticles.get(position).getDescription());
        Picasso.get().load(newsArticles.get(position).getUrlToImage()).into(holder.mImageThumbNews);

    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewTitle;
        TextView mTextViewDescription;
        ImageView mImageThumbNews;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewTitle = itemView.findViewById(R.id.textView_title);
            mTextViewDescription = itemView.findViewById(R.id.textView_description);
            mImageThumbNews = itemView.findViewById(R.id.imageView_thumb);

        }
    }
}
