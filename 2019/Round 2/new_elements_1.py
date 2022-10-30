import sys


def main():
    T = int(input())

    out = open("output.txt", "w")

    for t in range(1, T + 1):
        N = int(input())
        C, J = [], []

        for i in range(N):
            (c, j) = (int(x) for x in input().split())
            C.append(c)
            J.append(j)

        R = {}

        for i in range(N):
            for j in range(i + 1, N):
                if J[i] == J[j]:
                    continue

                r = (C[i] - C[j]) / (J[j] - J[i])

                if r > 0:
                    R[r] = 1

        ans = len(R) + 1

        print(f'Case #{t}: {ans}')

        out.write(str(ans) + "\n")

    sys.stdout.flush()


if __name__ == '__main__':
    main()
