package xyz.digitaltools.boundbinderservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

    LocalBinder localBinder = new LocalBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;

    }

    public class LocalBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }


    public void showTost(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
