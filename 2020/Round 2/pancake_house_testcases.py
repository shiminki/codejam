import random


def main():
    f = open('pancake.in', 'w')
    T = 1
    f.write(str(T) + '\n')
    N = int(1e18)

    for t in range(T):
        L, R = random.randint(1, N), random.randint(1, N)
        f.write(f'{L} {R}\n')


if __name__ == '__main__':
    main()
