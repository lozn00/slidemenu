package space.qssq.dragdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class VerticalSrollConfictActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_sroll_confict);
        AutoUtils.initTestHorzontalData((RecyclerView) findViewById(R.id.recycler_view));
    }
}
