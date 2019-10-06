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
import br.com.marcosouza.justamobile.model.RecyclingCompany;

public class RecyclingCompanyAdapter extends RecyclerView.Adapter<RecyclingCompanyAdapter.RecyclingCompanyViewHolder>{

    private Context context;
    ArrayList<RecyclingCompany> recyclingCompanies;

    public RecyclingCompanyAdapter(Context context, ArrayList<RecyclingCompany> recyclingCompanies) {
        this.context = context;
        this.recyclingCompanies = recyclingCompanies;
    }


    @NonNull
    @Override
    public RecyclingCompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycling_companies_item, parent, false);
        return new RecyclingCompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclingCompanyViewHolder holder, int position) {
        holder.mTextViewName.setText(recyclingCompanies.get(position).getName());
        holder.mTextViewAddress.setText(recyclingCompanies.get(position).getAddress());
        holder.mTextViewPhone.setText(recyclingCompanies.get(position).getPhone());
        Picasso.get().load(recyclingCompanies.get(position).getThumb()).into(holder.mImageThumb);
    }


    @Override
    public int getItemCount() {
        return recyclingCompanies.size();
    }



    public class RecyclingCompanyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewName;
        TextView mTextViewAddress;
        TextView mTextViewPhone;
        ImageView mImageThumb;

        public RecyclingCompanyViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewName = itemView.findViewById(R.id.textView_companies_name);
            mTextViewAddress= itemView.findViewById(R.id.textView_companies_address);
            mTextViewPhone= itemView.findViewById(R.id.textView_companies_phone);
            mImageThumb = itemView.findViewById(R.id.imageView_thumb);

        }
    }
}
