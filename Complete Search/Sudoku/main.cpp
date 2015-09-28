#include <iostream>
#include<stdio.h>
#include<stdlib.h>

using namespace std;
int n,rows[10]={0},cols[10]={0},squares[10]={0},grid[10][10],result[10][10];
bool flag=false;

void generation(int row,int col,int square){
    if(flag)
        return;
    if(col==n*n)
        generation(row+1,0,square);
    if(flag)
        return;
    if(row==n*n){
        flag=true;
        for(int i=0;i<n*n;i++)
            for(int j=0;j<n*n;j++)
                result[i][j]=grid[i][j];
        return;
    }
    if(row/n==0 && col/n==0)
        square=1;
    else if(row/n==0 && col/n==1)
        square=2;
    else if(row/n==0 && col/n==2)
        square=3;
    else if(row/n==1 && col/n==0)
        square=4;
    else if(row/n==1 && col/n==1)
        square=5;
    else if(row/n==1 && col/n==2)
        square=6;
    else if(row/n==2 && col/n==0)
        square=7;
    else if(row/n==2 && col/n==1)
        square=8;
    else if(row/n==2 && col/n==2)
        square=9;

    if(grid[row][col]==0){
        for(int i=1;i<=n*n;i++){
            if( (((rows[row])&(1<<i))==0) && (((cols[col])&(1<<i))==0) && (((squares[square])&(1<<i))==0)){
                grid[row][col]=i;
                rows[row]|=(1<<i);
                cols[col]|=(1<<i);
                squares[square]|=(1<<i);
                generation(row,col+1,square);
                if(flag)
                    return;
                grid[row][col]=0;
                rows[row]&=(~(1<<i));
                cols[col]&=(~(1<<i));
                squares[square]&=(~(1<<i));
            }
        }
    }
    else
        generation(row,col+1,square);
    if(flag)
        return;

}

int main()
{
    bool araf=false;

    while(scanf("%d",&n)!=EOF){
        if(araf)
            printf("\n");
        araf=true;
        for(int i=0;i<n*n;i++)
            for(int j=0;j<n*n;j++)
                scanf("%d",&grid[i][j]);

        flag=false;

        for(int i=0;i<10;i++)
            rows[i]=cols[i]=squares[i]=0;

        for(int i=0;i<n*n;i++){
            for(int j=0;j<n*n;j++){
                if(grid[i][j]!=0){
                    rows[i]|=(1<<grid[i][j]);
                    cols[j]|=(1<<grid[i][j]);
                    int square=-1;
                    if(i/n==0 && j/n==0)
                        square=1;
                    else if(i/n==0 && j/n==1)
                        square=2;
                    else if(i/n==0 && j/n==2)
                        square=3;
                    else if(i/n==1 && j/n==0)
                        square=4;
                    else if(i/n==1 && j/n==1)
                        square=5;
                    else if(i/n==1 && j/n==2)
                        square=6;
                    else if(i/n==2 && j/n==0)
                        square=7;
                    else if(i/n==2 && j/n==1)
                        square=8;
                    else if(i/n==2 && j/n==2)
                        square=9;
                    squares[square]|=(1<<grid[i][j]);
                }
            }
        }

        generation(0,0,0);
        if(flag){
            for(int i=0;i<n*n;i++){
                for(int j=0;j<n*n;j++){
                    if(j==n*n-1)
                        printf("%d\n",grid[i][j]);
                    else
                        printf("%d ",grid[i][j]);
                }
            }
        }

        else
            printf("NO SOLUTION\n");

    }
    return 0;
}
