#include <bits/stdc++.h>
using namespace std;

const int mx = 1e5;

bool vis[mx];
vector <int> graph[mx];

void bfs(int u) {
    queue <int> q;
    q.push(u);
    vis[u] = true;

    while (!q.empty()) {
        u = q.front();
        q.pop();
        cout << u << " ";
        for (int i = 0; i < graph[u].size(); i++) {
            int v = graph[u][i];
            if (!vis[v]) {
                q.push(v);
                vis[v] = true;
            }
        }
    }
}

int main() {

    int node = 5, edge = 7;
    int u [] = { 0,1,1,2,3,4,5};
    int v [] = { 2,2,3,4,5,0,2};
  
    for(int i = 0; i < edge; i++) {
        graph[u[i]].push_back(v[i]);
    }
    for (int i = 0; i < node; i++) {
        if (!vis[i]) {
            bfs(i);
        }
    }
    cout << "\n";
    return 0;
}
