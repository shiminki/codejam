def main():
    num_tests = int(input())

    for t in range(1, num_tests + 1):
        [r, c] = [int(x) for x in input().split()]
        grid = [['.' for i in range(2*c + 1)] for j in range(2*r + 1)]

        for i in range(2, 2*c + 1):
            grid[0][i] = '+' if i % 2 == 0 else '-'
            grid[1][i] = '|' if i % 2 == 0 else '.'
            grid[2][i] = '+' if i % 2 == 0 else '-'

        grid[2][0], grid[2][1] = '+', '-'

        for row in range(1, r):
            grid[2*row + 1][0], grid[2*row + 2][0] = '|', '+'

            for col in range(c):
                grid[2*row + 1][2*col + 1] = '.'
                grid[2*row + 1][2*col + 2] = '|'
                grid[2*row + 2][2*col + 1] = '-'
                grid[2*row + 2][2*col + 2] = '+'

        print(f'Case #{t}:')
        for rowlist in grid:
            print(''.join(rowlist))


if __name__ == '__main__':
    main()
