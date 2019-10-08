package br.com.marcosouza.justamobile.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.marcosouza.justamobile.R;
import br.com.marcosouza.justamobile.model.Neighborhoods;

public class NeighbordhoodsAdapter extends RecyclerView.Adapter<NeighbordhoodsAdapter.RecyclingCompanyViewHolder>{

    private Context context;
    ArrayList<Neighborhoods> neighborhoods;

    public NeighbordhoodsAdapter(Context context, ArrayList<Neighborhoods> neighborhoods) {
        this.context = context;
        this.neighborhoods = neighborhoods;
    }


    @NonNull
    @Override
    public RecyclingCompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.neighborhood_item, parent, false);
        return new RecyclingCompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclingCompanyViewHolder holder, int position) {
        holder.mTextViewName.setText(neighborhoods.get(position).getName());
        holder.mTextViewWeekDay.setText(neighborhoods.get(position).getDays());
        holder.mTextViewSchedule.setText(neighborhoods.get(position).getSchedule());
    }

    public void filteredList(List<Neighborhoods> newList){
     neighborhoods = new ArrayList<>();
     neighborhoods.addAll(newList);
     notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return neighborhoods.size();
    }



    public class RecyclingCompanyViewHolder extends RecyclerView.ViewHolder{
        TextView mTextViewName;
        TextView mTextViewWeekDay;
        TextView mTextViewSchedule;

        public RecyclingCompanyViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewName = itemView.findViewById(R.id.textView_neighborhood_name);
            mTextViewWeekDay= itemView.findViewById(R.id.textView_week_day);
            mTextViewSchedule= itemView.findViewById(R.id.textView_schedule);

        }

    }

}
