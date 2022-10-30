import sys
import math


def main():
    T = int(input())

    for t in range(1, T + 1):
        N = int(input())
        C, J = [], []

        for i in range(N):
            c, j = [int(x) for x in input().split()]
            C.append(c)
            J.append(j)

        min_r, max_r = 0, float("inf")
        impossible = False

        for i in range(N):
            for j in range(i + 1, N):
                if impossible:
                    break

                if C[i] >= C[j] and J[i] >= J[j]:
                    impossible = True

                if C[i] == C[j]:
                    continue

                r = (J[j] - J[i]) / (C[i] - C[j])

                if r <= 0:
                    continue

                if C[i] < C[j]:
                    # update min
                    min_r = max(min_r, r)
                else:
                    # update max
                    max_r = min(max_r, r)

        if min_r < max_r and not impossible:
            ans = 1

            while True:
                a = ans / max_r if max_r < float("inf") else 1
                b = ans / min_r if min_r > 0 else float("inf")

                if math.ceil(a) < b:
                    print(f'Case #{t}: {ans} {math.ceil(a)}')
                    break

                ans += 1
        else:
            print(f"Case #{t}: IMPOSSIBLE")

    sys.stdout.flush()


if __name__ == '__main__':
    main()
