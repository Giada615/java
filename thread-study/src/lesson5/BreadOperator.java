package lesson5;

/**
 * @ClassName $ {HJY}
 * @Description TODO
 * @Author $ {USER}
 * @Date $ {DATE} 11:15
 * @Version 1.8
 **/
public class BreadOperator {
    // 库存面包数量：上限100，下限0
    public  static volatile int SUM;

    public static void main(String[] args) {
        // 启动生产者线程，生产面包
        for (int i = 0; i <5; i++) {
            new Thread(new Producer(),"面包师傅"+i).start();
        }
        // 启动消费者线程，消费面包
        for (int i = 0; i <20; i++) {
            new Thread(new Consumer(),"消费者"+i).start();
        }
    }

    // 默认一个生产者：面包师傅生产面包，一次生产3个面包。每个面包师傅生产20次
    private static class Producer implements Runnable{

        @Override
        public void run() {
            try {
                for (int i = 0; i <5; i++) {
                    synchronized (BreadOperator.class){
                     while (SUM + 3 > 100){  // 生产完以后，库存>100是不行，所以库存在97以上不能生产
                         // 释放对象锁，唤醒其他线程，让其他线程进入同步代码块，当前线程进行阻塞状态
                         BreadOperator.class.wait();  //阻塞在该行代码
                     }
                     SUM += 3; // 生产面包
                      Thread.sleep(100);
                      BreadOperator.class.notify();
                      System.out.println(Thread.currentThread().getName()+",生产了,库存为："+SUM);
                  }
                  Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    // 默认消费者：消费面包，一次消费一个。消费者一直消费
    private static class Consumer implements Runnable{

        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (BreadOperator.class){
                      // 库存为0，不能消费，阻塞当前线程不能消费
                      while (SUM == 0){
                        BreadOperator.class.wait();
                      }
                      SUM--;
                      Thread.sleep(100);
                      BreadOperator.class.notify();  // 通知
                      // notify()和notifyAll()都是通知调用wait()被阻塞的线程。
                      // notify()是随机唤醒一个wait()阻塞的线程，notifyAll()是唤醒全部
                      // 在synchronized代码结束，也就是释放对象锁之后，才会唤醒
                      // 等于说，synchronized结束以后，wait()和synchronized代码行阻塞的线程，都会被唤醒
                      System.out.println(Thread.currentThread().getName()+",消费了,库存为："+SUM);
                      Thread.sleep(100);
                  }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
