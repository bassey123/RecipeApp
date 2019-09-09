package com.example.recipeapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.recipeapp.adapter.RecipeAdapter;
import com.example.recipeapp.api.RecipeAPI;
import com.example.recipeapp.api.RecipeClient;
import com.example.recipeapp.model.RecipeModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeActivity extends AppCompatActivity {

    static List<RecipeModel> recipesList;
    private RecipeAdapter adapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recipesList = new ArrayList<>();
        adapter = new RecipeAdapter(this, recipesList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        RecipeAPI service = RecipeClient.getClient().create(RecipeAPI.class);
        Call<List<RecipeModel>> call = service.readJson();
        call.enqueue(new Callback<List<RecipeModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<RecipeModel>> call, @NotNull Response<List<RecipeModel>> response) {
                recipesList = response.body();
                assert recipesList != null;
                adapter.setRecipes(recipesList);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(@NotNull Call<List<RecipeModel>> call, @NotNull Throwable t) {
                Log.e("RecipeActivity", "Error calling API", t);
            }
        });
    }

    public  static RecipeModel getRecipe(int position){
        return recipesList.get(position);
    }
}
