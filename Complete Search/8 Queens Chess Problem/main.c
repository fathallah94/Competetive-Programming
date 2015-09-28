#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include<ctype.h>
#include<math.h>
/*int compare (const void * a, const void * b)
{
  return ( *(int*)a - *(int*)b );
}
//qsort (arr2, n, sizeof(int), compare);*/

int cases[94][10];
int board[10][10],counter=0;
int temp[10];

void queens(int column,int row,int diagonal1,int diagonal2){
    if(column==8){
        int z;
        for(z=0;z<8;z++)
            cases[counter][z]=temp[z];
        counter++;
        return;
    }
    int i;
    for(i=0;i<8;i++){
        if((row&(1<<i))==0 && (diagonal1&(1<<(column+i)))==0 && (diagonal2&(1<<(column-i+8)))==0 ) {
            row|=(1<<i);
            diagonal1|=(1<<(column+i));
            diagonal2|=(1<<(column-i+8));
            temp[column]=i;
            queens(column+1,row,diagonal1,diagonal2);
            row&=~(1<<i);
            diagonal1&=~(1<<(column+i));
            diagonal2&=~(1<<(column-i+8));
            temp[column]=-1;
        }
    }


}
int main(){
    queens(0,0,0,0);
    int k,z;
    scanf("%d",&k);
    for(z=0;z<k;z++){
        int n,m;
        scanf("%d%d",&n,&m);
        n--;
        m--;
        int i,j,counts=0;
        printf("SOLN       COLUMN\n #      1 2 3 4 5 6 7 8\n\n");
        for(i=0;i<92;i++){
            if(cases[i][m]==n){
                counts++;
                if(counts<10)
                    printf(" %d      ",counts);
                else
                    printf("%d      ",counts);
                for(j=0;j<8;j++){
                    if(j<7)
                        printf("%d ",cases[i][j]+1);
                    else if(j==7)
                        printf("%d\n",cases[i][j]+1);
                }
            }
        }
        if(z<k-1)
            printf("\n");
    }
    return 0;
}
