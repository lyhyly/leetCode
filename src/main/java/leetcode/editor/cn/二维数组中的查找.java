package leetcode.editor.cn;
//在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个
//整数，判断数组中是否含有该整数。 
//
// 
//
// 示例: 
//
// 现有矩阵 matrix 如下： 
//
// 
//[
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
// 
//
// 给定 target = 5，返回 true。 
//
// 给定 target = 20，返回 false。 
//
// 
//
// 限制： 
//
// 0 <= n <= 1000 
//
// 0 <= m <= 1000 
//
// 
//
// 注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/ 
// Related Topics 数组 二分查找 分治 矩阵 👍 514 👎 0

public class 二维数组中的查找 {
    public static void main(String[] args) {
        int[][] param = new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            //判空
            if (matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }
            //确定行列点
            int rowMin = 0;
            int rowMax = matrix.length - 1;
            int colMin = 0;
            int colMax = matrix[0].length - 1;
            //二分查找
            while (true) {
                int row = (rowMax - rowMin) / 2 + rowMin;
                int col = (colMax - colMin) / 2 + colMin;
                if (target == matrix[row][col]) {
                    return true;
                }
                if (matrix[row][col] < target) {
                    rowMin = row;
                    colMin = col;
                    if (rowMax - rowMin <= 1 && colMax - colMin <= 1) {
                        break;
                    }
                } else {
                    rowMax = row;
                    colMax = col;
                    if (rowMax - rowMin < 1 && colMax - colMin < 1) {
                        break;
                    }
                }
            }
            int rowPoint = rowMax;
            int colPoint = colMax;
            int row = 0;
            int col = 0;
            //横向二分
            while (true) {
                if (colMax == col + 1) {
                    for (int i = col; i <= colMax; i++) {
                        if (matrix[rowPoint][i] == target) {
                            return true;
                        }
                    }
                    break;
                } else {
                    if (matrix[rowPoint][(colMax - col) / 2 + col] == target) {
                        return true;
                    }
                    if (matrix[rowPoint][(colMax - col) / 2 + col] < target) {
                        col = (colMax - col) / 2 + col;
                    } else {
                        colMax = (colMax - col) / 2 + col;
                    }
                    if (colMax < col + 1) {
                        break;
                    }
                }
            }
            //竖向二分
            while (true) {
                if (rowMax == row + 1) {
                    for (int i = 0; i <= rowPoint; i++) {
                        if (matrix[i][colPoint] == target) {
                            return true;
                        }
                    }
                    break;
                }
                if (matrix[(rowMax - row) / 2 + row][colPoint] == target) {
                    return true;
                }
                if (matrix[(rowMax - row) / 2 + row][colPoint] < target) {
                    row = (rowMax - row) / 2 + row;
                } else {
                    rowMax = (rowMax - row) / 2 + row;
                }
                if (rowMax < row + 1) {
                    break;
                }
            }

            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}