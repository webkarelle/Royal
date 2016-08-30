package karelle.env.royal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {
ImageView ivBackToHome;
    TextView tvIdOrder,tvPriceOrder;
  String  priceOrderString,numOrderString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        
        numOrderString=getIntent().getStringExtra("numOrderString");
        tvIdOrder = (TextView)findViewById(R.id.tvIdOrder) ;
        tvIdOrder.setText(numOrderString);


        priceOrderString=getIntent().getStringExtra("priceOrderString");
        tvPriceOrder = (TextView)findViewById(R.id.tvPriceOrder) ;
        tvPriceOrder.setText(priceOrderString);

        ivBackToHome = (ImageView)findViewById(R.id.ivBackToHome);
        ivBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(FinalActivity.this, WelcomeActivity.class);
                startActivity(i);
            }
        });
    }
}
