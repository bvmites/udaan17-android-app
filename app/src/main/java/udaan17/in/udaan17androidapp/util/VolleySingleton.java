package udaan17.in.udaan17androidapp.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by pranshu on 5/3/17.
 * For: udaan17-android-app
 * <p>
 * This class is used to make only a single object of VolleySingleton(indirectly requestQueue)
 * exist in the entire app. Reason why we need this is because the app is required to have a single
 * requestQueue for all purposes.
 */

public class VolleySingleton {

    private static VolleySingleton instance;
    private static Context context;
    private RequestQueue requestQueue;

    public VolleySingleton(Context context) {
        this.context = context;
        requestQueue = this.getRequestQueue();
    }

    public static VolleySingleton getinstance(Context c) {
        if (instance == null)
            instance = new VolleySingleton(c);
        return instance;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
