#include <iostream>
#include<stdio.h>
#include<stdlib.h>
using namespace std;
int n,l;
bool arr[205][205]={false};
int colors[205]={0};

bool isBipartite(int i){
    bool answer=true;
    for(int j=0;j<n;j++){
        if(arr[i][j]){
            if(colors[i]==colors[j])
                return false;
            else if(colors[j]==0){
                colors[j]=3-colors[i];
                answer = answer && isBipartite(j);
            }
        }

    }
    return answer;
}
bool checkBipartite(){
    for(int i=0;i<n;i++){
        if(colors[i]==0){
            colors[i]=1;
            if(!isBipartite(i))
                return false;
        }
    }
    return true;
}

int main()
{
    scanf("%d",&n);
    while(n!=0){
        scanf("%d",&l);

        for(int i=0;i<205;i++){
            colors[i]=0;
            for(int j=0;j<205;j++)
                arr[i][j]=false;
        }

        int temp,temp2;
        for(int i=0;i<l;i++){
            scanf("%d%d",&temp,&temp2);
            arr[temp][temp2]=true;
            arr[temp2][temp]=true;
        }
        if(checkBipartite())
            printf("BICOLORABLE.\n");
        else
            printf("NOT BICOLORABLE.\n");
        scanf("%d",&n);

    }
    return 0;
}
