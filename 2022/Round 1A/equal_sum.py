import sys


def main():
    num_tests = int(input())
    pow2 = [1]

    while (pow2[-1] < 1e9):
        pow2.append(pow2[-1] * 2)
    pow2.pop()

    for t in range(1, num_tests + 1):
        N = int(input())
        A = []
        for p in pow2:
            A.append(p)

        for i in range(2, 30):
            A.append(pow2[i] + 1)
            A.append(pow2[i] + 2)

        for i in range(29, 15, -1):
            A.append(pow2[i] - 1)

        if len(A) != N:
            A[1e7]

        output = ''
        for a in A:
            output += str(a) + ' '
        print(output)

        B = [int(b) for b in input().split()]

        sum = 0

        for i in range(N):
            sum += A[i] + B[i]

        goal = sum / 2

        first_A = [False for _ in range(N)]
        first_B = [False for _ in range(N)]
        current = 0

        for i in range(N):
            if current + B[i] < goal:
                first_B[i] = True
                current += B[i]

        if goal - current > 1e9:
            for i in range(30, N):
                if current + A[i] < goal:
                    first_A[i] = True
                    current += A[i]

        num = goal - current
        idx = 0

        while num > 0:
            num, r = num // 2, num % 2
            if r == 1:
                first_A[idx] = True
            idx += 1

        output = ''

        for i in range(N):
            if first_A[i]:
                output += str(A[i]) + ' '
            if first_B[i]:
                output += str(B[i]) + ' '

        print(output)

    sys.stdout.flush()


if __name__ == '__main__':
    main()
