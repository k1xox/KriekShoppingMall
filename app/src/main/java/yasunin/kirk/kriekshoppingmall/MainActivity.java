package yasunin.kirk.kriekshoppingmall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yasunin.kirk.kriekshoppingmall.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ( savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentMainFragment,new MainFragment()).commit();
        }


    }//Main Mehod
}//Main Class
