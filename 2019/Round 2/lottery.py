import sys
import random


class InteractiveTester:
    def __init__(self):
        self.V = {i: [] for i in range(1, 21)}
        self.day = 0

    def next_day(self):
        self.day += 1
        self.V[random.randint(1, 20)].append(self.day)

    def add_token(self, v, n):
        self.V[v].append(n)

    def inspect(self, v):
        return [v, self.V[v]]

    def get_winner(self):
        winner, length = None, None

        for i in range(1, 21):
            if winner is None or length > len(self.V[i]):
                winner = i
                length = len(self.V[i])

        for i in range(1, 21):
            if i == winner:
                continue
            else:
                if length == len(self.V[i]):
                    return None

        cnt = {}

        for num in self.V[winner]:
            if num in cnt:
                return None
            cnt[num] = 1

        return winner


def main():
    # T = 250
    T = int(input())

    num_correct = 0

    for t in range(T):
        tester = InteractiveTester()

        winner = None
        W, M = 14, 60
        length = {}

        for day in range(1, 101):
            tester.next_day()

            # reads day input
            # num = int(input())

            if day in range(1, M + 1):
                v = day % W + 1
                tester.add_token(v, day)
                print(f"{v} {day}")

            elif day in range(M + 1, M + 21):
                v = (day - M) % (20) + 1
                v, V = tester.inspect(v)
                print(f"{v} 0")
                sys.stdout.flush()
                # V = [int(x) for x in input().split()]

                length[v] = len(V)
                # length[v] = len(V) - 1

                if winner is None or length[winner] > length[v]:
                    winner = v

            elif day in range(M + 21, 100):
                v = 1

                for vase in range(1, 21):
                    if vase == winner:
                        continue
                    if length[v] > length[vase]:
                        v = vase

                # tester.add_token(v, day)
                print(f"{v} {day}")
                length[v] += 1

            else:
                # day = 100
                # tester.add_token(winner, 100)
                print(f"{winner} 100")

            sys.stdout.flush()

        if winner == tester.get_winner():
            num_correct += 1

    print(f"Accuracy: {round(num_correct / T * 100, 4)}")


if __name__ == '__main__':
    main()
