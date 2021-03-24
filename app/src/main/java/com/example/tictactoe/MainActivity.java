package com.example.tictactoe;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button Reset;
    boolean gameActive = true;
    int activePlayer = 0;
    int count=0;
    int[] gameState = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    //    State meanings:
    //    0 - X
    //    1 - O
    //    -1 - Null
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};
    public void playatap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage] == -1) {
            gameState[tappedImage] = activePlayer;
            if (activePlayer == 0)
            {
                count+=1;
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("Tom's Turn - Tap to play");
            }
            else
            {
                count+=1;
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("Jerry's Turn - Tap to play");
            }

        }
        // Check Which player has won
        if (count<9){
        for(int[] winPosition: winPositions)
        {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != -1)
            {

                String win;
                gameActive = false;
                if (gameState[winPosition[0]] == 0)
                {
                    win = "Jerry has won";
                    count=0;

                }
                else
                {
                    win = "Tom has won";
                    count=0;

                }
                TextView status = findViewById(R.id.status);
                status.setText(win);
            }
        }}
        else
        {
            TextView status = findViewById(R.id.status);
            status.setText("Draw");
            gameActive = false;

        }
    }
    public void gameReset(View view)
    {
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++)
        {
            gameState[i] = -1;
        }
        count=0;
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("Jerry's Turn - Tap to play");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Reset = (Button) findViewById(R.id.Reset);
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameReset(view);
            }
        });
    }
}

