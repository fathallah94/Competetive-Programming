#include <iostream>
#include<stdio.h>
#include<stdlib.h>
using namespace std;

int n,m,arr1[100005],arr2[100005];

long long predicate(int maxi){
    long long operations=0;
    for(int i=0;i<n;i++){
        if(arr1[i]<maxi)
            operations+=maxi-arr1[i];
    }
    for(int i=0;i<m;i++){
        if(arr2[i]>maxi)
            operations+=arr2[i]-maxi;
    }
    return operations;
}

long long ts(){
    int lo=0,hi=1e9,m1,m2;
    while(hi-lo>3){
        m1=lo+(hi-lo)/3;
        m2=lo+(hi-lo)*2/3;
        if(predicate(m1)>predicate(m2))
            lo=m1;
        else
            hi=m2;
    }
    for(int i=lo;i<=hi;i++){
        if(predicate(i)<predicate(lo))
            lo=i;
    }
    return predicate(lo);
}

int main()
{
    scanf("%d%d",&n,&m);
    for(int i=0;i<n;i++)
        scanf("%d",&arr1[i]);
    for(int i=0;i<m;i++)
        scanf("%d",&arr2[i]);

    printf("%lld\n",ts());
    return 0;
}
