package mattsturgill.tdl10;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by matthewsturgill on 10/27/16.
 */

public class ToDoDetailActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText tdTitle;
    private EditText tdText;
    private Button saveButton;
    private int index;
    Spinner spinner;
    String[] spinnerChoices = {"Work", "Personal", "Grocery"};

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_to_do_item_page);

        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerChoices);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(categoryAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tdTitle = (EditText) findViewById(R.id.td_title);
        tdText = (EditText) findViewById(R.id.td_text);

        Intent intent = getIntent();

        tdTitle.setText(intent.getStringExtra("Title"));
        tdText.setText(intent.getStringExtra("Text"));
        index = intent.getIntExtra("Index", -1);

        final Button saveButton = (Button) findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                intent.putExtra("Title", tdTitle.getText().toString());
                intent.putExtra("Text", tdText.getText().toString());
                intent.putExtra("Index", index);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    //Methods for spinner, implemented by studio.
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView categoryTest = (TextView) view;
        Toast.makeText(this, categoryTest.getText(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ToDoDetail Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    //End of methods for spinner.
}


