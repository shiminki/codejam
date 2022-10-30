import sys
import numpy as np

def main():
    num_tests = int(input())

    pow10 = [1.0]

    for _ in range(100):
        pow10.append(pow10[-1] * 10)
    
    for t in range(1, num_tests + 1):
        N = int(input())
        X = [int(x) for x in input().split()]
        cnt = 0
        
        for i in range(N - 1):
            if X[i + 1] > X[i]: 
                continue
            elif X[i + 1] == X[i]:
                X[i + 1] *= 10
                cnt += 1
            else:
                n = int(np.log10(X[i] / X[i + 1]))
                X[i + 1] *= pow10[n]
                cnt += n
                if X[i + 1] > X[i]:
                    continue
                elif X[i + 1] + pow10[n] - 1 > X[i]:
                    X[i + 1] += (X[i] % pow10[n]) + 1
                else:
                    X[i + 1] *= 10
                    cnt += 1
        
        print(f'Case #{t}: {cnt}')
	
    sys.stdout.flush()

if __name__ == '__main__':
	main()