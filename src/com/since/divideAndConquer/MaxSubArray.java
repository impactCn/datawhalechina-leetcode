package com.since.divideAndConquer;

/**
 * @author: since
 * @date: 2020/8/19
 */
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
