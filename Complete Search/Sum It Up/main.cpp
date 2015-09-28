#include <iostream>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

using namespace std;

int   result[100000][50]={0};
int resCounter=0,one[100000]={0};

int main()
{
    int n,t,arr[20]={0};
    scanf("%d%d",&t,&n);
    while(n!=0){
        resCounter=0;
        printf("Sums of %d:\n",t);

        bool flag=false;

        for(int i=0;i<n;i++)
            scanf("%d",&arr[i]);


        for(int i=0;i<(1<<n);i++){
            int ones=0,sum=0;
            for(int j=0;j<n;j++){
                if(((1<<j)&i)!=0){
                    sum+=arr[j];
                    ones++;
                }
            }
            if(sum==t){

                flag=true;
                one[resCounter]=ones;
                int counter=0;
                for(int j=0;j<n;j++){
                    if(((1<<j)&i)!=0){
                        result[resCounter][counter++]=arr[j];
                    }
                }
                resCounter++;
            }
        }
        if(!flag)
            printf("NONE\n");
        else{
            for(int i=0;i<resCounter;i++){
                for(int j=0;j<resCounter-1;j++){
                    int k;
                    for(k=0;k<max(one[j],one[j+1]) && result[j][k]==result[j+1][k];k++){}
                    if(result[j+1][k]>result[j][k]){
                        int temp;
                        for(int l=0;l<max(one[j],one[j+1]);l++){
                            temp=result[j][l];
                            result[j][l]=result[j+1][l];
                            result[j+1][l]=temp;
                            temp=one[j];
                            one[j]=one[j+1];
                            one[j+1]=temp;
                        }
                    }
                }
            }

            int i,j;
            for( i=0;i<resCounter;i++){
                for( j=i+1;j<resCounter;j++){
                    bool flag=true;
                    if(one[j]==one[i]){
                        for(int k=0;k<one[j];k++){
                            if(result[i][k]!=result[j][k]){
                                flag=false;
                                break;
                            }
                        }
                    }
                    else
                        flag=false;

                    if(!flag)
                        break;
                    else
                        one[j]=-1;
                }
                i=j-1;
            }
            for(i=0;i<resCounter;i++){
                if(one[i]!=-1){
                    for(j=0;j<one[i]-1;j++){
                        printf("%d+",result[i][j]);
                    }
                    printf("%d\n",result[i][j]);
                    }
            }
        }
        scanf("%d%d",&t,&n);
    }
    return 0;
}
