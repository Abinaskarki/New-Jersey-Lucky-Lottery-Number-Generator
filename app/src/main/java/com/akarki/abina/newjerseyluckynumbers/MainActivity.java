package com.akarki.abina.newjerseyluckynumbers;


import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import static android.net.Uri.parse;

public class MainActivity extends AppCompatActivity  {

    private TextView p1;
    private TextView p2;
    private TextView p3;
    private TextView p4;
    private TextView p5;
    private TextView p6;
    private TextView m1;
    private TextView m2;
    private TextView m3;
    private TextView m4;
    private TextView m5;
    private TextView m6;
    private TextView l1;
    private TextView l2;
    private TextView l3;
    private TextView l4;
    private TextView l5;
    private TextView l6;
    private TextView pk61;
    private TextView pk62;
    private TextView pk63;
    private TextView pk64;
    private TextView pk65;
    private TextView pk66;
    private TextView pk51;
    private TextView pk52;
    private TextView pk53;
    private TextView pk54;
    private TextView pk55;
    private TextView pk41;
    private TextView pk31;
    private TextView qd1,qd2,qd3,qd4,qd5,qd6,qd7,qd8,qd9,qd10;
    private int op1;
    private int op2;
    private int op3;
    private int op4;
    private int op5;
    private int op6;
    private int op7;
    private int op8;
    private int op9;
    private int op10;

    private Button news1;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private ListView lst1;
    private ListView lst2;
    private ListView lst3;
    private ListView lst4;


    //for Fortune
    private LayoutInflater flayoutInflater;
    private PopupWindow fpopup;
    private Button fortune;
    private ListView fortunelist;
    private Dialog thisDialog;

    //Spinner
    private Spinner mySpinner;

    private Dialog myDialog;
    private static final String FILE_NAME = "mynumbers.txt";


    private Random r;
    public static final String IMAGE_RES_ID_KEY = "IMAGE_RES_ID_KEY";
    public static final String IMAGE_RES_ID_KEY1 = "IMAGE_RES_ID_KEY1";

    public static final String IMG_NAME = "IMG_NAME";
    public static final String IMG_NAME1 = "IMG_NAME1";



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();


