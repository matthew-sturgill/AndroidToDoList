package mattsturgill.tdl10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by matthewsturgill on 10/27/16.
 */

public class ToDoArrayAdapter extends ArrayAdapter<ToDoItem>{
    private int resource;
    private ArrayList<ToDoItem> items;
    private LayoutInflater inflater;
    private SimpleDateFormat formatter;

    public ToDoArrayAdapter(Context context, int resource, ArrayList<ToDoItem> objects)
    {
        super(context, resource, objects);
        this.resource = resource;
        this.items = objects;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        formatter = new SimpleDateFormat("MM/dd/yyyy");

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ItemRow = inflater.inflate(resource, parent, false);

        TextView noteTitle = (TextView)ItemRow.findViewById(R.id.td_title);
        TextView noteText = (TextView)ItemRow.findViewById(R.id.td_text);
        TextView noteDate = (TextView)ItemRow.findViewById(R.id.td_date);

        ToDoItem tdItem = items.get(position);

        noteTitle.setText(tdItem.getTitle());
        noteText.setText(tdItem.getText());
        noteDate.setText(formatter.format(tdItem.getDate()));

        return ItemRow;
    }
    public void updateAdapter(ArrayList<ToDoItem> toDoItems){
        this.items = toDoItems;
        super.notifyDataSetChanged();
    }
}

