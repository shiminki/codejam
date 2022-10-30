import sys


NUM = 1000000007
fact, inverse_fact = [1], [1]
# fact[n] = (n!) mod NUM
# inverse[n] = (inverse_fact(n)) mod NUM where
# inverse_fact(n) * fact[n] = 1 mod NUM

def power(n, r):
    # return n^r mod NUM
    # O(log r) time

    if r == 0:
        return 1
    p = power(n, r//2)

    return (p*p) % NUM if r % 2 == 0 else (p*p*n) % NUM

def nCr(n, r):
    return (fact[n] * inverse_fact[n - r] * inverse_fact[r]) % NUM


def f(V, start, end, decrease):
    if start >= end:
        return 1

    ans = 0
    n = end - start

    for i in range(start, end):
        if V[i] - decrease == 1:
            ans += nCr(n - 1, i - start) * f(V, start, i,
                                             decrease) * f(V, i + 1, end, decrease + 1)
            ans %= NUM
        elif V[i] - decrease < 1 or V[i] - decrease > i - start + 1:
            return 0
    return ans


def main():
    T = int(input())

    for i in range(1, int(1e5) + 1):
        fact.append((fact[-1] * i) % NUM)
        # fermat's little theorem: a^p = a mod p
        inverse_fact.append(power(fact[-1], NUM - 2))

    for t in range(1, T + 1):
        N = int(input())
        V = [int(x) for x in input().split()]
        ans = 0

        for i in range(N):
            if V[i] == 1:
                ans += nCr(N - 1, i) * f(V, 0, i, 0) * f(V, i + 1, N, 1)
                ans %= NUM

        print(f'Case #{t}: {int(ans)}')

    sys.stdout.flush()


if __name__ == '__main__':
    main()
