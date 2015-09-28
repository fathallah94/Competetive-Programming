#include <iostream>
#include<stdio.h>
#include<stdlib.h>

using namespace std;

long long factorial (int n){
    long long res=1;
    for(int i=2;i<=n;i++)
        res*=i;
    return res;
}
long long C(int n,int r){
    return factorial(n)/(factorial(n-r)*factorial(r));
}
long long dearrange(int n){
    long long res=factorial(n);
    int sign=-1;
    for(int i=1;i<=n;i++){
        res+=sign*(C(n,i)*factorial(n-i));
        sign*=-1;
    }
    return res;
}

int main()
{
    int n,m;
    while(scanf("%d",&n)!=EOF){
        scanf("%d",&m);
        long long result=0;
        for(int i=0;i<=m;i++){
            result+=C(n,i)*dearrange(n-i);
        }
        printf("%lld\n",result);
    }
    return 0;
}
