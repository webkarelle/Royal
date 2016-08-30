package karelle.env.royal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import karelle.env.royal.db.OrderDetailsDAO;
import karelle.env.royal.db.OrdersDAO;
import karelle.env.royal.db.SubCategoriesDAO;
import karelle.env.royal.db.SubOrderDetailsDAO;
import karelle.env.royal.models.Category;
import karelle.env.royal.models.SubCategory;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail;
    EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    etEmail = (EditText) findViewById(R.id.etEmail);
    etPassword = (EditText) findViewById(R.id.etPassword);
    hideKeyboardWhenNeeded();
}

    /**
     * Hides the keyboard when layout is touched.
     */
    private void hideKeyboardWhenNeeded() {
        findViewById(R.id.layoutLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });
    }
    private void hideKeyboard(){
        View v = getCurrentFocus();
        if (v == null)
            v = new View(LoginActivity.this);
        InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public void login(final View view) {
        hideKeyboard();
        showProgressDialog();
        FirebaseAuth.getInstance().
                signInWithEmailAndPassword(getEmail(), getPassword()).
                addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        gotoWelcome();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showSnackBar(e, view);
            }
        });
    }

    private ProgressDialog dialog;

    private void showProgressDialog() {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setTitle("Logging you in...");
            dialog.setIcon(R.drawable.logoroyalpizza);
            dialog.setInverseBackgroundForced(true);
            dialog.setMessage("Connecting to server");
        }
        dialog.show();
    }

    private void hideProgress() {
        dialog.dismiss();
    }


    private void showSnackBar(Exception e, View view) {
        hideProgress();
        Snackbar.make(view, e.getLocalizedMessage(),
                Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        }).show();
    }

    /**
     * Start an intent without adding the activity to the stack
     */
    private void gotoWelcome() {
        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void signUp(final View view) {
        hideKeyboard();
        showProgressDialog();
        FirebaseAuth.
                getInstance().
                createUserWithEmailAndPassword(getEmail(), getPassword()).
                addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        saveUserToDataBase();
                        gotoWelcome();
                    }
                }).
                addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showSnackBar(e, view);
                    }
                });
    }

    private void saveUserToDataBase() {

        //get the current user:
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //init a model of user:
        User user = new User(currentUser.getUid(), currentUser.getEmail());

        //get a reference to the users table
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(user.getUserId());
        // save the new User under the node <userID>
        ref.setValue(user);
    }


    public String getEmail() {
        return etEmail.getText().toString();
    }

    public String getPassword() {
        return etPassword.getText().toString();
    }



}



