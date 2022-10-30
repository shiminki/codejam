def main():
    inp = open("input.txt", "r")
    ans = open("answer.txt", "r")
    sol = open("output.txt", "r")

    T = int(inp.readline())

    correct = True

    for t in range(T):
        answer = ans.readline()
        solution = sol.readline()
        if answer != solution:
            print("Wrong answer")
            print("Answer:", answer)
            print("Solution:", solution)
            correct = False
            break

    if correct:
        print("Correct answer")


if __name__ == "__main__":
    main()
