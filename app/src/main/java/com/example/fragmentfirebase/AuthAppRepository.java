package com.example.fragmentfirebase;

import android.content.Context;
import android.os.Message;
import android.view.Display;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.fragmentfirebase.messageFragment.ModelMessage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class AuthAppRepository {

    DatabaseReference databaseReference;

    private Context context;
    private FirebaseAuth firebaseAuth;
    private MutableLiveData<List<ModelMessage>> messageLiveData = new MutableLiveData<>();
    private MutableLiveData<FirebaseUser> userLiveData = new MutableLiveData<>();

    List<ModelMessage> listMessage = new ArrayList<ModelMessage>();

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

                            userLiveData.setValue(firebaseAuth.getCurrentUser());
                            String userId = firebaseAuth.getUid();

                            DatabaseReference databaseReference = getInstance().getReference();

                            ModelUser modelUser = new ModelUser(password, email);
                            databaseReference.child("users").child(userId).setValue(modelUser);

                            ModelMessage modelMessage = new ModelMessage(email, "body");

                            Map<String, Object> messageValues = modelMessage.toMap();
                            Map<String, Object> childUpdates = new HashMap<>();

                            String key = databaseReference.child("messages").push().getKey();
                            childUpdates.put("/messages/" + key, messageValues);
                            childUpdates.put("/user-messages/" + userId + "/" + key, messageValues);

                            databaseReference.updateChildren(childUpdates);
                        } else {

                            System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQ");

                        }

                    }
                });

    }


    public void login(String email, String password) {

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            userLiveData.setValue(firebaseAuth.getCurrentUser());

                            DatabaseReference databaseReference = getInstance().getReference("messages");

                            databaseReference.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                    ModelMessage modelMessage = snapshot.getValue(ModelMessage.class);

                                    listMessage.add(modelMessage);
                                    messageLiveData.setValue(listMessage);

                                    System.out.println(listMessage + "---------MESSAGES--------");

                                }

                                @Override
                                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                }

                                @Override
                                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                                }

                                @Override
                                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            Toast.makeText(context, "Login Success", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Login Failure: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }


}
