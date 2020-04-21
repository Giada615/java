import java.util.Arrays;

/**
 * @ClassName $ {NAME}
 * @Description TODO
 * @Author $ {USER}
 * @Date $ {DATE} 10:01
 * @Version 1.8
 **/
public class java19 {
    public static void main(String[]args){
        int [][] array={{1,2},{3,4},{5,6}};
        //for(int i=0;i<3;i++){  i<3，对于不规则数组，容易发生数组越界
        for(int i=0;i<array.length;i++)
            for(int j=0;j<)

            System.out.println(Arrays.deepToString(array)); //打印二维数组

     // int [][] array={{1,2},{3,4},{5,6}};
        // int [][] array=new int [][]{{1,2},{3,4},{5,6}};
        // int [][] array={{1},{3,4},{5,6}};//不规则的二维数组
        //int [][]array=new int[3][];//打印出来是0 00 000 00000

        //研究Arrays.sort底层是什么排序？



/*
!!  public class java19 {
    public static void main(String[] args) {
        int [] array={1,2,3,4,5,6};
        System.out.println(binarySearch);
    }

    public static int binarySearch(int [] array,int key,int left,int right,int) mid {
        int left=0;
        int right=array.length-1;
        if(left>right){
            return -1;
        }
        int mid=array.length/2;
        if (array[mid]==key)
        return mid;
        }else if(array[mid]>key){
        return binarySearch(int [] array,key,left,right,mid-1);
    }

    }



!!! public class java19 {
    public static void main(String[] args) {
        int[] array={1,2,3,4,5,6,7,8};
        System.out.println();
    }

    public static int func(int[] array) {
        int[] array1=new int[array.length];
        int left=0;
        int right=array.length-1;
        int tmp=0;
        while (left<right;left++){
            if(left/2==0) {
                tmp = array[left];

            }




            }

        }
    }

    }




public class java19 {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6};
        System.out.println(arange(array));
    }

    public static double arange(int[] array) {
        double arange = 0.0;
        int i;
        for (i = 0; i < array.length; i++) {
            if (i < array.length) {
                arange+= array[i];
            }
        }
        return (arange/(double)array.length);
    }
}




public class java19 {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        System.out.println(min(array));
    }

    public static int min(int[] array) {
        int min = array[0];
        int i;
        for (i = 0; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
            }
        }
        return min;
    }
}


public class java19 {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        System.out.println(max(array));
    }

    public static int max(int[] array) {
        int max = array[0];
        int i;
        for (i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        return max;
    }
}



    //  ④数组名：clone,  数组名：拷贝的数组-》原数组
    public static void main(String[] args) {  //③速度慢，内部调用了System.arraycopy
        int[] array = {1, 2, 3, 4, 5};
        int[] array2 = new int[array.length];
        array2 = Arrays.copyOf(array, array.length);
        System.out.println(Arrays.toString(array2));
    }
}

        public static void main(String[] args) {  //②速度快，底层由C/C++编写，被native修饰
            int[] array = {1, 2, 3, 4, 5};
            int[] array2 = new int[array.length];
            System.out.println(Arrays.toString(array));
            System.arraycopy(array, 0, array2, 0, array.length);
            System.out.println(Arrays.toString(array2));
        }
    }





  public static void main3(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(toString(arr));
    }

    public static String toString(int[] arr) {
        String ret = "[";
        for (int i = 0; i < arr.length; i++) {
            ret += arr[i];
            if (i != arr.length - 1) {
                ret += ", ";
            }
        }
        ret += "]";
        return ret;
    }
}

  ①public static void main3(String[] args) {
        int[] array={1,2,3,4,5};
        int[]array2=new int[array.length];
        System.out.println(Arrays.toString(array));
        for(int i=0;i<array.length;i++){
            array2[i]=array[i];
        }
        System.out.println(Arrays.toString(array2));
    }





    public static void main1(String[] args) {
        int []array={1,2,3,4,5};
        System.out.println(toString(array));
}

    public static String toString(int[] array) {
        String str = "[";
        for (int i = 0; i < array.length; i++) {
            str += array[i];
            if (i != array.length - 1) {
                str += ",";
            }
        }
        str += "]";
        return str;
    }
}
*/

