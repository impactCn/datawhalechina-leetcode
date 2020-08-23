package com.since.dynamicProgramming;

/**
 * @program:
 * @description:
 * @author: SY_zheng
 * @create: 2020-08-23
 */
public class LongestPalindrome {

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     *
     * 示例 1：
     *
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     *
     * 输入: "cbbd"
     * 输出: "bb"
     *
     * @param s
     * @return
     */
    public String method(String s) {

        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;

        String res = s.substring(0, 1);

        // 枚举所有长度大于等于2的字串
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && valid(s, i, j)) {
                    maxLen = j - i + 1;
                    res = s.substring(i, j+1);
                }
            }
        }

        return res;

    }

    private boolean valid(String s, int left, int right) {

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;

        }
        return true;
    }
}
