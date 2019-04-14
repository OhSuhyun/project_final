package com.example.owner.project_final;

/*
다른 자바 파일에 툴바와 내비게이션 바와 관련된 것을 넣으면 앱 강제종료 당함
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    // For Toolbar ---------------------------------------------------------------------------------
    Toolbar toolBar;
    //----------------------------------------------------------------------------------------------

    // For Navigation Drawer -----------------------------------------------------------------------
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    // ---------------------------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*/ For AdView ------------------------------------------------------------------------------
        AdView mAdView = (AdView) findViewById(R.id.ads_main);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        // -----------------------------------------------------------------------------------------*/

        // For Toolbar -----------------------------------------------------------------------------
        toolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("우리동네 자취생");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_menu_black_24dp);
        // -----------------------------------------------------------------------------------------



        Button btn_first=(Button)findViewById(R.id.btn_first);  //tab1
        Button btn_second=(Button)findViewById(R.id.btn_second);    //tab2
        Button btn_third=(Button)findViewById(R.id.btn_third);  //tab3
        Button btn_fourth=(Button)findViewById(R.id.btn_fourth);    //LED
        Button btn_fifth=(Button)findViewById(R.id.btn_fifth);  //EQ
        Button btn_sixth=(Button)findViewById(R.id.btn_sixth);  //FreeActivity
        Button chatButton=(Button)findViewById(R.id.chatButton);


        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent().setClass( getApplicationContext(),Tab1Activity.class );
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btn_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent().setClass( getApplicationContext(),Tab2Activity.class );
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);

            }
        });
        btn_third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent().setClass( getApplicationContext(),Tab3Activity.class );
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        btn_fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent().setClass( getApplicationContext(),BluetoothLED.class );
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        /*

        btn_fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent().setClass( getApplicationContext(),Tab2Activity.class );
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);

            }
        });
*/
        btn_sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent().setClass( getApplicationContext(), FreeActivity.class );
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent().setClass( getApplicationContext(),ChattingActivity.class );
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }

    // For Toolbar ---------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_general, menu);    //게시판 목록 외에서 사용할 툴바 메뉴
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);

        drawerLayout = (DrawerLayout) findViewById(R.id.activity);

        switch (item.getItemId()) {
            case R.id.MainButton:
                intent = new Intent().setClass( getApplicationContext(), MainActivity.class );  //MainActivity로 이동
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.ChatButton:
                intent = new Intent().setClass( getApplicationContext(), ChattingActivity.class );  //ChattingActivity로 이동
                startActivity(intent);
                return true;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.MyPageButton:
                intent = new Intent().setClass( getApplicationContext(), MypageActivity.class );    //MyㅔageActivity로 이동
                startActivity(intent);
                return true;
            case R.id.LogOutButton:
                if(user != null){
                    FirebaseAuth.getInstance().signOut();
                    intent = new Intent().setClass( getApplicationContext(), LoginActivity.class ); //로그아웃 후 LoginActivity로 이동
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "로그아웃 성공", Toast.LENGTH_LONG).show();
                    overridePendingTransition(0, 0);
                }else{
                    Toast.makeText(getApplicationContext(), "로그아웃 실패", Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }
    //----------------------------------------------------------------------------------------------



}