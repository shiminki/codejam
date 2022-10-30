import sys


def main():
    num_tests = int(input())

    for t in range(1, num_tests + 1):
        S = input()

        highlight = [False for _ in range(len(S))]

        output, idx = '', 0

        while idx < len(S) - 1:
            if S[idx] < S[idx + 1]:
                highlight[idx] = True
                idx += 1
            elif S[idx] == S[idx + 1]:
                pointer = idx + 1
                while pointer < len(S) and S[pointer] == S[idx]:
                    pointer += 1

                if pointer != len(S) and S[pointer] > S[idx]:
                    for i in range(idx, pointer):
                        highlight[i] = True
                idx = pointer
            else:
                idx += 1

        for i in range(len(S)):
            output += S[i]

            if highlight[i]:
                output += S[i]

        print(f'Case #{t}: {output}')

    sys.stdout.flush()


if __name__ == '__main__':
    main()
