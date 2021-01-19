package data.ba.se.lajamayca;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateInfo extends AppCompatActivity {
    private static final String TAG = "EditDataActivity";
    Cursor cursor;
    SQLiteDatabase db;
    private Button btnSave,btnDelete;
    private EditText editable_email,editable_pass,editable_phone,editable_first,editable_last;

    DatabaseHelper mDatabaseHelper;

    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_email = (EditText) findViewById(R.id.txteditemail);
        editable_pass = (EditText) findViewById(R.id.txteditdate);
        editable_phone = (EditText) findViewById(R.id.txteditphone);
        editable_first = (EditText) findViewById(R.id.txteditfirst);
        editable_last = (EditText) findViewById(R.id.txteditlastname);

        mDatabaseHelper = new DatabaseHelper(this);
        db = mDatabaseHelper.getWritableDatabase();

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("ID",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("Email");

        //set the text to show the current selected name


        final String email = selectedName;
        receivedIntent.putExtra("Email", email);
        String xtraemail = "";
        String xtrapass = "";
        String xtrafirst = "";
        String xtralast = "";
        String xtraphone = "";
        cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.TABLE_NAME + " WHERE Email =?", new String[]{email});
        while (cursor.moveToNext()) {
            xtraemail = cursor.getString(cursor.getColumnIndex("Email"));
            xtrapass = cursor.getString(cursor.getColumnIndex("Password"));
            xtrafirst = cursor.getString(cursor.getColumnIndex("FirstName"));
            xtralast = cursor.getString(cursor.getColumnIndex("LastName"));
            xtraphone = cursor.getString(cursor.getColumnIndex("Phone"));
        }
        editable_email.setText(xtraemail);
        editable_pass.setText(xtrapass);
        editable_phone.setText(xtraphone);
        editable_first.setText(xtrafirst);
        editable_last.setText(xtralast);













        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emaildb=email;
                String item = editable_email.getText().toString();
                String item2 = editable_pass.getText().toString();
                String item3 = editable_phone.getText().toString();
                String item4 = editable_first.getText().toString();
                String item5 = editable_last.getText().toString();
                if(!item.equals("")&&!item2.equals("")&&!item3.equals("")&&!item4.equals("")&&!item5.equals("")){
                    db=mDatabaseHelper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("Email", editable_email.getText().toString());
                    contentValues.put("FirstName", editable_first.getText().toString());
                    contentValues.put("LastName", editable_last.getText().toString());
                    contentValues.put("Password", editable_pass.getText().toString());
                    contentValues.put("Phone", editable_phone.getText().toString());
                    long id = db.update("registeration", contentValues, "Email =?", new String[]{email});
                    Toast.makeText(getApplicationContext(), "Password was Successfully Changed!",Toast.LENGTH_LONG).show();
                }else{
                    toastMessage("Dont Leave anything blank");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                editable_email.setText("");
                toastMessage("removed from database");
            }
        });

    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}