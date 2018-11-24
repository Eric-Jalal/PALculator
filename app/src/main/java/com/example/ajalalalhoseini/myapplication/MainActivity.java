package com.example.ajalalalhoseini.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity {

    private Evaluate eval = new Evaluate();
    @BindView(R.id.button0)
    Button bt0;
    @BindView(R.id.button1)
    Button bt1;
    @BindView(R.id.button2)
    Button bt2;
    @BindView(R.id.button3)
    Button bt3;
    @BindView(R.id.button4)
    Button bt4;
    @BindView(R.id.button5)
    Button bt5;
    @BindView(R.id.button6)
    Button bt6;
    @BindView(R.id.button7)
    Button bt7;
    @BindView(R.id.button8)
    Button bt8;
    @BindView(R.id.button9)
    Button bt9;
    @BindView(R.id.buttonDivision)
    Button btDi;
    @BindView(R.id.buttonDot)
    ImageButton btSpace;
    @BindView(R.id.buttonBackspace)
    ImageButton btBackspace;
    @BindView(R.id.buttonMultiply)
    Button btM;
    @BindView(R.id.buttonPlus)
    Button btP;
    @BindView(R.id.buttonSubtract)
    Button btS;
    @BindView(R.id.buttonEqual)
    Button btEqual;
    @BindView(R.id.tcExp)
    TextView tv;
    @BindView(R.id.tvResult)
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Roboto-Thin.ttf").setFontAttrId(R.attr.fontPath).build()); // Declaring the globalized font face
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        //setSupportActionBar(myToolbar);

        eval.addOperator(new Plus(2));
        eval.addOperator(new Minus(2));
        eval.addOperator(new Mult(2));
        eval.addOperator(new Divid(2));
        final DatabaseHandler db = new DatabaseHandler(this);
        db.dbCreator();

        btM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(tv.getText() + "×");
            }
        });
        btDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(tv.getText() + "/");
            }
        });
        btP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(tv.getText() + "+");
            }
        });
        btS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(tv.getText() + "-");
            }
        });
        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(tv.getText() + "0");
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(tv.getText() + "1");
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(tv.getText() + "2");
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(tv.getText() + "3");
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(tv.getText() + "4");
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(tv.getText() + "5");
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(tv.getText() + "6");
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(tv.getText() + "7");
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(tv.getText() + "8");
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(tv.getText() + "9");
            }
        });
        btSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(tv.getText() + " ");
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("HISTORY_SELECTED_INFO");
            tv.setText(value);
        }

        btBackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = tv.getText().toString();
                if (str.length() > 1) {
                    str = str.substring(0, str.length() - 1);
                    tv.setText(str);
                } else if (str.length() <= 1) {
                    tv.setText("");
                }
            }
        });

        btBackspace.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tv.setText("");
                tvResult.setText("");
                return true;
            }
        });

        btEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = eval.expAnalyzer(tv.getText().toString());
                tvResult.setText( result + " ");
                ///////
                //Log.d("Insert", "Inserting...");
                db.addRecord(new HistoryDefinition(tv.getText().toString(), Integer.toString(result)));
                ///////
                /*Log.d("Insert", "Fetch Inserted");
                List<HistoryDefinition> historyList = db.getAllHistory();
                for (HistoryDefinition hi : historyList) {
                    String log = "Id: "+ hi.getId() +" ,Statement: " + hi.getStatement() + " Result " + result;
                    // Writing Contacts to log
                    Log.d("Name: ", log);
                }*/
                //Status st = new Status();
                if(eval.getSt()){
                    Toast.makeText(getApplicationContext(), "Evaluated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong notation", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    } // inject the override context of font face
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_share:
                Log.d("share bt pressed", "1");
                String shareBody = "Statement: " + tv.getText() + " = " + tvResult.getText();
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "PrefixPostfix Calculator");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, shareBody));
                return true;
            case R.id.action_history:
                Intent historyIntent=new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(historyIntent);
                return true;
            case R.id.action_help:
                Intent helpIntent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(helpIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
