// 当项目是library的时候被忽略
package io.yghysdr.login.debug;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.yghysdr.login.R;

public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
    }
}
