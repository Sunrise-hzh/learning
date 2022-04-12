package zm01;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author huangzihua
 * @date 2021-03-30
 */
public class TestWaitAndNotify {

    public static void main(String[] args) {
        List<String> queue = new ArrayList<>();
        ProducerThread producer = new ProducerThread(queue);
        ConsumerThread consumer = new ConsumerThread(queue);
        producer.start();
        consumer.start();

    }

    public static class ProducerThread extends Thread {
        private List<String> queue;
        public ProducerThread(List<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 10) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.add("apple");
                    System.out.println("producer " + queue);
                    queue.notifyAll();
                }
            }

        }
    }

    public static class ConsumerThread extends Thread {
        private List<String> queue;
        public ConsumerThread(List<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.remove(queue.size() - 1);
                    System.out.println("consumer " + queue);
                    queue.notifyAll();
                }
            }
        }
    }





}
