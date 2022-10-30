import sys


def main():
    num_tests = int(input())

    for t in range(1, num_tests + 1):
        [E, W] = [int(x) for x in input().split()]
        X = []

        for e in range(E):
            exercise = [int(x) for x in input().split()]
            X.append(exercise)

        weight_layer = [[X[0][w] for w in range(W)]]

        weight_moved = sum(X[0])

        for e in range(1, E):
            cnt = X[e - 1].copy()

            for w in range(W):
                if cnt[w] > X[e][w]:
                    # weight w has to be removed
                    num_removed = cnt[w] - X[e][w]

                    if num_removed <= weight_layer[-1][w]:
                        weight_layer[-1][w] -= num_removed
                        cnt[w] -= num_removed
                        weight_moved += abs(num_removed)
                    else:
                        while num_removed > 0:
                            if num_removed <= weight_layer[-1][w]:
                                weight_layer[-1][w] -= num_removed
                                cnt[w] -= num_removed
                                num_removed = 0
                                weight_moved += abs(num_removed)
                            else:
                                num_removed -= weight_layer[-1][w]
                                for u in range(W):
                                    cnt[u] -= weight_layer[-1][u]
                                    weight_moved += abs(weight_layer[-1][u])
                                weight_layer.pop()

            layer_cnt = sum(weight_layer[-1])

            while layer_cnt == 0 and len(weight_layer) > 1:
                weight_layer.pop()
                layer_cnt = sum(weight_layer[-1])

            if (layer_cnt > 0):
                weight_layer.append([0 for w in range(W)])

            for w in range(W):
                if cnt[w] <= X[e][w]:
                    # weights has to be added
                    weight_layer[-1][w] += X[e][w] - cnt[w]
                    weight_moved += abs(X[e][w] - cnt[w])

                if weight_layer[-1][w] == 0:
                    if e < E - 1 and X[e][w] > X[e + 1][w]:
                        num_moved = X[e][w] - X[e + 1][w]
                        if weight_layer[-2][w] >= num_moved:
                            weight_layer[-2][w] -= num_moved
                            weight_layer[-1][w] = num_moved
                            weight_moved += abs(2*num_moved)
                        else:
                            weight_layer[-1][w] = weight_layer[-2][w]
                            weight_layer[-2][w] = 0
                            weight_moved += abs(2*weight_layer[-1][w])

            layer_cnt = sum(weight_layer[-1])

            while layer_cnt == 0 and len(weight_layer) > 1:
                weight_layer.pop()
                layer_cnt = sum(weight_layer[-1])

        weight_moved += sum(X[-1])

        print(f'Case #{t}: {weight_moved}')

    sys.stdout.flush()


if __name__ == '__main__':
    main()
