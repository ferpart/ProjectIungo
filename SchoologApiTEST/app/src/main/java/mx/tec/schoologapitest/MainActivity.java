package mx.tec.schoologapitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;

    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.mTextView);
        queue = Volley.newRequestQueue(this);
        String url = "https://api.schoology.com/v1/messages/inbox";
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //mTextView.setText("Response is: "+ response.substring(0,500));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
                //mTextView.append(error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();

                Date date= new Date();
                String x = uniqid("", false);
                params.put("Authorization", "OAuth realm=\"Schoology API\","+
                        "oauth_consumer_key=\"97b46bdb6be792873147356672df19e605c58e990\","+
                        "oauth_token=\"\","+
                        "oauth_nonce="+x+","+
                        "oauth_timestamp=" + date.getTime() + ","+
                        "oauth_signature_method=\"PLAINTEXT\","+
                        "oauth_version=\"1.0\","+
                        "oauth_signature=\"d8ac3bba9e5e6c90bae8807871c19187%26\")");
                params.put("Accept", "text/xml;q=1.0,application/json;q=0.0");
                params.put("Host", "api.schoology.com");
                params.put("Content-Type", "text/xml");
                mTextView.append(x);
                /*params.put("realm","Schoology API");
                params.put("oauth_consumer_key", "97b46bdb6be792873147356672df19e605c58e990");
                params.put("oauth_token","");
                Date date= new Date();
                params.put("oauth_nonce", uniqid("", false));
                params.put("oauth_timestamp", ""+date.getTime());
                params.put("oauth_signature_method", "PLAINTEXT");
                params.put("oauth_version","1.0");
                params.put("oauth_signature", "d8ac3bba9e5e6c90bae8807871c19187%26");


                params.put("oauth_consumer_key", "97b46bdb6be792873147356672df19e605c58e990");
                params.put("oauth_signature_method", "PLAINTEXT");
                params.put("oauth_timestamp","1550078607");
                params.put("oauth_nonce","X259sux8pqR");
                params.put("oauth_version","1.0");
                params.put("oauth_consumer_secret","d8ac3bba9e5e6c90bae8807871c19187");
                */
                return params;
            }
        };
        queue.add(stringRequest);
        /*
        String header = "https://api.schoology.com/v1/messages/inbox?"+
                "oauth_consumer_key=97b46bdb6be792873147356672df19e605c58e990&"+
                "oauth_signature_method=HMAC-SHA1&"+
                "oauth_timestamp=1550078607&"+
                "oauth_nonce=X259sux8pqR&"+
                "oauth_version=1.0&"+
                "oauth_signature=18NfORE3uJS5KzHchd//PUI8D9g=";
        JsonArrayRequest request = new JsonArrayRequest(header, successListener, failListener);
        queue.add(request);
        */
    }

    public String uniqid(String prefix,boolean more_entropy)
    {
        long time = System.currentTimeMillis();
        //String uniqid = String.format("%fd%05f", Math.floor(time),(time-Math.floor(time))*1000000);
        //uniqid = uniqid.substring(0, 13);
        String uniqid = "";
        if(!more_entropy)
        {
            uniqid = String.format("%s%08x%05x", prefix, time/1000, time);
        }else
        {
            SecureRandom sec = new SecureRandom();
            byte[] sbuf = sec.generateSeed(8);
            ByteBuffer bb = ByteBuffer.wrap(sbuf);

            uniqid = String.format("%s%08x%05x", prefix, time/1000, time);
            uniqid += "." + String.format("%.8s", ""+bb.getLong()*-1);
        }


        return uniqid ;
    }

    /*
    final Response.Listener<JSONArray> successListener = new Response.Listener<JSONArray>() {
                public void onResponse(JSONArray response) {
                    try {
                        String query = response.getString(0);
                        mTextView.append("Original query: " + query + "\n");
                        JSONArray rest = response.getJSONArray(1);
                        mTextView.setText("We got: " + rest.length() + " results:\n");
                        for (int i = 0; i < rest.length(); i++) {
                            mTextView.append(rest.getString(i) + "\n");
                        }
                    } catch (JSONException e) {
                        mTextView.append("\nFailed\n");
                        e.printStackTrace();
                    }
                }
    };

    final Response.ErrorListener failListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mTextView.setText("That didn't work!\n" +
                    "Error: " + error + "\n" +
                    "Detail:" + error.getMessage() + "\n" +
                    "Cause: " + error.getCause());
            error.printStackTrace();
        }
    };
    */
}