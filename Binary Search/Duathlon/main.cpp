#include <iostream>
#include<stdio.h>
#include<stdlib.h>
#include<limits.h>

using namespace std;

int t,n;
double times[25],arr[25][2];
bool flag=false;

double predicate(double r){
    double k=t-r;
    for(int i=0;i<n;i++){
        times[i]=(r/arr[i][0])+(k/arr[i][1]);
    }
    double minim=times[0];
    int mini=0;
    for(int i=1;i<n;i++){
        if(times[i]<minim){
            minim=times[i];
            mini=i;
        }
    }
    double sminim=1e9;
    int smini=-1;
    for(int i=0;i<n;i++){
        if(i!=mini && times[i]<sminim){
            sminim=times[i];
            smini=i;
        }
    }
    if(mini==n-1){
        flag=true;
        return times[n-1]-times[smini];
    }
    return times[n-1]-times[mini];
}

double ts(){
    double lo=0,hi=t,m1,m2;
    for(int i=0;i<50;i++){
        m1=lo+(hi-lo)/3;
        m2=lo+2*(hi-lo)/3;
        if(predicate(m1)<predicate(m2))
            hi=m2;
        else
            lo=m1;
    }
    return lo;
}

int main()
{
    while(scanf("%d",&t)!=EOF){
        scanf("%d",&n);
        flag=false;
        for(int i=0;i<n;i++){
            double temp,temp2;
            scanf("%lf%lf",&temp,&temp2);
            arr[i][0]=temp;
            arr[i][1]=temp2;
        }
        double res=ts(),sec=predicate(res)*60*60;
        if(sec<0) sec*=-1;
        if(flag)
            printf("The cheater can win by %.0lf seconds with r = %.2lfkm and k = %.2lfkm.\n",sec,res,t-res);
        else
            printf("The cheater cannot win.\n");

    }
    return 0;
}
