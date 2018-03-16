#find primary numbers
maxNum = int(input('Enter the maximum number you wish to check for primes:'))
for outer in range(2,maxNum):
    for inner in range(2,outer):
        if not outer % inner: break
    else: print(outer, ' ') # NOTE: after breaking from the inner loop, else should be at same level for the inner loop
            
