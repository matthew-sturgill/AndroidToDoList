package mattsturgill.tdl10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;

public class ToDoItem implements Comparable<ToDoItem>{
    private String title;
    private String text;
    private Date date;

    public ToDoItem(String title, String text, Date date) {
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(ToDoItem another) {
        return getDate().compareTo(another.getDate());
    }
}
