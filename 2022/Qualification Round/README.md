# Qualification Round 2022

# [Punched Cards]([url](https://codingcompetitions.withgoogle.com/codejam/round/0000000000876ff1/0000000000a4621b))

To make our punched card, we can simply use nested for-loop, in which odd rows alternate between '+' and '-' while even rows alternate between '|' and '.'. We just have to modify the top left corner, and thus the overall time complexity would be O(R * C)

# [3D Printing]([url](https://codingcompetitions.withgoogle.com/codejam/round/0000000000876ff1/0000000000a4672b))

Printer i has ink cartridge of $C_i, M_i, Y_i, K_i$ units (i is from 1 to 3). Our objective is to determine $c, m, y, k$ such that

1. $c + m + y + k = 10^6$
2. $x <= X_i$ for color $x$ and $i = 1, 2, 3$

Let $C, M, Y, K$ be the minimum cartridge of each color. (i.e. $C = \min(C_1, C_2, C_3)$ ). Then it is only possible to output our answer if $C + M + Y + K \ge 10^6$ In such cases, we simply set $c, m, y, k$ that satisfies the above condition.

# [d1000000]([url](https://codingcompetitions.withgoogle.com/codejam/round/0000000000876ff1/0000000000a46471))

Given N dice with different number of sides, we must create the longest "straight line." In order to maximize the "straight line," the series of consecutive numbers should start from 1. For the sake of simplicity, we first sort S in increasing order. Suppose up to S<sub>idx</sub>, we can make a straight line from 1 to L. If S<sub>idx + 1</sub> is greater than or equal to L + 1, then we can set (idx + 1)<sup>th</sup> dice to L + 1, which will increase our length of straight line by 1. We can thus iteratively calculate the longest straight line in S.

1. Sort S
2. for i = 1 to N, if S[i] >= L + 1, increase L by 1

# [Chain Reaction]([url](https://codingcompetitions.withgoogle.com/codejam/round/0000000000876ff1/0000000000a45ef7))

We first construct a graph G = (V, E) where verticies V are integers 0 to N (0 means abyss) and edges E consists of edges (u -> v) where module u leads to module v with u > v. A unique characteristic about this graph is that out-degree of every vertices is 1, as each module only triggers one other module. Let's first consider a  simple scenario of when every module points to abyss. In that case, regardless of the order that Wile starts the initiator, the total fun value will be  F[1] + ... + F[N]. 

For the generic case, we ought to reduce the graph G into such form, while not altering the maximum fun value. Lets consider a vertex v where all the vertices that points to v are initiators. We will name those vertices as u<sub>1</sub>, ... u<sub>m</sub>. Observe that after the first initiator has been triggered, the remaining m - 1 modules won't trigger a chain reaction, as v is already initiated. Thus, if the first initiator is u<sub>j</sub>, then the total fun value would be

$$\max(F[u_j], F[v]) + \sum_{i = 1 | i \neq j}^m F[u_i]$$

In order to maximize the above quantity, we must minimize $F[u_j]$, in which u<sub>j</sub> will be the vertex with the least fun value. To iterate onto the next vertex, we will set the fun value of u<sub>j</sub> as $\max(F[u_j], F[v])$ and module u<sub>j</sub> to point pointer[v]. Setting pointer[u<sub>j</sub> as pointer[v] will eliminate unnecessary vertices, as vertex v will be initiated by vertex u<sub>j</sub>. Because G is a tree, as there are N + 1 verticies and N edges, we can iterate the above procedure from the leaf. Furthermore, for all edge (u -> v), u is greater than v. Thus we can simply iterate from N to 1.

1. Construct G = (V, E) and predetermine the initiators. Let pointer[v] be the module that module v points to.
2. for u = n to 1, if u is not an initiator, 
  a. find vertex x where pointer[x] is u and it has the smallest fun value.
  b. set F[x] <- max(F[x], F[v]
  c. set pointer[x] <- pointer[v]
  
From sorting, the overall time complexity of above algorithm will be O(N * log N). Since F[v] is bounded by $10^9$, one can further reduce the time complexity to O(N) via base-10 radix sort.
