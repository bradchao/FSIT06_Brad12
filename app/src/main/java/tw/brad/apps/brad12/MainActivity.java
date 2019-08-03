package tw.brad.apps.brad12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("config", MODE_PRIVATE);
        editor = sp.edit();

    }

    // output data
    public void test1(View view) {
        editor.putString("name", "Brad");
        editor.putInt("stage", 4);
        editor.putBoolean("sound", true);
        editor.commit();
        Toast.makeText(this, "Test1 OK", Toast.LENGTH_SHORT).show();
    }
    public void test2(View view){
        int stage = sp.getInt("stage",-1);
        String name = sp.getString("name","nobody");
        boolean sound = sp.getBoolean("sound", false);

        Log.v("brad", name + ":" + stage + ":" + sound);

    }

    public void test3(View view) {
        try {
            FileOutputStream fout = openFileOutput("file1.txt", MODE_PRIVATE);
            fout.write("Hello, World".getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(this, "Save OK", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.v("brad", e.toString());
        }
    }

    public void test4(View view) {
        try {
            FileInputStream fin = openFileInput("file1.txt");
            int len; byte[] buf = new byte[4096];
            while ( (len = fin.read(buf)) != -1){
                Log.v("brad", new String(buf,0,len));
            }
            fin.close();
        }catch (Exception e){

        }

    }
}
