#include <iostream>
#include<stdio.h>
#include<stdlib.h>

using namespace std;

int arr[10]={0},selection[10],cases[130][10],counter=0;
char temp[4][3],signs[150][5];
bool taken[10]={false};

void permutations(int index){
    if(index==5){
        for(int i=0;i<5;i++)
            cases[counter][i]=selection[i];
        counter++;

        return;
    }
    for(int i=0;i<5;i++){
        if(!taken[i]){
            selection[index]=arr[i];
            taken[i]=true;
            permutations(index+1);
            taken[i]=false;
        }
    }
}

int main()
{
    for(int i=0;i<4;i++){
        temp[i][0]='+';
        temp[i][1]='-';
        temp[i][2]='*';
    }
    int signCounter=0;
    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            for(int k=0;k<3;k++){
                for(int l=0;l<3;l++){
                    signs[signCounter][0]=temp[0][i];
                    signs[signCounter][1]=temp[1][j];
                    signs[signCounter][2]=temp[2][k];
                    signs[signCounter][3]=temp[3][l];
                    signCounter++;
                }
            }
        }
    }
    for(int i=0;i<5;i++)
        scanf("%d",&arr[i]);
    while(arr[0]!=0 && arr[1]!=0 && arr[2]!=0 && arr[3]!=0 && arr[4]!=0){
        counter=0;
        for(int i=0;i<10;i++)
            taken[i]=false;

        permutations(0);
        bool flag=false;
        for(int i=0;i<counter;i++){
            if(flag)
                break;
            for(int j=0;j<signCounter;j++){
                int sum=cases[i][0];
                for(int k=0;k<4;k++){
                    if(signs[j][k]=='+')
                        sum+=cases[i][k+1];
                    else if(signs[j][k]=='-')
                        sum-=cases[i][k+1];
                    else if(signs[j][k]=='*')
                        sum*=cases[i][k+1];
                }
                if(sum==23){
                    flag=true;
                    break;
                }
            }

        }
        if(flag)
            printf("Possible\n");
        else
            printf("Impossible\n");
        for(int i=0;i<5;i++)
            scanf("%d",&arr[i]);
    }
    return 0;
}
