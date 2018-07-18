package com.example.liz.intentfilecamerapermissiondemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.liz.intentfilecamerapermissiondemo.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;


public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{

    private List<String> mList;
    private Context mContext;
    private int mImageWidth,mImageHeight;

    public RecyclerViewAdapter(List<String> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
        mImageWidth = getWidth();
        mImageHeight = getWidth();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_recyclerview,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String path = mList.get(position);
        File file = new File(path);
        Picasso.get().load(file).noFade()
                .noPlaceholder().resize(mImageWidth,mImageHeight)
                .into(holder.mImageView);
    }
    private int getWidth(){
        return (mContext.getResources().getDisplayMetrics().widthPixels)/2;

    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
        }
    }

}

