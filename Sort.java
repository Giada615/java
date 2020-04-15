import java.util.Stack;

/**
 * @ClassName $ {HJY}
 * @Description TODO
 * @Author $ {USER}
 * @Date $ {DATE} 19:54
 * @Version 1.8
 **/
// 1. 插入排序
public class Sort {
    public static void inserSort(int[] array, int left, int right) {
        for (int i = 1; i < array.length; ++i) {
            int key = array[i];
            int end = i - 1;

            // 待插入元素在前面已排好序部分的位置
            while (end >= 0 && key < array[end]) {
                array[end + 1] = array[end];
                end--;
            }

            //2.插入元素
            array[end + 1] = key;
        }
    }

    public static void printArray(int[] array) {
        for (int e : array) {
            System.out.print(e + " ");
        }
    }

    // 2.希尔排序
    public static void shellSort(int[] array) {
        int gap = 3;  //int gap = array.length;
        while (gap > 0) {
            // gap=gap/3+1;
            for (int i = gap; i < array.length; ++i) {  //不是i+gap，分组后的内容交替进行
                int key = array[i];
                int end = i - gap;

                // 待插入元素在前面已排好序部分的位置
                while (end >= 0 && key < array[end]) {
                    array[end + gap] = array[end];
                    end -= gap;
                }

                //2.插入元素
                array[end + gap] = key;
            }
            gap--;
        }
    }


    // 3.选择排序
    public static void selsectSort(int[] array) {
        // 选择的趟数
        for (int i = 0; i < array.length - 1; ++i) { // -1是因为循环一次排好的元素就多一个，最后一趟不需要排序
            // 具体选择的方式：找到最大元素
            int maxPos = 0;
            for (int j = 1; j < array.length - i; ++j) {
                if (array[maxPos] < array[j]) {
                    maxPos = j;
                }
            }
            if (maxPos != array.length - 1 - i) {
                swap(array, maxPos, array.length - 1 - i);
            }
        }
    }

    // 4.选择排序的优化
    public static void selectSortOP(int[] array) {
        int begin = 0;
        int end = array.length - 1;
        // 一趟可寻找到最大元素或最小元素
        while (begin < end) {
            int maxPos = begin;
            int minPos = begin;
            int index = begin + 1;
            while (index <= end) {
                if (array[index] > array[maxPos]) {
                    maxPos = index;
                }
                if (array[index] < array[minPos]) {
                    minPos = index;
                }
                ++index;
            }
            // 将最大元素放在区间最后的位置
            if (maxPos != end) {
                swap(array, maxPos, end);
            }
            // 如果最小元素刚好在最后的位置
            if (minPos == end) {
                minPos = maxPos;
            }
            if (minPos != begin) {
                swap(array, minPos, begin);
            }
            begin++;
            end--;
        }
    }

    // 5. 堆排序
    // 向下调整
    public static void shiftDown(int[] array, int parent, int size) {
        int child = parent * 2 + 1;
        while (child < size) {
            // 找左右孩子中较大的元素
            if (child + 1 < size && array[child + 1] > array[child]) {
                child += 1;
            }
            // 检测双亲是否比较大的孩子小
            if (array[child] > array[parent]) {
                swap(array, child, parent);
                parent = child;
                child = parent * 2 + 1;
            } else {
                break;
            }
        }
    }

    public static void heapSort(int[] array) {
        // 1.建堆
        // 找倒数第一个非叶子节点
        int lastLeaf = (array.length - 2) >> 1;

        // 从lastLeaf到root的位置不断进行向下调整
        for (int root = lastLeaf; root >= 0; root--) {
            shiftDown(array, root, array.length);
        }
        // 2.利用堆删除的思想进行排序
        int end = array.length - 1;
        while (end >= 0) {
            swap(array, 0, end);
            shiftDown(array, 0, end);
            end--;
        }
    }

    // 6.快速排序
    public static void quickSort(int[] array, int left, int right) {
        if (right - left > 1) {
            //说明区间中至少有两个元素
            // 按照基准值对[left,right)区间进行分割l
            int div = partion(array, left, right);

            //递归排基准值左半侧
            quickSort(array, left, div);
            //递归排基准值右半侧
            quickSort(array, div + 1, right);
        }
    }

