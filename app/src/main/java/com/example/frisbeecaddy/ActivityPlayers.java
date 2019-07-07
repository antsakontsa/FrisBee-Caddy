package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ActivityPlayers extends AppCompatActivity {
    public ArrayList<NameItem> mNameList;

    private RecyclerView mRecyclerViewPlayers;
    private NameAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button buttonAdd;
    private EditText textAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        loadData();
        buildRecyclerView();
        setButtons();
    }

    /**
     * ALL OUTER METHODS GOES UNDER THIS
     ********************************************************************************************/
    private void sortArrayList() {
        Collections.sort(mNameList, new Comparator<NameItem>() {
            @Override
            public int compare(NameItem nameItem, NameItem t1) {
                return nameItem.getText1().compareTo(t1.getText1());
            }
        });

        mAdapter.notifyDataSetChanged();
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

    private void addItem(int position) {
        /** Get user input (name) **/
        textAdd = findViewById(R.id.name_input);

        /** If no matches, add name to the list **/
        mNameList.add(position, new NameItem(textAdd.getText().toString().trim()));
        sortArrayList();
        saveData();
        mAdapter.notifyItemInserted(position);
        textAdd.getText().clear();
    }

    private void removeItem(int position) {
        mNameList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    private void buildRecyclerView() {
        mRecyclerViewPlayers = findViewById(R.id.recyclerViewPlayers);
        mRecyclerViewPlayers.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new NameAdapter(mNameList);

        mRecyclerViewPlayers.setLayoutManager(mLayoutManager);
        mRecyclerViewPlayers.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new NameAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
                sortArrayList();
                saveData();
            }
        });
    }

    private void setButtons() {
        /** "add" button has to be disabled when page loads **/
        buttonAdd = findViewById(R.id.add_btn);
        buttonAdd.setEnabled(false);

        /** When "add" button been clicked, add a name to the namelist **/
        textAdd = findViewById(R.id.name_input);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Close soft keyboard first **/
                InputMethodManager input = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                input.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                /** Then add item **/
                addItem(0);
            }
        });

        /** This enable "add" button when user insert some input **/
        textAdd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /** Have to be here even tho it's not containing anything **/
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String name_input = textAdd.getText().toString().trim();

                buttonAdd.setEnabled(!name_input.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                /** Have to be here even tho it's not containing anything **/
            }
        });
    }

    /**
     * Back button
     **/
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityPlayers.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}