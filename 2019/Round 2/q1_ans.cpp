# include <bits/stdc++.h>
using namespace std;
 
#define pb push_back
#define sz(v) ((int)(v).size())
#define all(v) (v).begin(), (v).end()
typedef pair<int, int> pii;
 
int T, N;
int A[301], B[301];
 
int gcd(int a, int b){ return b ? gcd(b, a%b) : a; }
 
void solve()
{
    scanf("%d", &N);
    for (int i=1;i<=N;i++) scanf("%d%d", A+i, B+i);
    vector <pii> arr;
    for (int i=1;i<=N;i++) for (int j=1;j<=N;j++){
        if (A[i] > A[j] && B[i] < B[j]){
            int a = B[j]-B[i], b = A[i]-A[j], g = gcd(a, b);
            a /= g, b /= g;
            arr.pb({a, b});
        }
    }
    sort(all(arr));
    arr.erase(unique(all(arr)), arr.end());
    printf("%d\n", sz(arr)+1);
}
 
int main()
{
    for (scanf("%d", &T);T--;){
        static int ts = 0; printf("Case #%d: ", ++ts);
        solve();
    }
}