package com.xitric.glideexercise;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private static final int MAX_ID = 1084;
    private static final String BASE_URL = "https://picsum.photos/500/500?image=";
    private int[] imageIds;

    private final GlideRequests glide;

    public ImageAdapter(GlideRequests glide) {
        this.glide = glide;
        imageIds = new int[50];
        for (int i = 0; i < 50; i++) {
            imageIds[i] = (int) (Math.random() * MAX_ID);
        }
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View cellView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_cell, viewGroup, false);
        return new ImageViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder imageViewHolder, int i) {
        glide.load(BASE_URL + imageIds[i])
                .placeholder(R.drawable.move)
                .centerCrop()
//                .into(imageViewHolder.imageView);
        .into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                imageViewHolder.imageView.setImageDrawable(resource);
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                imageIds[imageViewHolder.getAdapterPosition()] = (int) (Math.random() * MAX_ID);
                notifyItemChanged(imageViewHolder.getAdapterPosition());
                Log.i("XITRIC", "Fixed image id error");
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageIds.length;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ImageViewHolder(@NonNull View view) {
            super(view);
            this.imageView = view.findViewById(R.id.cellImageView);
        }
    }
}
