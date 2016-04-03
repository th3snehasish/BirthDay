package com.banane.alarm;

/**
 * Created by Minarva on 31-03-2016.
 */
import android.app.Activity;
import android.app.WallpaperManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class Wallpaper extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.main);

        // Create an actionbar
//        ActionBar actionBar = getActionBar();
//       actionBar.show();

        // Locate ImageView in activity_main.xml
        ImageView mywallpaper = (ImageView) findViewById(R.id.wall);

        // Attach image into ImageView
        mywallpaper.setImageResource(R.drawable.wallpaper);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Create an actionbar menu
        menu.add("Set as Wallpaper")
                // Add a new Menu Button
                .setOnMenuItemClickListener(this.SetWallpaperClickListener)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return super.onCreateOptionsMenu(menu);
    }

    // Capture actionbar menu item click
    OnMenuItemClickListener SetWallpaperClickListener = new OnMenuItemClickListener() {

        public boolean onMenuItemClick(MenuItem item) {

            // Retrieve a WallpaperManager
            WallpaperManager myWallpaperManager = WallpaperManager
                    .getInstance(Wallpaper.this);

            try {
                // Change the current system wallpaper
                myWallpaperManager.setResource(R.raw.wallpaper);

                // Show a toast message on successful change
                Toast.makeText(Wallpaper.this,
                        "Wallpaper successfully changed", Toast.LENGTH_SHORT)
                        .show();

            } catch (IOException e) {
                // TODO Auto-generated catch block
            }

            return false;
        }
    };
}
