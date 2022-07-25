package com.azizbek.carona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<Corona> coronaArrayList;
    CoronaAdapter coronaAdapter;
    RelativeLayout relativeLayout;
    RecyclerView recyclerView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycle1);
        relativeLayout=findViewById(R.id.relative);
        editText=findViewById(R.id.edittext1);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://disease.sh/v3/covid-19/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api= retrofit.create(Api.class);
        Call<List<Corona>> call=api.getAllcountry();
        call.enqueue(new Callback<List<Corona>>() {
            @Override
            public void onResponse(Call<List<Corona>> call, Response<List<Corona>> response) {
                if (response.code()==200){
                    coronaArrayList= (ArrayList<Corona>) response.body();
                    coronaAdapter=new CoronaAdapter(MainActivity.this,coronaArrayList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(coronaAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Corona>> call, Throwable t) {

            }
        });

    }
    private void filter(String text) {
        ArrayList<Corona> filteredList = new ArrayList<>();

        for (Corona item : coronaArrayList) {
            if (item.getCountry().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        coronaAdapter.filterList(filteredList);

    }

}