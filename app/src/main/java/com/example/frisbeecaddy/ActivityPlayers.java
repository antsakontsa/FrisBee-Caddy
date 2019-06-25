package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        buildRecyclerView();
        setButtons();
    }

    public void addItem(int position) {
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
                int position = Integer.parseInt(textAdd.getText().toString().trim());
                addItem(position);
            }
        });
    }
}
