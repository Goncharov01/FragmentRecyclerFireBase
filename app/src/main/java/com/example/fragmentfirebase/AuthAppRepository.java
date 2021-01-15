package com.example.fragmentfirebase;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.fragmentfirebase.model.ModelMessage;
import com.example.fragmentfirebase.model.ModelUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class AuthAppRepository {

    DatabaseReference databaseReference;

    private Context context;
    private FirebaseAuth firebaseAuth;
    private MutableLiveData<List<ModelMessage>> messagesLiveData = new MutableLiveData<>();
    private MutableLiveData<FirebaseUser> userLiveData;

    public List<ModelMessage> listMessage = new ArrayList<ModelMessage>();

    public AuthAppRepository(Context context) {
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();
        this.userLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<ModelMessage>> getMessagesLiveData() {
        System.out.println(messagesLiveData + "LIVEDATA GETT");
        return messagesLiveData;
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

                            System.out.println("--------Exception Regist---------");

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

                            databaseReference = getInstance().getReference("messages");
                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                                        listMessage.add(dataSnapshot.getValue(ModelMessage.class));
                                    }

                                    messagesLiveData.setValue(listMessage);
                                    System.out.println(listMessage + "-------LISTMESS------");
                                    System.out.println(messagesLiveData.getValue() + "-------LIVEDATA-----");
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
