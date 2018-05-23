package com.anuj.potdar.pesavetask;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.anuj.potdar.pesavetask.databinding.ActivityMainBinding;
import com.anuj.potdar.pesavetask.transaction.Message;
import com.anuj.potdar.pesavetask.transaction.Response;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private APIInterface apiInterface = ServiceGenerator.createService(APIInterface.class,this);
    private ArrayList<Message> messages;
    private ActivityMainBinding binding;
    private TransactionAdapter adapter;
    private MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        activity = this;
        apiInterface = ServiceGenerator.createService(APIInterface.class,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        binding.recyclerView.setLayoutManager(linearLayoutManager);

        getData();

        binding.buttonRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

        setContentView(binding.getRoot());
    }

    private void getData() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.buttonRetry.setVisibility(View.GONE);
        binding.errorMsg.setVisibility(View.GONE);
        Call<Response> transactionsCall = apiInterface.getTransactionDetails();
        transactionsCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response != null){
                    if(response.body()!=null){
                        if(response.body().getStatus().equals("success")){
                            binding.progressBar.setVisibility(View.GONE);
                            messages = (ArrayList<Message>) response.body().getMessage();
                            adapter = new TransactionAdapter(messages,activity);
                            binding.recyclerView.setAdapter(adapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                binding.buttonRetry.setVisibility(View.VISIBLE);
                binding.errorMsg.setVisibility(View.VISIBLE);
            }
        });
    }
}
