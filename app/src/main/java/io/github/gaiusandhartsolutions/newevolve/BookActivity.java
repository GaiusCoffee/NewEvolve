package io.github.gaiusandhartsolutions.newevolve;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BookActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String book_contents = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.book_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.book_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.book_drawer_layout);
                drawer.openDrawer(GravityCompat.START);
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                */
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.book_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.book_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        this.book_contents = intent.getStringExtra(Engine.TAG_BOOK);
        MenuItem nav_book_quickstart = navigationView.getMenu().findItem(R.id.nav_book_quickstart);
        MenuItem nav_book_lkm = navigationView.getMenu().findItem(R.id.nav_book_lkm);
        MenuItem nav_book_agb = navigationView.getMenu().findItem(R.id.nav_book_agb);
        // MenuItem nav_book_f = (MenuItem)navigationView.getMenu().findItem(R.id.nav_book_f);
        MenuItem nav_book_c1 = navigationView.getMenu().findItem(R.id.nav_book_c1);
        MenuItem nav_book_c2 = navigationView.getMenu().findItem(R.id.nav_book_c2);
        MenuItem nav_book_c3 = navigationView.getMenu().findItem(R.id.nav_book_c3);
        MenuItem nav_book_c4 = navigationView.getMenu().findItem(R.id.nav_book_c4);
        MenuItem nav_book_c5 = navigationView.getMenu().findItem(R.id.nav_book_c5);
        MenuItem nav_book_c6 = navigationView.getMenu().findItem(R.id.nav_book_c6);
        MenuItem nav_book_c7 = navigationView.getMenu().findItem(R.id.nav_book_c7);
        MenuItem nav_book_c8 = navigationView.getMenu().findItem(R.id.nav_book_c8);
        MenuItem nav_book_c9 = navigationView.getMenu().findItem(R.id.nav_book_c9);
        ImageView book_nav_imageView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.book_nav_imageView);
        TextView book_nav_title = (TextView) navigationView.getHeaderView(0).findViewById(R.id.book_nav_title);

        if (this.book_contents.equals(getString(R.string.book_qst))) {
            nav_book_quickstart.setChecked(true);
            book_nav_imageView.setImageResource(R.drawable.book_quickstart);
            book_nav_title.setText(getString(R.string.book_qst));
            if (getString(R.string.bq_c1_title).equals("")) {
                nav_book_c1.setVisible(false);
            } else {
                nav_book_c1.setTitle(getString(R.string.bq_c1_title));
            }
            if (getString(R.string.bq_c2_title).equals("")) {
                nav_book_c2.setVisible(false);
            } else {
                nav_book_c2.setTitle(getString(R.string.bq_c2_title));
            }
            if (getString(R.string.bq_c3_title).equals("")) {
                nav_book_c3.setVisible(false);
            } else {
                nav_book_c3.setTitle(getString(R.string.bq_c3_title));
            }
            if (getString(R.string.bq_c4_title).equals("")) {
                nav_book_c4.setVisible(false);
            } else {
                nav_book_c4.setTitle(getString(R.string.bq_c4_title));
            }
            if (getString(R.string.bq_c5_title).equals("")) {
                nav_book_c5.setVisible(false);
            } else {
                nav_book_c5.setTitle(getString(R.string.bq_c5_title));
            }
            if (getString(R.string.bq_c6_title).equals("")) {
                nav_book_c6.setVisible(false);
            } else {
                nav_book_c6.setTitle(getString(R.string.bq_c6_title));
            }
            if (getString(R.string.bq_c7_title).equals("")) {
                nav_book_c7.setVisible(false);
            } else {
                nav_book_c7.setTitle(getString(R.string.bq_c7_title));
            }
            if (getString(R.string.bq_c8_title).equals("")) {
                nav_book_c8.setVisible(false);
            } else {
                nav_book_c8.setTitle(getString(R.string.bq_c8_title));
            }
            if (getString(R.string.bq_c9_title).equals("")) {
                nav_book_c9.setVisible(false);
            } else {
                nav_book_c9.setTitle(getString(R.string.bq_c9_title));
            }
        }
        if (this.book_contents.equals(getString(R.string.book_lkm))) {
            nav_book_lkm.setChecked(true);
            book_nav_imageView.setImageResource(R.drawable.book_lkm);
            book_nav_title.setText(getString(R.string.book_lkm));
        }
        if (this.book_contents.equals(getString(R.string.book_agb))) {
            nav_book_agb.setChecked(true);
            book_nav_imageView.setImageResource(R.drawable.book_agb);
            book_nav_title.setText(getString(R.string.book_agb));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.book_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            goToMain();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.book, menu);

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
        if (id == R.id.home) {
            goToMain();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.book_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
