import random


def main():
    f = open('wormhole_in_one.in', 'w')
    T = 1
    f.write(str(T) + '\n')
    L = 10

    for t in range(T):
        N = 10
        f.write(str(N) + '\n')

        points = {}

        for i in range(N):
            (x, y) = random.randint(-1*L, L), random.randint(-1*L, L)
            while (x, y) in points:
                (x, y) = random.randint(-1*L, L), random.randint(-1*L, L)
            points[(x, y)] = True

            f.write(str(x) + ' ' + str(y) + '\n')


if __name__ == '__main__':
    main()
