import pandas as pd
import numpy as np
import Levenshtein
import re
import math
import codecs
import csv


def calculate(i,j):
    # 总相似度
    score=1
    # 各特征相似度
    score_name=1
    score_director=1
    score_actor=1
    score_release=1
    score_time=1
    # 各特征值
    movie_l=webs[i]
    name_l=movie_l[2]
    director_l=movie_l[15]
    actor_l=movie_l[6]
    release_l=movie_l[4]
    time_l=movie_l[5]

    movie_r=webs[j]
    name_r=movie_r[2]
    director_r=movie_r[15]
    actor_r=movie_r[6]
    release_r=movie_r[4]
    time_r=movie_r[5]




    return score




CSV="MovieProducts.csv"
data=pd.read_csv(CSV)
data=np.array(data)
webs=[] # 网页数据列表204654
for frame in data:
    j = 0
    for i in frame:
        if pd.isna(i):
            frame[j] = ""
        j += 1
    webs.append(frame.tolist())
map=[[0]for i in range(len(webs))] # 网页相关程度邻接表 204654
movies=[] # 电影划分，order填充，可在webs里获得完整data
left=0
right=1
HumanSense=0.5 # 将相似度大于0.5的产品判定为相同电影


# 邻接表的构建
for i in range(len(webs)-1):
    for j in range(len(webs)-i-1):
        left=i
        right=j+i+1
        similar=calculate(left,right)
        print("{}->{}:{}\n".format(left,right,similar))
        if similar>=HumanSense:
            map[left].append(right)
            map[right].append(left)














i=0
