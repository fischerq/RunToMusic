package de.fischerq.runtomusic;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;


import java.util.ArrayList;


public class MainActivity extends Activity {

    static final int SELECT_PLAYLIST_REQUEST = 1;

    private ArrayList<String> current_playlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar speedSlider = (SeekBar) findViewById(R.id.speedSlider);
        speedSlider.setProgress(10);
        TextView speedDisplay = (TextView) findViewById(R.id.speedDisplay);
        speedDisplay.setText("BPM: " + 160);

        speedSlider.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                update(seekBar.getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            void update(int position)
            {
                int bpm = 160+(int)( ((double)position-10)/10 *20);
                TextView speedDisplay = (TextView) findViewById(R.id.speedDisplay);
                speedDisplay.setText("BPM: " + bpm);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the "Load a Song" button */
    public void selectPlaylist(View view) {

        Intent intent = new Intent(this, SelectPlaylistActivity.class);
        startActivityForResult(intent, SELECT_PLAYLIST_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == SELECT_PLAYLIST_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                current_playlist = (ArrayList<String>) data.getSerializableExtra("playlist");
                playSong(0);
            }
        }
    }

    void playSong(int songNr)
    {
        String songName = current_playlist.get(songNr);
        TextView myTextView = (TextView) findViewById(R.id.current_song);
        myTextView.setText(songName);
    }
}
