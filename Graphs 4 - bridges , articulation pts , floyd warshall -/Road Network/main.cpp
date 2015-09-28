#include <iostream>
#include<stdio.h>
#include<stdlib.h>
#include<algorithm>
#include<math.h>

using namespace std;
int n,q,p;
int ranking[155],dist[155][155][155];

int main(){
    int cases;
    scanf("%d",&cases);
    for(int z=1;z<=cases;z++){
        scanf("%d",&n);
        for(int i=1;i<=n;i++)
            dist[0][i][i]=0;

        for(int i=1;i<=n;i++){
            for(int j=i+1;j<=n;j++){
                int temp;
                scanf("%d",&temp);
                dist[0][i][j]=temp;
                dist[0][j][i]=temp;
                if(dist[0][i][j]==-1)
                    dist[0][i][j]=dist[0][j][i]=100000;
            }
        }

        scanf("%d",&p);
        for(int i=0;i<p;i++)
            scanf("%d",&ranking[i]);

        for(int k=0;k<p;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    dist[k+1][i][j]=min(dist[k][i][ranking[k]]+dist[k][ranking[k]][j],dist[k][i][j]);
                }
            }
        }
        scanf("%d",&q);
        int w,from,to;
        printf("Case %d:",z);
        for(int i=0;i<q;i++){
            scanf("%d%d%d",&w,&from,&to);
            if(dist[w][from][to]==100000)
                printf(" -1");
            else
                printf(" %d",dist[w][from][to]);
        }
        printf("\n");
    }

    return 0;
}
