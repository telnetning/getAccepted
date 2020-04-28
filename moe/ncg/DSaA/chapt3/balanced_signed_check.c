#include <stdio.h>
#include "stack_with_array.h"
#include <stdlib.h>

#define file_name "test_balanced_signed_check.txt"

int main()
{
    Stack S;
    S = CreateStack(1000);
    char ch;

    FILE *fp;
    fp = fopen(file_name, "r");    
    if(fp == NULL){
        Error("open file failed!"); 
    }
    while( (ch = fgetc( fp )) != EOF ){
        if( (ch == '(') || (ch =='[') || (ch == '{')){
            Push(ch, S); 
        }

        if( ch == ')' ){
            if(TopAndPop( S ) != '('){
                Error("wrong file."); 
                return -1;
            }        
        }
        if( ch == ']'){
            if(TopAndPop( S ) != '['){
                Error("wrong file."); 
                return -1;
            } 
        }
        if( ch == '}'){
            if(TopAndPop( S ) != '{'){
                Error("wrong file."); 
                return -1;
            } 
        }
    } //end while
    if(!IsEmpty( S )){
        Error("wrong file."); 
        return -1;
    }

    printf("right file.\n");
    return 0;
}