    //① Hoare法
    public static int partion1(int[] array, int left, int right) {
        int begin = left;
        int end = right - 1;
        int key = array[end];
        while (begin < end) {  //保证区间有元素
            //1. begin 从前往后找，找比基准值大的元素
            while (begin < end && array[begin] <= key) { // begin<end 防止越界
                begin++;
            }
            //2. 让end从后往前找,找比基准值小的元素
            while (begin < end && array[end] >= key) {
                end--;
            }
            if (begin < end) {
                swap(array, begin, end);
            }
        }
        if (begin != right - 1) {
            swap(array, begin, right - 1);
        }
        return begin;
    }

    //② 挖坑法
    public static int partion2(int[] array, int left, int right) {
        int begin = left;
        int end = right - 1;
        int key = array[end];
        while (begin < end) {
            //1. begin 从前往后找，找比基准值大的元素
            while (begin < end && array[begin] <= key) {
                begin++;
            }
            //已经找到了一个比基准值大的元素，用该元素填end的坑
            if (begin < end) {
                array[end--] = array[begin];
            }

            //2. 让end从后往前找,找比基准值小的元素
            while (begin < end && array[end] >= key) {
                end--;
            }
            // end从后往前找到了一个比基准值小的元素，用该元素填begin位置的坑
            if (begin < end) {
                array[begin++] = array[end];
            }
        }
        //用key填最后一个坑
        array[begin] = key;
        return begin;
    }

    // ③ 前后索引
    public static int partion(int[] array, int left, int right) {
        int cur = left;
        int prev = cur - 1;
        int key = array[right - 1];

        while (cur < right) {
            if (array[cur] < key && ++prev != cur) {
                swap(array, cur, prev);
            }
            ++cur;
        }
        if (++prev != right - 1) {
            swap(array, prev, right - 1);
        }
        return prev;
    }

    // 取基准值的优化---三数取中法
    public static int getIndexMiddle(int[] array, int left, int right) {
        int mid = left + ((right - left) >> 1); // 不能直接左加右，容易溢出
        if (array[left] < array[right - 1]) {
            if (array[mid] < array[left]) {
                return left;
            } else if (array[mid] > array[right - 1]) {
                return right - 1;
            } else {
                return mid;
            }
        } else {
            if (array[mid] > array[left]) {
                return left;
            } else if (array[mid] < array[right - 1]) {
                return right - 1;
            } else {
                return mid;
            }
        }
    }

    // ④ 使用三数取中法优化后的代码
    public static int partion4(int[] array, int left, int right) {
        int begin = left;
        int end = right - 1;
        int mid = getIndexMiddle(array, left, right);
        swap(array, mid, right - 1); // 交换位置
        int key = array[end];
        while (begin < end) {  //保证区间有元素
            //1. begin 从前往后找，找比基准值大的元素
            while (begin < end && array[begin] <= key) { // begin<end 防止越界
                begin++;
            }
            //2. 让end从后往前找,找比基准值小的元素
            while (begin < end && array[end] >= key) {
                end--;
            }
            if (begin < end) {
                swap(array, begin, end);
            }
        }
        if (begin != right - 1) {
            swap(array, begin, right - 1);
        }
        return begin;
    }

    // 优化递归过深而可能导致的栈溢出的问题
    public static void quickSort1(int[] array, int left, int right) {
        if (right - left > 16) {
            inserSort(array, left, right);
        }
        //说明区间中至少有两个元素
        // 按照基准值对[left,right)区间进行分割l
        else {
            int div = partion(array, left, right);

            //递归排基准值左半侧
            quickSort(array, left, div);
            //递归排基准值右半侧
            quickSort(array, div + 1, right);
        }
    }

    // 循环实现快排
    public static void quickSortOP(int[] array){
        Stack<Integer> s=new Stack<>();
        s.push(array.length);
        s.push(0);

        while (!s.empty()){
            int left=s.pop();
            int right=s.pop();

            if (right-left>1){
                int div=partion1(array,left,right);
                //形成[left,div)
                // [div+1,right)
                s.push(right);
                s.push(div+1);
                s.push(div);
                s.push(left);
            }
        }
    }




    public static void swap(int[] array,int left,int right){
        int temp=array[left];
        array[left]=array[right];
        array[right]=temp;
}

    public static void main(String[] args) {
        int[] array={3,6,9,4,1,5,2,0,8,7};
        //inserSort(array);
        //shellSort(array);
        //selsectSort(array);
        //selectSortOP(array);
        //heapSort(array);
        quickSort(array,0,array.length);
        printArray(array);
    }
}
