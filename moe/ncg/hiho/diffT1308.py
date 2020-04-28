#!/usr/bin/env python2
#-*- coding: utf-8 -*-

import random

fileName = "./t1308.data"

def makeData():
    fs = open(fileName, "ab+")
    print "每一组数据需要用几行表达:"      
    line_num = raw_input()
    print "每一行包含的数据个数:"          
    for i in line_num:
        print "第" + i + "行包含的数据数:" 
        i_argu = raw_input()
    
    for k in range(0, 10000):
        for i in range(0,int(line_num)):
            for j in range(0, int(i_argu)):
                fs.write(createRandom())    
                fs.write("\t")
            fs.write("\n")

def createRandom():
    s = ["A", "B", "C", "D", "E", "F", "G", "H"];
    return s[random.randint(0, 7)] + str(random.randint(1, 8)) 

if __name__ == "__main__":
    makeData()
