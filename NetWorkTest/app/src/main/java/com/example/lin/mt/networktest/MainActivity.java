package com.example.lin.mt.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSendRequestByHttpURLConnection;
    private Button btnSendRequestByOkHttp;
    private Button btnParseXMLWithPull;
    private Button btnParseXMLWithSAX;
    private Button btnParseJsonWithJSONObject;
    private Button btnParseJsonWithGSON;
    private TextView responseText;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSendRequestByHttpURLConnection = findViewById(R.id.send_request_http_url_connection);
        btnSendRequestByOkHttp = findViewById(R.id.send_request_ok_http);
        btnParseXMLWithPull = findViewById(R.id.parse_xml_with_pull);
        btnParseXMLWithSAX = findViewById(R.id.parse_xml_with_sax);
        btnParseJsonWithGSON = findViewById(R.id.parse_json_with_gson);
        btnParseJsonWithJSONObject = findViewById(R.id.parse_json_with_jsonObject);
        responseText = findViewById(R.id.response_text);
        btnSendRequestByHttpURLConnection.setOnClickListener(this);
        btnSendRequestByOkHttp.setOnClickListener(this);
        btnParseXMLWithSAX.setOnClickListener(this);
        btnParseXMLWithPull.setOnClickListener(this);
        btnParseJsonWithJSONObject.setOnClickListener(this);
        btnParseJsonWithGSON.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_request_http_url_connection:
                sendRequestWithHttpURLConnection();
                break;
            case R.id.send_request_ok_http:
                sendRequestByOKHttp();
                break;
            case R.id.parse_xml_with_pull:
                parseXMLWithPull();
                break;
            case R.id.parse_xml_with_sax:
                parseXMLWithSAX();
            case R.id.parse_json_with_jsonObject:
                parseJsonWithJSONObject();
                break;
            case R.id.parse_json_with_gson:
                parseJsonWithGSON();
                break;
            default:
                break;
        }
    }

    private void parseJsonWithGSON() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://192.168.123.170/json_data.json")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJsonByGSON(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJsonByGSON(String jsonData) {
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>(){}.getType());
        for (App app : appList) {
            Log.d(TAG, "parseJsonByGSON: id is " + app.getId());
            Log.d(TAG, "parseJsonByGSON: name is " + app.getName());
            Log.d(TAG, "parseJsonByGSON: version is " + app.getVersion());
        }
    }

    private void parseJsonWithJSONObject() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://192.168.123.170/json_data.json")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJsonByJSONObject(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJsonByJSONObject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.d(TAG, "parseJsonByJSONObject: id is " + jsonObject.getString("id"));
                Log.d(TAG, "parseJsonByJSONObject: name is " + jsonObject.getString("name"));
                Log.d(TAG, "parseJsonByJSONObject: version is " + jsonObject.getString("version"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseXMLWithSAX() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://192.168.123.170/xml_data.xml")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseXMLBySAX(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseXMLBySAX(String xmlData) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler handler = new ContentHandler();
            // 将ContentHandler的实例设置到XMLReader中
            xmlReader.setContentHandler(handler);
            // 开始执行解析
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseXMLWithPull() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://192.168.123.170/xml_data.xml")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseXMLByPull(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseXMLByPull(String xmlData) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT){
                String nodeName = xmlPullParser.getName();
                switch (eventType){
                    // 开始解析某个节点
                    case XmlPullParser.START_TAG:
                        if ("id".equals(nodeName)){
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodeName)){
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)){
                            version = xmlPullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("app".equals(nodeName)){
                            Log.d(TAG, "parseXMLByPull: id is " + id);
                            Log.d(TAG, "parseXMLByPull: name is " + name);
                            Log.d(TAG, "parseXMLByPull: version is " + version);
                        }
                        break;
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendRequestByOKHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://www.baidu.com")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void sendRequestWithHttpURLConnection() {
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL("http://www.baidu.com");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);
                    httpURLConnection.setInstanceFollowRedirects(false);
                    if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                        Log.d(TAG, "run: HTTP_OK");
                    } else {
                        Log.d(TAG, "run(ResponseCode): " + httpURLConnection.getResponseCode());
                        // 处理重定向
                        if (302 == httpURLConnection.getResponseCode()){
                            String location = httpURLConnection.getHeaderField("Location");
                            Log.d(TAG, "run: location" + location);
                            url  = new URL(location);
                            httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setRequestMethod("GET");
                        }
                    }

                    InputStream inputStream = httpURLConnection.getInputStream();
                    // 下面对获取到的输入流进行读取
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    Log.d(TAG, "run: start read");
                    while ((line = bufferedReader.readLine()) != null){
                        response.append(line);
                        Log.d(TAG, "run: " + line);
                    }
                    showResponse(response.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedReader != null){
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (null != httpURLConnection){
                        httpURLConnection.disconnect();
                    }
                }

            }
        }).start();
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                responseText.setText(response);
            }
        });
    }
}
