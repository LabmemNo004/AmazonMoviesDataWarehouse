import pandas as pd
import numpy as np
import re
import math
import codecs
import csv

# 预计剩余电影总量220k到200k
data=pd.read_csv("Website_ETL.CSV")
data=np.array(data)
dic={}
dic["Jan"]="1"
dic["Feb"]="2"
dic["Mar"]="3"
dic["Apr"]="4"
dic["May"]="5"
dic["Jun"]="6"
dic["Jul"]="7"
dic["Aug"]="8"
dic["Sep"]="9"
dic["Oct"]="10"
dic["Nov"]="11"
dic["Dec"]="12"

fileFirstMenu=open("ASIN.txt","r",encoding="utf-8")
isbnBanned=fileFirstMenu.readlines()
bannedIsbn={}
for i in isbnBanned:
    bannedIsbn[i]=1




turn=0
for frame in data:
    Drop=False # 是否丢弃该数据
    loss=0 # 参数损失值

    # narray转换到list
    j = 0
    for i in frame:
        if (pd.isna(i)):
            frame[j] = ""
        j += 1
    frame = frame.tolist()

    # 预处理bannedIsbn（包含一级目录判断依据和评论判断依据）
    bannedNum = bannedIsbn.get(frame[3], 0)
    if bannedNum==True:
        Drop=True


    times=frame[5]
    try:   # 时间规范化处理
        result=re.findall(r"\d+\.?\d*",times)
    except:
        pass
    else:
        time=0
        if(len(result)==0):
            time=0
            loss+=1
        elif(len(result)==1):
            if(times[-1]=="r"):
                time=int(result[0])*60
            else:
                time = int(result[0])
        else:
            time=int(result[0])*60+int(result[1])
        if(time>300 or time<30 and time>0):
            Drop=True
        frame[5]=str(time)



    release=frame[4]
      # 日期规范化处理
    try:
        resultNum=re.findall(r"\d+\.?\d*",release)
        resultMonth=re.findall(r"(?<=-)(.+?)(?=-)",release) # 未找到时不返回异常，返回空
    except:
        pass
    else:
        if (len(resultNum) == 0):
            frame[4] = ""
            loss+=1
        elif (len(resultNum) == 1):
            frame[4] = "XX/XX/" + resultNum[0]
            frame[12] = resultNum[0]
        elif len(resultNum)==2 and len(resultMonth)==1:
            if(int(resultNum[1])<=20):
                resultNum[1]="20"+resultNum[1]
            else:
                resultNum[1] = "19" + resultNum[1]
            frame[4]=resultNum[0]+"/"+dic[resultMonth[0]]+"/"+resultNum[1]
            frame[12] = resultNum[1]
            frame[13] = dic[resultMonth[0]]
            frame[14] = resultNum[0]
        else:
            Drop=True

    name=frame[2]
    # 名称筛选
    try:
        findA=re.findall(r"Analysis of",name)
        findT=re.findall(r"technique",name)
        findC = re.findall(r"Collection", name)
    except:
        pass
    else:
        if(len(findA)or len(findT)or len(findC)):
            Drop=True

    # 判断电影非空值
    if frame[15]=="":
        loss+=1

    if loss>=3:
        Drop=True

    type=frame[7]
    # 标签筛选
    try:
        findBL=re.findall(r"Bollywood",type)
        findM=re.findall(r"Movies",type)
        findB=re.findall(r"Boxed Sets",type)
        findT=re.findall(r"TV",type)
        findE = re.findall(r"Exercise", type)
        findS = re.findall(r"Special Interest", type)
        findP = re.findall(r"PBS", type)
        findMusical = re.findall(r"Musical", type)
        findMusic=re.findall(r"Music", type)
        findRock=re.findall(r"Rock", type)
        findBlues = re.findall(r"Blues", type)
        findBroad = re.findall(r"Broad", type)
        findSpoken = re.findall(r"Spoken", type)
        findCountry = re.findall(r"Country", type)
        findDance = re.findall(r"Dance", type)
        findFolk = re.findall(r"Folk", type)
        findGospel = re.findall(r"Gospel", type)
        findHoliday = re.findall(r"Holiday", type)
        findJazz = re.findall(r"Jazz", type)
        findKaraoke = re.findall(r"Karaoke", type)
        findLatin = re.findall(r"Latin", type)
        findMetal = re.findall(r"Metal", type)
        findNew = re.findall(r"New Age", type)
        findOpera = re.findall(r"Opera", type)
        findPop = re.findall(r"Pop", type)
        findRB = re.findall(r"R&B", type)
        findRap = re.findall(r"Rap", type)
        findReggae = re.findall(r"Reggae", type)
        findSound = re.findall(r"Sound", type)
    except:
        pass
    else:
        if len(findM)or len(findBL):
            Drop=False
        elif len(findB) or len(findT)or len(findE) or len(findP)or len(findS)or len(findRock)or len(findBlues)or len(findBroad)or len(findSpoken)or len(findCountry)or len(findDance)or len(findFolk)or len(findGospel)or len(findHoliday)or len(findJazz)or len(findKaraoke)or len(findLatin)or len(findMetal)or len(findNew)or len(findOpera)or len(findPop)or len(findRB)or len(findRap)or len(findReggae)or len(findSound) :
            Drop=True
        elif len(findMusical)==0 and len(findMusic):
            Drop=True


    format=frame[9]
    try:
        find=re.findall(r"Audio CD",format)
    except:
        pass
    else:
        if len(find):
            Drop=True

    # 是否删除
    CSV = "regularMovie.csv"
    if(Drop):
        CSV="dropMovie.csv"


    # 写入数据
    fileOut = codecs.open(CSV, 'a+', 'utf-8')  # 数据集
    writerOut = csv.writer(fileOut)
    try:

        writerOut.writerow(frame)
    except:
        pass
    fileOut.close()

    # 进度显示
    if(turn%1000==0):
        print("\nturn{}".format(turn))
    turn+=1







