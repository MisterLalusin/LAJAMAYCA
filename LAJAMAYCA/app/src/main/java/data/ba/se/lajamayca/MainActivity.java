package data.ba.se.lajamayca;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import static data.ba.se.lajamayca.R.id.uname;
import static data.ba.se.lajamayca.R.id.upass;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    Button _btnLogin;
    ImageButton _btnhere;
    EditText _txtEmail, _txtPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        _txtEmail = (EditText) findViewById(uname);
        _txtPass = (EditText) findViewById(upass);
        _btnLogin = (Button) findViewById(R.id.ulogin);
       _btnhere = (ImageButton) findViewById(R.id.imgbtnhere);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = _txtEmail.getText().toString();
                String pass = _txtPass.getText().toString();
                String xtraemail="";
                cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.TABLE_NAME + " WHERE Email =? AND Password =?", new String[]{email, pass});
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        xtraemail = cursor.getString(cursor.getColumnIndex("Email"));
                        Toast.makeText(MainActivity.this, "This Is The Email: " + xtraemail, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, customer.class);
                        startActivity(i);
                    }

                    if (cursor.getCount() > 0) {
                        Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(MainActivity.this, customer.class);
                        intent1.putExtra("aaa", xtraemail);
                        startActivity(intent1);
                    } else if (email.equals("Kumamonokuma@elyen.com") && pass.equals("kumamon")) {
                        Toast.makeText(MainActivity.this, "HELLO KUMAMON!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, admin.class);
                        startActivity(i);
                    }

                    else if (cursor != null){
                        cursor = db.rawQuery("SELECT *FROM tbladmin WHERE Email =? AND Password =?", new String[]{email, pass});
                        if (cursor != null) {
                            while (cursor.moveToNext()) {
                                xtraemail = cursor.getString(cursor.getColumnIndex("Email"));
                                Toast.makeText(MainActivity.this, "This Is The Email: " + xtraemail, Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MainActivity.this, customer.class);
                                startActivity(i);
                            }

                            if (cursor.getCount() > 0) {
                                Toast.makeText(MainActivity.this, "Welcome Admin!", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(MainActivity.this, admin.class);
                                intent1.putExtra("aaa", xtraemail);
                                startActivity(intent1);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Login Error", Toast.LENGTH_SHORT).show();}

                        }

                       // else{Toast.makeText(MainActivity.this, "Login Error", Toast.LENGTH_SHORT).show();}
                    }
                }
                //else{Toast.makeText(MainActivity.this, "Login Error", Toast.LENGTH_SHORT).show();}


            }
        });
        _btnhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, CreateAccount.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public void butt (View view){
        Intent intent = new Intent (this, CreateAccount.class);
        startActivity(intent);
    finish();}
}

