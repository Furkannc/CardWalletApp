package com.example.cardwallet;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
         ImageView imgAdd=(ImageView) findViewById(R.id.addIcon);
        layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        SQLiteHelper db=new SQLiteHelper(this);

        List<Card> cardList=db.GetAllCard();

        CustomAdapter adapter = new CustomAdapter(cardList,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,CardAdd.class);
                startActivity(intent);
            }
        });

    }
}