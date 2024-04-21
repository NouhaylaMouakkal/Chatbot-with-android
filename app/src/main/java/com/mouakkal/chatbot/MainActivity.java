package com.mouakkal.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mouakkal.chatbot.api.BrainShopApi;
import com.mouakkal.chatbot.model.BrainShopResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private List<Message> messages = new ArrayList<>();
    private EditText editTextUser;
    private ImageButton buttonSend;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUser=findViewById(R.id.edtMessage);
        buttonSend = findViewById(R.id.buttonSend);
        recyclerView = findViewById(R.id.rvChBot);
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit= new Retrofit.Builder()
                //.baseUrl("http://api.brainshop.ai/get?bid=181503&key=qN06oZmNFc6ItSZ3&uid=[uid]&msg=hello")
                .baseUrl("http://api.brainshop.ai/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        BrainShopApi brainShopApi= retrofit.create(BrainShopApi.class);
        buttonSend.setOnClickListener(view ->{
            String msg = editTextUser.getText().toString();
            String url = "get?bid=181503&key=qN06oZmNFc6ItSZ3&uid=[uid]&msg="+msg;
            Call<BrainShopResponse> response = brainShopApi.getMessage(url);
            editTextUser.setText("");
            response.enqueue(new Callback<BrainShopResponse>() {
                @Override
                public void onResponse(Call<BrainShopResponse> call, Response<BrainShopResponse> response) {
                    Log.i("info",response.body().getCnt());
                }

                @Override
                public void onFailure(Call<BrainShopResponse> call, Throwable t) {
                    Log.i("info",t.getMessage());
                }
            });
        });
    }
}