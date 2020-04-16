import java.util.Date;

/**
 * @ClassName $ {HJY}
 * @Description TODO
 * @Author $ {USER}
 * @Date $ {DATE} 20:43
 * @Version 1.8
 **/

class ListNode {   //节点类
    public int data;
    public ListNode next;

    public ListNode(int data) {
        this.data = data;
        this.next = null;
    }
}


    class MySignalList {  //单链表
        public ListNode head;

        public MySignalList() {

        }


        //1.头插法：插到以前的头结点的前头
        // 判断是不是第一次插入
        public void addFirst(int data) {
            ListNode node = new ListNode(data);
            if (this.head == null) {
                this.head = node;
            } else {
                node.next = this.head;
                this.head = node;
            }
        }

        // 2.尾插法：定义一个引用指向头，去寻找尾
        public ListNode cur;

        public void addLast(int data) {
            ListNode node = new ListNode(data);
            ListNode cur = this.head;
            if (this.head == null) {
                this.head = node;
            } else {
                while (cur.next != null) {
                    cur = cur.next;
                }
                cur.next = node;
            }
        }


        //3.查找是否包含关键字key是否在单链表当中
        public boolean contains(int key) {
            ListNode cur = this.head;
            while (cur != null) {
                if (cur.data == key) {
                    return true;
                }
                cur = cur.next;
            }
            return false;
        }


        // 4.得到单链表的长度
        public int getLength() {
            int count = 0;
            ListNode cur = this.head;
            while (cur != null) {
                cur = cur.next;
                count++;
            }
            return count;
        }

        private ListNode searchIndex(int index) {  // 找到index-1 的位置,只需让cur走index-1的步数
            ListNode cur = this.head;
            // cur走index-1步（循环）
            int count = 0;
            while (count < index - 1) {
                cur = cur.next;
                count++;
            }
            return cur;
        }

        //5.任意位置插入，第一个数据节点为0号的下标
        public boolean addIndex(int index, int data) {
            //判断index是否合法
            if (index < 0 | index > getLength()) {
                System.out.println("index不合法");
                return false;
            }

            if (index == 0) {  //相当于头插法
                addFirst(data);
                return true;
            }
            //index 不为0，找到index-1的位置
            ListNode cur = searchIndex(index);
            ListNode node = new ListNode(data);
            node.next = cur.next;
            cur.next = node;
            return true;
        }


        private ListNode searchPrev(int key) { //找到key的前驱
            ListNode prev = this.head;
            while (prev.next != null) {   //prev.next  头已经判断过了
                if (prev.next.data == key) {
                    return prev;
                }
                prev = prev.next;
            }
            return null;
        }

        //6.去除第一次出现关键字为key的节点
        public void remove(int key) {
            //判断头结点是否为空
            if (this.head == null) {
                System.out.println("单链表为空");
                return;
            }
            //0、删除的节点是否是头结点
            if (this.head.data == key) {
                this.head = this.head.next;
                return;
            }

            //1、找到key的前驱  如果返回空
            ListNode prev = searchPrev(key);
            if (prev == null) {
                return;
            }

            //2、删除节点
            ListNode del = prev.next;
            prev.next = del.next;
        }


        //7.删除所有值为key的节点
        public void removeAllKey(int key) {
            ListNode prev = this.head;
            ListNode cur = this.head.next;
            while (cur != null) {
                if (prev.next.data == key) {
                    prev.next = cur.next;
                    cur = cur.next;
                } else {
                    prev = cur;
                    cur = cur.next;
                }
            }
            if (this.head.data == key) {
                this.head = head.next;
            }
        }


        //内存泄漏问题
        public void clear() {
//            if (this.head!=null){
//            ListNode cur=this.head.next;
//        }
            //直接置为空（双向链表不可以）
            this.head=null;
            //一个一个置为空
            while (this.head != null) {
                ListNode cur = this.head.next;
                this.head.next = null;
                this.head = cur;
            }
        }


        // 8.迭代法（循环）反转函数    //头插法
        public ListNode reverseList() {
            ListNode prev = null;
            ListNode newHead = null;
            ListNode cur = this.head; // cur:当前需要反转的结点
            while (cur != null) {
                ListNode curNext = cur.next;//cur不为空的前提下
                if (curNext == null) {
                    newHead = cur;
                }
                cur.next = prev;
                prev = cur;
                cur = curNext;
            }
            return newHead;
        }

        public void display2(ListNode newHead) {
            ListNode cur = newHead;
            while (cur != null) {
                System.out.println(cur.data + " ");
                cur = cur.next;
            }
            System.out.println();
        }


        //9.单链表的中间节点  //快慢指针
        public ListNode middleNode() {
            ListNode fast = this.head;
            ListNode slow = this.head;
            while (fast != null && fast.next != null) { //不能只用fast.next!=null来判断，因为这样fast.next.next不能确保是否是空
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }


        //10.输入一个链表，输出该单链表倒数第K个节点
        public ListNode findKthToTail(int k) {
            if (k <= 0) {
                return null;
            }

            ListNode fast = this.head;
            ListNode slow = this.head;
            while (k - 1 > 0) {
                if (fast.next != null) {
                    fast = fast.next;
                    k--;
                } else {
                    System.out.println("没有这个节点");
                    return null;
                }
            }
            while (fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }
            return slow;  //fast.next 为空时，slow所指的就是该节点
        }


        //11.以给定值x为基准将链表分割成两部分，所有小于x的结点排在大于或等于x的结点之前
        public ListNode partition(int x) {
            ListNode cur = this.head;
            ListNode beforeStart = null;
            ListNode beforeEnd = null;
            ListNode afterStart = null;
            ListNode afterEnd = null;
            while (cur != null) {
                ListNode curNext = cur.next;
                cur.next = null;
                //cur.data < x
                if (cur.data < x) {
                    //第一次插入
                    if (beforeStart == null) {
                        beforeStart = cur;
                        beforeEnd = cur;

                    } else if (beforeStart != null) {
                        beforeEnd.next = cur;
                        beforeEnd = beforeEnd.next;
                    }

                } else {
                    //第一次插入
                    if (afterStart == null) {
                        afterStart = cur;
                        afterEnd = cur;

                    } else if (afterStart != null) {
                        afterEnd.next = cur;
                        afterEnd = afterEnd.next;
                    }
                    //cur = cur.next;
                    cur = curNext;
                }
                if (beforeStart == null) {
                    return afterStart;
                }
                beforeEnd.next = afterStart;

                //        if (afterStart!=null){
                //            afterStart.next=null;
                //        }
            }
            return beforeStart;
        }

        //12.删除重复的节点
        public ListNode deleteDuplication() {
            ListNode node = new ListNode(-1);
            ListNode cur = this.head;
            ListNode tmp = node;
            while (cur != null) {
                if (cur.next != null &&
                        cur.data == cur.next.data) {
                    //1、循环
                    //2、退出循环 cur要多走一步
                    while (cur.next != null &&
                            cur.data == cur.next.data) {
                        cur = cur.next;
                    }
                    cur = cur.next;//多走的一步
                } else {
                    //当前节点 不等于下一个节点的时候
                    tmp.next = cur;
                    cur = cur.next;
                    tmp = tmp.next;
                }
            }
            tmp.next = null;
            this.head = node.next;
            return this.head;
        }


        //13.列表的回文结构
        public boolean chkPalindrome() {
            ListNode fast = this.head;
            ListNode slow = this.head;

            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            //反转
            ListNode p = slow.next;
            while (p != null) {
                ListNode pNext = p.next;
                //反转
                p.next = slow;
                slow = p;
                p = pNext;
            } //
            while (this.head != slow) {
                if (this.head.data != slow.data) {
                    return false;
                }
                if (this.head.next == slow) {
                    return true;
                }
                this.head = this.head.next;
                slow = slow.next;
            }
            return true;
        }


        //14.是否有环  一个走一步，一个走两步(最快)
        public void createLoop() { // 制环
            ListNode cur = this.head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = this.head.next;
        }

        public boolean hasCycle() {
            ListNode fast = this.head;
            ListNode slow = this.head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) {
                    return true;
                }
            }
            return false;
        }


        //判断有没有环，找入口
        public ListNode detectCycle() {
            ListNode fast = this.head;
            ListNode slow = this.head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) {
                    break;
                }

                if (fast == null || fast.next == null) {
                    return null;
                }
                fast = this.head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
            }
            return slow;
        }

        //求环的长度
        public int listLen() {
            ListNode fast = this.head;
            ListNode slow = this.head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) {

                }
            }
            return 1;
        }





        // 打印函数
        public void display() {
            ListNode cur = this.head;
            while (cur != null) {
                System.out.print(cur.data + " ");
                cur = cur.next;
            }
            System.out.println();
        }
    }



