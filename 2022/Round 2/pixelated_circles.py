import sys, math

def circle_filled(R):
    cnt = 0

    for x in range(-1 * R, R + 1):
        y = math.round(math.sqrt(R*R - x*x))

        while math.round(math.sqrt(x * x + (y + 1) * (y + 1))) <= R:
            y += 1
        cnt += 2 * y + 1

    return cnt

def circle_filled_wrong(R):
    cnt = 1
    
    for r in range(1, R + 1):
        x = math.round(r / math.sqrt(2))
        y = math.round(math.sqrt(r * r - x * x))

        if x == y:
            cnt += 8 * x
        else:
            cnt += 4 * (2 + min(x, y) + 1)
    
    return cnt

def main():
    T = int(input())

    out = open("output.txt", "w")
    
    for t in range(1, T + 1):
        R = int(input())
        ans = circle_filled(R) - circle_filled_wrong(R)
        print(f'Case #{t}: {ans}')
        out.write(f'Case #{t}: {ans}\n')
	
    sys.stdout.flush()

if __name__ == '__main__':
	main()