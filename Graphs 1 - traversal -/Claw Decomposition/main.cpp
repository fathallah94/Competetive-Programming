#include <iostream>
#include<stdio.h>
#include<stdlib.h>

using namespace std;
int n;
bool arr[305][305]={false};
int colors[305]={0};

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
    int a,b;
    scanf("%d",&n);
    while(n!=0){
        for(int i=0;i<305;i++){
            colors[i]=0;
            for(int j=0;j<305;j++)
                arr[i][j]=false;
        }
        scanf("%d%d",&a,&b);
        while(true){
            if(a==0 && b==0)break;
            arr[a][b]=true;
            arr[b][a]=true;
            scanf("%d%d",&a,&b);
        }
        if(checkBipartite())
            printf("YES\n");
        else
            printf("NO\n");
        scanf("%d",&n);
    }
    return 0;
}
