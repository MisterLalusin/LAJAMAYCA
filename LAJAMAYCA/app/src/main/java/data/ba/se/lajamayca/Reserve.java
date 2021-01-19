package data.ba.se.lajamayca;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Reserve extends AppCompatActivity {
    ImageButton pop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        pop = (ImageButton) findViewById(R.id.imageButton7);
        Intent intent= getIntent();
        String isemail=intent.getStringExtra("aaa");
        final String showact=isemail;
        pop.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view){
                                       Intent intent = new Intent(Reserve.this, pop.class);
                                       intent.putExtra("aaa",showact);
                                       startActivity(intent);
                                   }
                               }
        );

    }




}
