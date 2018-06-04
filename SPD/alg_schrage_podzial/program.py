# -*- coding: utf-8 -*-
from utils.input import reader
import argparse
from utils.testing import tester
import sys

parser = argparse.ArgumentParser()
parser.add_argument('-i', type=str, help="input file")
args = parser.parse_args()

def schrage_split(file_name):
    ql = 10000000000
    raw_inupt = reader(file_name)
    l = [0, 0, ql]                      # current task
    G = []  # ready to realisation
    t = 0   # time moment
    Cmax = 0 # max 
    N = sorted(raw_inupt, key=lambda x: x[0]) # task list
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

tester(schrage_split)
