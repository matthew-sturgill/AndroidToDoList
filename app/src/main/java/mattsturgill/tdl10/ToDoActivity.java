package mattsturgill.tdl10;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


/**
 * Created by matthewsturgill on 10/27/16.
 */

public class ToDoActivity extends AppCompatActivity {
    private ListView tdList;
    private ToDoArrayAdapter tdArrayAdapter;
    private ArrayList<ToDoItem> tdArray;
    private SharedPreferences tdPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        tdPrefs = getPreferences(Context.MODE_PRIVATE);
        setupItem();

        Collections.sort(tdArray);
        tdList = (ListView) findViewById(R.id.listView);

        tdArrayAdapter = new ToDoArrayAdapter(this, R.layout.list_item_to_do, tdArray);
        tdList.setAdapter(tdArrayAdapter);
        tdList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // if the element is clicked create the intent
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToDoItem item = tdArray.get(position);
                Intent intent = new Intent(ToDoActivity.this, ToDoDetailActivity.class);
                intent.putExtra("Title", item.getTitle());
                intent.putExtra("Text", item.getText());
           //     intent.putExtra("Category", item.getCategory());
                intent.putExtra("Index", position);

                startActivityForResult(intent, 1);
            }
        });

        tdList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            //what to do if the view gets a long click
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ToDoActivity.this);
                alertBuilder.setTitle("Delete");
                alertBuilder.setMessage("Are you sure?");
                alertBuilder.setNegativeButton("Cancel", null);
                alertBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToDoItem note = tdArray.get(position);
                        deleteFile("##" + note.getTitle());
                        tdArray.remove(position);
                        tdArrayAdapter.updateAdapter(tdArray);
                    }
                });
                alertBuilder.create().show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            int index = data.getIntExtra("Index", -1);

            ToDoItem item = new ToDoItem (data.getStringExtra("Title"),
                    data.getStringExtra("Text"), new Date());
            if (index == -1) {
                tdArray.add(item);
            } else {
                ToDoItem oldItem = tdArray.get(index);
                tdArray.set(index, item);
                //  writeFile(note);
                if (!oldItem.getTitle().equals(item.getTitle())) {
                    File oldFile = new File(this.getFilesDir(), "##" + oldItem.getTitle());
                    File newFile = new File(this.getFilesDir(), "##" + item.getTitle());
                    oldFile.renameTo(newFile);
                }
            }
            Collections.sort(tdArray);
            tdArrayAdapter.updateAdapter(tdArray);
        }
    }

    private void setupItem() {
        tdArray = new ArrayList<>();
        if (tdPrefs.getBoolean("firstRun", true)) {
            SharedPreferences.Editor editor = tdPrefs.edit();
            editor.putBoolean("firstRun", false);
            editor.apply();

            ToDoItem item1 = new ToDoItem("Item 1", "", new Date());
            tdArray.add(item1);
            tdArray.add(new ToDoItem("Item 2", "", new Date()));
            tdArray.add(new ToDoItem("Item 3", "", new Date()));

            for (ToDoItem item : tdArray) {

                writeFile(item);
            }
        } else {
            File[] filesDir = this.getFilesDir().listFiles();
            for (File file : filesDir) {
                FileInputStream inputStream = null;
                String title = file.getName();
                if (!title.startsWith("##")) {
                    continue;
                } else {
                    title = title.substring(2, title.length());
                }
                Date date = new Date(file.lastModified());
                String text = "";
                try {
                    inputStream = openFileInput("##" + title);
                    byte[] input = new byte[inputStream.available()];
                    while (inputStream.read(input) != -1) {
                    }
                    text += new String(input);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {

                        inputStream.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                tdArray.add(new ToDoItem(title, text, date));
            }
        }
    }

    private void writeFile(ToDoItem item) {
        FileOutputStream outputStream = null;
        try {
            outputStream = openFileOutput("##" + item.getTitle(), Context.MODE_PRIVATE);
            outputStream.write(item.getText().getBytes());
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException ioe) {
            } catch (NullPointerException npe) {
            } catch (Exception e) {
            }
        }
    }
    //menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_to_do, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(ToDoActivity.this, ToDoDetailActivity.class);
            intent.putExtra("Title", "");
            intent.putExtra("Text", "");
            startActivityForResult(intent, 1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}