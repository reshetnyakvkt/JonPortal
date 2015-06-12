package ua.com.jon.cabinet.server;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import javax.mail.Message;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 11.06.15
 */
@Service("DeferredService")
public class DeferredResultService implements Runnable {
    private static final Logger logger = Logger.getLogger(DeferredResultService.class);

    private final BlockingQueue<DeferredResult<String>> resultQueue = new LinkedBlockingQueue<>();
    private int counter = 20;
    private Thread thread;

    private volatile boolean start = true;

    private LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    public DeferredResultService() {
        subscribe();
    }

    public void subscribe() {
        logger.info("Starting server");
        startThread();
    }

    private void startThread() {

        if (start) {
            synchronized (this) {
                if (start) {
                    start = false;
                    thread = new Thread(this, "Studio Teletype");
                    thread.start();
                }
            }
        }
    }

    @Override
    public void run() {

        while (true) {
            try {

                DeferredResult<String> result = resultQueue.take();
//                Message message = queue.take();
                Thread.sleep(5000);
                result.setResult(counter++ + "\nres");

            } catch (InterruptedException e) {
                throw new RuntimeException("Cannot get latest update. " + e.getMessage(), e);
            }
        }
    }

    public void getUpdate(DeferredResult<String> result) {
        resultQueue.add(result);
    }

}


