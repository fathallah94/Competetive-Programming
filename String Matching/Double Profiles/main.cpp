#include <iostream>
#include<stdio.h>
#include<stdlib.h>
#include<vector>
#include<algorithm>

using namespace std;

int n,m;
vector<int> adj[1000000+10];
long long b = 10007,b2=100003, mod = 10000000019LL , mod2 = 100000004987LL;
vector< pair<long long,long long> > values ;
vector< pair<long long,long long> > values2 ;

int main()
{
    scanf("%d%d",&n,&m);

    for(int i=0;i<m;i++){
        int from,to;
        scanf("%d%d",&from,&to);
        adj[from].push_back(to);
        adj[to].push_back(from);
    }
    for(int i=1;i<=n;i++){
        if(adj[i].size()==0){
            values.push_back(make_pair(0LL,0LL));
            values.push_back(make_pair((long long)i, (long long )i));
            continue;
        }
        adj[i].push_back(i);
        sort(adj[i].begin(),adj[i].end());
        long long hash1=0,hash2=0;

        long long hash12=0 , hash22=0;
        for(int j=0;j<adj[i].size();j++){
            hash1 = (hash1*b+adj[i][j])%mod;
            hash12 = (hash12*b2 + adj[i][j])%mod2;
            if(adj[i][j]!=i){
                hash2 = (hash2*b + adj[i][j])%mod;
                hash22 = (hash22*b2+adj[i][j])%mod;
            }
        }
        values.push_back(make_pair(hash1,hash12));
        values2.push_back(make_pair(hash2,hash22));
    }
    sort(values.begin(),values.end());
    sort(values2.begin(),values2.end());
    long long cnt =1,res=0;
    for(int i=1;i<values.size();i++){
        if(values[i].first-values[i-1].first==0 && values[i].second-values[i-1].second==0)cnt++;
        else{
            res+=cnt*(cnt-1)/2;
            cnt=1;
        }
    }
    res+=cnt*(cnt-1)/2;
    cnt=1;
    for(int i=1;i<values2.size();i++){
        if(values2[i].first-values2[i-1].first==0 && values2[i].second-values2[i-1].second==0)cnt++;
        else{
            res+=cnt*(cnt-1)/2;
            cnt=1;
        }
    }
    res+=cnt*(cnt-1)/2;
    printf("%I64d\n",res);
    return 0;
}
