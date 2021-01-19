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

public class UpdateTranscAdmin extends AppCompatActivity {


private static final String TAG = "EditDataActivity";
        Cursor cursor;
        SQLiteDatabase db;
private Button btnSave,btnDelete;
private EditText editable_email,editable_date,editable_phone,editable_first,editable_last,editable_status,editable_details,editable_rid;

        DatabaseHelper mDatabaseHelper;

private String selectedName,id;
private int selectedID;

@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_transc_admin);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_email = (EditText) findViewById(R.id.txteditemail);
        editable_date = (EditText) findViewById(R.id.txteditdate);
        editable_phone = (EditText) findViewById(R.id.txteditphone);
        editable_first = (EditText) findViewById(R.id.txteditfirst);
        editable_last = (EditText) findViewById(R.id.txteditlastname);
        editable_status = (EditText) findViewById(R.id.texteditstatus);
         editable_details = (EditText) findViewById(R.id.texteditdetails);
    editable_rid = (EditText) findViewById(R.id.txteditrid);



    mDatabaseHelper = new DatabaseHelper(this);
        db = mDatabaseHelper.getWritableDatabase();

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("ID",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("Email");
        id=""+selectedID;
//set the text to show the current selected name


final String email = selectedName;
        receivedIntent.putExtra("Email", email);
         String xtrarid = "";
        String xtraemail = "";
        String xtradate = "";
        String xtrafirst = "";
        String xtralast = "";
        String xtraphone = "";
        String xtradetails ="";
        String xtrastatus ="";
        cursor = db.rawQuery("SELECT *FROM tblreserve WHERE ID =? AND Show =?", new String[]{id, selectedName});
        while (cursor.moveToNext()) {
            xtrarid = cursor.getString(cursor.getColumnIndex("ID"));
        xtraemail = cursor.getString(cursor.getColumnIndex("Email"));
        xtradate = cursor.getString(cursor.getColumnIndex("Date"));
        xtrafirst = cursor.getString(cursor.getColumnIndex("FirstName"));
        xtralast = cursor.getString(cursor.getColumnIndex("LastName"));
        xtraphone = cursor.getString(cursor.getColumnIndex("Phone"));
            xtradetails = cursor.getString(cursor.getColumnIndex("Details"));
            xtrastatus = cursor.getString(cursor.getColumnIndex("Status"));
        }
    editable_rid.setText(xtrarid);
        editable_email.setText(xtraemail);
        editable_date.setText(xtradate);
        editable_phone.setText(xtraphone);
        editable_first.setText(xtrafirst);
        editable_last.setText(xtralast);
    editable_details.setText(xtradetails);
    editable_status.setText(xtrastatus);














        btnSave.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
    String show="Requested by: "+editable_email.getText().toString()+"\nOn: "+editable_date.getText().toString()+"\nStatus: "+editable_status.getText().toString()+"";
final String emaildb=email;
        String item = editable_email.getText().toString();
        String item2 = editable_date.getText().toString();
        String item3 = editable_phone.getText().toString();
        String item4 = editable_first.getText().toString();
        String item5 = editable_last.getText().toString();
        if(!item.equals("")&&!item2.equals("")&&!item3.equals("")&&!item4.equals("")&&!item5.equals("")){
        db=mDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Show", show);
        contentValues.put("Email", editable_email.getText().toString());
        contentValues.put("Date", editable_date.getText().toString());
        contentValues.put("FirstName", editable_first.getText().toString());
        contentValues.put("LastName", editable_last.getText().toString());
        contentValues.put("Phone", editable_phone.getText().toString());
            contentValues.put("Details", editable_details.getText().toString());
            contentValues.put("Status", editable_status.getText().toString());
        long id = db.update("tblreserve", contentValues, "Show =?", new String[]{email});
        Toast.makeText(getApplicationContext(), "Password was Successfully Changed!", Toast.LENGTH_LONG).show();
        }else{
        toastMessage("Dont Leave anything blank");
        }
        }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        mDatabaseHelper.deleteNameReservation(selectedID,selectedName);
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