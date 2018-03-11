list = []
with open('JACK8.dat', 'r') as inp:
    inp.readline() # to skip first line
    for line in inp:
        new_line = line.split()
        #print(new_line)
        list.append([int(new_line[0]), int(new_line[1])])

#print(list)


list = sorted(list, key=lambda entry: entry[0])


result = 0
for i in range(0, len(list)):
    result = max(int(list[i][0]), result) + int(list[i][1])

print(result)