package com.example.myapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.myapp.fragment.Frag_Login;
import com.example.myapp.fragment.PM_Place_View;
import com.example.myapp.provider.StudentsProvider;
import com.example.myapp.service.MyService;

import java.util.HashMap;
import java.util.Map;

public class MyActivity extends Activity implements PM_Place_View.OnArticleSelectedListener, Frag_Login.LoginInputListener {
    String msg = "My Android: ";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ImageView imageView = (ImageView) findViewById(R.id.myimageview);
//        imageView.setImageResource(R.drawable.myimage);
        setContentView(R.layout.activity_main);
        Log.d(msg, "create");

        /*
         Hide System Bottom Bar.
         */
        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(msg, "start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "The onPause() event");
    }

    /**
     * Called when the activity is no longer visible.
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    /**
     * Called just before the activity is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }

    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));
    }

    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }

    public void sendBroadcast(View view) {
        Intent intent = new Intent();
        intent.setAction("CUSTOM_INTENT");
        sendBroadcast(intent);
    }

    public void onClickAddName(View view) {
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME, ((EditText) findViewById(R.id.txtName)).getText().toString());
        values.put(StudentsProvider.GRADE, ((EditText) findViewById(R.id.txtGrade)).getText().toString());
        Uri uri = getContentResolver().insert(StudentsProvider.content_uri, values);
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view) {
        String URL = "content://com.example.provider.College/students";
        Map<String, String> name2Grade = new HashMap<>();
        Uri students = Uri.parse(URL);

        CursorLoader cursorLoader = new CursorLoader(getBaseContext(), students, null, null, null,
                "name");
//        Cursor c = managedQuery(students, null, null, null, "name");
        Cursor c = cursorLoader.loadInBackground();
        if (c.moveToFirst()) {
            do {
//                Toast.makeText(this, c.getString(c.getColumnIndex(StudentsProvider._ID)) + ", "
//                        + c.getString(c.getColumnIndex(StudentsProvider.NAME)) + ", "
//                        + c.getString(c.getColumnIndex(StudentsProvider.GRADE)), Toast.LENGTH_SHORT).show();
                String name = c.getString(c.getColumnIndex(StudentsProvider.NAME)) ;
                String grade = c.getString(c.getColumnIndex(StudentsProvider.GRADE));
                name2Grade.put(name, grade);
            } while (c.moveToNext());
        }

        showPlaces(name2Grade);



    }

    private void showPlaces(Map<String, String> map) {
        final LinearLayout layout2=(LinearLayout) findViewById(R.id.scroll_view_places_layout);
        layout2.removeAllViews();

        //1.获取FragmentManager对象
        FragmentManager manager = getFragmentManager();
        //2.获取FragmentTransaction对象
        FragmentTransaction transaction = manager.beginTransaction();
        //添加Fragment对象
        PM_Place_View placeView = new PM_Place_View();
        placeView.buildData(map);

        transaction.add(R.id.scroll_view_places_layout, placeView , "frag_place_view");
        //4.提交
        transaction.commit();
    }

    public void intentToPhone(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        startActivity(i);
    }

    public void intentToBrowser(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:10086"));
        startActivity(i);
    }

    public void startBrowserA(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        startActivity(i);
    }

    public void StartBrowserB(View view) {
//        view.getParent()
    }

    public void StartBrowserC(View view) {
        Intent i = new Intent("example.LAUNCH", Uri.parse("https://www.baidu.com"));
        startActivity(i);
    }

    @Override
    public void onItemSelected(String name, String grade) {
        Intent i = new Intent("example.LAUNCH", Uri.parse("http://www.baidu.com"));
        i.putExtra("name", name);
        i.putExtra("grade", grade);
        startActivity(i);
    }

    public void showLogInFragment(View view)
    {
        Frag_Login dialog = new Frag_Login();
        dialog.show(getFragmentManager(), "loginDialog");
    }

    @Override
    public void onLoginInputComplete(String username, String password) {
        Toast.makeText(this, "帐号：" + username + ",  密码 :" + password,
                Toast.LENGTH_SHORT).show();
    }
}
