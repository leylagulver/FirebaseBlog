package com.example.user.firebaseblog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class PostActivity extends AppCompatActivity {

    ArrayList<String> userEmailsFromFB;
    ArrayList<String> userImageFromFB;
    ArrayList<String> userCommentFromFB;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    ListView listView;
    PostClass adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_post,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()== R.id.add_post) {
            Intent intent =new Intent(getApplicationContext(), UploadActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        userEmailsFromFB = new ArrayList<String>();
        userCommentFromFB = new ArrayList<String>();
        userImageFromFB = new ArrayList<String>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        listView= findViewById(R.id.listView);

        adapter = new PostClass(userEmailsFromFB,userImageFromFB,userCommentFromFB,this);
        listView.setAdapter(adapter);
        getDataFromFirebase();

    }
    protected void getDataFromFirebase() {
        DatabaseReference newReference = firebaseDatabase.getReference("Posts");
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               //System.out.println("children" + dataSnapshot.getChildren());
               //  System.out.println("key" + dataSnapshot.getKey());
               // System.out.println("value" + dataSnapshot.getValue());

                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    HashMap<String, String> hashMap = (HashMap<String, String>) ds.getValue();
                userEmailsFromFB.add(hashMap.get("useremail"));
                userCommentFromFB.add(hashMap.get("comment"));
                userImageFromFB.add(hashMap.get("downloadurl"));
                adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

}
}
