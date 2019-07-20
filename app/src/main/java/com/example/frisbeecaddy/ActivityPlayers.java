package com.example.frisbeecaddy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ActivityPlayers extends AppCompatActivity {
    public static ArrayList<NameItem> mNameList;

    private RecyclerView mRecyclerViewPlayers;
    private NameAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button buttonAdd;
    private EditText textAdd;

    private int checkNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        loadData(this);
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
        /** save data to shared pref **/
        SharedPreferences prefs = getSharedPreferences("shared preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        try {
            editor.putString("SharedPrefKey", ObjectSerializer.serialize(mNameList));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();
    }

    public static void loadData(Context context) {
        if (mNameList == null) {
            mNameList = new ArrayList<>();
        }

        SharedPreferences prefs = context.getSharedPreferences("shared preference", Context.MODE_PRIVATE);
        try {
            mNameList = (ArrayList<NameItem>) ObjectSerializer.deserialize(prefs.getString("SharedPrefKey", ObjectSerializer.serialize(new ArrayList<NameItem>())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addItem(int position) {
        /** Get user input (name) **/
        textAdd = findViewById(R.id.name_input);

        /** Add name to the list **/
        mNameList.add(position, new NameItem(textAdd.getText().toString().trim()));

        /** sort that list **/
        sortArrayList();

        /** Save to shared pref **/
        saveData();

        /** Show changed list to user **/
        mAdapter.notifyItemInserted(position);

        /** Clear the input field **/
        textAdd.getText().clear();
    }

    private void removeItem(final int position) {
        /** Create dialog which ask if user is sure about delete name from list **/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Player");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to delete player: \n\n\"" + mNameList.get(position).getText1() + "\"?")
                .setCancelable(false)

                /** If user click "yes" button, delete player from list and save changes **/
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mNameList.remove(position);
                        mAdapter.notifyItemRemoved(position);
                        saveData();
                    }
                })

                /** If user click "no" button, name won't delete from list **/
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        /** Create Dialog **/
        AlertDialog alert = builder.create();
        alert.show();
        alert.getButton(alert.BUTTON_NEGATIVE).setTextColor(R.style.AlertDialogCustom);
        alert.getButton(alert.BUTTON_POSITIVE).setTextColor(R.style.AlertDialogCustom);
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

        /** This is for dialog **/
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        /** When "add" button been clicked... **/
        textAdd = findViewById(R.id.name_input);
        buttonAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                /** Loop the list **/
                for (int i = 0; i < mNameList.size(); i++) {
                    if (mNameList.get(i).getText1().equalsIgnoreCase(textAdd.getText().toString().trim())) {
                        checkNumber = 1;
                        break;
                    } else {
                        checkNumber = 0;
                    }
                }

                /** if checkNumber is still 0 **/
                if (checkNumber == 0) {
                    /** Close soft keyboard **/
                    InputMethodManager input = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    input.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);

                    /** ...add a name to the namelist **/
                    addItem(0);
                } else {
                    /** Create dialog **/
                    builder.setTitle("Naming error");
                    builder.setIcon(R.mipmap.ic_launcher);
                    builder.setMessage("This player name is already on a list")

                            .setCancelable(false)

                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });

                    /** Build Dialog **/
                    AlertDialog alert = builder.create();
                    alert.show();
                    alert.getButton(alert.BUTTON_NEGATIVE).setTextColor(R.style.AlertDialogCustom);
                }
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