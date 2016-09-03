package karelle.env.royal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import karelle.env.royal.db.StoreDAO;
import karelle.env.royal.db.UserDAO;
import karelle.env.royal.models.Store;
import karelle.env.royal.models.User;

public class LocateActivity extends AppCompatActivity {
    TextView tvAddHome,tvAddStore1,tvAddStore2;
    ImageView ivBackToHome,ivChange,ivValideAdd;
    UserDAO userDAO;
    StoreDAO storeDAO;
    EditText etNewAdd;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);


        userDAO = new UserDAO(this);
        storeDAO = new StoreDAO(this);
        ArrayList<Store> stores = storeDAO.query();
        user = userDAO.query();

        tvAddStore1= (TextView)findViewById(R.id.tvAddStore1);
        tvAddStore1.setText(stores.get(0).getAddStore());
        tvAddStore2= (TextView)findViewById(R.id.tvAddStore2);
        tvAddStore2.setText(stores.get(1).getAddStore());
        tvAddHome = (TextView) findViewById(R.id.tvAddHome);
        tvAddHome.setText(user.getAddHome());

        etNewAdd = (EditText)findViewById(R.id.etNewAdd);
        etNewAdd.setVisibility(View.INVISIBLE);
        ivValideAdd = (ImageView)findViewById(R.id.ivValideAdd);
        ivValideAdd.setVisibility(View.INVISIBLE);
        ivBackToHome = (ImageView)findViewById(R.id.ivBackToHome);
        ivBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new  Intent(LocateActivity.this,WelcomeActivity.class);
                startActivity(i);
            }
        });
       ivChange = (ImageView)findViewById(R.id.ivChange);
       ivChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNewAdd.setVisibility(View.VISIBLE);
                ivValideAdd.setVisibility(View.VISIBLE);

            }
        });

        ivValideAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setAddHome(etNewAdd.getText().toString());
                userDAO.update(user);
                tvAddHome.setText(etNewAdd.getText().toString());
                etNewAdd.setVisibility(View.INVISIBLE);
                ivValideAdd.setVisibility(View.INVISIBLE);
            }
        });

    }
}
