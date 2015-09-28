#include <iostream>
#include<stdio.h>
#include<stdlib.h>
using namespace std;
int n,counter=0;
char arr[30][30]={'\0'};
bool adj[630][630]={false},visited[630]={false};

void dfs(int i){
    visited[i]=true;
    for(int j=0;j<n*n;j++){
        if(!visited[j] && adj[i][j]){
            visited[j]=true;
            dfs(j);
        }
    }
}

int main()
{
    int cases=1;
    while(scanf("%d",&n)!=EOF){
        for(int i=0;i<=n*n;i++){
            visited[i]=false;
            for(int j=0;j<=n*n;j++)
                adj[i][j]=false;
        }

        counter=0;

        for(int i=0;i<n;i++)
            scanf("%s",arr[i]);

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                    if(i-1>=0){
                        if(arr[i-1][j]=='1'){
                            adj[i*n+j][(i-1)*n+j]=true;

                        }
                        if(j-1>=0 && arr[i-1][j-1]=='1'){
                            adj[i*n+j][(i-1)*n+j-1]=true;

                        }
                        if(j+1<n && arr[i-1][j+1]=='1'){
                            adj[i*n+j][(i-1)*n+j+1]=true;

                        }
                    }
                    if(i+1<n){
                        if(arr[i+1][j]=='1'){
                            adj[i*n+j][(i+1)*n+j]=true;

                        }
                        if(j-1>=0 && arr[i+1][j-1]=='1'){
                            adj[i*n+j][(i+1)*n+j-1]=true;

                        }
                        if(j+1<n && arr[i+1][j+1]=='1'){
                            adj[i*n+j][(i+1)*n+j+1]=true;

                        }
                    }
                    if(j-1>=0 && arr[i][j-1]=='1'){
                        adj[i*n+j][i*n+j-1]=true;

                    }
                    if(j+1<n && arr[i][j+1]=='1'){
                        adj[i*n+j][i*n+j+1]=true;

                    }


            }
        }

        for(int i=0;i<n*n;i++){
            if(!visited[i] && arr[i/n][i%n]=='1'){
                dfs(i);
                counter++;
            }
        }
        printf("Image number %d contains %d war eagles.\n",cases,counter);
        cases++;
    }
    return 0;
}
