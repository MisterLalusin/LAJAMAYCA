package data.ba.se.lajamayca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Packages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);
    }
    public void pack2 (View view){
        //Intent intent = new Intent (this, Packages1.class);
        //startActivity(intent);
        Intent pabebe = new Intent(getApplicationContext(), Packages1.class);
        startActivity(pabebe);

    }
}
