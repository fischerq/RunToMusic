package de.fischerq.runtomusic;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;

public class SelectPlaylistActivity extends Activity{
    private String[] songsArray = { "Ellie Goulding -Love Me Like You Do", "Jason Derulo - Want To Want Me", "C"};

    private ArrayList<String> playlist;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_playlist);

        playlist = new ArrayList<String>();
        TextView playlistSummary = (TextView) findViewById(R.id.playlist_summary);
        playlistSummary.setText("Playlist size: 0");

        ListView songsListView = (ListView) findViewById(R.id.songs_list);

        // this-The current activity context.
        // Second param is the resource Id for list layout row item
        // Third param is input array
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songsArray);
        songsListView.setAdapter(arrayAdapter);

        songsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String song = (String) parent.getAdapter().getItem(position);
                playlist.add(song);
                TextView playlistSummary = (TextView) findViewById(R.id.playlist_summary);
                playlistSummary.setText("Playlist size: "+playlist.size());
            }
        });
    }

    /** Called when the user clicks the "Load a Song" button */
    public void confirm(View view) {
        // Do something in response to button
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("playlist", playlist);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}