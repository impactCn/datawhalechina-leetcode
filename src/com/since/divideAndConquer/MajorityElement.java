package com.since.divideAndConquer;


import java.util.HashMap;
import java.util.Map;

/**
 * @author: since
 * @date: 2020/8/19
 */
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
