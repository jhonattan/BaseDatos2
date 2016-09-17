package com.example.android.basedatos2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // objetos
    private DataBaseManager manager;

    private Button btn_qry;
    private Button btn_ins;
    private Button btn_upd;
    private Button btn_del;
    private TextView query;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // mapeo
        manager = new DataBaseManager(this);

        btn_qry = (Button) findViewById(R.id.button);
        btn_ins = (Button) findViewById(R.id.button2);
        btn_upd = (Button) findViewById(R.id.button3);
        btn_del = (Button) findViewById(R.id.button4);
        query = (TextView) findViewById(R.id.textView);

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

    // eventos
    public void frasesQry(View view) {
        String qry = manager.frasesQry();
        query.setText(qry);
    }

    public void frasesIns(View view) {
        manager.frasesIns(1, "no siempre 1 + 1 es 2");
        query.setText("INS OK...");
    }

    public void frasesUpd(View view) {
        manager.frasesUpd(1, "algo...", 4);
        query.setText("UPD OK...");
    }

    public void frasesDel(View view) {
        manager.frasesDel(4);
        query.setText("DEL OK...");
    }

}
