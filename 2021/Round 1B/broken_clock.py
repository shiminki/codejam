import sys
import itertools


def main():
    num_tests = int(input())

    for t in range(1, num_tests + 1):
        [A, B, C] = [int(x) for x in input().split()]

        angles = list(itertools.permutations([A, B, C]))

        h_ans, m_ans, s_ans, n_ans = 0, 0, 0, 1
        found = False

        for h in range(12):
            for m in range(60):
                for s in range(60):
                    for a, b, c in angles:
                        n1 = (3600e9*h - 660e9*m - 11e9*s - (a - b)) / 11
                        n2 = (720e9*m - 708e9*s - (b - c)) / 708
                        n3 = (c - a + 3600e9*h + 60e9*m - 719e9*s) / 719

                        if (h, m, s) == (1, 15, 42):
                            print(n1, n2, n3)

                        if n1 == n2 and n2 == n3:
                            print(n1, h, m, s)
                            if int(n1) == n1 and n1 < 1e9 and n1 >= 0:
                                n_ans = int(n1)
                                h_ans, m_ans, s_ans = h, m, s

        print(f'Case #{t}: {h_ans} {m_ans} {s_ans} {n_ans}')

    sys.stdout.flush()


if __name__ == '__main__':
    main()
