package me.ppxpp.project.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.ppxpp.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends PreferenceFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
