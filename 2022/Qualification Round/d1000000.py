def main():
    num_tests = int(input())
    for t in range(1, num_tests + 1):
        n = int(input())
        s = [int(x) for x in input().split()]
        s.sort()  # O(n log n)
        num, idx = 1, 0

        while idx < n: # O(n)
            if s[idx] >= num:
                num += 1
            idx += 1

        print(f'Case #{t}: {num - 1}')


if __name__ == '__main__':
    main()
