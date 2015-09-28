#include <iostream>
#include <stdio.h>
#include<stdlib.h>

using namespace std;

int n,m,arr[100000+5];

int compare (const void * a, const void * b)
{
  return ( *(int*)a - *(int*)b );
}
//qsort (arr2, n, sizeof(int), compare);

int test(double max){
    int needed=1;
    double distance=arr[0]+max;
    for(int i=1;i<m;i++){
        if(arr[i]-distance>max){
            distance=arr[i]+max;
            needed++;
        }
    }
    return needed;
}

double binarySearch(double lo,double hi){
    double mid;
    for(int i=0;i<200;i++){
        mid=(lo+hi)/2.0;
        if(test(mid)>n)
            lo=mid;
        else
            hi=mid;
    }
    return lo;
}

int main()
{
    int t;
    scanf("%d",&t);
    for(int z=0;z<t;z++){
        scanf("%d%d",&n,&m);
        for(int i=0;i<m;i++)
            scanf("%d",&arr[i]);
        qsort (arr, m, sizeof(int), compare);
        printf("%.1f\n",binarySearch(0,1000000));

    }
    return 0;
}
