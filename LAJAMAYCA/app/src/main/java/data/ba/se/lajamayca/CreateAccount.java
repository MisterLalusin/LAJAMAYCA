package data.ba.se.lajamayca;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnreg;
    ImageButton _btnlogin;
    EditText _txtfname, _txtlname, _txtpass, _txtemail, _txtphone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        openHelper = new DatabaseHelper(this);
        _txtfname = (EditText) findViewById(R.id.txtfname);
        _txtlname = (EditText) findViewById(R.id.txtlname);
        _txtpass = (EditText) findViewById(R.id.txtpassword);
        _txtemail = (EditText) findViewById(R.id.txtemail);
        _txtphone = (EditText) findViewById(R.id.txtphone);
        _btnlogin = (ImageButton) findViewById(R.id.imgbtnlog);
        _btnreg = (Button) findViewById(R.id.btnreg);
        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("FirstName", _txtfname.getText().toString());
                contentValues.put("LastName", _txtlname.getText().toString());
                contentValues.put("Password", _txtpass.getText().toString());
                contentValues.put("Email", _txtemail.getText().toString());
                contentValues.put("Phone", _txtphone.getText().toString());
                long id = db.insert("registeration", null, contentValues);
                Toast.makeText(getApplicationContext(), "register successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }}
