import sys
import itertools


def main():
    T = int(input())

    out = open('output.txt', 'w')

    for t in range(1, T + 1):
        N = int(input())
        C, J = [], []

        for i in range(N):
            (c, j) = (int(x) for x in input().split())
            C.append(c)
            J.append(j)

        L = [i for i in range(N)]

        P = list(itertools.permutations(L))

        ans = 0

        for p in P:
            min_r, max_r = 0, float('inf')

            for x in range(N):
                for y in range(x + 1, N):
                    i, j = p[x], p[y]
                    if C[i] == C[j]:
                        if J[i] > J[j]:
                            min_r, max_r = float('inf'), 0
                        break

                    r = (J[j] - J[i]) / (C[i] - C[j])

                    if C[i] > C[j]:
                        max_r = min(max_r, r)
                    else:
                        min_r = max(min_r, r)

            if min_r < max_r:
                ans += 1

        print(f'Case #{t}: {ans}')
        out.write(str(ans) + "\n")

    sys.stdout.flush()


if __name__ == '__main__':
    main()
