import random


def main():
    f = open('weight_lifting.txt', 'w')
    num_tests = 3
    f.write(str(num_tests) + '\n')

    E, W = 10, 3

    for t in range(num_tests):
        f.write(str(E) + ' ' + str(W) + '\n')
        for e in range(E):
            for w in range(W):
                f.write(str(random.randint(0, W)) + ' ')
            f.write('\n')


if __name__ == '__main__':
    main()
