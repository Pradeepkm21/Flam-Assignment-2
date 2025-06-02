#include<bits/stdc++.h>
using namespace std;

// Problem: N-Queens
// You are given an integer N, you have to place N queens on an N*N chessboard such that no two queens attack each other.

///Time Complexity: (N! * N) nearly.
///Space Complexity: O(N^2)

vector<vector<string>>result;
void solve(int n, int row, vector<bool>&cols, vector<bool>&mainDiagonal, vector<bool>&antiDiagonal, vector<string>&board){

    //store valid board;
    if(n==row){
        result.push_back(board);
        return;
    }

    for(int col=0; col<n; col++){
        //not safe column
        if(cols[col] || mainDiagonal[(n-1) + (row-col)] || antiDiagonal[row+col]) continue;

        //mark safe
        board[row][col] = 'Q';
        cols[col] = true;
        mainDiagonal[(n-1) + (row-col)] = true;
        antiDiagonal[row+col] = true;

        //recurse for next queen;
        solve(n, row+1, cols, mainDiagonal, antiDiagonal, board);

        //backtrack
        board[row][col] = '.';
        cols[col] = false;
        mainDiagonal[(n-1) + (row-col)] = false;
        antiDiagonal[row+col] = false;
    }
}

vector<vector<string>> solveNQueens(int n) {
    vector<string>board(n, string(n, '.'));
    vector<bool>cols(n, false), mainDiagonal(2*n, false), antiDiagonal(2*n, false);

    solve(n, 0, cols, mainDiagonal, antiDiagonal, board);

    return result;
}

int main(){

    /* #ifndef ONLINE_JUDGE
    freopen("input.txt","r", stdin);
    freopen("output.txt","w", stdout);
    #endif */


    int N=4;
    cin>>N;
    vector<vector<string>>ans = solveNQueens(N);
    
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            cout<<ans[i][j]<<endl;
        }
        cout<<endl;
    }

    return 0;
}