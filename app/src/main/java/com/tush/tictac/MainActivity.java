package com.tush.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean gameStatus = true;
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    @SuppressLint("SetTextI18n")
    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int position = Integer.parseInt(counter.getTag().toString());


            if(gameState[position]==2 && gameStatus) {
                gameState[position] = activePlayer;
                counter.setTranslationY(-1500);

                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.greencount);
                    activePlayer = 1;
                } else {
                    counter.setImageResource(R.drawable.red);
                    activePlayer = 0;
                }
                counter.animate().translationYBy(1500);

                for (int[] winPosition : winPositions) {

                    if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[2]] == gameState[winPosition[0]] && gameState[winPosition[0]] != 2 ) {

                        gameStatus = false;
                        String winner = "";

                        if (activePlayer == 1) {
                            winner = "Green";


                        } else {
                            winner = "Red";
                        }
                        TextView gameWinnerText = (TextView) findViewById(R.id.gameWinnerText);
                        Button restartGame = (Button) findViewById(R.id.startNewGame);
                        gameWinnerText.setText(winner + " has Won!!");
                        gameWinnerText.setVisibility(View.VISIBLE);
                        restartGame.setVisibility(View.VISIBLE);

                    }

                }
            }


    }

    public  void  startNew(View view){

        Button restartGame = (Button) findViewById(R.id.startNewGame);
        TextView gameWinnerText = (TextView) findViewById(R.id.gameWinnerText);
        restartGame.setVisibility(View.INVISIBLE);
        gameWinnerText.setVisibility(View.INVISIBLE);
        GridLayout gridLayout;
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i<gridLayout.getChildCount();i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        Arrays.fill(gameState, 2);
        gameStatus = true;
        activePlayer = 0;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}