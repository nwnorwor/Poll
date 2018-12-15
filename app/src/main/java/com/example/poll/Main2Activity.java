package com.example.poll;

import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.poll.db.DatabaseHelper;
import com.example.poll.model.ScoreItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.poll.db.DatabaseHelper.COL_ID;
import static com.example.poll.db.DatabaseHelper.COL_IMAGE;
import static com.example.poll.db.DatabaseHelper.COL_NUM;
import static com.example.poll.db.DatabaseHelper.COL_SCORE;
import static com.example.poll.db.DatabaseHelper.TABLE_NAME;

public class Main2Activity extends AppCompatActivity {

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<ScoreItem> mScoreItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    mHelper = new DatabaseHelper(Main2Activity.this);
    mDb = mHelper.getWritableDatabase();

    loadPhoneData();
    setupListView();

    Button deleteScore = findViewById(R.id.delete);
        deleteScore.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ContentValues cv0 = new ContentValues();
            cv0.put(COL_NUM, "no");
            cv0.put(COL_SCORE,"0");
            cv0.put(COL_IMAGE,"vote_no.png");


            mDb.update(
                    TABLE_NAME,
                    cv0,
                    COL_ID + " = ?",
                    new String[]{String.valueOf(1)}
            );

            ContentValues cv1 = new ContentValues();
            cv1.put(COL_NUM, "1");
            cv1.put(COL_SCORE,"0");
            cv1.put(COL_IMAGE,"one.png");


            mDb.update(
                    TABLE_NAME,
                    cv1,
                    COL_ID + " = ?",
                    new String[]{String.valueOf(2)}
            );

            ContentValues cv2 = new ContentValues();
            cv2.put(COL_NUM, "2");
            cv2.put(COL_SCORE,"0");
            cv2.put(COL_IMAGE,"two.png");


            mDb.update(
                    TABLE_NAME,
                    cv2,
                    COL_ID + " = ?",
                    new String[]{String.valueOf(3)}
            );

            ContentValues cv3 = new ContentValues();
            cv3.put(COL_NUM, "3");
            cv3.put(COL_SCORE,"0");
            cv3.put(COL_IMAGE,"three.png");


            mDb.update(
                    TABLE_NAME,
                    cv3,
                    COL_ID + " = ?",
                    new String[]{String.valueOf(4)}
            );

            loadPhoneData();
            setupListView();
        }
    });

}

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                finish();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadPhoneData();
        setupListView();
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
    private void setupListView() {
        Adapter adapter = new Adapter(
                Main2Activity.this,
                R.layout.score,
                mScoreItemList
        );
        ListView lv = findViewById(R.id.result;
        lv.setAdapter(adapter);
    }


}
