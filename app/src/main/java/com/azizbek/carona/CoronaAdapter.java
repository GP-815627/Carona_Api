package com.azizbek.carona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CoronaAdapter extends RecyclerView.Adapter<CoronaAdapter.CoronaViewHolder> {
    Context context;
    ArrayList<Corona> coronaArrayList;

    public CoronaAdapter(Context context, ArrayList<Corona> coronaArrayList) {
        this.context = context;
        this.coronaArrayList = coronaArrayList;
    }

    @NonNull
    @Override
    public CoronaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.carano_layaut,parent,false);
        return new CoronaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoronaViewHolder holder, int position) {

        holder.textView1.setText("Страна: "+coronaArrayList.get(position).getCountry());
        holder.textView2.setText("Зараженный: "+coronaArrayList.get(position).getCases());
        holder.textView3.setText("Выздоровевшие: "+coronaArrayList.get(position).getRecovered());
        holder.textView4.setText("Мертвый : "+coronaArrayList.get(position).getDeaths());
        Glide.with(context).load(coronaArrayList.get(position).getCountryInfo().getFlag()).into(holder.imageViewFLAG);


    }

    @Override
    public int getItemCount() {
        return coronaArrayList.size();
    }
    public void filterList(ArrayList<Corona> filteredList) {
        coronaArrayList = filteredList;
        notifyDataSetChanged();
    }


    class CoronaViewHolder extends RecyclerView.ViewHolder {
        TextView textView1,textView2,textView3,textView4;
        ImageView imageViewFLAG;
        public CoronaViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.text1);
            textView2=itemView.findViewById(R.id.text2);
            textView3=itemView.findViewById(R.id.text3);
            textView4=itemView.findViewById(R.id.text4);
            imageViewFLAG=itemView.findViewById(R.id.imageFLAG);
        }
    }
}
