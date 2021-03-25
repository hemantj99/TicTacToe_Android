package com.example.tictactoe;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flatdialoglibrary.dialog.FlatDialog;

public class MainActivity extends AppCompatActivity {
    Button Reset;
    TextView textView1,textView2;
    boolean gameActive = true;
    int activePlayer = 0;
    int count=0,x=0;
    int[] gameState = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    //    State meanings:
    //    0 - X
    //    1 - O
    //    -1 - Null
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};
    public String Person1,Person2;
    public int winnercount1,winnercount2;

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
                img.setImageResource(R.drawable.o);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setTextColor(Color.parseColor("#2C7A3D"));
                status.setText(Person2 + "'s Turn - Tap to play");
            }
            else
            {
                count+=1;
                img.setImageResource(R.drawable.x);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setTextColor(Color.parseColor("#2C7A3D"));
                status.setText(Person1 + "'s Turn - Tap to play");
            }

        }
        // Check Which player has won
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
                    win = Person1;
                    winnercount1+=1;
                    count=0;
                    activePlayer = 1;

                }
                else
                {
                    win = Person2;
                    winnercount2+=1;
                    count=0;
                    activePlayer = 0;

                }
                final FlatDialog flatDialog = new FlatDialog(MainActivity.this);
                flatDialog.setTitle("               Winner is "+ win)
                .setFirstButtonText("Play Again")
                .withFirstButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flatDialog.dismiss();
                        gameReset(view);
                    }
                })
                .show();
            }
        }
        if(count==9)
        {
            TextView status = findViewById(R.id.status);
            status.setText("Draw");
            gameActive = false;
        }
    }
    public void gameReset(View view)
    {
        gameActive = true;
        textView1.setText(""+winnercount1);
        textView2.setText(""+winnercount2);
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
        ((ImageView)findViewById(R.id.imageView8 )).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setTextColor(Color.parseColor("#2C7A3D"));
        if (x!=winnercount1){
            activePlayer = 1;
            status.setText(Person2 + "'s Turn - Tap to play");
            x=winnercount1;
        }
        else{
            activePlayer = 0;
            status.setText(Person1 + "'s Turn - Tap to play");
            x=winnercount2;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FlatDialog flatDialog = new FlatDialog(MainActivity.this);
        flatDialog.setTitle("Choose Player's Name");
        flatDialog.setFirstTextFieldHint("Person 1 - Tom");
        flatDialog.setSecondTextFieldHint("Person 2 - Jerry");
        flatDialog.setFirstButtonText("Start");
        flatDialog.withFirstButtonListner(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person1 = flatDialog.getFirstTextField();
                Person2 = flatDialog.getSecondTextField();
                if(Person1.equals("")){
                    Person1 = "Tom";
                }
                if(Person2.equals("")){
                    Person2 = "Jerry";
                }
                flatDialog.dismiss();
                gameReset(view);
            }
        });
        flatDialog.show();
        Reset = (Button) findViewById(R.id.Reset);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameReset(view);
            }
        });
    }
}

