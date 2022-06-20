package com.ebaschiera.triplecamel;

import android.app.Activity;
import android.content.ActivityNotFoundException;
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
 * Tested strings:
 * Grin&Bear Herren Hoodie Slim fit Kapuzenpullover Kapuzenjacke mit gestempeltem Design Logo GEC540 https://amzn.eu/d/2Euir6I
 * Acer Chromebook 314 CB314-1H-P2EM Notebook, Pc Portatile con Processore Intel Pentium Silver N5030, Ram 8 GB DDR4, eMMC 64 GB, Display 14" Full HD LED LCD, Scheda Grafica Intel UHD, Chrome OS, Silver https://amzn.eu/d/1679v13
 */
public class ShareActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the intent that started this activity
        Intent intent = getIntent();
        Uri data = intent.getData();

        String amazon_share_text = intent.getStringExtra(Intent.EXTRA_TEXT);
        //Log.d("triple", amazon_share_text);
        assert amazon_share_text != null;
        if (amazon_share_text.matches(".*https://(?:www\\.|smile\\.)?(?:amazon|amzn)\\.(?:com|ae|ca|cn|de|es|eu|fr|in|it|nl|sa|sg|co\\.jp|co\\.uk|com\\.au|com\\.br|com\\.mx|com\\.tr)/.*")) {
            //Log.d("triple", "It matches!");
            Pattern p = Pattern.compile(".*(https://(?:www\\.|smile\\.)?(?:amazon|amzn)\\.(?:com|ae|ca|cn|de|es|eu|fr|in|it|nl|sa|sg|co\\.jp|co\\.uk|com\\.au|com\\.br|com\\.mx|com\\.tr)/.*)");
            Matcher m = p.matcher(amazon_share_text);
            String amazon_share_url = "";
            while (m.find()) { // Find each match in turn; String can't do this.
                amazon_share_url = m.group(1); // Access a submatch group; String can't do this.
            }
            assert amazon_share_url != null;
            if (amazon_share_url.equals("")) {
                //return a warning and stop the intent
                Context context = getApplicationContext();
                String text = getResources().getString(R.string.amazon_link_not_matching);
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                finish();
            }
            amazon_share_url = Uri.encode(amazon_share_url);
            Uri camel_search_uri = Uri.parse("https://camelcamelcamel.com/search?sq=" + amazon_share_url);
            //Log.d("triple", amazon_share_url);
            //Log.d("new_intent", camel_search_uri.toString());
            Intent webIntent = new Intent(Intent.ACTION_VIEW, camel_search_uri);

            try {
                startActivity(webIntent);
            } catch (ActivityNotFoundException e) {
                Context context = getApplicationContext();
                String text = getResources().getString(R.string.no_web_browser);
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                finish();
            }
        } else {
            //return a warning and stop the intent
            Context context = getApplicationContext();
            String text = getResources().getString(R.string.amazon_link_not_matching);
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            finish();
        }



    }
}
