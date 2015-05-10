package com.ebaschiera.triplecamel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.text.Html;
import android.text.Spanned;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get tracker.
        Tracker t = ((TripleCamelApplication) getApplication()).getTracker(
                TripleCamelApplication.TrackerName.APP_TRACKER);
        // Build and send an Event.
        t.send(new HitBuilders.EventBuilder()
                .setCategory("default")
                .setAction("aboutpage_display")
                .build());
        setContentView(R.layout.activity_main);
    }

    /**
     * A placeholder fragment containing a simple view. This fragment
     * would include your content.
     */
    public static class MainFragment extends Fragment {

        public MainFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView about_text = (TextView) rootView.findViewById(R.id.innerTextViewAbout);
            Spanned sp = Html.fromHtml(getString(R.string.about_this_app));
            about_text.setText(sp);
            about_text.setMovementMethod(LinkMovementMethod.getInstance());
            return rootView;
        }
    }

}
