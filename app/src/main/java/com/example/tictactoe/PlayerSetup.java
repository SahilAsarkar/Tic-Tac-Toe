package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlayerSetup extends AppCompatActivity {
    private EditText player1;
    private EditText player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.player_setup);

        player1 = findViewById(R.id.playerName1);
        player2 = findViewById(R.id.playerName2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void submitButtonClick(View view) {
        String player1name = player1.getText().toString().trim();
        String player2name = player2.getText().toString().trim();

        if (player1name.isEmpty() || player2name.isEmpty()) {
            Toast.makeText(this, "Please enter names for both players", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, GameDisplay.class);
            intent.putExtra("PLAYERS_NAMES", new String[]{player1name, player2name});
            startActivity(intent);
        }
    }
}
