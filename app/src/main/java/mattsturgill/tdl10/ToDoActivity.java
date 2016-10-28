package mattsturgill.tdl10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


/**
 * Created by matthewsturgill on 10/27/16.
 */

public class ToDoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_to_do);

        final Button button1 = (Button) findViewById(R.id.addToDoButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.add_to_do_item_page);
            }
        });

        final Button button2 = (Button) findViewById(R.id.workList);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                      setContentView(R.layout.list_item_to_do);
            }
        });

        final Button button3 = (Button) findViewById(R.id.personalList);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        setContentView(R.layout.list_item_to_do);
            }
        });

        final Button button4 = (Button) findViewById(R.id.groceryList);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                      setContentView(R.layout.list_item_to_do);
            }
        });

    }


}