        ActionBar actionBar = getSupportActionBar();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(actionBar).setDisplayShowHomeEnabled(true);
        }
        actionBar.setIcon(R.mipmap.ic_launcher_thenjlucky);

        //Daily Lucky Numbers

        Toast.makeText(MainActivity.this,"New Random Lucky Numbers",Toast.LENGTH_SHORT).show();


        //for my numbers
        Button myNumbers = findViewById(R.id.myNumbers);
        myNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog =  new Dialog(MainActivity.this);
                myDialog.show();
                myDialog.setContentView(R.layout.my_numbers);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Objects.requireNonNull(myDialog.getWindow()).setDimAmount(0.5f);
                }
                final EditText myedittext = myDialog.findViewById(R.id.myEditText);
                Button mysave = myDialog.findViewById(R.id.mySave);
                Button myedit = myDialog.findViewById(R.id.myEdit);
                Button mycancel = myDialog.findViewById(R.id.myClose);

                FileInputStream fis = null;

                try {
                    fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String text;

                    while ((text=br.readLine())!=null){
                        sb.append(text).append("\n");
                    }
                    myedittext.setText(sb.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(fis!=null){
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

                mysave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String text  = myedittext.getText().toString();
                        FileOutputStream fos = null;

                        try {
                            fos = openFileOutput(FILE_NAME,MODE_PRIVATE);
                            fos.write(text.getBytes());

                            myedittext.getText();
                            myedittext.setEnabled(false);
                            Toast.makeText(MainActivity.this,"Your numbers is saved.",Toast.LENGTH_SHORT).show();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        finally {
                            if(fos!=null){
                                try {
                                    fos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }
                });
                myedit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myedittext.setEnabled(true);
                    }
                });
                mycancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.cancel();
                    }
                });
            }
        });

        //For Latest Updates and Horoscope
        news1 = findViewById(R.id.news1);
        news1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutInflater = (LayoutInflater) getApplication().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    container = (ViewGroup) Objects.requireNonNull(layoutInflater).inflate(R.layout.news, null);
                }
                popupWindow = new PopupWindow(container, ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
                popupWindow.showAsDropDown(news1);
                lst1 = container.findViewById(R.id.listView1);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.newsPaper));
                lst1.setAdapter(adapter1);
                lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(MainActivity.this, getResources().getStringArray(R.array.newsPaper)[i], Toast.LENGTH_SHORT).show();
                        switch (i) {
                            case 0: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.nj.com/starledger/"));
                                startActivity(browser);
                                break;
                            }
                            case 1: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.nytimes.com/"));
                                startActivity(browser);
                                break;
                            }
                            case 2: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.wsj.com/"));
                                startActivity(browser);
                                break;
                            }
                            case 3: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("http://www.nydailynews.com/"));
                                startActivity(browser);
                                break;
                            }
                            case 4: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://nypost.com/"));
                                startActivity(browser);
                                break;
                            }
                            case 5: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("http://www.njherald.com/News"));
                                startActivity(browser);
                                break;
                            }
                        }
                    }
                });

                lst2 = container.findViewById(R.id.listView2);
                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.national));
                lst2.setAdapter(adapter2);
                lst2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(MainActivity.this,getResources().getStringArray(R.array.national)[i],Toast.LENGTH_SHORT).show();
                        switch (i) {
                            case 0: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.cnn.com/"));
                                startActivity(browser);
                                break;
                            }
                            case 1: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("http://www.foxnews.com/"));
                                startActivity(browser);
                                break;
                            }
                            case 2: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://abcnews.go.com/Live"));
                                startActivity(browser);
                                break;
                            }
                            case 3: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.cbs.com/"));
                                startActivity(browser);
                                break;
                            }
                            case 4: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("http://www.espn.com/"));
                                startActivity(browser);
                                break;
                            }
                        }
                    }
                });

                lst3 = container.findViewById(R.id.listView3);
                ArrayAdapter<String> adapter3 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.majorLeague));
                lst3.setAdapter(adapter3);
                lst3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(MainActivity.this,getResources().getStringArray(R.array.majorLeague)[i],Toast.LENGTH_SHORT).show();
                        if(i==0){
                            Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.nfl.com/"));
                            startActivity(browser);
                        }
                        if(i==1){
                            Intent browser = new Intent(Intent.ACTION_VIEW, parse("http://www.nba.com/news"));
                            startActivity(browser);
                        }
                        if(i==2){
                            Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.mlb.com/"));
                            startActivity(browser);
                        }
                        if(i==3){
                            Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.nhl.com/"));
                            startActivity(browser);
                        }
                        if(i==4){
                            Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.mlssoccer.com/"));
                            startActivity(browser);
                        }
                    }
                });

                lst4 = container.findViewById(R.id.listView4);
                ArrayAdapter<String> adapter4 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.shopping));
                lst4.setAdapter(adapter4);
                lst4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(MainActivity.this,getResources().getStringArray(R.array.shopping)[i],Toast.LENGTH_SHORT).show();
                        switch (i) {
                            case 0: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.coupons.com/"));
                                startActivity(browser);
                                break;
                            }
                            case 1: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.amazon.com/"));
                                startActivity(browser);
                                break;
                            }
                            case 2: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.walmart.com/"));
                                startActivity(browser);
                                break;
                            }
                            case 3: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.ebay.com/"));
                                startActivity(browser);
                                break;
                            }
                            case 4: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.retailmenot.com/"));
                                startActivity(browser);
                                break;
                            }
                        }
                    }
                });

                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        popupWindow.dismiss();
                        return false;
                    }
                });
            }
        });

        fortune= findViewById(R.id.fortune);
        fortune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flayoutInflater = (LayoutInflater) getApplication().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup fcontainer = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    fcontainer = (ViewGroup) Objects.requireNonNull(flayoutInflater).inflate(R.layout.fortune, null);
                }
                fpopup = new PopupWindow(fcontainer,ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
                fpopup.showAsDropDown(fortune);
                fortunelist = fcontainer.findViewById(R.id.fortunelist);
                ArrayAdapter<String> fadapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.fortunelist));
                fortunelist.setAdapter(fadapter);
                fortunelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(MainActivity.this,getResources().getStringArray(R.array.fortunelist)[i],Toast.LENGTH_SHORT).show();
                        switch (i) {
                            case 0: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("http://jokes.cc.com/"));
                                startActivity(browser);
                                break;
                            }
                            case 1: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.astrology.com/horoscope/daily/aries.html"));
                                startActivity(browser);
                                break;
                            }
                            case 2: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.astrology.com/horoscope/daily/taurus.html"));
                                startActivity(browser);
                                break;
                            }
                            case 3: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.astrology.com/horoscope/daily/gemini.html"));
                                startActivity(browser);
                                break;
                            }
                            case 4: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.astrology.com/horoscope/daily/cancer.html"));
                                startActivity(browser);
                                break;
                            }
                            case 5: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.astrology.com/horoscope/daily/leo.html"));
                                startActivity(browser);
                                break;
                            }
                            case 6: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.astrology.com/horoscope/daily/virgo.html"));
                                startActivity(browser);
                                break;
                            }
                            case 7: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.astrology.com/horoscope/daily/libra.html"));
                                startActivity(browser);
                                break;
                            }
                            case 8: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.astrology.com/horoscope/daily/scorpio.html"));
                                startActivity(browser);
                                break;
                            }
                            case 9: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.astrology.com/horoscope/daily/sagittarius.html"));
                                startActivity(browser);
                                break;
                            }
                            case 10: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.astrology.com/horoscope/daily/capricorn.html"));
                                startActivity(browser);
                                break;
                            }
                            case 11: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.astrology.com/horoscope/daily/aquarius.html"));
                                startActivity(browser);
                                break;
                            }
                            case 12: {
                                Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.astrology.com/horoscope/daily/pisces.html"));
                                startActivity(browser);
                                break;
                            }
                        }


                    }
                });

                fcontainer.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        fpopup.dismiss();
                        return false;
                    }
                });
            }
        });


        //for Powerball Random Numbers
        Button power1 = findViewById(R.id.power1);
        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);
        p3 = findViewById(R.id.p3);
        p4 = findViewById(R.id.p4);
        p5 = findViewById(R.id.p5);
        p6 = findViewById(R.id.p6);

        powerClick();
        power1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                powerClick();
                Toast.makeText(MainActivity.this,"Your Lucky Powerball Number",Toast.LENGTH_LONG).show();

            }
        });

        //for MegaMillions Random Numbers
        Button mega1 = findViewById(R.id.mega1);
        m1 = findViewById(R.id.m1);
        m2 = findViewById(R.id.m2);
        m3 = findViewById(R.id.m3);
        m4 = findViewById(R.id.m4);
        m5 = findViewById(R.id.m5);
        m6 = findViewById(R.id.m6);
        megaClick();
        mega1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                megaClick();
                Toast.makeText(MainActivity.this,"Your Lucky Mega Millions Number",Toast.LENGTH_LONG).show();

            }
        });

        //for Lucky for Life Numbers
        final Button lucky1 = findViewById(R.id.lucky1);
        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
        l4 = findViewById(R.id.l4);
        l5 = findViewById(R.id.l5);
        l6 = findViewById(R.id.l6);

        luckyClick();
        lucky1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                luckyClick();
                Toast.makeText(MainActivity.this,"Your Lucky Cash 4 Life Number",Toast.LENGTH_LONG).show();

            }
        });
        //for Pick 3 Numbers
        Button pick31 = findViewById(R.id.pick31);
        pk31 = findViewById(R.id.pk31);
        pick3Click();
        pick31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick3Click();
                Toast.makeText(MainActivity.this,"Your Lucky Pick 3 Number",Toast.LENGTH_LONG).show();

            }
        });

        //for Pick 4 Numbers
        Button pick41 = findViewById(R.id.pick41);
        pk41 = findViewById(R.id.pk41);
        pick4Click();
        pick41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick4Click();
                Toast.makeText(MainActivity.this,"Your Lucky Pick 4 Number",Toast.LENGTH_LONG).show();

            }
        });

        //for Pick 5 Numbers
        Button pick51 = findViewById(R.id.pick51);
        pk51 = findViewById(R.id.pk51);
        pk52 = findViewById(R.id.pk52);
        pk53 = findViewById(R.id.pk53);
        pk54 = findViewById(R.id.pk54);
        pk55 = findViewById(R.id.pk55);
        cash5Click();
        pick51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cash5Click();
                Toast.makeText(MainActivity.this,"Your Lucky Jersey Cash 5 Number",Toast.LENGTH_LONG).show();

            }
        });

        //for Pick 6 Numbers
        Button pick61 = findViewById(R.id.pick61);
        pk61 = findViewById(R.id.pk61);
        pk62 = findViewById(R.id.pk62);
        pk63 = findViewById(R.id.pk63);
        pk64 = findViewById(R.id.pk64);
        pk65 = findViewById(R.id.pk65);
        pk66 = findViewById(R.id.pk66);
        pick6Click();
        pick61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick6Click();
                Toast.makeText(MainActivity.this,"Your Lucky Pick 6 Number",Toast.LENGTH_LONG).show();

            }
        });

        //For Quick Draw
        mySpinner = findViewById(R.id.spinner1);
        final ArrayAdapter<String> myAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.numbers));
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mySpinner.setAdapter(myAdapter);

        Button quickDraw = findViewById(R.id.qd);
        qd1 = findViewById(R.id.qd1);
        qd2 = findViewById(R.id.qd2);
        qd3 = findViewById(R.id.qd3);
        qd4 = findViewById(R.id.qd4);
        qd5 = findViewById(R.id.qd5);
        qd6 = findViewById(R.id.qd6);
        qd7 = findViewById(R.id.qd7);
        qd8 = findViewById(R.id.qd8);
        qd9 = findViewById(R.id.qd9);
        qd10 = findViewById(R.id.qd10);
        quickClick();

        final String[] cardStatusString = new String[1];
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            public void onItemSelected(AdapterView<?> parent,
                                       View view, int pos, long id) {
                cardStatusString[0] = parent.getItemAtPosition(pos).toString();
            }


            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        quickDraw .setOnClickListener(new View.OnClickListener() {


            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Toast.makeText(MainActivity.this,"Your lucky QuickDraw numbers",Toast.LENGTH_SHORT).show();
                HashSet<Integer> set= new HashSet<>();

                for(int i = 0; i<15; i++){
                    set.add(r.nextInt(80)+1);
                }
                int j = 0;
                final int output[] = new int[set.size()];

                for (Integer aSet : set) {
                    output[j] = aSet;
                    j++;
                }

                switch (cardStatusString[0]) {
                    case "1":
                        qd3.setVisibility(View.VISIBLE);
                        qd3.setText(String.format(Locale.getDefault(), "%02d", output[0]));
                        qd3.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd4.setVisibility(View.GONE);
                        qd1.setVisibility(View.GONE);
                        qd2.setVisibility(View.GONE);
                        qd5.setVisibility(View.GONE);
                        qd6.setVisibility(View.GONE);
                        qd7.setVisibility(View.GONE);
                        qd8.setVisibility(View.GONE);
                        qd9.setVisibility(View.GONE);
                        qd10.setVisibility(View.GONE);

                        break;
                    case "2":
                        qd3.setVisibility(View.VISIBLE);
                        qd3.setText(String.format(Locale.getDefault(), "%02d", output[0]));
                        qd3.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd2.setVisibility(View.VISIBLE);
                        qd2.setText(String.format(Locale.getDefault(), "%02d", output[1]));
                        qd2.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd4.setVisibility(View.GONE);
                        qd1.setVisibility(View.GONE);
                        qd5.setVisibility(View.GONE);
                        qd6.setVisibility(View.GONE);
                        qd7.setVisibility(View.GONE);
                        qd8.setVisibility(View.GONE);
                        qd9.setVisibility(View.GONE);
                        qd10.setVisibility(View.GONE);


                        break;
                    case "3":
                        qd3.setVisibility(View.VISIBLE);
                        qd3.setText(String.format(Locale.getDefault(), "%02d", output[0]));
                        qd3.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd2.setVisibility(View.VISIBLE);
                        qd2.setText(String.format(Locale.getDefault(), "%02d", output[1]));
                        qd2.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd4.setVisibility(View.VISIBLE);
                        qd4.setText(String.format(Locale.getDefault(), "%02d", output[2]));
                        qd4.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd1.setVisibility(View.GONE);
                        qd5.setVisibility(View.GONE);
                        qd6.setVisibility(View.GONE);
                        qd7.setVisibility(View.GONE);
                        qd8.setVisibility(View.GONE);
                        qd9.setVisibility(View.GONE);
                        qd10.setVisibility(View.GONE);
                        break;
                    case "4":
                        qd3.setVisibility(View.VISIBLE);
                        qd3.setText(String.format(Locale.getDefault(), "%02d", output[0]));
                        qd3.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd2.setVisibility(View.VISIBLE);
                        qd2.setText(String.format(Locale.getDefault(), "%02d", output[1]));
                        qd2.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd4.setVisibility(View.VISIBLE);
                        qd4.setText(String.format(Locale.getDefault(), "%02d", output[2]));
                        qd4.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd1.setVisibility(View.VISIBLE);
                        qd1.setText(String.format(Locale.getDefault(), "%02d", output[3]));
                        qd1.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd5.setVisibility(View.GONE);
                        qd6.setVisibility(View.GONE);
                        qd7.setVisibility(View.GONE);
                        qd8.setVisibility(View.GONE);
                        qd9.setVisibility(View.GONE);
                        qd10.setVisibility(View.GONE);

                        break;
                    case "5":
                        qd3.setVisibility(View.VISIBLE);
                        qd3.setText(String.format(Locale.getDefault(), "%02d", output[0]));
                        qd3.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd2.setVisibility(View.VISIBLE);
                        qd2.setText(String.format(Locale.getDefault(), "%02d", output[1]));
                        qd2.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd4.setVisibility(View.VISIBLE);
                        qd4.setText(String.format(Locale.getDefault(), "%02d", output[2]));
                        qd4.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd1.setVisibility(View.VISIBLE);
                        qd1.setText(String.format(Locale.getDefault(), "%02d", output[3]));
                        qd1.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd5.setVisibility(View.VISIBLE);
                        qd5.setText(String.format(Locale.getDefault(), "%02d", output[4]));
                        qd5.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd6.setVisibility(View.GONE);
                        qd7.setVisibility(View.GONE);
                        qd8.setVisibility(View.GONE);
                        qd9.setVisibility(View.GONE);
                        qd10.setVisibility(View.GONE);

                        break;
                    case "6":
                        qd3.setVisibility(View.VISIBLE);
                        qd2.setVisibility(View.VISIBLE);
                        qd4.setVisibility(View.VISIBLE);
                        qd1.setVisibility(View.VISIBLE);
                        qd5.setVisibility(View.VISIBLE);
                        qd8.setVisibility(View.VISIBLE);
                        qd3.setText(String.format(Locale.getDefault(), "%02d", output[0]));
                        qd3.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd2.setText(String.format(Locale.getDefault(), "%02d", output[1]));
                        qd2.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd4.setText(String.format(Locale.getDefault(), "%02d", output[2]));
                        qd4.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd1.setText(String.format(Locale.getDefault(), "%02d", output[3]));
                        qd1.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd5.setText(String.format(Locale.getDefault(), "%02d", output[4]));
                        qd5.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd8.setText(String.format(Locale.getDefault(), "%02d", output[5]));
                        qd8.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd6.setVisibility(View.GONE);
                        qd7.setVisibility(View.GONE);
                        qd9.setVisibility(View.GONE);
                        qd10.setVisibility(View.GONE);
                        break;
                    case "7":
                        qd3.setVisibility(View.VISIBLE);
                        qd2.setVisibility(View.VISIBLE);
                        qd4.setVisibility(View.VISIBLE);
                        qd1.setVisibility(View.VISIBLE);
                        qd5.setVisibility(View.VISIBLE);
                        qd8.setVisibility(View.VISIBLE);
                        qd7.setVisibility(View.VISIBLE);
                        qd3.setText(String.format(Locale.getDefault(), "%02d", output[0]));
                        qd3.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd2.setText(String.format(Locale.getDefault(), "%02d", output[1]));
                        qd2.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd4.setText(String.format(Locale.getDefault(), "%02d", output[2]));
                        qd4.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd1.setText(String.format(Locale.getDefault(), "%02d", output[3]));
                        qd1.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd5.setText(String.format(Locale.getDefault(), "%02d", output[4]));
                        qd5.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd8.setText(String.format(Locale.getDefault(), "%02d", output[5]));
                        qd8.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd7.setText(String.format(Locale.getDefault(), "%02d", output[6]));
                        qd7.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd6.setVisibility(View.GONE);
                        qd9.setVisibility(View.GONE);
                        qd10.setVisibility(View.GONE);
                        break;
                    case "8":
                        qd3.setVisibility(View.VISIBLE);
                        qd2.setVisibility(View.VISIBLE);
                        qd4.setVisibility(View.VISIBLE);
                        qd1.setVisibility(View.VISIBLE);
                        qd5.setVisibility(View.VISIBLE);
                        qd8.setVisibility(View.VISIBLE);
                        qd7.setVisibility(View.VISIBLE);
                        qd9.setVisibility(View.VISIBLE);

                        qd3.setText(String.format(Locale.getDefault(), "%02d", output[0]));
                        qd3.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd2.setText(String.format(Locale.getDefault(), "%02d", output[1]));
                        qd2.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd4.setText(String.format(Locale.getDefault(), "%02d", output[2]));
                        qd4.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd1.setText(String.format(Locale.getDefault(), "%02d", output[3]));
                        qd1.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd5.setText(String.format(Locale.getDefault(), "%02d", output[4]));
                        qd5.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd8.setText(String.format(Locale.getDefault(), "%02d", output[5]));
                        qd8.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd7.setText(String.format(Locale.getDefault(), "%02d", output[6]));
                        qd7.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd9.setText(String.format(Locale.getDefault(), "%02d", output[7]));
                        qd9.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd6.setVisibility(View.GONE);
                        qd10.setVisibility(View.GONE);
                        break;
                    case "9":
                        qd3.setVisibility(View.VISIBLE);
                        qd2.setVisibility(View.VISIBLE);
                        qd4.setVisibility(View.VISIBLE);
                        qd1.setVisibility(View.VISIBLE);
                        qd5.setVisibility(View.VISIBLE);
                        qd8.setVisibility(View.VISIBLE);
                        qd7.setVisibility(View.VISIBLE);
                        qd6.setVisibility(View.VISIBLE);
                        qd9.setVisibility(View.VISIBLE);
                        qd3.setText(String.format(Locale.getDefault(), "%02d", output[0]));
                        qd3.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd2.setText(String.format(Locale.getDefault(), "%02d", output[1]));
                        qd2.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd4.setText(String.format(Locale.getDefault(), "%02d", output[2]));
                        qd4.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd1.setText(String.format(Locale.getDefault(), "%02d", output[3]));
                        qd1.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd5.setText(String.format(Locale.getDefault(), "%02d", output[4]));
                        qd5.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd8.setText(String.format(Locale.getDefault(), "%02d", output[5]));
                        qd8.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd7.setText(String.format(Locale.getDefault(), "%02d", output[6]));
                        qd7.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd9.setText(String.format(Locale.getDefault(), "%02d", output[7]));
                        qd9.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd6.setText(String.format(Locale.getDefault(), "%02d", output[8]));
                        qd6.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd10.setVisibility(View.GONE);
                        break;
                    case "10":
                        qd3.setVisibility(View.VISIBLE);
                        qd2.setVisibility(View.VISIBLE);
                        qd4.setVisibility(View.VISIBLE);
                        qd1.setVisibility(View.VISIBLE);
                        qd5.setVisibility(View.VISIBLE);
                        qd8.setVisibility(View.VISIBLE);
                        qd7.setVisibility(View.VISIBLE);
                        qd6.setVisibility(View.VISIBLE);
                        qd9.setVisibility(View.VISIBLE);
                        qd10.setVisibility(View.VISIBLE);

                        qd3.setText(String.format(Locale.getDefault(), "%02d", output[0]));
                        qd3.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd2.setText(String.format(Locale.getDefault(), "%02d", output[1]));
                        qd2.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd4.setText(String.format(Locale.getDefault(), "%02d", output[2]));
                        qd4.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd1.setText(String.format(Locale.getDefault(), "%02d", output[3]));
                        qd1.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd5.setText(String.format(Locale.getDefault(), "%02d", output[4]));
                        qd5.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd8.setText(String.format(Locale.getDefault(), "%02d", output[5]));
                        qd8.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd7.setText(String.format(Locale.getDefault(), "%02d", output[6]));
                        qd7.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd9.setText(String.format(Locale.getDefault(), "%02d", output[7]));
                        qd9.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd6.setText(String.format(Locale.getDefault(), "%02d", output[8]));
                        qd6.setBackgroundColor(Color.parseColor("#ffffff"));
                        qd10.setText(String.format(Locale.getDefault(), "%02d", output[9]));
                        qd10.setBackgroundColor(Color.parseColor("#ffffff"));
                        break;
                }

            }
        });


        //for feedback
        Button feedback = findViewById(R.id.feedback);

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thisDialog =  new Dialog(MainActivity.this);
                thisDialog.show();
                thisDialog.setContentView(R.layout.feedback);
                thisDialog.setTitle("Send Your Feedback");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Objects.requireNonNull(thisDialog.getWindow()).setDimAmount(0.5f);
                }
                final EditText editText = thisDialog.findViewById(R.id.editText);
                Button send = thisDialog.findViewById(R.id.send);
                Button cancel = thisDialog.findViewById(R.id.cancel);
                editText.setEnabled(true);
                send.setEnabled(true);
                cancel.setEnabled(true);


                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"Thank you. One more step.",Toast.LENGTH_SHORT).show();
                        thisDialog.cancel();
                        Intent i = new Intent(Intent.ACTION_SENDTO);
                        i.setType("message/rfc822");
                        i.setData(parse("mailto:"));
                        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"thenewtechnoapp@gmail.com"});
                        i.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                        i.putExtra(Intent.EXTRA_TEXT   , editText.getText().toString());
                        try {
                            startActivity(Intent.createChooser(i, "Send mail..."));
                        } catch (ActivityNotFoundException ex) {
                            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        thisDialog.cancel();
                    }
                });
            }
        });


    }

    private void powerClick(){
        p1.setBackgroundColor(Color.parseColor("#ffffff"));
        p2.setBackgroundColor(Color.parseColor("#ffffff"));
        p3.setBackgroundColor(Color.parseColor("#ffffff"));
        p4.setBackgroundColor(Color.parseColor("#ffffff"));
        p5.setBackgroundColor(Color.parseColor("#ffffff"));
        p6.setBackgroundColor(Color.parseColor("#ffffff"));

        HashSet<Integer> set= new HashSet<>();

        for(int i = 0; i<8; i++){
            set.add(r.nextInt(69)+1);
        }
        int j = 0;
        int output[] = new int[set.size()];

        for (Integer aSet : set) {
            output[j] = aSet;
            j++;
        }

        op6 = r.nextInt(26)+1;


        p1.setText(String.format(Locale.getDefault(),"%02d",output[0]));
        p2.setText(String.format(Locale.getDefault(),"%02d",output[1]));
        p3.setText(String.format(Locale.getDefault(),"%02d",output[2]));
        p4.setText(String.format(Locale.getDefault(),"%02d",output[3]));
        p5.setText(String.format(Locale.getDefault(),"%02d",output[4]));
        p6.setText(String.format(Locale.getDefault(),"%02d",op6));
    }

    private void megaClick(){
        m1.setBackgroundColor(Color.parseColor("#ffffff"));
        m2.setBackgroundColor(Color.parseColor("#ffffff"));
        m3.setBackgroundColor(Color.parseColor("#ffffff"));
        m4.setBackgroundColor(Color.parseColor("#ffffff"));
        m5.setBackgroundColor(Color.parseColor("#ffffff"));
        m6.setBackgroundColor(Color.parseColor("#ffffff"));

        HashSet<Integer> set= new HashSet<>();

        for(int i = 0; i<12; i++){
            set.add(r.nextInt(70)+1);
        }
        int j = 0;
        int output[] = new int[set.size()];

        for (Integer aSet : set) {
            output[j] = aSet;
            j++;
        }

        op6 = r.nextInt(25)+1;


        m1.setText(String.format(Locale.getDefault(),"%02d",output[0]));
        m2.setText(String.format(Locale.getDefault(),"%02d",output[1]));
        m3.setText(String.format(Locale.getDefault(),"%02d",output[2]));
        m4.setText(String.format(Locale.getDefault(),"%02d",output[3]));
        m5.setText(String.format(Locale.getDefault(),"%02d",output[4]));
        m6.setText(String.format(Locale.getDefault(),"%02d",op6));
    }

    private void luckyClick(){

        l1.setBackgroundColor(Color.parseColor("#ffffff"));
        l2.setBackgroundColor(Color.parseColor("#ffffff"));
        l3.setBackgroundColor(Color.parseColor("#ffffff"));
        l4.setBackgroundColor(Color.parseColor("#ffffff"));
        l5.setBackgroundColor(Color.parseColor("#ffffff"));
        l6.setBackgroundColor(Color.parseColor("#ffffff"));


        HashSet<Integer> set= new HashSet<>();

        for(int i = 0; i<12; i++){
            set.add(r.nextInt(60)+1);
        }
        int j = 0;
        int output[] = new int[set.size()];

        for (Integer aSet : set) {
            output[j] = aSet;
            j++;
        }

        op6 = r.nextInt(4)+1;
        l1.setText(String.format(Locale.getDefault(),"%02d",output[0]));
        l2.setText(String.format(Locale.getDefault(),"%02d",output[1]));
        l3.setText(String.format(Locale.getDefault(),"%02d",output[2]));
        l4.setText(String.format(Locale.getDefault(),"%02d",output[3]));
        l5.setText(String.format(Locale.getDefault(),"%02d",output[4]));
        l6.setText(String.format(Locale.getDefault(),"%02d",op6));
    }

    private void pick3Click(){
        op1= r.nextInt(999)+1;
        String output = String.format(Locale.getDefault(),"%03d",op1 );
        pk31.setBackgroundColor(Color.parseColor("#ffffff"));
        pk31.setText(output);
    }
    private void pick4Click(){
        op1= r.nextInt(9999)+1;
        String output = String.format(Locale.getDefault(),"%04d",op1 );
        pk41.setBackgroundColor(Color.parseColor("#ffffff"));
        pk41.setText(output);
    }
    private void cash5Click(){
        pk51.setBackgroundColor(Color.parseColor("#ffffff"));
        pk52.setBackgroundColor(Color.parseColor("#ffffff"));
        pk53.setBackgroundColor(Color.parseColor("#ffffff"));
        pk54.setBackgroundColor(Color.parseColor("#ffffff"));
        pk55.setBackgroundColor(Color.parseColor("#ffffff"));

        HashSet<Integer> set= new HashSet<>();

        for(int i = 0; i<10; i++){
            set.add(r.nextInt(39)+1);
        }
        int j = 0;
        int output[] = new int[set.size()];

        for (Integer aSet : set) {
            output[j] = aSet;
            j++;
        }

        pk51.setText(String.format(Locale.getDefault(),"%02d",output[0]));
        pk52.setText(String.format(Locale.getDefault(),"%02d",output[1]));
        pk53.setText(String.format(Locale.getDefault(),"%02d",output[2]));
        pk54.setText(String.format(Locale.getDefault(),"%02d",output[3]));
        pk55.setText(String.format(Locale.getDefault(),"%02d",output[4]));
    }

    private void pick6Click(){
        pk61.setBackgroundColor(Color.parseColor("#ffffff"));
        pk62.setBackgroundColor(Color.parseColor("#ffffff"));
        pk63.setBackgroundColor(Color.parseColor("#ffffff"));
        pk64.setBackgroundColor(Color.parseColor("#ffffff"));
        pk65.setBackgroundColor(Color.parseColor("#ffffff"));
        pk66.setBackgroundColor(Color.parseColor("#ffffff"));


        HashSet<Integer> set= new HashSet<>();

        for(int i = 0; i<8; i++){
            set.add(r.nextInt(49)+1);
        }
        int j = 0;
        int output[] = new int[set.size()];

        for (Integer aSet : set) {
            output[j] = aSet;
            j++;
        }

        pk61.setText(String.format(Locale.getDefault(),"%02d",output[0]));
        pk62.setText(String.format(Locale.getDefault(),"%02d",output[1]));
        pk63.setText(String.format(Locale.getDefault(),"%02d",output[2]));
        pk64.setText(String.format(Locale.getDefault(),"%02d",output[3]));
        pk65.setText(String.format(Locale.getDefault(),"%02d",output[4]));
        pk66.setText(String.format(Locale.getDefault(),"%02d",output[5]));
    }

    private void quickClick(){
        HashSet<Integer> set= new HashSet<>();

        for(int i = 0; i<15; i++){
            set.add(r.nextInt(80)+1);
        }
        int j = 0;
        final int output[] = new int[set.size()];

        for (Integer aSet : set) {
            output[j] = aSet;
            j++;
        }

        mySpinner.setSelection(9);

        qd3.setVisibility(View.VISIBLE);
        qd2.setVisibility(View.VISIBLE);
        qd4.setVisibility(View.VISIBLE);
        qd1.setVisibility(View.VISIBLE);
        qd5.setVisibility(View.VISIBLE);
        qd8.setVisibility(View.VISIBLE);
        qd7.setVisibility(View.VISIBLE);
        qd6.setVisibility(View.VISIBLE);
        qd9.setVisibility(View.VISIBLE);
        qd10.setVisibility(View.VISIBLE);

        qd3.setText(String.format(Locale.getDefault(), "%02d", output[0]));
        qd3.setBackgroundColor(Color.parseColor("#ffffff"));
        qd2.setText(String.format(Locale.getDefault(), "%02d", output[1]));
        qd2.setBackgroundColor(Color.parseColor("#ffffff"));
        qd4.setText(String.format(Locale.getDefault(), "%02d", output[2]));
        qd4.setBackgroundColor(Color.parseColor("#ffffff"));
        qd1.setText(String.format(Locale.getDefault(), "%02d", output[3]));
        qd1.setBackgroundColor(Color.parseColor("#ffffff"));
        qd5.setText(String.format(Locale.getDefault(), "%02d", output[4]));
        qd5.setBackgroundColor(Color.parseColor("#ffffff"));
        qd8.setText(String.format(Locale.getDefault(), "%02d", output[5]));
        qd8.setBackgroundColor(Color.parseColor("#ffffff"));
        qd7.setText(String.format(Locale.getDefault(), "%02d", output[6]));
        qd7.setBackgroundColor(Color.parseColor("#ffffff"));
        qd9.setText(String.format(Locale.getDefault(), "%02d", output[7]));
        qd9.setBackgroundColor(Color.parseColor("#ffffff"));
        qd6.setText(String.format(Locale.getDefault(), "%02d", output[8]));
        qd6.setBackgroundColor(Color.parseColor("#ffffff"));
        qd10.setText(String.format(Locale.getDefault(), "%02d", output[9]));
        qd10.setBackgroundColor(Color.parseColor("#ffffff"));

    }

    public  void pResult(View view){
        Toast.makeText(MainActivity.this,"Powerball Result",Toast.LENGTH_SHORT).show();
        Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.njlottery.com/en-us/drawgames/powerball.html"));
        startActivity(browser);
    }
    public void pClear(View view){
        p1.setBackgroundColor(Color.parseColor("#DAF7A6"));
        p2.setBackgroundColor(Color.parseColor("#DAF7A6"));
        p3.setBackgroundColor(Color.parseColor("#DAF7A6"));
        p4.setBackgroundColor(Color.parseColor("#DAF7A6"));
        p5.setBackgroundColor(Color.parseColor("#DAF7A6"));
        p6.setBackgroundColor(Color.parseColor("#DAF7A6"));
        p1.setText("");
        p2.setText("");
        p3.setText("");
        p4.setText("");
        p5.setText("");
        p6.setText("");
    }
    public void mResult(View view){
        Toast.makeText(MainActivity.this,"Mega Millions Result",Toast.LENGTH_SHORT).show();
        Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.njlottery.com/en-us/drawgames/megamillions.html"));
        startActivity(browser);
    }
    public void mClear(View view){

        m1.setBackgroundColor(Color.parseColor("#DAF7A6"));
        m2.setBackgroundColor(Color.parseColor("#DAF7A6"));
        m3.setBackgroundColor(Color.parseColor("#DAF7A6"));
        m4.setBackgroundColor(Color.parseColor("#DAF7A6"));
        m5.setBackgroundColor(Color.parseColor("#DAF7A6"));
        m6.setBackgroundColor(Color.parseColor("#DAF7A6"));
        m1.setText("");
        m2.setText("");
        m3.setText("");
        m4.setText("");
        m5.setText("");
        m6.setText("");
    }
    public void lResult(View view){
        Toast.makeText(MainActivity.this,"Cash 4 Life Result",Toast.LENGTH_SHORT).show();
        Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.njlottery.com/en-us/drawgames/cash4life.html"));
        startActivity(browser);
    }
    public void lClear(View view){

        l1.setBackgroundColor(Color.parseColor("#DAF7A6"));
        l2.setBackgroundColor(Color.parseColor("#DAF7A6"));
        l3.setBackgroundColor(Color.parseColor("#DAF7A6"));
        l4.setBackgroundColor(Color.parseColor("#DAF7A6"));
        l5.setBackgroundColor(Color.parseColor("#DAF7A6"));
        l6.setBackgroundColor(Color.parseColor("#DAF7A6"));
        l1.setText("");
        l2.setText("");
        l3.setText("");
        l4.setText("");
        l5.setText("");
        l6.setText("");
    }
    public void pk3Result(View view){
        Toast.makeText(MainActivity.this,"Pick 3 Result",Toast.LENGTH_SHORT).show();
        Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.njlottery.com/en-us/drawgames/pick3.html"));
        startActivity(browser);
    }
    public void pk3Clear(View view)
    {
        pk31.setBackgroundColor(Color.parseColor("#DAF7A6"));
        pk31.setText("");
    }
    public void pk4Result(View view){
        Toast.makeText(MainActivity.this,"Pick 4 Result",Toast.LENGTH_SHORT).show();
        Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.njlottery.com/en-us/drawgames/pick4.html"));
        startActivity(browser);
    }
    public void pk4Clear(View view){
        pk41.setBackgroundColor(Color.parseColor("#DAF7A6"));
        pk41.setText("");
    }
    public void pk5Result(View view){
        Toast.makeText(MainActivity.this,"Jersey Cash 5 Result",Toast.LENGTH_SHORT).show();
        Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.njlottery.com/en-us/drawgames/jerseycash.html"));
        startActivity(browser);
    }
    public void pk5Clear(View view){

        pk51.setBackgroundColor(Color.parseColor("#DAF7A6"));
        pk52.setBackgroundColor(Color.parseColor("#DAF7A6"));
        pk53.setBackgroundColor(Color.parseColor("#DAF7A6"));
        pk54.setBackgroundColor(Color.parseColor("#DAF7A6"));
        pk55.setBackgroundColor(Color.parseColor("#DAF7A6"));
        pk51.setText("");
        pk52.setText("");
        pk53.setText("");
        pk54.setText("");
        pk55.setText("");
    }
    public void pk6Result(View view){
        Toast.makeText(MainActivity.this,"Pick 6 Result",Toast.LENGTH_SHORT).show();
        Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.njlottery.com/en-us/drawgames/pick6lotto.html"));
        startActivity(browser);
    }
    public void pk6Clear(View view){

        pk61.setBackgroundColor(Color.parseColor("#DAF7A6"));
        pk62.setBackgroundColor(Color.parseColor("#DAF7A6"));
        pk63.setBackgroundColor(Color.parseColor("#DAF7A6"));
        pk64.setBackgroundColor(Color.parseColor("#DAF7A6"));
        pk65.setBackgroundColor(Color.parseColor("#DAF7A6"));
        pk66.setBackgroundColor(Color.parseColor("#DAF7A6"));
        pk61.setText("");
        pk62.setText("");
        pk63.setText("");
        pk64.setText("");
        pk65.setText("");
        pk66.setText("");
    }

    public  void qdResult(View view){
        Toast.makeText(MainActivity.this,"QuickDraw Result",Toast.LENGTH_SHORT).show();
        Intent browser = new Intent(Intent.ACTION_VIEW, parse("https://www.njlottery.com/en-us/drawgames/quickDraw.html"));
        startActivity(browser);
    }
    public void qdClear(View view){

        qd1.setBackgroundColor(Color.parseColor("#DAF7A6"));
        qd2.setBackgroundColor(Color.parseColor("#DAF7A6"));
        qd3.setBackgroundColor(Color.parseColor("#DAF7A6"));
        qd4.setBackgroundColor(Color.parseColor("#DAF7A6"));
        qd5.setBackgroundColor(Color.parseColor("#DAF7A6"));
        qd6.setBackgroundColor(Color.parseColor("#DAF7A6"));
        qd7.setBackgroundColor(Color.parseColor("#DAF7A6"));
        qd8.setBackgroundColor(Color.parseColor("#DAF7A6"));
        qd9.setBackgroundColor(Color.parseColor("#DAF7A6"));
        qd10.setBackgroundColor(Color.parseColor("#DAF7A6"));

        qd1.setText("");
        qd2.setText("");
        qd3.setText("");
        qd4.setText("");
        qd5.setText("");
        qd6.setText("");
        qd7.setText("");
        qd8.setText("");
        qd9.setText("");
        qd10.setText("");

    }
    //Analysis of every game
    public void showPower(View view){
        Intent intent = new Intent(this, Main3Activity.class);

        intent.putExtra(IMAGE_RES_ID_KEY,R.drawable.powerchart);
        intent.putExtra(IMAGE_RES_ID_KEY1,R.drawable.powernumchart);
        intent.putExtra(IMG_NAME,"White Ball Number");
        intent.putExtra(IMG_NAME1,"Power Ball Number");

        startActivity(intent);
    }

    public void showMega(View view){
        Intent intent = new Intent(this,Main3Activity.class);

        intent.putExtra(IMAGE_RES_ID_KEY,R.drawable.megachart);
        intent.putExtra(IMAGE_RES_ID_KEY1,R.drawable.megaballchart);
        intent.putExtra(IMG_NAME,"White Ball Number");
        intent.putExtra(IMG_NAME1,"Mega Ball Number");

        startActivity(intent);

    }

    public void showCash(View view){
        Intent intent = new Intent(this,Main3Activity.class);

        intent.putExtra(IMAGE_RES_ID_KEY,R.drawable.cashchart);
        intent.putExtra(IMAGE_RES_ID_KEY1,R.drawable.cashball);
        intent.putExtra(IMG_NAME,"White Ball Number");
        intent.putExtra(IMG_NAME1,"Cash Ball Number");

        startActivity(intent);

    }

    public void showPick6(View view){
        Intent intent = new Intent(this,Main3Activity.class);

        intent.putExtra(IMAGE_RES_ID_KEY,R.drawable.pick6chart);

        startActivity(intent);
    }

    public void showCash5(View view){
        Intent intent = new Intent(this,Main3Activity.class);
        intent.putExtra(IMAGE_RES_ID_KEY,R.drawable.cash5chart);
        startActivity(intent);
    }

    public void showPick3(View view){
        Intent intent = new Intent(this,Main3Activity.class);
        intent.putExtra(IMAGE_RES_ID_KEY,R.drawable.pk3mid);
        intent.putExtra(IMAGE_RES_ID_KEY1,R.drawable.pk3eve);
        intent.putExtra(IMG_NAME,"MidDay");
        intent.putExtra(IMG_NAME1,"Evening");

        startActivity(intent);
    }

    public void showPick4(View view){
        Intent intent = new Intent(this,Main3Activity.class);
        intent.putExtra(IMAGE_RES_ID_KEY,R.drawable.pick4mid);
        intent.putExtra(IMAGE_RES_ID_KEY1,R.drawable.pick4eve);
        intent.putExtra(IMG_NAME,"MidDay");
        intent.putExtra(IMG_NAME1,"Evening");

        startActivity(intent);
    }

    public void showQuick(View view){
        Intent intent = new Intent(this,Main3Activity.class);
        intent.putExtra(IMAGE_RES_ID_KEY1,R.drawable.qdtips);
        intent.putExtra(IMG_NAME,"Hot/Cold Numbers");

        startActivity(intent);
    }

    public void showAddict(View view){
        Intent intent = new Intent(this, Main3Activity.class);
        intent.putExtra(IMAGE_RES_ID_KEY,R.drawable.noaddict);
        startActivity(intent);
    }

}
