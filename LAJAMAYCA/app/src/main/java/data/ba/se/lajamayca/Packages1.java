package data.ba.se.lajamayca;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Packages1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages1);

    }
    public void pack1 (View view){
        Intent intent = new Intent (this, Packages.class);
        startActivity(intent);
    }
}
