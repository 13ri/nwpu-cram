import java.util.HashMap;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<Integer,Integer> frequency = new HashMap<Integer,Integer>();
        // �����м��� [100,34,100,22,22,50,34,100]������ͳ����ͬ���ֵĸ���
        int[] nums = new int[]{100,34,100,22,22,50,34,100};
        for(int i=0;i<nums.length;i++){
            if(frequency.containsKey(nums[i]))
                frequency.put(nums[i],frequency.get(nums[i])+1);
            else frequency.put(nums[i],1);
        }
        System.out.println(frequency);
    }
}
