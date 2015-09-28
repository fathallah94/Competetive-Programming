#include <iostream>
#include<stdio.h>
#include<stdlib.h>

using namespace std;
int dist[1005],edges[2005][4],n,m;

bool cycle(int s,int t){
    for(int i=0;i<n;i++)
        dist[i]=1e9;

    dist[s]=0;
    for(int i=0;i<n-1;i++){
        for(int j=0;j<m;j++){
            if(dist[edges[j][0]]+edges[j][2]<dist[edges[j][1]])
                dist[edges[j][1]]=dist[edges[j][0]]+edges[j][2];
        }
    }
    for(int j=0;j<m;j++){
        if(dist[edges[j][0]]+edges[j][2]<dist[edges[j][1]])
            return true;
    }
    return false;
}

int main()
{
    int c;
    scanf("%d",&c);
    for(int z=0;z<c;z++){
        scanf("%d%d",&n,&m);
        for(int i=0;i<m;i++){
            int temp,temp2,temp3;
            scanf("%d%d%d",&temp,&temp2,&temp3);
            edges[i][0]=temp;
            edges[i][1]=temp2;
            edges[i][2]=temp3;
        }
        if(cycle(0,n-1))
            printf("possible\n");
        else
            printf("not possible\n");
    }
    return 0;
}
