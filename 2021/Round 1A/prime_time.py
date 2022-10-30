import sys

def get_sum(P, N):
    sum = 0

    for i in range(len(P)):
        sum += P[i] * N[i]
    
    return sum

def prime_factor(num, P, N):
    M = len(P)
    n = [0 for _ in range(M)]

    # check if modified num is carried onto main()

    for i in range(M):
        while num % P[i] == 0:
            n[i] += 1
            num /= P[i]
            if n[i] > N[i]:
                return -1
    if num == 1:
        return n
    else:
        return -1

def main():
    num_tests = int(input())
    
    for t in range(1, num_tests + 1):
        M = int(input()) # number of primes
        P, N = [], []

        for i in range(M):
            p, n = (int(x) for x in input().split())
            P.append(p)
            N.append(n)

        sum = get_sum(P, N)
        ans = 0
        
        for num in range(sum, 0, -1):
            n = prime_factor(num, P, N)

            if n == -1:
                continue

            if num == sum - get_sum(P, n):
                ans = num
                break

        print(f'Case #{t}: {ans}')
	
    sys.stdout.flush()

if __name__ == '__main__':
	main()