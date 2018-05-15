package xyz.digitaltools.boundbinderservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button;
    boolean ok;
    MyService myService;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ok = true;
            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            myService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ok = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(MainActivity.this,MyService.class),connection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ok){
                    myService.showTost("sagor");
                }


            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
