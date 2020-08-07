package com.example.prm3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    ListView lvFavorite;
    List<Article> articleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        lvFavorite = (ListView) findViewById(R.id.ulubioneList);
        Intent intent = getIntent();
        final String comeUsername = intent.getStringExtra("username");
        articleList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Favourite");
        reference.orderByChild("username").equalTo(comeUsername).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    String icon = datas.child("icon").getValue().toString();
                    String link = datas.child("link").getValue().toString();
                    String title = datas.child("title").getValue().toString();
                    articleList.add(new Article("",icon,title,link));
                }
                CustomAdapter customAdapter = new CustomAdapter(FavoriteActivity.this, articleList);
                lvFavorite.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        lvFavorite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                parent.getChildAt(position).setBackgroundColor(Color.GRAY);
                Uri uri = Uri.parse(articleList.get(position).getArticalURL());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
