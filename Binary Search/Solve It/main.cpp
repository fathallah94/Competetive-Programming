    #include <iostream>
    #include<stdio.h>
    #include<stdlib.h>
    #include<math.h>

    using namespace std;
    int p,q,r,s,t,u;

    double solve(double x){
    double answer=(q*sin(x))+(r*cos(x))+(s*tan(x))+(t*x*x)+u+(p*exp(-x));
    return answer;
    }

    double bs(){
    double lo=0,hi=1,mid;
    int i;
    for(i=0;i<200;i++){
    mid=lo+(hi-lo)/2;
    double ans=solve(mid);
    if(ans>0)
    lo=mid;
    else
    hi=mid;
    }
    return lo;
    }

    int main()
    {
    while(scanf("%d",&p)!=EOF){
    scanf("%d%d%d%d%d",&q,&r,&s,&t,&u);
    double x=bs();
    if(fabs(solve(x))>1e-10)
    printf("No solution\n");
    else
    printf("%.4lf\n",x);
    }
    return 0;
    }
