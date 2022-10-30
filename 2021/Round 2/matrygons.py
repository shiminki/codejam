import sys


def get_K(K, n):
    if n <= 2:
        K[n] = 1
    elif n not in K:
        K[n] = 2  # when n is prime
        for i in range(2, n + 1):
            if (n - 1) % i == 0:
                K[n] = max(K[n], get_K(K, (n - 1) // i) + 1)
    return K[n]


def get_matrygons(N):
    K = {}
    ans = 1

    for i in range(3, N):
        if N % i == 0:
            ans = max(ans, get_K(K, N//i))
    return ans


def main():
    num_tests = int(input())

    for t in range(1, num_tests + 1):
        N = int(input())
        print(f'Case #{t}: {get_matrygons(N)}')

    sys.stdout.flush()


if __name__ == '__main__':
    main()
