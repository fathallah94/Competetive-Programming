#include <iostream>
#include<stdio.h>
#include<stdlib.h>

using namespace std;

bool adj[105][105]={false},visited[105]={false};
int n,m;

void dfs(int i){
    visited[i]=true;
    for(int j=1;j<=n;j++){
        if(adj[i][j] && !visited[j])
            dfs(j);
    }
}

int main()
{
    scanf("%d%d",&n,&m);
    for(int i=0;i<m;i++){
        int temp,temp2;
        scanf("%d%d",&temp,&temp2);
        adj[temp][temp2]=true;
        adj[temp2][temp]=true;
    }
    if(n!=m)
        printf("NO\n");
    else{
        dfs(1);
        bool flag=true;
        for(int i=1;i<=n;i++){
            if(!visited[i]){
                flag=false;
                break;
            }
        }
        if(flag)
            printf("FHTAGN!\n");
        else
            printf("NO\n");
    }
    return 0;
}
