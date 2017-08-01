package com.example.a15056233.c347p11knowyournationalday;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvSingapore;
    ArrayList<String> alList = new ArrayList<String>();
    ArrayAdapter aaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSingapore = (ListView) findViewById(R.id.lvSingapore);

        alList.add("Singapore National Day is on 9 Aug");
        alList.add("Singapore is 52 years old");
        alList.add("Theme is '#OneNationTogether'");

        aaList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alList);

        lvSingapore.setAdapter(aaList);

    }

    @Override
    protected void onStart() {
        super.onStart();

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout passPhrase =
                (LinearLayout) inflater.inflate(R.layout.passcode, null);
        final EditText etPassCode = (EditText) passPhrase
                .findViewById(R.id.editTextPassCode);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Enter")
                .setView(passPhrase)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String passcode = etPassCode.getText().toString();
                        String password = "738964";
                        if (passcode.equals(password)) {
                            Toast.makeText(MainActivity.this, "Access code Accepted", Toast.LENGTH_LONG).show();
                            dialog.dismiss();

                        } else {
                            Toast.makeText(MainActivity.this, "Incorrect Access code", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Tally against the respective action item clicked
        //  and implement the appropriate action
        if (item.getItemId() == R.id.action_Quit) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Quit?")
                    // Set text for the positive button and the corresponding
                    //  OnClickListener when it is clicked
                    .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    // Set text for the negative button and the corresponding
                    //  OnClickListener when it is clicked
                    .setNegativeButton("Not Really", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            // Create the AlertDialog object and return it
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }else if (item.getItemId() == R.id.action_Send){

            String [] send = new String[] {"Email", "SMS" };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select the way to enrich your friend")
                    // Set the list of items easily by just supplying an
                    //  array of the items
                    .setItems(send, new DialogInterface.OnClickListener() {
                        // The parameter "which" is the item index
                        // clicked, starting from 0
                        public void onClick(DialogInterface dialog, int which) {
                            String list = "";

                            for(int i = 0 ; i<alList.size(); i++){
                               list +=  alList.get(i) + "\n";
                            }

                            if (which == 0) {

                                Intent email = new Intent(Intent.ACTION_SEND);
                                email.putExtra(Intent.EXTRA_TEXT, list);

                                //need this to prompts email client only
                                email.setType("message/rfc822");

                                startActivity(Intent.createChooser(email, "Choose an Email client :"));

                            }  else {

                                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                                smsIntent.setType("vnd.android-dir/mms-sms");
                                smsIntent.putExtra("sms_body",list);
                                startActivity(smsIntent);

                            }
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }else if (item.getItemId() == R.id.action_quiz){

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

}
