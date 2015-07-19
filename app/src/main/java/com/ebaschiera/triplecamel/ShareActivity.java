package com.ebaschiera.triplecamel;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.content.pm.*;
import android.widget.Toast;
import java.util.regex.*;
import java.util.List;


/**
 * Created by ebaschiera on 01/01/15.
 */
public class ShareActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((TripleCamelApp) getApplication()).getTracker()
                .trackScreenView("/share", "Share intent");

        // Get the intent that started this activity
        Intent intent = getIntent();
        Uri data = intent.getData();

        String amazon_share_text = intent.getStringExtra(Intent.EXTRA_TEXT);
        //Log.d("triple", amazon_share_text);
        if (amazon_share_text.matches(".*http[s]?://www\\.amazon\\.(?:com|ca|cn|de|es|fr|in|it|co\\.jp|co\\.uk)/.*")) {
            //Log.d("triple", "It matches!");
            Pattern p = Pattern.compile(".*(http[s]?://www\\.amazon\\.(?:com|ca|cn|de|es|fr|in|it|co\\.jp|co\\.uk|com)/.*)");
            Matcher m = p.matcher(amazon_share_text);
            String amazon_share_url = "";
            while (m.find()) { // Find each match in turn; String can't do this.
                amazon_share_url = m.group(1); // Access a submatch group; String can't do this.
            }
            if (amazon_share_url == "") {
                //return a warning and stop the intent
                ((TripleCamelApp) getApplication()).getTracker().trackEvent("share", "failed", "label", 1);
                Context context = getApplicationContext();
                String text = getResources().getString(R.string.amazon_link_not_matching);
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                finish();
            }
            amazon_share_url = Uri.encode(amazon_share_url);
            Uri camel_search_uri = Uri.parse("http://camelcamelcamel.com/search?sq=" + amazon_share_url);
            //Log.d("triple", amazon_share_url);
            //Log.d("new_intent", camel_search_uri.toString());
            Intent webIntent = new Intent(Intent.ACTION_VIEW, camel_search_uri);

            // Verify it resolves
            PackageManager packageManager = getPackageManager();
            List<ResolveInfo> activities = packageManager.queryIntentActivities(webIntent, 0);
            boolean isIntentSafe = activities.size() > 0;

            // Start an activity if it's safe
            if (isIntentSafe) {
                ((TripleCamelApp) getApplication()).getTracker().trackEvent("share", "successful", "label", 1);
                startActivity(webIntent);
                finish();
            } else {
                ((TripleCamelApp) getApplication()).getTracker().trackEvent("share", "failed", "label", 1);
                Context context = getApplicationContext();
                String text = getResources().getString(R.string.no_web_browser);
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                finish();
            }
        } else {
            //return a warning and stop the intent
            ((TripleCamelApp) getApplication()).getTracker().trackEvent("share", "failed", "label", 1);
            Context context = getApplicationContext();
            String text = getResources().getString(R.string.amazon_link_not_matching);
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            finish();
        }



    }
}
