package com.example.suraj.ttt;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import static android.R.attr.delay;


public class MainActivity extends AppCompatActivity {

    int[][] matrix = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    int counter = 0, gamecount = 0;
    int score1 = 0, score2 = 0;
    TextView t;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }


    public void click(View v) throws InterruptedException {

        Button button = (Button) v;
        boolean check, check1;
        int c;

        CharSequence data = button.getText();

        check = data.equals("X");
        check1 = data.equals("O");

        if (!(check == true || check1 == true)) {

            if (gamecount % 2 == 0) {

                if (counter % 2 == 0) {
                    button.setText("X");
                    c = 1;

                } else {
                    button.setText("O");
                    c = 2;
                }
            } else {

                if (counter % 2 == 0) {
                    button.setText("O");
                    c = 2;
                } else {
                    button.setText("X");
                    c = 1;
                }
            }

            String s = v.getResources().getResourceName(v.getId());


            switch (s.charAt(s.length() - 1)) {

                case '1':
                    matrix[0][0] = c;
                    break;

                case '2':
                    matrix[0][1] = c;
                    break;

                case '3':
                    matrix[0][2] = c;
                    break;

                case '4':
                    matrix[1][0] = c;
                    break;

                case '5':
                    matrix[1][1] = c;
                    break;

                case '6':
                    matrix[1][2] = c;
                    break;

                case '7':
                    matrix[2][0] = c;
                    break;

                case '8':
                    matrix[2][1] = c;
                    break;

                case '9':
                    matrix[2][2] = c;
                    break;
            }


            int r = win(c);
            incCounter();

            if (r == 1) {
                Context context = getApplicationContext();
                CharSequence text = "The winner is Player " + c;
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();


                if (c == 1) score1++;
                else score2++;

                incGamecount();
                gameStarter();
                displayGamecount();
                displayScores();
                resetCounter();
                updateMatrix();
                clearButtons();

            }


            if (counter == 9) {
                Context context = getApplicationContext();
                CharSequence text = "The Game Has Ended In a Draw";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                incGamecount();
                gameStarter();
                displayGamecount();
                resetCounter();
                updateMatrix();

                clearButtons();

            }


        }

    }


    public int win(int c) {

        if ((matrix[0][0] == c && matrix[0][1] == c && matrix[0][2] == c) ||
                (matrix[1][0] == c && matrix[1][1] == c && matrix[1][2] == c) ||
                (matrix[2][0] == c && matrix[2][1] == c && matrix[2][2] == c) ||
                (matrix[0][0] == c && matrix[1][0] == c && matrix[2][0] == c) ||
                (matrix[0][1] == c && matrix[1][1] == c && matrix[2][1] == c) ||
                (matrix[0][2] == c && matrix[1][2] == c && matrix[2][2] == c) ||
                (matrix[0][0] == c && matrix[1][1] == c && matrix[2][2] == c) ||
                (matrix[0][2] == c && matrix[1][1] == c && matrix[2][0] == c))

            return 1;

        else return 0;


    }


    public void updateMatrix() {
        int i, j;

        for (i = 0; i < 3; i++)
            for (j = 0; j < 3; j++)
                matrix[i][j] = 0;

    }

    public void clearButtons() {

        Button b1 = (Button) findViewById(R.id.button1);
        b1.setText("");
        b1 = (Button) findViewById(R.id.button2);
        b1.setText("");
        b1 = (Button) findViewById(R.id.button3);
        b1.setText("");
        b1 = (Button) findViewById(R.id.button4);
        b1.setText("");
        b1 = (Button) findViewById(R.id.button5);
        b1.setText("");
        b1 = (Button) findViewById(R.id.button6);
        b1.setText("");
        b1 = (Button) findViewById(R.id.button7);
        b1.setText("");
        b1 = (Button) findViewById(R.id.button8);
        b1.setText("");
        b1 = (Button) findViewById(R.id.button9);
        b1.setText("");

    }

    public void resetScore() {
        score1 = 0;
        score2 = 0;
    }

    public void resetGamecount() {
        gamecount = 0;
    }

    public void resetCounter() {
        counter = 0;
    }

    public void incGamecount() {
        gamecount++;
    }

    public void incCounter() {
        counter++;
    }

    public void displayScores() {
        TextView Player1 = (TextView) findViewById(R.id.score1);
        Player1.setText("SCORE : " + score1);

        Player1 = (TextView) findViewById(R.id.score2);
        Player1.setText("SCORE : " + score2);
    }

    public void displayGamecount() {

        TextView S = (TextView) findViewById(R.id.textView);
        S.setText("NUMBER OF GAMES : " + gamecount);
    }

    public void reset(View v) {
        resetCounter();
        resetGamecount();
        updateMatrix();
        clearButtons();
        resetScore();
        gameStarter();
        displayScores();
        displayGamecount();

    }


    public void gameStarter()
    {
        TextView T;

        if(gamecount % 2 == 0)
        {
            T = (TextView)findViewById(R.id.p1);
            T.setText("PLAYER 1 (X)*");

            T = (TextView)findViewById(R.id.p2);
            T.setText("PLAYER 2 (O)");
        }

        else
        {
            T = (TextView)findViewById(R.id.p2);
            T.setText("PLAYER 2 (O)*");

            T = (TextView)findViewById(R.id.p1);
            T.setText("PLAYER 1 (X)");
        }


    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}


