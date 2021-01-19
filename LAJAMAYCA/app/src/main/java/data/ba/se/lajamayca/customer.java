package data.ba.se.lajamayca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class customer extends AppCompatActivity {
    private TextView showname;
    private Button showdetails,reserve,viewtransc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        showname = (TextView)findViewById(R.id.showname);
        showdetails = (Button) findViewById(R.id.showdetails);
        reserve = (Button) findViewById(R.id.button3);
        viewtransc = (Button) findViewById(R.id.button2);

        Intent intent= getIntent();
        String isemail=intent.getStringExtra("aaa");
        Intent intent1= new Intent(this, MainActivity.class);

        intent1.putExtra("aaa",isemail);
        showname.setText("You are logged in as: " + isemail);
        final String showact=isemail;



        showdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1a = new Intent(getApplicationContext(), Show.class);
                intent1a.putExtra("aaa", showact);
                startActivity(intent1a);

            }

        });

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (customer.this, Reserve.class);
                intent.putExtra("aaa", showact);
                startActivity(intent);
            }
    });

        viewtransc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent (customer.this, ViewTranscUser.class);
                intent.putExtra("aaa", showact);
                    startActivity(intent);
                }
            }
        );

}
}

