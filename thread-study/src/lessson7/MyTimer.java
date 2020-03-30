package lessson7;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * @ClassName $ {HJY}
 * @Description TODO
 * @Author $ {USER}
 * @Date $ {DATE} 22:59
 * @Version 1.8
 **/
/*
定时器：
1. 在约定好的时间点上，执行某个任务
2. 间隔时间到了，不停的执行任务
 */
public class MyTimer {


    private BlockingQueue<MyTimerTask> queue =new PriorityBlockingQueue();
    private long next;

    // 使用一个构造方法
    public MyTimer(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                // 可优化
                try {
                    while(true){
                        // BlockingQueue本来就是线程安全的，所以这里的方法调用，不要放在同步代码块中
                        MyTimerTask task=queue.take();
                        //加锁操作
                        synchronized(queue){
                            long current=System.currentTimeMillis();
                            if (task.next > System.currentTimeMillis()){
                                queue.wait(task.next-current);
                                queue.put(task);
                            }else {
                                task.task.run();
                                if (task.period>0){
                                    task.next=task.next+task.period;
                                    queue.put(task);
                                }
                            }
                        }

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     *
     * @param task  需要执行的任务
     * @param delay 从当前时间延迟多少毫秒，执行任务
     * @param period 间隔时间：<=0 就忽略掉，>0 需要每间隔给定时间去执行任务>
     */
    public void schedule(Runnable task,long delay,long period){
        try {
            queue.put(new MyTimerTask(task,System.currentTimeMillis()+delay,period));
            synchronized(queue){
                queue.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//    long current = System.currentTimeMillis();
//        try {
//            Thread.sleep(delay);
//            new Thread(task).start();
//            while (period>0){
//                Thread.sleep(period);
//                new Thread(task).start();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//         }


//        while (true){
//            long next = System.currentTimeMillis();
//            // 当前时间next超过约定好的执行时间current+delay，执行任务
//            if (current + delay <= next){
//                new Thread(task).start();
//
//                if (period <= 0){
//                    break;
//
//                }else {
//
//                }
//            }
//
//        }
    }

    // 工作线程类
    private static class Myworker implements Runnable{

        @Override
        public void run() {

        }
    }

    private static class MyTimerTask implements Runnable,Comparable<MyTimer>{

        // 定时任务
        private Runnable task;
        // 下次时间
        private long next;
        private long period;
        public MyTimerTask(Runnable task,long next,long period){
            this.task=task;
            this.next=next;
            this.period=period;
        }
        @Override
        public void run() {
        }

        @Override
        public int compareTo(MyTimer o) {
            return Long.compare(next,o.next);
        }
    }
    // jdk的时间操作
    public static void main(String[] args) {
       // Date的学习
        Date date1 = new Date(); // 无参的构造方法：返回系统当前时间
        Date date2 = new Date(99999999); // 以格林威治时间1970-01-01开始，经过给定时间数量的毫秒
        // DateFormat
        DateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm：ss");
//        System.out.println(df.format(date1));
//        System.out.println(df.format(date2));

        // ①System时间获取:从1970-01 -01开始到当前时间点经过的毫秒数
        long current = System.currentTimeMillis();
//
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("起床了");
//            }
//        };
//        new Timer().schedule(task,3000,1000);


//     ②   new MyTimer().schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("起床了");
//            }
//        },3000,1000);

//        ExecutorService pool = Executors.newSingleThreadExecutor(); // 单个线程池的创建
//        ExecutorService pool = Executors.newFixedThreadPool(4); //正式工作人员有4个，没有临时工
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(4); // 给定数量的线程池，也都是正式员工
//        ExecutorService pool = Executors.newCachedThreadPool(); // 正式员工为零，临时工人数不限制

//      ①      pool.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("起床了");
//                }
//            },1,TimeUnit.SECONDS); // 延迟时间进行任务


//      ②      pool.scheduleAtFixedRate(new Runnable() {
//               @Override
//               public void run() {
//                   System.out.println("起床了");
//               }
//           },1,1,TimeUnit.SECONDS);
     }
}
