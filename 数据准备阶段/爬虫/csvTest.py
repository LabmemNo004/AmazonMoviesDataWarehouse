import csv
import codecs
file=codecs.open('web.csv','a+','utf-8')
data=[[1,'test','asdfasdf'],[2,'test','asdfasdfadsf']]
writer=csv.writer(file)
for i in data:
    writer.writerow(i)
file.close()