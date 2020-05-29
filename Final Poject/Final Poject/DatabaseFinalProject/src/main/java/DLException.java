import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/*
    Class DLException - manages exceptions
 */
public class DLException extends Exception {
    //attributes
    private Map<String, String> info;
    private Exception exception;
    private static final SimpleDateFormat time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /*
        Constructor #1
     */
    public DLException(Exception e) {
        this.exception = exception;
        this.info = new HashMap<String, String>();
        info.put("Description: ", "Operation could not be completed. Please contact the administrator.");
    }

    //constructor #2
    public DLException(Exception exception, Map<String, String> info) {
        this.exception = exception;
        this.info = info;
    }

    /*
        Method log locates the textlogfile.txt and writes exceptions into it
     */
    public void log() {
        File newFile = new File("src/textlogfile.txt");
        boolean exists = false;
        try {
            if (newFile.exists()) {
                exists = true;
            }
        } catch (SecurityException e) {
            System.out.println("Log file cannot be opened.");
        }
        try {
            PrintWriter print = new PrintWriter(new FileWriter(newFile, exists));
            String log = this.createLog();
            System.out.println(log);
            print.append(log);
            print.flush();
            print.close();

        } catch (IOException e) {
            System.out.println("Log file cannot be opened.");
        }

    }

    /*
        Method createLog creates log messages to be printed into textLogFile.txt
     */
    private String createLog() {
        String log = "Time: " + time.format(new Timestamp(System.currentTimeMillis()));
        for (String key : info.keySet()) {
            log += "\r\n\t" + key + ": " + info.get(key);
        }
        log += "\r\n\r\n";
        return log;
    }


}
