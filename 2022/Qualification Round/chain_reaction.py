def dfs(u, adj, parent, max_fun):
    next = None
    for v in adj[u]:
        if (next is None or max_fun[v] < max_fun[next]) and parent[v] is None:
            next = v

    if next is None:
        return parent
    else:
        parent[next] = u
        return dfs(next, adj, parent, max_fun)


def main():
    num_tests = int(input())

    for t in range(1, num_tests + 1):
        n = int(input())
        fun, pointer = [0], [0]
        for x in input().split():
            fun.append(int(x))
        for x in input().split():
            pointer.append(int(x))
        initiator = {i: 0 for i in range(1, n + 1)}
        for p in pointer:
            if p in initiator:
                del initiator[p]

        max_fun = fun.copy()

        for i in range(1, n + 1):
            current = i
            while pointer[current] != 0:
                p = pointer[current]
                max_fun[p] = max(max_fun[p], max_fun[current])
                current = p

        adj = [[] for _ in range(n + 1)]
        parent = [None for _ in range(n + 1)]

        for i in range(1, n + 1):
            adj[pointer[i]].append(i)

        for i in range(1, n + 1):
            if parent[i] is None:
                dfs(i, adj, parent, max_fun)

        total_fun = 0

        for i in initiator:
            current = i
            path_max = fun[i]

            while current is not None:
                path_max = max(path_max, fun[current])

                if parent[current] is None:
                    total_fun += path_max
                    break
                current = parent[current]

        print(f'Case #{t}: {total_fun}')

        file = open('result_py.txt', 'a')
        file.write(str(total_fun) + '\n')


if __name__ == '__main__':
    main()
