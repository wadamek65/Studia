import queue

list = []
with open('SCHRAGE5.dat', 'r') as inp:
    size = int(inp.readline()) # to skip first line
    N = queue.PriorityQueue(size) # sortowane po r
    G = queue.PriorityQueue(size)
    N2 = queue.PriorityQueue(size) # sortowane po q
    for line in inp:
        new_line = line.split()
        #print(new_line)
        N.put([int(new_line[0]), int(new_line[1]), int(new_line[2])])
        N2.put([int(new_line[2]), int(new_line[1]), int(new_line[0])])

print(N.get())
print(N.get())
print(N.get())

#print(list)
# dwie kolejki N i Q
# min(n[0], q[0])
print(size)
t = 0
k = 0
pi = 0
C = 0
#list = sorted(list, key=lambda entry: entry[0])
e = [1, 0, 0]
#N.put(temp)

while not N.empty() or not G.empty():
    while not N.empty() and e[0] <= t:  # temp [0] pierwsze r (najmniejsze)
        e = N.get()
    if not G.empty():
        t = e[0]
    e = N2.get() # TU JEST BŁĄD, MA WYCIĄGAĆ Z PIERWSZEJ KOLEJKI
    k += 1
    pi = e
    t += e[1]
    C = max(C, t+e[1])

print(C)