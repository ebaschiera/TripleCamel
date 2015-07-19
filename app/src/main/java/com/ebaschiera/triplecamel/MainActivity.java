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


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TripleCamelApp) getApplication()).getTracker()
                .trackScreenView("/info_screen", "Info screen");
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
