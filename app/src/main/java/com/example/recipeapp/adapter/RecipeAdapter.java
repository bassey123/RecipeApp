package com.example.recipeapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.recipeapp.R;
import com.example.recipeapp.model.RecipeModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    Context mContext;
    List<RecipeModel> recipes;
    RecipeOnclickListener mListener;

    public RecipeAdapter(Context mContext, List<RecipeModel> recipes) {
        this.mContext = mContext;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recipe_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        RecipeModel recipeModel = recipes.get(i);
        viewHolder.name.setText(recipeModel.getName());

        Glide.with(mContext).load(recipeModel.getImage())
                .error(R.drawable.ic_launcher_background)
                .apply(RequestOptions.centerCropTransform())
                .into(viewHolder.image);

    }

    @Override
    public int getItemCount() {
        return recipes == null ? 0 : recipes.size();
    }

    public void setRecipes(List<RecipeModel> recipes){
        for(RecipeModel recipe:recipes){
            addRecipe(recipe);
        }
    }

    public void addRecipe(RecipeModel recipe){
        recipes.add(recipe);
        notifyItemInserted(recipes.size());
    }

    public interface  RecipeOnclickListener{
        void onClick(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name;
        private CircleImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.recipe_name);
            image = itemView.findViewById(R.id.recipe_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
