public class Solution {
    public static void main(String[] args) {
        int n = 5;
        int[][] trust = {{1,3},{2,3}};
        int res = findProphet(n,trust);
        System.out.println(res);
    }
    public static int findProphet(int n, int[][] trust) {
        //如果有n个人，但是只有n-2组trust,证明有一个人的信任对象无法确定，顾无法确定预言家身份
        if(trust.length == 0||trust.length + 1 != n)return -1;
        if(n == 2&&trust.length == 1)return trust[0][1];
        int len = trust.length;
        int predict_man = trust[0][1];
        for(int i = 1;i < trust.length;i++){
            if(trust[i][1] != predict_man){return -1;}
        }
        return predict_man;
    }
}
