package data.ba.se.lajamayca;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewTranscInfoUser extends AppCompatActivity {
    Cursor cursor;
    SQLiteDatabase db;
    private Button btnSave,btnDelete;
    private TextView editable_email,editable_date,editable_phone,editable_first,editable_last,editable_status,editable_details,editable_rid;

    DatabaseHelper mDatabaseHelper;

    private String selectedName,id;
    private int selectedID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transc_info_user);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_email = (TextView) findViewById(R.id.txteditemail);
        editable_date = (TextView) findViewById(R.id.txteditdate);
        editable_phone = (TextView) findViewById(R.id.txteditphone);
        editable_first = (TextView) findViewById(R.id.txteditfirst);
        editable_last = (TextView) findViewById(R.id.txteditlastname);
        editable_status = (TextView) findViewById(R.id.texteditstatus);
        editable_details = (TextView) findViewById(R.id.texteditdetails);
        editable_rid = (TextView) findViewById(R.id.txteditrid);



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
        final String status=xtrastatus;
        final String dbemail = xtraemail;
        final String dbid = xtrarid;
        final String dbdate = xtradate;














        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String show="Requested by: "+dbemail+"\nOn: "+dbdate+"\nStatus: Cancelled";
                if (status.equals("Waiting for Approval")) {
                    db = mDatabaseHelper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("Show", show);
                    contentValues.put("Status", "Cancelled");
                    long id = db.update("tblreserve", contentValues, "ID =? AND Email =?", new String[]{dbid, dbemail});
                    Toast.makeText(getApplicationContext(), "Reservation Cancelled!", Toast.LENGTH_LONG).show();
                } else if (status.equals("Cancelled")) {
                    Toast.makeText(getApplicationContext(), "You Already Cancelled this Reservation!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "You Can't Cancel your Reservation now!", Toast.LENGTH_LONG).show();
                }
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