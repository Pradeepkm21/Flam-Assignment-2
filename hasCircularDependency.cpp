#include<bits/stdc++.h>
using namespace std;

//Function to check for circular dependencies in a directed graph.
//Implements Kahn's Algorithm (BFS Topological Sort).

///Time Complexity: (V + E)
///Space Complexity: O(V + E)

bool hasCircularDependency(int n, vector<vector<int>>& edges) {
    vector<int> indegree(n, 0);
    vector<vector<int>> adj(n);

    //create adjacency list and find indegree
    for (auto& edge : edges) {
        adj[edge[0]].push_back(edge[1]);
        indegree[edge[1]]++;
    }

    queue<int> q;
    //if nodes with zero indegree then add
    for (int i = 0; i < n; ++i)
        if (indegree[i] == 0)
            q.push(i);

    int nodes = 0;

    //Perform BFS
    while (!q.empty()) {
        int curr = q.front();
        q.pop();
        nodes++;

        for (int child : adj[curr]) {
            indegree[child]--;
            if (indegree[child] == 0)
                q.push(child);
        }
    }

    //If not all nodes were processed, a cycle exists
    return nodes != n;
}

int main(){

    /* #ifndef ONLINE_JUDGE
    freopen("input.txt","r", stdin);
    freopen("output.txt","w", stdout);
    #endif */


    int V, E;
    cout << "number of vertices V: ";
    cin >> V;

    cout << "number of edges E: ";
    cin >> E;

    vector<vector<int>> edges(E, vector<int>(2));

    cout << "Enter dependencies as pairs (a b) meaning a depends on b:" << endl;
    for(int i = 0; i < E; ++i){
        cin >> edges[i][0] >> edges[i][1];
    }

    if(hasCircularDependency(V, edges)){
        cout << "Cycle Detected" << endl;
    }
    else{
        cout << "No Cycle found" << endl;
    }

    return 0;
}