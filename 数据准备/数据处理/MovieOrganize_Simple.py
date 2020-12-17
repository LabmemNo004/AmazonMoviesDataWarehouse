import pandas as pd
import numpy as np
import jsonlines
import codecs
import csv

CSV="MovieProducts.csv"
data=pd.read_csv(CSV)
data=np.array(data)
webs=[] # 网页数据列表id
dicAsinLink={} # isbn链接isbn，访问后为[]
dicAsin={} # isbn链接id 0-200k 为查找到为-1，访问后为-1
group=[] # 分组order

sumJ=0
with open("data_fixed.jl", "r+", encoding="utf8") as f:
    for item in jsonlines.Reader(f):
        sumJ+=1
        item=dict(item)
        link=item["otherFormat"]
        addition=item.get("additionalOptions",[])
        link+=addition
        pid=item["pid"]
        if  len(link)>0:
            dicAsinLink[pid]=link


t=0
for frame in data:
    j = 0
    for i in frame:
        if pd.isna(i):
            frame[j] = ""
        j += 1
    webs.append(frame.tolist()[:16])
    ASIN=frame[3]
    dicAsin[ASIN]=t
    t+=1



insertNum=0
id=-1
groupNum=-1
for frame in webs:
    id += 1
    asin=frame[3]
    vector=[]
    iter=0
    mark=dicAsin.get(asin,-1)
    if mark==-1:
        continue
    dicAsin[asin]=-1
    group.append([])
    groupNum+=1
    group[groupNum].append(id)
    insertNum+=1
    vector.append(asin)
    while iter<len(vector):
        asin_in=vector[iter]
        iter+=1
        asin_list=dicAsinLink.get(asin_in,[])
        if asin_list!=[]:
            dicAsinLink[asin_in]=[]
            for i in asin_list:
                order=dicAsin.get(i,-1)
                if order!=-1:
                    asin_inn=webs[order][3]
                    vector.append(asin_inn)
                    group[groupNum].append(order)
                    insertNum+=1
                    dicAsin[i]=-1
                else:
                    pass
        else:
            pass
CSV="Movies.csv"
fileOut = codecs.open(CSV, 'a+', 'utf-8')  # 数据集
writerOut = csv.writer(fileOut)
team=0
for i in group:
    for j in i:
        try:
            writerOut.writerow([team]+webs[j])
        except:
            pass
    team+=1
fileOut.close()










