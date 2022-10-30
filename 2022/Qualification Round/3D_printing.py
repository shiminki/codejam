def main():
    num_tests = int(input())

    for t in range(1, num_tests + 1):
        colors = [[] for _ in range(4)]
        # colors[i] = list of ink units of each printers

        for i in range(3):
            printer = [int(x) for x in input().split()]

            for j in range(4):
                colors[j].append(printer[j])

        min_color = []

        for c in range(4):
            min_color.append(min(colors[c]))

        total = sum(min_color)
        output = f'Case #{t}: '

        if total < 1e6:
            output += 'IMPOSSIBLE'
        else:
            usage, total = [], 0

            for c in range(4):
                if total == 1e6:
                    usage.append(0)
                elif total + min_color[c] > 1e6:
                    usage.append(int(1e6 - total))
                    total = 1e6
                else:
                    usage.append(min_color[c])
                    total += min_color[c]
            output += ' '.join(str(x) for x in usage)
        print(output)


if __name__ == '__main__':
    main()
