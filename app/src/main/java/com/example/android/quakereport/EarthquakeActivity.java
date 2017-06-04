/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    /** Sample JSON response for a USGS query */
    private static final String SAMPLE_JSON_RESPONSE = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";
    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

      /*  // Create a fake list of earthquake locations.
        ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();
        earthquakes.add(new Earthquake("7.2","San Francisco","Feb 2, 2016"));
        earthquakes.add(new Earthquake("6.1","London","July 20, 2015"));
        earthquakes.add(new Earthquake("3.9","Tokyo","Nov 10, 2015"));
        earthquakes.add(new Earthquake("5.4","Mexico City","May 3, 2014"));
        earthquakes.add(new Earthquake("2.8","Moscow","Jan 31,2013"));
        earthquakes.add(new Earthquake("4.9","Rio de Janeiro","Aug 19, 2012"));
        earthquakes.add(new Earthquake("1.6","Paris","Oct 30, 2011")); */

        //Get the list of Earquakes from the ArrayList we have created in QueryUtils

       // List<Earthquake> earthquakes = QueryUtils.extractEarthquakes();

        // Create a new {@link ArrayAdapter} of earthquakes, past the information to the EarthquakeAdapter

       // EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this, earthquakes);

        // Find a reference to the {@link ListView} in the layout
    //    ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
      //  earthquakeListView.setAdapter(earthquakeAdapter);

        earthquakeAsyncTask earthquakeAsyncTask = new earthquakeAsyncTask();
        earthquakeAsyncTask.execute(SAMPLE_JSON_RESPONSE);
    }

    /**
     * Update the UI with the given earthquake information.
     */
    private void updateUi(List<Earthquake> earthquakes) {
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes the list of earthquakes as input
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>(earthquakes));

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }

    private class earthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {

        /**
         * This method runs on a background thread and performs the network request.
         * We should not update the UI from a background thread, so we return a list of
         * {@link Earthquake}s as the result.
         */
        @Override
        protected List<Earthquake> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Earthquake> earthquakes = QueryUtils.extractEarthquakes(urls[0]);
            return earthquakes;
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            // If there is no result, do nothing.
            if (earthquakes == null) {
                return;
            }

            updateUi(earthquakes);
        }
    }
}
