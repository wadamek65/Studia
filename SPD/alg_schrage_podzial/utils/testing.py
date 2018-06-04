import os

def tester(func):
    files = os.listdir('./tests/')
    input = []
    output = []
    for i in files:
        if 'OUT' in i:
            output.append(str("./tests/" + i))
        elif 'DAT' in i:
            input.append(str("./tests/" + i))
    input = sorted(input)
    output = sorted(output)
    print("{:<25} | {:<16} | {:<15} | {:<5}".format("Input name", "Calculated value", "Correct value", "Status"))
    for i in range(len(input)):
        corr = open(output[i])
        correct = corr.readline()
        print("{:<25} | {:<16} | {:<15} | {:<5}".format(input[i], func(input[i]), correct, status(func(input[i]), correct)))

def status(a, b):
    if int(a) == int(b):
        return "ok"
    else:
        return "phuck"
