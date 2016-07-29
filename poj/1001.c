#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char *result;   //Multi()函数得到的字符数组结果
int length;     //Mutli()函数得到的字符数组长度

/*该函数是用来计算两个大数的相乘*/
void Multi( char *str1, char *str2 )
{
    int len1, len2, i, j;
    int * s;
    
    len1 = length;
    len2 = strlen(str2);

    s = (int *)malloc( sizeof(int) * (len1 + len2 ) );

    for(i = 0; i < len1 + len2; i++ )
        s[i] = 0;

    int *str1_int;
    int *str2_int;

    //将字符数组变换成int数组来计算
    str1_int = (int *)malloc( sizeof(int) * len1 );
    str2_int = (int *)malloc( sizeof(int) * len2 );

    for( i = 0; i < len1; i++ )
        str1_int[i] = str1[len1-1-i] - '0';

    for( i = 0; i < len2; i++ )
        str2_int[i] = str2[len2-1-i] - '0';

    //先相乘
    for( i = 0; i < len1; i++ )
        for( j = 0; j < len2; j++ )
            s[i+j] += str1_int[i] * str2_int[j];

    //处理进位
    for( i = 0; i < len1 + len2 - 1; i++ ){
            s[i + 1] += s[i] / 10;
            s[i] = s[i] % 10;
    }

    //去高位的零
    for(i = len1 + len2 - 1; (s[i] == 0) && (i >= 0); i--);
    length = i + 1;

    //重新分配result
    free(result);
    result = (char *)malloc(sizeof(char) * (i + 1));
    for(j = 0; j <=i; j++)
        result[j] = s[i-j] + '0';

}

/*
 * 解析一个字符串string
 * 去掉小数点，返回字符串的整数值的字符串形式str1
 * 同时返回小数点的位置dot
 */
void parse_float_string(char *string, char *str1, int *dot){
    int string_len = strlen(string);                                    
    int i;
    int flag;

    *dot = 0; 
    for(i=string_len-1; i>=0; i++){
        //从低位到高位     
        if(0 == (string[i] - '.')){
            *dot = 5 - i;          
            break;
        }
    }

    flag = 0; 
    for(i=0; i<string_len; i++){
        if( ((string[i] == '0') || (string[i] == '.')) && (flag == 0)){
            continue;         
        }
        flag = 1;
        if(string[i] != '.'){
            *str1++ = string[i];     
        }
    }
    *(str1++) = '\0';
}


int main( int argc, char *argv[] )
{
    char string[6];
    int num, i, t, j;
    char *str1;
    int *dom;
    int dom_length, length, len;
    str1 = (char *)malloc(6*sizeof(char *));
    while( scanf("%s%d", string, &num) ==2){
        parse_float_string(string, str1, dom);                 
        printf("%s", str1);

        length = strlen(str1);
        result = malloc(sizeof(char) * length);
        for(i = 0; i < strlen(str1); i++)
        {
            result[i] = str1[i];
        } 
        for(i = 1; i < num; i++)
            Multi(result, str1);
       
   
    /*把得到的整数打印成真正的小数结果*/
    dom_length = (*dom) * num;
    len = length;

    t = dom_length - len;
    if(t > 0){
        for(i = len - 1; (i > 0) && result[i] == '0';i--);
        printf("0.");
        for(j = 1; j <= t; j++)
            printf("0");
        for(j = 0; j <= i; j++)
            printf("%d", (result[j] - '0'));
    } else {
        for(i = len - 1; (i >= 0 - t) && result[i] == '0'; i--);
        for(j = 0; j <= ( -1 - t); j++)
            printf("%d", (result[j] - '0'));
        //printf("\n%d %d\n",i,( -1 - t));
        if( i == (-1 - t)){
         ;
        } else {
            printf(".");
            for(j = (0 - t); j <= i ; j++)
                printf("%d", (result[j] - '0'));
        }
    } 
    free(str1);    
    free(result);
} 
    return 0;
}
