package com.example.fragmentfirebase;

import android.content.Context;
import android.view.Display;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class AuthAppRepository {

    DatabaseReference databaseReference;

    private Context context;
    private FirebaseAuth firebaseAuth;

    public List<ModelUser> modelUserList = new ArrayList<ModelUser>();

    public AuthAppRepository(Context context) {
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void regist(String email, String password) {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            DatabaseReference databaseReference = getInstance().getReference();

                            ModelUser modelUser = new ModelUser(password, email);

                            String userId = firebaseAuth.getUid();
                            databaseReference.child("users").child(userId).setValue(modelUser);

                        }

                    }
                });

    }


    private void writeNewUser(String userId, String username, String email) {
        ModelUser modelUser = new ModelUser(username, email);

        FirebaseDatabase.getInstance().getReference().child("users").child(userId).setValue(modelUser);
    }


}
