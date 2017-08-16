package com.example.admin.w3d2_price;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 8/16/2017.
 */

public class StuffAdapter extends RecyclerView.Adapter<StuffAdapter.ViewHolder> {

    ArrayList<Stuff> stuffList = new ArrayList<Stuff>();
    Context context;


    public StuffAdapter(ArrayList stuffList){
        this.stuffList = stuffList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOne, tvTwo, tvThree;
        ImageView picture;
        public ViewHolder(View itemView) {
            super(itemView);
            tvOne = (TextView) itemView.findViewById(R.id.tvOne);
            tvTwo = (TextView) itemView.findViewById(R.id.tvTwo);
            tvThree = (TextView) itemView.findViewById(R.id.tvThree);
            picture = (ImageView) itemView.findViewById(R.id.picture);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stuff_entry, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(StuffAdapter.ViewHolder holder, int position) {
        final Stuff thing = stuffList.get(position);
        holder.tvOne.setText(thing.getOne());
        holder.tvTwo.setText(thing.getTwo());
        holder.tvThree.setText(thing.getThree());
        holder.picture.setImageBitmap(thing.getPicture());
    }

    @Override
    public int getItemCount() {
        if(stuffList != null)
            return stuffList.size();
        else {
            return 0;
        }
    }


}
