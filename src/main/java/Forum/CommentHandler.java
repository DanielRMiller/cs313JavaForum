package Forum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class CommentHandler implements Runnable {
    volatile File _commentFile;
    volatile Boolean _stop;

    public CommentHandler(File file) {
        _commentFile = file;
        _stop = false;
    }

    synchronized public List<Comment> getComments() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(_commentFile))) {
            List<Comment> comments = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] comment = line.split("~");
                comments.add(new Comment(comment[0], comment[1], comment[2]));
            }
            return comments;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    synchronized public Boolean saveComment(String username, String content) {
        Boolean result = false;
        Comment comment = new Comment(username, content);
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(_commentFile, true)))) {
            out.println(comment.getUsername() + "~" + comment.getDate() + "~" + comment.getContent());
            result = true;
        } catch (IOException e) {
            result = false;
        }
        return result;
    }

    @Override
    public void run() {
        while (!_stop) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(CommentHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void stop() {
        _stop = true;
    }
}