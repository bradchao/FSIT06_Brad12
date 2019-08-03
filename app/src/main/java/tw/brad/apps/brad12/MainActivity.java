package tw.brad.apps.brad12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private File sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    87);
        }else{
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            init();
        }else{
            finish();
        }
    }

    private void init(){
        sp = getSharedPreferences("config", MODE_PRIVATE);
        editor = sp.edit();
        sd = Environment.getExternalStorageDirectory();
        Log.v("brad", sd.getAbsolutePath());

        File[] files = sd.listFiles();
        for (File file : files){
            Log.v("brad", file.getAbsolutePath());
        }


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

    public void test5(View view) {
    }

    public void test6(View view) {
    }
}
