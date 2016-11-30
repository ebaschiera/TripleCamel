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

import org.piwik.sdk.TrackHelper;
import org.piwik.sdk.Tracker;


public class MainActivity extends ActionBarActivity {

    private Tracker getTracker() {
        return ((TripleCamelApp) getApplication()).getTracker();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TrackHelper.track().screen("/info_screen").title("Info screen").with(getTracker());
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
