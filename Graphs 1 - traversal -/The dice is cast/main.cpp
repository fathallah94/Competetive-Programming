#include <iostream>
#include<stdio.h>
#include<stdlib.h>
using namespace std;
char pic[55][55]={'\0'};
bool adjDice[2505][2505]={false},adjX[2505][2505]={false},visited[2505]={false},visitedX[2505]={false};
int w,h,counter=0,counterX=0,counters[2505]={0},Xc=0;

void dfs(int i){
    visited[i]=true;
    for(int j=0;j<w*h;j++){
        if(adjDice[i][j] &&  pic[j/w][j%w]=='X' && pic[i/w][i%w]=='X'){
            adjX[i][j]=true;
            adjX[j][i]=true;
        }
        if(adjDice[i][j] && !visited[j]){
            dfs(j);
        }
    }
}

void dfsX(int i){
    visitedX[i]=true;
    for(int j=0;j<w*h;j++){
        if(adjX[i][j] && !visitedX[j]){
            dfsX(j);
        }
    }
}

int main()
{
    int cases=1;
    scanf("%d%d",&w,&h);
    while(w!=0 && h!=0){
        for(int i=0;i<2505;i++){
            for(int j=0;j<2505;j++){
                adjDice[i][j]=false;
                adjX[i][j]=false;
            }
            visited[i]=false;
            visitedX[i]=false;
            counters[i]=0;
        }
        counter=counterX=Xc=0;
        for(int i=0;i<h;i++)
            scanf("%s",pic[i]);
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                if(pic[i][j]=='*' || pic[i][j]=='X'){
                    if(i-1>=0 && (pic[i-1][j]=='*' || pic[i-1][j]=='X')){
                        adjDice[i*w+j][(i-1)*w+j]=true;
                        adjDice[(i-1)*w+j][i*w+j]=true;
                    }
                    if(i+1<h && (pic[i+1][j]=='*' || pic[i+1][j]=='X')){
                        adjDice[i*w+j][(i+1)*w+j]=true;
                        adjDice[(i+1)*w+j][i*w+j]=true;
                    }
                    if(j-1>=0 && (pic[i][j-1]=='*' || pic[i][j-1]=='X')){
                        adjDice[i*w+j][i*w+j-1]=true;
                        adjDice[i*w+j-1][i*w+j]=true;
                    }
                    if(j+1<w && (pic[i][j+1]=='*' || pic[i][j+1]=='X')){
                        adjDice[i*w+j][i*w+j+1]=true;
                        adjDice[i*w+j+1][i*w+j]=true;
                    }
                }
            }
        }
        bool flag=false;
        for(int i=0;i<w*h;i++){
            if(!visited[i] && (pic[i/w][i%w]=='*' ||pic[i/w][i%w]=='X')){
                dfs(i);
                counter++;

                for(int j=0;j<w*h;j++){
                    if(!visitedX[j] && pic[j/w][j%w]=='X' && visited[j]){
                        dfsX(j);
                        counterX++;
                    }
                }
                counters[Xc++]=counterX;
                counterX=0;

            }

        }

        for(int i=0;i<Xc;i++){
            for(int j=0;j<Xc-1;j++){
                if(counters[j]>counters[j+1]){
                    int temp=counters[j];
                    counters[j]=counters[j+1];
                    counters[j+1]=temp;
                }
            }
        }
        printf("Throw %d\n",cases++);
        for(int i=0;i<Xc-1;i++)
            printf("%d ",counters[i]);
        printf("%d\n\n",counters[Xc-1]);
        scanf("%d%d",&w,&h);
    }
    return 0;
}
