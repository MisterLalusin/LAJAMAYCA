package data.ba.se.lajamayca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void approve(View view) {
        Intent intent = new Intent(this, UpdatePassAdmin.class);
        startActivity(intent);
    }
    public void create(View view) {
        Intent intent = new Intent(this, ViewUsers.class);
        startActivity(intent);
    }
    public void viewtransc(View view) {
        Intent intent = new Intent(this, ViewTranscAdmin.class);
        startActivity(intent);
    }


}
