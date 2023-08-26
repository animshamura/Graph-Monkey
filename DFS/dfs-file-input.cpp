#include <bits/stdc++.h>
using namespace std;

const int mx = 1e5;

bool vis[mx];
vector <int> graph[mx];

void dfs(int u) {
    cout << u << " ";
    vis[u] = true;

    for (int i = 0; i < graph[u].size(); i++) {
        int v = graph[u][i];
        if (!vis[v]) {
            dfs(v);
        }
    }
}

int main() {
    freopen("in", "r", stdin);

    int node, edge, u, v;
    cin >> node >> edge;
    while (edge--) {
        cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }
    for (int i = 0; i < node; i++) {
        if (!vis[i]) {
            dfs(i);
        }
    }
    cout << "\n";
    return 0;
}
