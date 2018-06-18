package com.example.venkat.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    //0 = yellow, 1 = red, 2 = unplayed

    int activePlayer = 0;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    //0 means not won
    int isGameNotWon = 0;

    public void dropIn(View view) {
        if(isGameNotWon == 0) {
            ImageView counter = (ImageView) view;
            System.out.println(counter.getTag().toString());
            int tappedCounter = Integer.parseInt(counter.getTag().toString());

            if (gameState[tappedCounter] == 2) {

                counter.setTranslationY(-1000f);

                gameState[tappedCounter] = activePlayer; //saves the move of the current player

                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.yellow);
                    activePlayer = 1;
                } else {
                    counter.setImageResource(R.drawable.red);
                    activePlayer = 0;
                }
                counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

                for (int[] winningPosition : winningPositions) {
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                            gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                            gameState[winningPosition[0]] != 2) {
                        System.out.println(gameState[winningPosition[0]]);
                        isGameNotWon = 1;
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
