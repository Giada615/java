package lessson7;

import java.util.concurrent.*;

/**
 * @ClassName $ {HJY}
 * @Description TODO
 * @Author $ {USER}
 * @Date $ {DATE} 21:09
 * @Version 1.8
 **/
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {
        ExecutorService pool=new ThreadPoolExecutor(
                3,  // 核心线程数（正式员工）
                5, // 最大线程数（最多数量的员工=正式员工+临时工）
                // 临时工雇佣：正式员工忙不过来，就会创建临时工
                // 临时工：空闲时间超出设置的时间范围，就解雇
                40, // 时间数量
                TimeUnit.SECONDS, // 时间单位 （时间数量+时间单位表示一定范围的时间）

                // 阻塞队列：存放包裹的仓库（存放任务的数据结构）
                new ArrayBlockingQueue<>(1000),

                // （了解）线程池创建Thread线程的工厂类，没有提供的话，就使用线程池内部的默认的创建方法
//                new ThreadFactory() {
//                    @Override
//                    public Thread newThread(Runnable r) {
//                        return null;
//                    }
//                },

                // 拒绝策略
                //①CallerRunsPolicy:谁（execute所在的线程）让我（快递公司）送快递，不好意思，你自己去送
                //②AbortPolicy:直接抛出异常RejectedExecutionException
                //③DiscardPolicy:从阻塞队列里丢弃最新的任务（队尾）
                //④DiscardOldestPolicy:从阻塞队列里丢弃最旧的任务（队首）
                new ThreadPoolExecutor.CallerRunsPolicy()  );
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("送快递到陕西，A同学");
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("送快递到杭州，B同学");
            }
        });
        System.out.println("我正在做的事情");
    }
}
