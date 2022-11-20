# Qualification Round 2022

# [Punched Cards]([url](https://codingcompetitions.withgoogle.com/codejam/round/0000000000876ff1/0000000000a4621b))

To make our punched card, we can simply use nested for-loop, in which odd rows alternate between '+' and '-' while even rows alternate between '|' and '.'.
We just have to modify the top left corner, and thus the overall time complexity would be O(R * C)

# [3D Printing]([url](https://codingcompetitions.withgoogle.com/codejam/round/0000000000876ff1/0000000000a4672b))

Printer i has ink cartridge of $C_i, M_i, Y_i, K_i$ units (i is from 1 to 3). Our objective is to determine $c, m, y, k$ such that

1. $c + m + y + k = 10^6$
2. $x <= X_i$ for color $x$ and $i = 1, 2, 3$

Let $C, M, Y, K$ be the minimum cartridge of each color. (i.e. $C = \min(C_1, C_2, C_3)$ ). Then it is only possible to output our answer if $C + M + Y + K \ge 10^6$
In such cases, we simply set $c, m, y, k$ that satisfies the above condition.

# [d1000000]([url](https://codingcompetitions.withgoogle.com/codejam/round/0000000000876ff1/0000000000a46471))

Given N dice with different number of sides, we must create the longest "straight line" 
