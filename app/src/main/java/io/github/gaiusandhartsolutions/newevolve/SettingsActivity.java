package io.github.gaiusandhartsolutions.newevolve;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SettingsActivity extends AppCompatActivity {
    App A;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        A = (App) getApplication();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        setSupportActionBar(myToolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setTitle(getString(R.string.settings_title));
        ab.setDisplayHomeAsUpEnabled(true);

        final CheckBox settings_hideArchivedAdventurers =
                (CheckBox) findViewById(R.id.settings_hideArchivedAdventurers);
        final CheckBox settings_hideInactiveAdventures =
                (CheckBox) findViewById(R.id.settings_hideInactiveAdventures);
        settings_hideArchivedAdventurers.setChecked(
                Boolean.valueOf(A.getFromLocalConfig(Engine.CONFIG_hideArchivedAdventurers)));
        settings_hideInactiveAdventures.setChecked(
                Boolean.valueOf(A.getFromLocalConfig(Engine.CONFIG_hideInactiveAdventures)));

        final Button settings_button_apply =
                (Button) findViewById(R.id.settings_button_apply);
        final Button settings_button_signout =
                (Button) findViewById(R.id.settings_button_signout);
        settings_button_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                A.setToLocalConfig(Engine.CONFIG_hideArchivedAdventurers,
                        String.valueOf(settings_hideArchivedAdventurers.isChecked()));
                A.setToLocalConfig(Engine.CONFIG_hideInactiveAdventures,
                        String.valueOf(settings_hideInactiveAdventures.isChecked()));
            }
        });
        settings_button_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                A.getAuth().signOut();
                Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
