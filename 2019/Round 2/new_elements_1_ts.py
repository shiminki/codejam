import random


def main():
    f = open("input.txt", "w")
    T, max_N = 20, 300

    max_num = 1e3

    f.write(str(T) + "\n")

    for t in range(T):
        N = random.randint(2, max_N)

        f.write(str(N) + "\n")
        for i in range(N):
            f.write(str(random.randint(1, max_num)))
            f.write(" ")
            f.write(str(random.randint(1, max_num)))
            f.write("\n")


if __name__ == "__main__":
    main()
