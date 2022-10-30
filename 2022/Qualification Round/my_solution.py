import sys, random

def main():
    num_tests = int(input())

    for t in range(1, num_tests + 1):
        [n, k] = [int(x) for x in input().split()]
        [v, deg] = [int(x) for x in input().split()]
        vertex = []
        tot_edge = deg

        for i in range(1, n + 1):
            if i != v:
                vertex.append(i)

        random.shuffle(vertex)

        for _ in range(k):
            print('T', vertex.pop())
            sys.stdout.flush()
            [v, deg] = [int(x) for x in input().split()]
            tot_edge += deg
        
        estimate = int(tot_edge / (k + 1) * n / 2)

        print('E', estimate)
        sys.stdout.flush()

if __name__ == '__main__':
    main()