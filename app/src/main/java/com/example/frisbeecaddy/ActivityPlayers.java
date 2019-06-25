package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ActivityPlayers extends AppCompatActivity {
    private ArrayList<NameItem> mNameList;

    private RecyclerView mRecyclerView;
    private NameAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button buttonAdd;
    private EditText textAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        createNameList();
        loadData();
        buildRecyclerView();
        setButtons();

        /** When "save changes" button been clicked, save changes according to namelist **/
        Button save = findViewById(R.id.save_changes_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();

                /** Take user back to main menu **/
                Intent intent = new Intent(ActivityPlayers.this, MainActivity.class);
                startActivity(intent);

                /** Notify user that changes been saved successfully **/
                Toast toast = Toast.makeText(getApplicationContext(), "SAVED SUCCESSFULLY!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 150);
                toast.show();
            }
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mNameList);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<NameItem>>() {
        }.getType();
        mNameList = gson.fromJson(json, type);

        if (mNameList == null) {
            mNameList = new ArrayList<>();
        }
    }

    public void addItem(int position) {
        /**textAdd = findViewById(R.id.name_input);
         mNameList.add(position, new NameItem(textAdd.getText().toString()));**/

        mNameList.add(position, new NameItem("Antti"));
        mAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mNameList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void createNameList() {
        mNameList = new ArrayList<>();
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new NameAdapter(mNameList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new NameAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);

                /** Notify user to click save changes button **/
                Toast toast = Toast.makeText(getApplicationContext(), "CLICK SAVE CHANGES", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 250, 780);
                toast.show();
            }
        });
    }

    public void setButtons() {
        /** THIS IS ELEMENT IS HIDDEN IN LAYOUT **/
        textAdd = findViewById(R.id.edittext_insert);

        /** When "add" button been clicked, add name to the namelist **/
        buttonAdd = findViewById(R.id.add_btn);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = Integer.parseInt(textAdd.getText().toString());
                addItem(position);

                /** Notify user to click save changes button **/
                Toast toast = Toast.makeText(getApplicationContext(), "CLICK SAVE CHANGES", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 250, 780);
                toast.show();
            }
        });
    }
}
