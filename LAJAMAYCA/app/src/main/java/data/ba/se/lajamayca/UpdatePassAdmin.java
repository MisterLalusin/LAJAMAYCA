package data.ba.se.lajamayca;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePassAdmin extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnupdate;
    EditText _txtoldpass, _txtunpass, _txtcidpass;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pass_admin);
        _txtoldpass = (EditText) findViewById(R.id.txtoldpass);
        _txtunpass = (EditText) findViewById(R.id.txtunpass);
        _txtcidpass = (EditText) findViewById(R.id.txtcidpass);
        _btnupdate = (Button) findViewById(R.id.btnupdate);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();

        _btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpass = _txtoldpass.getText().toString();
                String newpass = _txtunpass.getText().toString();
                String conpass = _txtcidpass.getText().toString();
                Intent intenta = getIntent();
                String xtrapass = "";
                String email ="admin";
                cursor = db.rawQuery("SELECT *FROM tbladmin WHERE Email =?", new String[]{email});

                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        xtrapass = cursor.getString(cursor.getColumnIndex("Password"));
                    }
                    if (!oldpass.equals(xtrapass)){

                        Toast.makeText(UpdatePassAdmin.this, "Please check your old password",Toast.LENGTH_LONG).show();
                    }
                    else if (!newpass.equals(conpass)){
                        Toast.makeText(UpdatePassAdmin.this, "Please confirm your password",Toast.LENGTH_LONG).show();
                    }
                    else if (newpass.equals("")){
                        Toast.makeText(UpdatePassAdmin.this, "Please enter your password",Toast.LENGTH_LONG).show();
                    }
                    else {
                        db=openHelper.getWritableDatabase();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("Password", _txtunpass.getText().toString());
                        long id = db.update("tbladmin", contentValues, "Email =?", new String[]{email});
                        Toast.makeText(getApplicationContext(), "Password was Successfully Changed!",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
