import java.util.Arrays;

public class threeMid {
    public static void main(String[] args) {
        int[] array = {4,5,7,8,1,2,3,6};
        quickSort(array);
        System.out.print(Arrays.toString(array));
    }
    public static void quickSort(int[] array){
        quick(array,0,array.length-1);
    }
    //三数取中法，用于优化，提高效率
    private static int threeMid(int[] array,int left,int right){
        int mid=(left+right)>>>1;
        if(array[left]<array[right]){//左<右
            if(array[mid]<array[left]){//中<左
                return left;
            }else if(array[mid]<array[right]){//中<右，左<中
                return mid;
            }else{//中>右，左<中
                return right;
            }
        }else{//左>右
            if(array[mid]<array[right]){//中<右
                return right;
            }else if(array[mid]<array[left]){//左>中，右<中
                return mid;
            }else{//左<中，右<中
                return left;
            }
        }
    }
    private static void quick(int[] array, int start, int end){
        if(start>=end){
            return;
        }
        int index=threeMid(array,start,end);
        swap(array,index,start);
        //处理枢纽值
        int pivot=partition(array,start,end);
        quick(array,start,pivot-1);
        quick(array,pivot+1,end);
    }
    private static int partition(int[] array,int left,int right){
        int tmp=array[left];
        while (left<right){
            while (right>left&&array[right]>=tmp){
                right--;
            }
            //右边找到小于tmp的数
            array[left]=array[right];
            while (left<right&&array[left]<=tmp){
                left++;
            }
            //左边找到大于tmp的数
            array[right]=array[left];
        }
        array[left]=tmp;
        return left;
    }
    private static void swap(int[] array,int start,int end){
        int temp = array[start];
        array[start] = array[end];
        array[end] = temp;
    }
}
