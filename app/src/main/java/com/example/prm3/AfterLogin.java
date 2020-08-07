package com.example.prm3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AfterLogin extends AppCompatActivity {

    ListView lvRss;
    List<Article> articals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        lvRss= (ListView)findViewById(R.id.listView);
        Button ulubione = (Button)findViewById(R.id.ulubioneButton);
        articals = new ArrayList<Article>();
        new ProcessInBackground().execute();

        lvRss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                parent.getChildAt(position).setBackgroundColor(Color.GRAY);
                Uri uri = Uri.parse(articals.get(position).getArticalURL());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        lvRss.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(getApplicationContext(), "Dodano do ulubionych", Toast.LENGTH_SHORT);
                addFavorite( username,articals.get(position).getTitle(),articals.get(position).getArticalURL(),articals.get(position).getIconURL());
                toast.show();
                return true;
            }
        });

        ulubione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent afterLoginintent = new Intent(AfterLogin.this, FavoriteActivity.class);
                afterLoginintent.putExtra("username",username);
                AfterLogin.this.startActivity(afterLoginintent);
            }
        });

    }

    private void addFavorite(String username,String title,String link,String icon){
        Favourite favourite = new Favourite(username,title,link,icon);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Favourite");
        reference.push().setValue(favourite);
    }

    public InputStream getInputStream(URL url) {
        try {

            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }





    public class ProcessInBackground extends AsyncTask<Integer, Void, Exception>
    {
        ProgressDialog progressDialog = new ProgressDialog(AfterLogin.this);

        Exception exception = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Busy loading rss feed...please wait...");
            progressDialog.show();
        }

        @Override
        protected Exception doInBackground(Integer... params) {
            String text = "";
            String title = "";
            String icon = "";
            String link = "";
            try
            {
                URL url = new URL("http://feeds.news24.com/articles/fin24/tech/rss");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                factory.setNamespaceAware(false);

                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(getInputStream(url), "UTF_8");


                boolean insideItem = false;

                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT)
                {
                    if (eventType == XmlPullParser.START_TAG)
                    {

                        if (xpp.getName().equalsIgnoreCase("item"))
                        {
                            insideItem = true;
                        }

                        else if (xpp.getName().equalsIgnoreCase("title"))
                        {
                            if (insideItem)
                            {
                                title = xpp.nextText();
                            }
                        }

                        else if (xpp.getName().equalsIgnoreCase("link"))
                        {
                            if (insideItem)
                            {
                                link = xpp.nextText();
                            }
                        }

                        else if (xpp.getName().equalsIgnoreCase("description"))
                        {
                            if (insideItem)
                            {
                                text = xpp.nextText();
                            }
                        }

                        else if (xpp.getName().equalsIgnoreCase("enclosure"))
                        {
                            if (insideItem)
                            {
                                icon = xpp.getAttributeValue(0);
                            }
                        }
                    }

                    else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item"))
                    {
                        insideItem = false;
                        articals.add(new Article(text,icon,title,link));
                        System.out.println(icon);
                        text = "";
                        icon = "";
                        title = "";
                        link = "";
                    }

                    eventType = xpp.next();
                }


            }
            catch (MalformedURLException e)
            {
                exception = e;
            }
            catch (XmlPullParserException e)
            {
                exception = e;
            }
            catch (IOException e)
            {
                exception = e;
            }

            return exception;
        }

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);

            CustomAdapter customAdapter = new CustomAdapter(AfterLogin.this,articals);
            lvRss.setAdapter(customAdapter);


            progressDialog.dismiss();
        }
    }
}
