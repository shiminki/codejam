# 2022 Round 2

# Spirialing Into Control Naive Solution

In the competition, I have implemented a naive solution with O(K * N^2) time complexity, resulting in partial credit. Observe that we are not searching for the optimal
route, as the minimum number of moves, which is N - 1, is not necessarily K. Thus, we must keep track of all possible routes while not implementing a solution with
an exponential time complexity. I have maintained a list of set P, where P[i] contains all the possible number of moves from 1 to i. Using P, we can use dynamic programming
to solve the problem in O(K * N^2) time. 

for k = 0 to K and i = 1 to N^2:
- let j be the adjacent block of i.
- if j > i and P[i] contains k, then add k + 1 to P[j] as we can travel up to i in k moves and then move from i to j

if P[N^2] contains K, then it is possible to move to N^2 in K moves.

Since there are K * N^2 subproblems, each taking O(1) time and space, the overall complexity is O(K * N^2). However, since N < 9,999 in test set 3, the above solution
is not efficient enough.


# Pixelated Circle Naive Solution

The naive implementation leads to a O(R^2) solution which is adequate for tese set 1 with R <= 100. However, such naive solution has to be further optimized for test set
2.
