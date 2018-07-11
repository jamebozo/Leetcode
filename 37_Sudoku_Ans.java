////////////////////////////////
public class Solution {

    ///////////////////////////////////////////////////
    public void solveSudoku(char[][] board) {
        dfs(board,0);
    }
    ///////////////////////////////////////////////////
    // In board: 9x9 board with ii as row, jj as col.
    // In d: 0~81, pos of check, check 1 pos at a time.
    // Starting from (0, 0), from left to right, top to bottom, 
    // try finding a valid num (flag[k]) using backtracking. 
    // If unable to find valid num(flag[k]), reset board[i][j] = '.' , 
    //    and return false.
    // Retrun Tr
    //        @b - True, inserted num is valid, or all 9x9 board is filled.
    //        @b - False, cannot find valid num for [i][j]
    private boolean dfs(char[][] board, int d) {
        
        int i = d/9, j = d%9;

        if (d == 81) 
            return true; //found solution

        if (board[i][j]!='.') 
            return dfs(board, d+1); //prefill number skip

        /////////////////////////////////////
        // this function is         
        boolean[] flag=new boolean[10];
        validate(board, i, j, flag);

        for (int k=1; k<=9; k++) {
            if (flag[k]) { // k is the missing num
                board[i][j]=(char)('0'+k);
                if (dfs(board,d+1)) 
                    return true;
                // if false, try another flag[k]
            }
        }
        board[i][j]='.'; //if can not solve, in the wrong path, change back to '.' and out
        return false;
    }

    ///////////////////////////////////////////////////
    // out flag[], check row col cube, if there is missing num(0-9), mark as true.
    // 
    private void validate(char[][] board, int i, int j, boolean[] flag) {
        Arrays.fill(flag,true);

        for (int k=0; k<9; k++) {

            /////////////////////////
            // check jj for missing 0-9
            if (board[i][k]!='.') 
                flag[board[i][k]-'0']=false;

            /////////////////////////
            // check ii for missing 0-9
            if (board[k][j]!='.') 
                flag[board[k][j]-'0']=false;

            /////////////////////////
            // check Cube for missing 0-9
            //   i/3 * 3, makes i=012 => 0,  i=345 => 3...
            //   k is for checking cube.
            
            int r = i/3 * 3 + k/3;   // i/3 * 3 makes 0 if ii = 012, makes 3 if ii = 345
            int c = j/3 * 3 + k%3;
            if (board[r][c]!='.') 
                flag[board[r][c]-'0']=false;
        }
    }
}
