package com.ebaschiera.triplecamel;

import org.piwik.sdk.PiwikApplication;

/**
 * Created by ebaschiera on 19/07/15.
 */
public class TripleCamelApp extends PiwikApplication {

    @Override
    public String getTrackerUrl() {
        return "http://ebaschiera.com/piwik/piwik.php";
    }

    @Override
    public Integer getSiteId() {
        return 1;
    }

}