#include <iostream>
#include<stdio.h>
#include<stdlib.h>
using namespace std;

long long primes[664580];
int v[10000001]={0};
int selection[20],needed;
bool taken[20]={false};

bool check(int n){
    bool flag=false;
    for(int i=0;i<664580 && primes[i]<=n ;i++){
        if(primes[i]==n){
            flag=true;
            break;
        }
    }
    return flag;
}

void permutations(int index){
    if(index==needed){
        for(int i=0;i<needed-1;i++)
            printf("%d ",selection[i]);
        printf("%d\n",selection[needed-1]);
    }

    for(int i=2;i<=needed;i++){
        if(!taken[i] && i%2!=selection[index-1]%2){
            if(( index!=needed-1  && check(selection[index-1]+i) ) || (index==needed-1 && i%2==0 && check(1+i) && check(i+selection[index-1])) ){
                taken[i]=true;
                selection[index]=i;
                permutations(index+1);
                taken[i]=false;
            }

        }
    }
}
int main(){

    //sieve
    v[0]=v[1]=1;
    long long i, j;
    for (i = 2; i < 35 ; i++) {
        if (!v[i]) {
            for (j = i * i; j <35 ; j += i) {
                v[j] = 1;
            }
        }
    }

    int index = 0;
    for (i = 2; i < 35; i++) {
        if (!v[i]) {
            primes[index++] = i;
        }
    }
    //
    selection[0]=1;
    int counter=0;
    while(scanf("%d",&needed)!=EOF){
        if(counter!=0)
            printf("\n");

      for(int i=0;i<20;i++)
            taken[i]=false;
        counter++;
        printf("Case %d:\n",counter);
        permutations(1);
    }


    return 0;
}
