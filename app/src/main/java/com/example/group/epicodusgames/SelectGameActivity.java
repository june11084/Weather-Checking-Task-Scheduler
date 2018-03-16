package com.example.group.epicodusgames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectGameActivity extends AppCompatActivity {
    @BindView(R.id.listView) ListView mListView;
    @BindView(R.id.gameWelcome) TextView mGameWelcome;
    private String[] games = new String[] {"Boggle", "Tic-Tac-Toe", "Black Jack", "Mad-Libs"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        mGameWelcome.setText(message);
        SelectGameArrayAdapter adapter = new SelectGameArrayAdapter(this, android.R.layout.simple_list_item_1, games);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String whatGame = ((TextView)view).getText().toString();
                if(whatGame.equals("Boggle")) {
                    startActivity(new Intent(getBaseContext(), BoggleActivity.class));
                } else if(whatGame.equals("Tic-Tac-Toe") || whatGame.equals("Black Jack") || whatGame.equals("Mad-Libs")){
                    Toast.makeText(SelectGameActivity.this, "Not Yet Implemented", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
