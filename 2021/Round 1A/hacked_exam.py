import sys

def main():
    num_tests = int(input())
    
    for t in range(1, num_tests + 1):
        '''
        For the two student case, let p_ab be the probability that a question's
        correct answer is True, given that each students answered a and b.
        Then p_TT + p_FF = 1 and p_TF + p_FT = 1 b/c P(ans = 1|TT) = 1 - P(ans = 0|TT)
        = 1 - P(ans = 1|FF)
        Thus each student's score can be expressed as a linear function of p_TT and p_TF
        which leads to two equations w/ two unknowns. Solve for p_TT and p_TF and make
        greedy choice for the answer
        '''
        [N, Q] = [int(x) for x in input().split()]

        seq = ''
        z, w = 0, 0

        if N == 1:
            [ans, score] = input().split()
            score = int(score)

            if score > 1/2:
                seq = ans
                z, w = score, 1
            else:
                for a in ans:
                    seq += 'T' if a == 'F' else 'F'
                z, w = (Q - score), 1
        elif N == 2:
            [ans1, score1] = input().split()
            [ans2, score2] = input().split()
            score1, score2 = int(score1), int(score2)

            '''
            for student 1
            n_TT * q_TT + n_TF * q_TF + n_FT * (1 - q_FT) + n_FF * (1 - q_FF) = score1

            Equivalently,
            n_TT * q_TT + n_TF * q_TF + n_FT * q_TF + n_FF * q_TT = score1

            for student 2
            n_TT * q_TT + n_TF * (1 - q_TF) + n_FT * q_FT + n_FF * (1 - q_FF) = score2

            Equivalently,
            n_TT * q_TT + n_TF * (1 - q_TF) + n_FT * (1 - q_TF) + n_FF * q_TT = score2

            Two equations are 
            (n_TT + n_FF) * q_TT + (n_TF + n_FT) * q_TF = score1
            (n_TT + n_FF) * q_TT - (n_TF + n_FT) * q_TF = score2 - (n_TF + n_FT)
            '''

            n = [[0, 0], [0, 0]] # n[T][T] = n[1][1]
            
            for i in range(Q):
                if ans1[i] == ans2[i]:
                    if ans1[i] == 'T':
                        n[1][1] += 1
                    else:
                        n[0][0] += 1
                else:
                    if ans1[i] == 'T':
                        n[1][0] += 1
                    else:
                        n[0][1] += 1
            
            if n[1][1] + n[0][0] > 0:
                p_TT = (score1 + score2 - n[0][1] - n[1][0]) / (2*(n[1][1] + n[0][0]))
            else:
                p_TT = 1/2
            if n[1][0] + n[0][1] > 0:
                p_TF = (score1 - score2 + n[0][1] + n[1][0]) / (2*(n[1][0] + n[0][1]))
            else:
                p_TF = 1/2
            
            score = 0

            for i in range(Q):
                if ans1[i] == ans2[i]:
                    if ans1[i] == 'T':
                        if p_TT > 1/2:
                            seq += 'T'
                            score += p_TT
                        else:
                            seq += 'F'
                            score += (1 - p_TT)
                    else:
                        if (1 - p_TT) > 1/2: # p_FF = P(ans = 1 | FF) > 1/2
                            seq += 'T'
                            score += (1 - p_TT)
                        else:
                            seq += 'F'
                            score += p_TT
                else:
                    if ans1[i] == 'T':
                        if p_TF > 1/2:
                            seq += 'T'
                            score += p_TF
                        else:
                            seq += 'F'
                            score += (1 - p_TF)
                    else:
                        if (1 - p_TF) > 1/2: # p_FT > 1/2
                            seq += 'T'
                            score += (1 - p_TF)
                        else:
                            seq += 'F'
                            score += p_TF

            z, w = score.as_integer_ratio()
            
        
        print(f'Case #{t}: {seq} {z}/{w}')
	
    sys.stdout.flush()

if __name__ == '__main__':
	main()