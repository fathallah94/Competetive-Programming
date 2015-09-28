#include <iostream>
#include<stdio.h>
#include<stdlib.h>
#include<math.h>

using namespace std;

double counts(long long n,long long k){
    double res=0;
    for(long long i=n;i>k;i--){
        res+=log10(i);
    }
    return res;
}

double counts2(long long n){
    double res=0;
    for(long long i=n;i>=1;i--){
        res+=log10(i);
    }
    return res;
}


int main()
{
    long long n,k;
    while(scanf("%lld",&n)!=EOF){
        scanf("%lld",&k);
        double result=counts(n,max(n-k,k))-counts2(min(n-k,k))+1;
        printf("%.f\n",floor(result));
    }
    return 0;
}
