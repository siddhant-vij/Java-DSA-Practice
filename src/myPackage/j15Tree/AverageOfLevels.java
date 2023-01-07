// Leetcode: 637. Average of Levels in Binary Tree

package myPackage.j15Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class AverageOfLevels {

  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  static TreeNode buildTree(String str) {
    if (str.length() == 0 || str.charAt(0) == 'N') {
      return null;
    }
    String ip[] = str.split(" ");
    TreeNode root = new TreeNode(Integer.parseInt(ip[0]));
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int i = 1;
    while (queue.size() > 0 && i < ip.length) {
      TreeNode currNode = queue.peek();
      queue.remove();
      String currVal = ip[i];
      if (!currVal.equals("N")) {
        currNode.left = new TreeNode(Integer.parseInt(currVal));
        queue.add(currNode.left);
      }
      i++;
      if (i >= ip.length)
        break;
      currVal = ip[i];
      if (!currVal.equals("N")) {
        currNode.right = new TreeNode(Integer.parseInt(currVal));
        queue.add(currNode.right);
      }
      i++;
    }
    return root;
  }

  static List<Double> averageOfLevels(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    queue.add(null);
    List<Double> result = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    long sum = 0L;
    while (queue.size() > 1) {
      TreeNode cur = queue.remove();
      if (cur == null) {
        int n = list.size();
        result.add(1.0 * sum / n);
        sum = 0;
        queue.add(null);
        list = new ArrayList<>();
      } else {
        list.add(cur.val);
        sum += cur.val;
        if (cur.left != null)
          queue.add(cur.left);
        if (cur.right != null)
          queue.add(cur.right);
      }
    }
    int n = list.size();
    result.add(1.0 * sum / n);
    return result;
  }

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      String str = sc.nextLine();
      TreeNode root = buildTree(str);
      System.out.println(averageOfLevels(root));
    }
  }
}
