package com.anuj.potdar.pesavetask;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.anuj.potdar.pesavetask.databinding.ActivityMainBinding;
import com.anuj.potdar.pesavetask.transaction.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Message> messages;
    private ActivityMainBinding binding;
    private TransactionAdapter adapter;
    private MainActivity activity;
    private RequestQueue requestQueue;
    private String url = "http://uat.pesave.com/apiv2/CronController/jsonTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        activity = this;
        requestQueue = Volley.newRequestQueue(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        binding.recyclerView.setLayoutManager(linearLayoutManager);

        messages = new ArrayList<>();

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

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("message");
                    binding.progressBar.setVisibility(View.GONE);
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject message = jsonArray.getJSONObject(i);

                        messages.add(new Message(message.getString("transactionName"),
                                message.getString("transactionAmount"),
                                message.getString("chillrAmount"),
                                message.getString("transactionTime")));
                    }

                    adapter = new TransactionAdapter(messages,activity);
                    binding.recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    binding.progressBar.setVisibility(View.GONE);
                    binding.buttonRetry.setVisibility(View.VISIBLE);
                    binding.errorMsg.setVisibility(View.VISIBLE);
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                binding.progressBar.setVisibility(View.GONE);
                binding.buttonRetry.setVisibility(View.VISIBLE);
                binding.errorMsg.setVisibility(View.VISIBLE);
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

}
