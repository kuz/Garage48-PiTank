package com.example.kuz.myfirstapp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.view.Window;
import android.view.WindowManager;
import de.mjpegsample.MjpegView.MjpegInputStream;
import de.mjpegsample.MjpegView.MjpegView;
import android.content.pm.ActivityInfo;
import android.view.Surface;
import android.view.Display;
import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.widget.Button;


public class MainActivity extends Activity {

    private MjpegView mv;
    //private static final int MENU_QUIT = 1;

    public void onPause() {
        super.onPause();
        mv.stopPlayback();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);

        // MJPG stuff
        super.onCreate(savedInstanceState);
        //String URL = "http://192.168.42.1:8080/cam.mjpg";

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        //mv = new MjpegView(this);
        //setContentView(mv);
        //mv.setSource(MjpegInputStream.read(URL));
        //mv.setDisplayMode(MjpegView.SIZE_BEST_FIT);
        //mv.showFps(false);

        // deafult
        setContentView(R.layout.activity_main);

        // Webview
        //WebView myWebView = (WebView) findViewById(R.id.webView);
        //WebSettings webSettings = myWebView.getSettings();
        //webSettings.setJavaScriptEnabled(true);
        //myWebView.loadUrl("http://192.168.42.1:8080/index.html");

        /*
        final Button button = (Button) findViewById(R.id.button);
        button.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    new CommandTask('1').execute();
                    System.out.println("move");
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    new CommandTask('1').execute();
                    System.out.println("pressed");
                } else {
                    System.out.println("done");
                }

                try {
                    Thread.sleep(1000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                return false;
            }
        });
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void moveLeft(View view) {
        new CommandTask('1').execute();
    }

    public void moveRight(View view) {
        new CommandTask('2').execute();
    }

    public void moveForward(View view) {
        new CommandTask('3').execute();
    }

    public void moveBack(View view) {
        new CommandTask('4').execute();
    }

    public void stop(View view) {
        new CommandTask('0').execute();
    }

    public void laserOnOff(View view) {
        new CommandTask('9').execute();
    }

    /*
    private MjpegView mv;
    private static final int MENU_QUIT = 1;

    // Creates the menu items
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_QUIT, 0, "Quit");
        return true;
    }

    // Handles item selections
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_QUIT:
                finish();
                return true;
        }
        return false;
    }

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //sample public cam
        String URL = "http://192.168.42.1:8080/cam.mjpg";

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        mv = new MjpegView(this);
        setContentView(mv);
        mv.setSource(MjpegInputStream.read(URL));
        mv.setDisplayMode(MjpegView.SIZE_BEST_FIT);
        mv.showFps(false);
    }

    public void onPause() {
        super.onPause();
        mv.stopPlayback();
    }
    */
}

class CommandTask extends AsyncTask<String, Void, String> {

    char command = '0';

    public CommandTask(char command) {
        super();
        this.command = command;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            Socket socket = new Socket("192.168.42.1", 12345);
            OutputStream out = socket.getOutputStream();
            out.write(this.command);
            out.flush();
            out.close();

        } catch (UnknownHostException e) {
            //
        } catch (java.io.IOException e) {
            //
        }

        return "Done";

    }

    @Override
    protected void onPostExecute(String result) {
        //
    }
}