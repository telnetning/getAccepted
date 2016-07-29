
#include<stdio.h>
#include<stdlib.h>

int main() {
    int opt[64] = {
        0,3,2,3,2,3,4,5,
        3,2,1,2,3,4,3,4,
        2,1,4,3,2,3,4,5,
        3,2,3,2,3,4,3,4,
        2,3,2,3,4,3,4,5,
        3,4,3,4,3,4,5,4,
        4,3,4,3,4,5,4,5,
        5,4,5,4,5,4,5,6
    };
    int opta[64] = {
        0,3,2,3,2,3,4,5,
        3,4,1,2,3,4,3,4,
        2,1,4,3,2,3,4,5,
        3,2,3,2,3,4,3,4,
        2,3,2,3,4,3,4,5,
        3,4,3,4,3,4,5,4,
        4,3,4,3,4,5,4,5,
        5,4,5,4,5,4,5,6
    };
    
    int t;
    char nc[3];
    int ni[3];
    int minsteps;
    freopen("t1308.data","r", stdin);    
    scanf("%d", &t);
    while(t > 0) {
        scanf(" %c%d %c%d %c%d", &nc[0], &ni[0], &nc[1], &ni[1], &nc[2], &ni[2]);
        //printf("%c%d %c%d %c%d\n", nc[0], ni[0], nc[1], ni[1], nc[2], ni[2]);
        char row;
        int col, i;
        minsteps = 100;
        for(row = 'A'; row <= 'H'; row++) {
            for(col = 1; col <= 8; col++) {
                int steps = 0;
                //printf("%c%d ", row, col);
                for(i = 0; i < 3; i++) {
                    char nrow = nc[i];
                    int ncol = ni[i];
                    if((nrow == 'A' || nrow == 'H') && (ncol == 1 || ncol == 8)
                       || (row == 'A' || row == 'H') && (col == 1 || col == 8)) {
                        steps += opta[abs(nrow - row) * 8 + abs(ncol - col)];
                        //printf("%d ", opta[abs(nrow - row) * 8 + abs(ncol - col)]);
                    } else {
                        steps += opt[abs(nrow - row) * 8 + abs(ncol - col)];
                        //printf("%d ", opt[abs(nrow - row) * 8 + abs(ncol - col)]);
                    }
                }
                //printf("%d\n", steps);
                if(steps < minsteps) {
                    minsteps = steps;
                }
            }
        }
        printf("%d\n", minsteps);
        t--;
        //printf("%d\n", t);
    }
    
    return 0;
} 
