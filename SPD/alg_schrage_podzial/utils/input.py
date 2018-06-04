def reader(filename):
    with open(filename) as f:
        lines = f.readlines()
    tablicaOut = [lines[x].rstrip().split(" ") for x in range(1, len(lines))]
    # parse data to int
    for i in range(0, len(tablicaOut)):
        tablicaOut[i][0] = int(tablicaOut[i][0])
        tablicaOut[i][1] = int(tablicaOut[i][1])
        tablicaOut[i][2] = int(tablicaOut[i][2])
    return tablicaOut
