"""
ZADANIE B - czas zakonczenia i dostarczenia jest rowne D max (c+q=Dmax)
jesli jest wiele takich zadan to bierzemy to najbardziej na prawo
ZADANIE A - r + czas wykonywania bloku + q = D max
wybieramy najbardziej na lewo
ZADANIE c - q mniejsze od zadania B
jesli jest kilka to najblizsze polozenia B

Dmax = q + t
"""
# -*- coding: utf-8 -*-
import argparse
import sys

parser = argparse.ArgumentParser()
parser.add_argument('-i', type=str, help="input file")
args = parser.parse_args()


def reader(file_name):
    list = []
    with open(file_name, 'r') as inp:
        inp.readline() # to skip first line
        x = 0
        for line in inp:
            new_line = line.split()
            #print(new_line)
            list.append([int(new_line[0]), int(new_line[1]), int(new_line[2]), x])
            x += 1
    return list


def schrage_split(file_name):
    ql = 1000000
    l = [0, 0, ql]
    raw_inupt = reader(file_name)
    print(raw_inupt)
    G = []
    t = 0
    Cmax = 0
    N = sorted(raw_inupt, key=lambda x: x[0])
    while len(N) != 0 or len(G) != 0:
        while len(N) != 0 and N[0][0] <= t:
            e = N.pop(0)
            G.append(e)
            if e[2] > l[2]:
                l[1] = t - e[0]
                t = e[0]
                if l[1] > 0:
                    G.append(l)
        if len(G) == 0:
            t = N[0][0]
        else:
            index = G.index(max(G, key=lambda x: x[2]))
            element = G.pop(index)
            t = t + element[1]
            Cmax = max(Cmax, t + element[2])
            l = element
    return Cmax


def calier(file_name):
    c = 0
    U = 0
    while c == 0:
        U = schrage_split(file_name)
        print(U)


schrage_split('SCHRAGE1.DAT')