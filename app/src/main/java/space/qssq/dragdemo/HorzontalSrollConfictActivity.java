package space.qssq.dragdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

public class HorzontalSrollConfictActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horzontal_sroll_confict);
        AutoUtils.initTestVerticalData((RecyclerView) findViewById(R.id.recycler_view));
    }
}
