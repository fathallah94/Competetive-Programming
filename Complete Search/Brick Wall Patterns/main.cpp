#include <iostream>
#include<stdio.h>
#include<stdlib.h>

using namespace std;

int counter(int n){
    if(n<=1)
        return 1;
    return counter(n-1)+counter(n-2);
}

int main()
{
    int n;
    scanf("%d",&n);
    while(n!=0){
        printf("%d\n",counter(n));
        scanf("%d",&n);
    }
    return 0;
}
