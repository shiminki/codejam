import sys


def main():
    num_tests, N, B, P = [int(x) for x in input().split()]

    out = False

    for t in range(1, num_tests + 1):
        height = [0 for _ in range(N + 1)]
        idx = 1

        for _ in range(N * B):
            D = int(input())

            if D == -1:
                out = True
                break

            if idx > N:
                for i in range(1, N + 1):
                    if height[i] == B - 1:
                        print(i)
                        height[i] += 1
                        break
            elif height[idx] == B - 1:
                if D == 9 or idx >= N:
                    idx += 1
                    for i in range(1, idx):
                        if height[i] == B - 1:
                            print(i)
                            height[i] += 1
                            break
                else:
                    idx += 1
                    print(idx)
                    height[idx] += 1
            else:
                print(idx)
                height[idx] += 1

            # print('output', height)

        if out:
            break

    sys.stdout.flush()


if __name__ == '__main__':
    main()
