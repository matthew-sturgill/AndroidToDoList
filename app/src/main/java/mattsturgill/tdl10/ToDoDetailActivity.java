package mattsturgill.tdl10;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by matthewsturgill on 10/27/16.
 */

public class ToDoDetailActivity extends AppCompatActivity {
    private EditText Title;
  //  private EditCategory Category;
    private EditText Text;
    private Button saveButton;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_to_do_item_page);

        Title = (EditText) findViewById(R.id.td_title);
        Text = (EditText) findViewById(R.id.td_text);


        Intent intent = getIntent();

        Title.setText(intent.getStringExtra("Title"));
        Text.setText(intent.getStringExtra("Text"));
        index = intent.getIntExtra("Index", -1);

        final Button saveButton = (Button) findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                intent.putExtra("Title", Title.getText().toString());
                intent.putExtra("Text", Text.getText().toString());
                intent.putExtra("Index", index);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}


