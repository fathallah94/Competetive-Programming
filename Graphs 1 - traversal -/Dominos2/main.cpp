#include <iostream>
#include<stdio.h>
#include<stdlib.h>

using namespace std;
int arr[10005][10005]={0},ls[10005],sizes[10005]={0};
bool fall[10005]={false};

void dfs(int i){
    fall[i]=true;
    for(int j=0;j<sizes[i];j++){
        if(!fall[arr[i][j]])
            dfs(arr[i][j]);
    }
}

int main()
{
    int t,n,m,l;
    scanf("%d",&t);
    for(int z=0;z<t;z++){
        scanf("%d%d%d",&n,&m,&l);
        for(int i=0;i<=n;i++){
            fall[i]=false;
            sizes[i]=0;
        }
        int temp,temp2;
        for(int i=0;i<m;i++){
            scanf("%d%d",&temp,&temp2);
            arr[temp][sizes[temp]++]=temp2;
        }
        for(int i=0;i<l;i++){
            scanf("%d",&temp);
            dfs(temp);
        }
        int counter=0;
        for(int i=1;i<=n;i++){
            if(fall[i])
                counter++;
        }
        printf("%d\n",counter);
    }
    return 0;
}
