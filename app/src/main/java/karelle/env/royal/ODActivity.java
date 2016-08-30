package karelle.env.royal;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ODActivity extends AppCompatActivity {
    private BottomSheetBehavior bsb;
    Button btnBSHide,btnBSCollapse,btnBSExpande;
    View BSView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_od);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        btnBSHide =(Button)findViewById(R.id.btnBSHide);
        btnBSCollapse =(Button)findViewById(R.id.btnBSCollapse);
        btnBSExpande =(Button)findViewById(R.id.btnBSExpand);
        BSView =(View)findViewById(R.id.BSView);

    }

}
