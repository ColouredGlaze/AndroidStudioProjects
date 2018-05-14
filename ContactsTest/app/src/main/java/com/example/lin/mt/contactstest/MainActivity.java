package com.example.lin.mt.contactstest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView contactsView;

    private Button btnReadContacts;

    private ArrayAdapter<String> arrayAdapter;

    List<String> contactsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactsView = findViewById(R.id.contacts_view);
        btnReadContacts = findViewById(R.id.read_contacts);
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, contactsList);
        contactsView.setAdapter(arrayAdapter);
        btnReadContacts.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // 读取联系人
                    readContacts();
                } else {
                    Toast.makeText(this, "你特么傲娇不允许我读取联系人？", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void readContacts(){
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null,null,null);
            if (null != cursor) {
                while (cursor.moveToNext()){
                    String displayName = cursor.getString(
                            cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(
                            cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsList.add(displayName + "\n" + number);
                }
                arrayAdapter.notifyDataSetChanged();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (null != cursor){
                cursor.close();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.read_contacts:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                        == PackageManager.PERMISSION_GRANTED){
                    // 读取联系人
                    readContacts();
                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this, Manifest.permission.READ_CONTACTS)){
                        Toast.makeText(this, "请允许读取联系人权限", Toast.LENGTH_SHORT).show();
                    }
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_CONTACTS}, 1);
                }
                break;
            default:
                break;
        }
    }
}
