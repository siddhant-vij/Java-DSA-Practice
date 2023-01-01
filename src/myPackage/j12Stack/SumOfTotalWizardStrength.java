// Leetcode: 2281. Sum of Total Strength of Wizards

package myPackage.j12Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class SumOfTotalWizardStrength {

  static int[] prevSmaller(int[] arr, int n) {
    int res[] = new int[n];
    Deque<Integer> st = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
      while (!st.isEmpty() && arr[st.peek()] >= arr[i])
        st.pop();
      res[i] = st.isEmpty() ? -1 : st.peek();
      st.push(i);
    }
    return res;
  }

  static int[] nextSmallerOrEqual(int[] arr, int n) {
    int res[] = new int[n];
    Deque<Integer> st = new ArrayDeque<>();
    for (int i = n - 1; i >= 0; i--) {
      while (!st.isEmpty() && arr[st.peek()] > arr[i])
        st.pop();
      res[i] = st.isEmpty() ? n : st.peek();
      st.push(i);
    }
    return res;
  }

  static long[] prefixOfPrefixSum(int[] arr, int n) {
    int MOD = 1000000007;
    long[] res = new long[n + 1];
    long sum = 0;
    res[0] = 0;
    for (int i = 1; i <= n; i++) {
      sum += arr[i - 1];
      sum %= MOD;
      res[i] = (res[i - 1] + sum) % MOD;
    }
    return res;
  }

  static int totalStrength(int[] strength) {
    int MOD = 1000000007;
    int n = strength.length;
    long[] pOfP = prefixOfPrefixSum(strength, n);
    int[] leftSmaller = prevSmaller(strength, n);
    int[] rightSmallerOrEqual = nextSmallerOrEqual(strength, n);
    long res = 0;
    for (int i = 0; i < n; i++) {
      int left = leftSmaller[i];
      int right = rightSmallerOrEqual[i];
      long val = (i - left) * (pOfP[right - 1 + 1] - pOfP[i - 1 + 1]) % MOD + MOD -
          (right - i) * (pOfP[i - 1 + 1] - pOfP[left - 1 + 1 < 0 ? 0 : left - 1 + 1]) % MOD;
      val = (strength[i] * val) % MOD;
      res += val;
      res %= MOD;
    }
    return (int) res;
  }

  public static void main(String args[]) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
      }
      System.out.println(totalStrength(arr));
    }
  }
}
