package data.ba.se.lajamayca;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Show extends AppCompatActivity {
    private TextView showemail, showfirst, showlast, showphone;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    Button popshow, btnupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        setContentView(R.layout.activity_show);
        popshow = (Button) findViewById(R.id.popshow);
        btnupdate = (Button) findViewById(R.id.btnupdate);
        Intent intent= getIntent();
        String isemail=intent.getStringExtra("aaa");
        Intent intent1= new Intent(this, customer.class);
        intent1.putExtra("aaa",isemail);
        final String showact=isemail;
        popshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intenta = getIntent();
                String xtraemail = "";
                String xtrafirst = "";
                String xtralast = "";
                String xtraphone = "";

                String email = intenta.getStringExtra("aaa");
                Intent intentput = new Intent(getApplicationContext(), customer.class);
                intentput.putExtra("aaa", email);
                cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.TABLE_NAME + " WHERE Email =?", new String[]{email});
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        xtraemail = cursor.getString(cursor.getColumnIndex("Email"));
                        xtrafirst = cursor.getString(cursor.getColumnIndex("FirstName"));
                        xtralast = cursor.getString(cursor.getColumnIndex("LastName"));
                        xtraphone = cursor.getString(cursor.getColumnIndex("Phone"));
                    }

                }


                showemail = (TextView) findViewById(R.id.semail);
                showfirst = (TextView) findViewById(R.id.sfirst);
                showlast = (TextView) findViewById(R.id.slast);
                showphone = (TextView) findViewById(R.id.sphone);

                showemail.setText("Email: " + xtraemail);
                showfirst.setText("First Name: " + xtrafirst);
                showlast.setText("Last Name: " + xtralast);
                showphone.setText("Phone: " + xtraphone);


            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Intent intent = new Intent (Show.this, UpdatePass.class);
                                             intent.putExtra("aaa", showact);
                                             startActivity(intent);

                                         }
                                     });
    }
}
