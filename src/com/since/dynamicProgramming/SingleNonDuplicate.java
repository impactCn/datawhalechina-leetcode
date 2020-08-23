package com.since.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * @program:
 * @description:
 * @author: SY_zheng
 * @create: 2020-08-22
 */
public class SingleNonDuplicate {

    /**
     * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
     *
     * 示例 1:
     *
     * 输入: [1,1,2,3,3,4,4,8,8]
     * 输出: 2
     * 示例 2:
     *
     * 输入: [3,3,7,7,10,11,11]
     * 输出: 10
     * 注意: 您的方案应该在 O(log n)时间复杂度和 O(1)空间复杂度中运行。
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
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1){
                return entry.getKey();
            }
        }
        return nums[0];
    }

    public int methodTwo(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        for (int i = 0; i < nums.length - 1; i += 2) {
            if ( nums[i] != nums[i + 1]){
                return nums[i];
            }
        }

        return nums[nums.length - 1];
    }
}
