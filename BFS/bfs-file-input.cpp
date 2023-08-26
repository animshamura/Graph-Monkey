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
    freopen("in", "r", stdin);

    int node, edge, u, v;
    cin >> node >> edge;
    while (edge--) {
        cin >> u >> v;
        graph[u].push_back(v);
    }
    for (int i = 0; i < node; i++) {
        if (!vis[i]) bfs(i);
    }
    cout << "\n";
    return 0;
}
