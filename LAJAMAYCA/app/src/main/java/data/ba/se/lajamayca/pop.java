package data.ba.se.lajamayca;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class pop extends AppCompatActivity {
    private TextView showemail;
    private long mLastClickTime = 0;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    Button reserve;
    CheckBox cfood,cwifi,cvip;
    String food="";
    String wifi="";
    String overnight="";
    String vip="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();
        cfood = (CheckBox) findViewById(R.id.checkBox);
        cwifi = (CheckBox) findViewById(R.id.checkBox2);
        cvip = (CheckBox) findViewById(R.id.checkBox3);

        Intent intent= getIntent();
       String isemail=intent.getStringExtra("aaa");
        final String email=isemail;

        showemail = (TextView) findViewById(R.id.name);
        showemail.setText("Email: " + email);


        cfood.setOnClickListener(new View.OnClickListener() {

                                     @Override
                                     public void onClick(View v) {
                                         //is chkEgg checked?
                                         if (((CheckBox) v).isChecked()) {
                                             food = food + "With Foods\n";
                                         }
                                     }
                                 }
        );
        cwifi.setOnClickListener(new View.OnClickListener() {

                                     @Override
                                     public void onClick(View v) {
                                         //is chkEgg checked?
                                         if (((CheckBox) v).isChecked()) {
                                             wifi = wifi + "With Wifi Access\n";
                                         }
                                     }
                                 }
        );
        cvip.setOnClickListener(new View.OnClickListener() {

                                     @Override
                                     public void onClick(View v) {
                                         //is chkEgg checked?
                                         if (((CheckBox) v).isChecked()) {
                                             vip = vip + "VIP Access\n";
                                         }
                                     }
                                 }
        );












        reserve = (Button) findViewById(R.id.reserve);
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new
                        SimpleDateFormat("yyyy / MM / dd HH:mm:ss");
                final String strDate = "" + mdformat.format(calendar.getTime());
                String xtraemail ="";
                String xtrafirst ="";
                String xtralast ="";
                String xtraphone ="";
                String status="Waiting for Approval";
                //final String fxtraemail=xtraemail;
                //final String fxtrafirst=xtrafirst;
               // final String fxtralast=xtralast;
               // final String fxtraphone=xtraphone;

                cursor = db.rawQuery("SELECT *FROM registeration WHERE Email =?", new String[]{email});
                if (SystemClock.elapsedRealtime() - mLastClickTime < 5000){
                    Toast.makeText(pop.this,"No Spamming! You must wait 5 seconds to request an reservation again!\n Spammers will be banned!", Toast.LENGTH_LONG).show();
                    mLastClickTime = SystemClock.elapsedRealtime();
                }
               else if (cursor != null) {
                    mLastClickTime = SystemClock.elapsedRealtime();
                    while (cursor.moveToNext()) {
                        xtraemail = cursor.getString(cursor.getColumnIndex("Email"));
                        xtrafirst = cursor.getString(cursor.getColumnIndex("FirstName"));
                        xtralast = cursor.getString(cursor.getColumnIndex("LastName"));
                        xtraphone = cursor.getString(cursor.getColumnIndex("Phone"));
                    }
                    String show="Requested by: "+xtraemail+"\nOn: "+strDate+"\nStatus: "+status+"";
                    String details=food+wifi+vip;
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("Show",show);
                    contentValues.put("Date", strDate);
                    contentValues.put("Email", xtraemail);
                    contentValues.put("FirstName", xtrafirst);
                    contentValues.put("LastName", xtralast);
                    contentValues.put("Phone", xtraphone);
                    contentValues.put("Details", details);
                    contentValues.put("Status", "Waiting for Approval");
                    db.insert("tblreserve", null, contentValues);
                    Toast.makeText(pop.this,"Reservation was received successfully!", Toast.LENGTH_LONG).show();
                    mLastClickTime = SystemClock.elapsedRealtime();

                }


            } });

}
    }