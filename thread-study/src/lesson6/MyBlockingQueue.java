package lesson6;

/**
 * @ClassName $ {HJY}
 * @Description TODO
 * @Author $ {USER}
 * @Date $ {DATE} 15:44
 * @Version 1.8
 *
 * 实现阻塞式队列：
 * 1.满足线程安全的生产、消费功能
 * 2.生产、消费达到上限/下限时，需要阻塞等待
 **/
public class MyBlockingQueue<E> {

    private Object[] items;
    private int putIndex; // 添加元素索引
    private int takeIndex; // 弹出元素索引
    private int size; // 有效容量

    public MyBlockingQueue(int capacity){
     items = new Object[capacity];
    }

    public synchronized void put(E e) throws InterruptedException {
        while (size==items.length){  // 判断有效数组容量是否达到上限，若达到需等待
           wait();
        }
        // 取模是防止下标越界
        putIndex=(putIndex+1)%items.length; // 存放元素的索引++。需满足循环队列索引>数组长度情况
        items[putIndex]=e; // 存放元素
        size++;
        notifyAll();
    }

    public synchronized E take() throws InterruptedException {
        // 1.while 来判断，不要用if(因为在判断代码中进行wait释放锁以后，
        // 其他线程会修改变量。再次wait被通知恢复的时候，条件已经不满足了）
        // 2. 使用notifyAll方法，通知所有wait方法被阻塞线程
        while (size== 0){  // 达到下限需等待
            wait();
        }
        takeIndex=(takeIndex+1)%items.length;
        size--;
        notifyAll();
        return (E) items[takeIndex];
    }

    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue<Integer> queue= new MyBlockingQueue<>(100);
        for (int i = 0; i <5; i++) {
            final int k = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int j = 0; j < 100; j++) {
                            queue.put(k * 100 + j);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
            while (true){  //这里可以改为多个线程，现在这里只是在main线程里进行操作
             // 可以观察下，取出来的数值，是不是从0—499,如果有重复元素，可能出现了问题
                int num=queue.take();
                System.out.println(num);
            }

    }
}
