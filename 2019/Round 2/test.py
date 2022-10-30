def main():
    f = open("output.txt", "r")

    N = 20

    for i in range(N):
        ans = int(input().split()[2])
        if ans != int(f.readline()):
            print("Wrong")


if __name__ == '__main__':
    main()
