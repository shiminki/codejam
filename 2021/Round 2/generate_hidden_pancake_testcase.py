import random


def main():
    f = open('pancake.in', 'w')
    T = 100
    f.write(str(T) + '\n')
    N = int(1e5)

    for t in range(T):
        f.write(str(N) + '\n')
        current = 1
        f.write('1')
        for _ in range(N - 1):
            x = random.random()
            if x > 2/3:
                current += 1
            elif x < 1/3 and current > 1:
                current -= 1
            f.write(' ' + str(current))

        f.write('\n')


if __name__ == '__main__':
    main()
