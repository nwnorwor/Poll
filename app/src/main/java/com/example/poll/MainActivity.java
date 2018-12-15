package com.example.poll;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.poll.db.DatabaseHelper;
import com.example.poll.model.ScoreItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.poll.db.DatabaseHelper.COL_ID;
import static com.example.poll.db.DatabaseHelper.COL_IMAGE;
import static com.example.poll.db.DatabaseHelper.COL_NUM;
import static com.example.poll.db.DatabaseHelper.COL_SCORE;
import static com.example.poll.db.DatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<ScoreItem> mScoreItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DatabaseHelper(MainActivity.this);
        mDb = mHelper.getWritableDatabase();

        Button vote_1 = findViewById(R.id.vote_1);
        Button vote_2 = findViewById(R.id.vote_2);
        Button vote_3 = findViewById(R.id.vote_3);
        Button vote_no = findViewById(R.id.vote_no);

        Button Result = findViewById(R.id.view_result);



        loadPhoneData();

        vote_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                ScoreItem item = mScoreItemList.get(0);
                String textscore = item.score.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NUM, "no");
                cv.put(COL_SCORE,newscore);
                cv.put(COL_IMAGE,"vote_no.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(1)}
                );
                loadPhoneData();


            }
        });

        vote_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                ScoreItem item = mScoreItemList.get(1);
                String textscore = item.score.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NUM, "1");
                cv.put(COL_SCORE,newscore);
                cv.put(COL_IMAGE,"one.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(2)}
                );
                loadPhoneData();

            }
        });

        vote_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                ScoreItem item = mScoreItemList.get(2);
                String textscore = item.score.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NUM, "2");
                cv.put(COL_SCORE,newscore);
                cv.put(COL_IMAGE,"two.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(3)}
                );
                loadPhoneData();

            }
        });

        vote_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                ScoreItem item = mScoreItemList.get(3);
                String textscore = item.score.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NUM, "3");
                cv.put(COL_SCORE,newscore);
                cv.put(COL_IMAGE,"three.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(4)}
                );
                loadPhoneData();

            }
        });



        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });



        //todo

    }

    private void loadPhoneData() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);

        mScoreItemList = new ArrayList<>();
        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(COL_ID));
            String name = c.getString(c.getColumnIndex(COL_NUM));
            String score = c.getString(c.getColumnIndex(COL_SCORE));
            String image = c.getString(c.getColumnIndex(COL_IMAGE));

            ScoreItem item = new ScoreItem(id, name, score,image);
            mScoreItemList.add(item);
        }
        c.close();
    }
}
