package com.example.venkat.connect3;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
                        //someone has won
                        System.out.println(gameState[winningPosition[0]]);
                        isGameNotWon = 1;
                        String winner = "Red";
                        TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                        winnerMessage.setTextColor(Color.RED);
                        if(gameState[winningPosition[0]] == 0) {
                            winner = "Yellow";
                            winnerMessage.setTextColor(Color.YELLOW);
                        }
                        winnerMessage.setText(winner + " has won!");
                        Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                        LinearLayout playAgainLayout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        playAgainLayout.startAnimation(slideUp);
                        playAgainLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view) {

        System.out.println("playAgain called");

        Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        LinearLayout playAgainLayout = (LinearLayout)findViewById(R.id.playAgainLayout);
        playAgainLayout.startAnimation(slideDown);
        playAgainLayout.setVisibility(View.INVISIBLE);

        System.out.println("Layout gone");

        activePlayer = 0;
        isGameNotWon = 0;
        for(int i = 0; i<gameState.length; i++) {
            gameState[i] = 2;
        }

        System.out.println("Array state reset");

        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout)findViewById(R.id.gridLayout);
        for(int i = 0; i<gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

        System.out.println("Image reset");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
