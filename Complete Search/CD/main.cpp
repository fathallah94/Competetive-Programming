#include <iostream>
#include<stdio.h>
#include<stdlib.h>

using namespace std;

int main()
{
    int n,arr[50]={0},num;
    while(scanf("%d",&n)!=EOF){
        scanf("%d",&num);
        for(int i=0;i<num;i++)
            scanf("%d",&arr[i]);

        int maxi=-1,max=-1;
        for(int i=0;i<(1<<num);i++){
            int sum=0;
            for(int j=0;j<num;j++){
                if(((1<<j)&i)!=0)
                    sum+=arr[j];
            }
            if(sum>max && sum<=n){
                maxi=i;
                max=sum;
            }
        }
        for(int i=0;i<num;i++){
            if(((1<<i)&maxi)!=0)
                printf("%d ",arr[i]);
        }
        printf("sum:%d\n",max);

    }
    return 0;
}
