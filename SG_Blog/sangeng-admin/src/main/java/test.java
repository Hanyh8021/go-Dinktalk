public class test {
    public static void main(String[] args) {
        int[] array = {7,6,5,4,3,2,1};
        shellSort(array);
    }
    public static void shellSort(int[] array){
        int gap=array.length;
        while (gap>1){
            gap=gap/2;
            shell(array,gap);
        }
        shell(array,1);
    }
    public static void shell(int[] array,int gap){
        for(int i=gap;i<array.length;i++){//从gap开始依次排序
            int tmp=array[i];
            int j=i-gap;
            for(;j>=0;j-=gap){
                if(tmp<array[j]){
                    array[j+gap]=array[j];
                } else{
                    break;
                }
            }
            array[j+gap]=tmp;
        }
    }
}
