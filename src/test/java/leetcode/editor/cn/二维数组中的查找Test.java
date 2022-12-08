package leetcode.editor.cn;

/**
 * @Author: Liu Yang
 * @Date: 2021/12/15 19:09
 * @Desc:
 */
class 二维数组中的查找Test {

    public static void main(String[] args) {
        int[][] param = new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        boolean numberIn2DArray = new 二维数组中的查找Test.Solution().findNumberIn2DArray(param, 25);
        System.out.println(numberIn2DArray);

        param = new int[][]{{-5}};
        numberIn2DArray = new 二维数组中的查找Test.Solution().findNumberIn2DArray(param, -5);

        System.out.println(numberIn2DArray);
        param = new int[][]{{-1,3}};

        numberIn2DArray = new 二维数组中的查找Test.Solution().findNumberIn2DArray(param, 3);
        System.out.println(numberIn2DArray);

        param = new int[][]{{1},{3},{5}};
        numberIn2DArray = new 二维数组中的查找Test.Solution().findNumberIn2DArray(param, 1);
        System.out.println(numberIn2DArray);

        param = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        numberIn2DArray = new 二维数组中的查找Test.Solution().findNumberIn2DArray(param, 1);
        System.out.println(numberIn2DArray);

        param = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        numberIn2DArray = new 二维数组中的查找Test.Solution().findNumberIn2DArray(param, 15);
        System.out.println(numberIn2DArray);

        param = new int[][]{{1,4},{2,5}};
//        numberIn2DArray = new 二维数组中的查找().Solution.Solution().findNumberIn2DArray(param, 1);
        System.out.println(numberIn2DArray);

    }

    static class Solution {
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

            return extracted(matrix, target, rowMin, rowMax, colMin, colMax);
        }

        private boolean extracted(int[][] matrix, int target, int rowMin, int rowMax, int colMin, int colMax) {
            if (rowMax < 1 && colMax < 1) {
                return matrix[rowMax][colMax] == target;
            }

            int rowPoint = rowMax;
            int colPoint = colMax;
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
                        rowPoint = rowMax;
                        colPoint = colMax;
                        //已经到右下角了,如果还小，就return
                        if (matrix[rowPoint][colPoint] < target) {
                            return false;
                        }
                        break;
                    }
                } else {
                    rowMax = row;
                    colMax = col;
                    if (rowMax - rowMin <= 1 && colMax - colMin <= 1) {

                        rowPoint = rowMin;
                        colPoint = colMin;
                        //已经到左上角,如果还大，就return
                        if (matrix[rowPoint][colPoint] > target) {
                            return false;
                        }
                        break;
                    }
                }
            }
//            extracted(matrix,target,)
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
}
