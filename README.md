# task01
## 什么是分治算法
就是把一个复杂的问题分成多个或更多相同类似的子问题。
再把子问题分成更小的字问题......直到子问题可以简单的直接求解，原问题的解即字问题的合并。
## 分治法的基本步骤
  * step1 分解：将问题分成若干个规模较小，且相互独立，与原问题形式相同的子问题
  * step2 解决：若子问题规模较小而容易被解决则直接解，否则递归地解各个问题
  * step3 合并：将各个子问题的解合并为原问题的解

## 以 LeetCode 的 50 53 169 题为例。
### 50 Pow(x. n)
```$xslt
public class MyPow {
    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     *
     * 示例 1:
     *
     * 输入: 2.00000, 10
     * 输出: 1024.00000
     * 示例 2:
     *
     * 输入: 2.10000, 3
     * 输出: 9.26100
     * 示例 3:
     *
     * 输入: 2.00000, -2
     * 输出: 0.25000
     * 解释: 2-2 = 1/22 = 1/4 = 0.25
     * 说明:
     *
     * -100.0 < x < 100.0
     * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
     *
     * 分治
     * @param x
     * @param n
     * @return
     */
    public double methodOne(double x, int n) {

        double res = 1.0;

        for (int i = n; i != 0; i /= 2) {
            if (i % 2 != 0) {
                res *= x;
            }
            x *= x;
        }

        return n < 0 ? 1 / res: res;

    }
    /**
     * 分治
     * @param x
     * @param n
     * @return
     */
    public double methodTwo(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    private double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }
}
``` 
本题给出两个解，着重说第二解，  
由于存在奇偶性，当n%2 != 0时：故需要多乘一个  
由于每次递归都会使得指数减少一半，因此递归的层数为 O(\log n)O(logn)，算法可以在很快的时间内得到结果。

### 复杂度分析

* 时间复杂度：O(\log n)O(logn)，即为递归的层数。

* 空间复杂度：O(\log n)O(logn)，即为递归的层数。这是由于递归的函数调用会使用栈空间。

### 53 最大子序和
```$xslt
public class MaxSubArray {

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例:
     *
     * 输入: [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * 暴力法
     * @param nums
     * @return
     */
    public int methodOne(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    public int methodTwo(int[] nums) {
        return maxSubArray(nums,0,nums.length-1);
    }

    /**
     * 分治法
     * @param nums
     * @param low
     * @param high
     * @return
     */
    private int maxSubArray(int[] nums, int low, int high) {

        if (low > high) {
            return Integer.MIN_VALUE;
        }

        int mid = (low + high) / 2;

        int leftSum = maxSubArray(nums, low, mid - 1);
        int rightSum = maxSubArray(nums, mid + 1, high);
        int midSum = midMax(nums, low, high, mid);

        return Math.max(midSum, Math.max(leftSum, rightSum));

    }

    private int midMax(int[] nums, int low, int high, int mid) {
        // 必含有nums[mid]，向两边扩张
        int midSumRight = 0;
        int sumRight = 0;

        for (int i = mid + 1; i <= high; i++) {
            sumRight += nums[i];
            midSumRight = Math.max(midSumRight, sumRight);
        }

        int midSumLeft = 0;
        int sumLeft = 0;

        for (int i = mid - 1; i >= low ; i--) {
            sumLeft += nums[i];
            midSumLeft = Math.max(midSumLeft, sumLeft);
        }

        return nums[mid] + midSumLeft + midSumRight;
    }
}
```
本题分治解法，将数组分成三部份，左右中，其中左右依次向边界扩展计算最大。
中间依靠左右的扩展，在分成两部分，比较两边的大小。
### 复杂度分析
* 空间复杂度：递归会使用 O(\log n)O(logn) 的栈空间，故渐进空间复杂度为 O(\log n)O(logn)。

### 169. 多数元素
```$xslt
public class MajorityElement {

    /**
     * 定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:
     *
     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     *
     * 暴力法
     * @param nums
     * @return
     */
    public int methodOne(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>(nums.length);


        for (int num : nums) {

            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }

        }

        int res = 0;
        int maxNumber = nums[0];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (res < entry.getValue()) {
                maxNumber = entry.getKey();
                res = entry.getValue();
            }
        }

        return maxNumber;
    }

    public int method(int[] nums) {

        if (nums.length %2 == 0) {
            return quickSearch(nums, 0, nums.length - 1, nums.length / 2 - 1);
        }
        
        return quickSearch(nums, 0, nums.length - 1, nums.length / 2);
    }

    private int quickSearch(int[] nums, int lo, int hi, int k) {
        // 每快排切分1次，找到排序后下标为j的元素，如果j恰好等于n/2就返回；
        int j = partition(nums, lo, hi);
        if (j == k) {
            return nums[j];
        }
        // 否则根据下标j与k的大小关系来决定继续切分左段还是右段。
        return j > k? quickSearch(nums, lo, j - 1, k): quickSearch(nums, j + 1, hi, k);
    }


    private int partition(int[] nums, int lo, int hi) {
        int v = nums[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (++i <= hi && nums[i] < v) {
                ;
            }
            while (--j >= lo && nums[j] > v) {
                ;
            }
            if (i >= j) {
                break;
            }
            int t = nums[j];
            nums[j] = nums[i];
            nums[i] = t;
        }
        nums[lo] = nums[j];
        nums[j] = v;
        return j;
    }

}
```
这道题分治。主要体现在排序，使用快排，将数组分成左右两边。之后每次交换，获取位置，然根据奇偶返回中间值。
### 复杂度分析
* 空间复杂度：递归会使用 O(\log n)O(logn) 的栈空间，故渐进空间复杂度为 O(\log n)O(logn)。

# task01
## 什么是动态规划
大致上，若要解一个给定问题，我们需要解其不同部分（即子问题），再根据子问题的解以得出原问题的解。
## 动态规划基本步骤
  * step1 分析最优解的性质，并刻画其最优子结构特征
  * step2 确定状态表示S(x1,x2,...)和状态递推方程，递归地定义最优值
  * step3 根据状态转移顺序，以自底向上的方式计算出最优值
  * step4 根据计算最优值时得到的信息，构造最优解