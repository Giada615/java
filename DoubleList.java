/**
 * @ClassName $ {HJY}
 * @Description TODO
 * @Author $ {USER}
 * @Date $ {DATE} 21:27
 * @Version 1.8
 **/
class ListNode {
    public int data;
    public ListNode prev;
    public ListNode next;

    public ListNode(int data) {
        this.data = data;
    }
}
public class DoubleList {
    public ListNode head;//头
    public ListNode last;//尾巴

    // 头插法
    public void addFirst(int data) {
        ListNode node = new ListNode(data);
        if (this.head == null) {
            this.head = node;
            this.last = node;
        } else {
            node.next = this.head;
            this.head.prev = node;
            this.head = node;
        }
    }

    //尾插法
    public void addLast(int data) {
        ListNode node = new ListNode(data);
        if (this.head == null) {
            this.head = node;
            this.last = node;
        } else {
            this.last.next = node;
            node.next = null;
            this.last = node;
        }
    }

    //打印顺序表
    public void display() {
        if (this.head == null) {
            return;
        }
        ListNode cur = this.head;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }


    private ListNode searchIndex(int index) {
        ListNode cur = this.head;
        int count = 0;
        while (count < index) {
            cur = cur.next;
            count++;
        }
        return cur;
    }


    //任意位置插入,第一个数据节点为0号下标
    public boolean addIndex(int index, int data) {
        if (index < 0 || index > getLength() || this.head == null) {
            System.out.println("插入位置不合法");
            return false;
        }
        if (this.head.prev == null) {
            addFirst(index);
            return true;
        }
        ListNode cur = searchIndex(index);
        if (cur == null) {
            return false;
        }
        ListNode node = new ListNode(-1);
        node.next = cur;
        cur.prev.next = node;
        node.prev = cur.prev;
        cur.prev = node;
        return true;
    }

    public int getLength() {
        ListNode cur = this.head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;

    }

    // 删除关键字key
    public int remove(int key) {
        int oldData = -1;
        ListNode cur = this.head;
        while (cur != null) {
            if (cur.data == key) {
                oldData = key;
                if (cur == this.head) {
                    this.head = this.head.next;
                    this.head.prev = null;//删除
                    return oldData;
                } else {
                    cur.prev.next = cur.next;
                    if (cur.next != null) {
                        cur.next.prev = cur.prev;
                    } else {
                        this.last = cur.prev;//尾巴结点
                    }
                    return oldData;
                }
            }
            cur = cur.next;
        }
        return -1;
    }



    // 删除所有key
    public void removeAllkey(int key){
        ListNode cur = this.head;
        while (cur != null) {
            if (cur.data == key) {
                if (cur == this.head) {
                    this.head = this.head.next;
                    this.head.prev = null;//删除
                } else {
                    cur.prev.next = cur.next;
                    if (cur.next != null) {
                        cur.next.prev = cur.prev;
                    } else {
                        this.last = cur.prev;//尾巴结点
                    }
                }
            }
            cur = cur.next;
        }
    }


    // 防止内存泄漏
    public void clear(){
        while (this.head!=null){
            ListNode cur=this.head.next;
            this.head.next=null;
            this.head.prev=null;
            this.head=cur;
        }
        this.last=null;
    }

}


