#include <iostream>
#include<stdio.h>
#include<stdlib.h>
#include<math.h>

using namespace std;

double x,y,c;
double predicate (double z){
    double h1=sqrt(x*x-z*z),h2=sqrt(y*y-z*z);

    return z*c*(1/h1+1/h2);
}

double bs(){
    double lo=0,hi=max(x,y),mid;
    for(int i=0;i<200;i++){
        mid=lo+(hi-lo)/2;
        if(predicate(mid)<mid)
            lo=mid;
        else
            hi=mid;
    }
    return lo;
}

int main()
{
    while(scanf("%lf",&x)!=EOF){
        scanf("%lf%lf",&y,&c);
        printf("%.3lf\n",bs());
    }
    return 0;
}
