package Forum;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Comment {
    String _username;
    String _content;
    String _date;
    DateFormat df;

    public Comment(String username, String date, String content) {
        _username = username;
        _content = content;
        _date = date;
    }

    public Comment(String username, String content) {
        df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        _username = username;
        _content = content;
        _date = df.format(Calendar.getInstance().getTime());
    }

    public String getUsername() {
        return _username;
    }

    public String getContent() {
        return _content;
    }

    public String getDate() {
        return _date;
    }

    public void setContent(String content) {
        _content = content;
    }

    @Override
    public String toString() {
        return "Comment by <strong>" + _username + "</strong> on " + _date + "<br />" + _content;
    }
}