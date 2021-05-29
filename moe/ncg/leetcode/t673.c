#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int lengthOfLIS(int* nums, int numsSize){
    int* dp;
    int* cnt;
    int max = 0;
    //memset(dp, 0, numsSize * sizeof(int));
    dp = malloc(numsSize * sizeof(int));
    cnt = malloc(numsSize * sizeof(int));
    for (int i = 0; i < numsSize; i++) {
        dp[i] = 1;
        cnt[i] = 1;
    }
    
    for (int i = 0; i < numsSize; i++) {
        for (int j = 0; j < i; j++) {
            if (*(nums + i) > *(nums + j)) {
                if (dp[i] < (dp[j] + 1)) {
                    dp[i] = dp[j] + 1;
                    cnt[i] = cnt[j];
                } else if (dp[i] == (dp[j] + 1)) {
                    cnt[i] += cnt[j];
                } else {
                }
                dp[i] = dp[i] > (dp[j] + 1) ? dp[i] : dp[j] + 1;
            }
        }

        max = max > dp[i] ? max : dp[i];
    }
    
    //debug
    //for (int i = 0; i < numsSize; i++) {
    //    printf("%d ", dp[i]);
    //}
    //printf("\n"); 
    //for (int i = 0; i < numsSize; i++) {
    //    printf("%d ", cnt[i]);
    //}
    //
    //printf("\nMax Length is %d.\n", max);
    int count = 0;
    for (int i = 0; i < numsSize; i++) {
        if (dp[i] == max) {
            count += cnt[i];
        }
    }

    return count; 
}

int main(int argc, char *argv[]) {
    int numsSize;
    scanf("%d\n", &numsSize);
    int input[numsSize];
    memset(input, 0, sizeof(int) * numsSize);
    for (int i = 0; i < numsSize; i++) {
        scanf("%d", &input[i]);
    }
    //int input[] = {10,9,2,5,3,7,101,18};  
    //int *p;
    //p = input;
    printf("Max length count of strict increasing subsequence is: %d\n", lengthOfLIS(input, numsSize));
    
    return 0;
}
