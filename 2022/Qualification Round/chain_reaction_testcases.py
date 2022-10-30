import random

def main():
    t = 100
    file = open('input.txt', 'w')
    file.write(str(t) + '\n')

    for test in range(t):
        n = random.randint(500, 1000)
        f, p = [], []

        for i in range(n):
            f.append(random.randint(0, 1e9))
            x = random.randint(0, len(f))
            if x == len(f) or random.random() < 0.2:
                p.append(0)
            else:
                p.append(x)

        file.write(str(n) + '\n')
        for num in f:
            file.write(str(num) + ' ')
        file.write('\n')
        for num in p:
            file.write(str(num) + ' ')
        file.write('\n')


if __name__ == '__main__':
    main()
