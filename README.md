# Flam-Assignment-2

## Problems Overview

### Problem 1: N-Queens Problem

**Problem Statement:**  
Place `n` queens on an `n x n` chessboard such that no two queens attack each other.

**Input:**  
An integer `n`.

**Output:**  
All distinct solutions as lists of strings representing the board.

**Approach:**  
- Uses **Backtracking**.
- Places queens one by one in different columns of each row.
- Checks for conflicts in columns and diagonals.
- Recursively backtracks to explore all valid board configurations.

**Time Complexity:** `O(N! * N)`  
**Space Complexity:** `O(N^2)`

---

### Problem 2: Module Dependency — Cycle Detection in Directed Graph

**Problem Statement:**  
Detect if there exists a circular dependency in a directed graph representing software modules and their dependencies.

**Input:**  
- An integer `n` (number of modules)
- A list of dependency edges.

**Output:**  
Returns `true` if a cycle exists, otherwise `false`.

**Approach:**  
- Implements **Kahn's Algorithm (BFS Topological Sort)**.
- Builds an adjacency list and computes indegrees.
- Uses a queue to process nodes with zero indegree.
- If not all nodes are processed, a cycle exists.

**Time Complexity:** `O(V + E)`  
**Space Complexity:** `O(V + E)`

---

### Problem 3: WeatherTrack — Android Weather App (Java + MVVM)

**Problem Statement:**  
Develop an Android app that:
- Fetches weather data from a mock API.
- Stores it locally in a Room database every 6 hours.
- Displays weekly summaries with detailed stats.

**Key Features:**
- **Fetch Weather:** Get weather data (temperature, humidity, condition) from a mock API.
- **Auto Background Sync:** WorkManager job runs every 6 hours.
- **Weekly Summary Screen:** Shows temperature trends graphically.
- **MVVM Architecture:** Clean separation of ViewModel, Repository, and Data Sources.
- **Error Handling:** User-friendly messages for no internet/API/database errors.

**Technologies:**
- Java
- Android Room Database
- WorkManager
- MVVM Architecture
