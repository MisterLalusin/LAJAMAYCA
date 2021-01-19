package data.ba.se.lajamayca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ReviewsAndFeedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_and_feedback);
    }
    public void toast(View view)
    { Toast.makeText(ReviewsAndFeedback.this, "Thank you for your feedback!" +
            "", Toast.LENGTH_LONG).show();
    Intent intent = new Intent(this, customer.class);
    startActivity(intent);}
}
