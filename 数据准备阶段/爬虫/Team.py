# -------------------------运行前必读------------------------------------
# 一个进程分配6000个网址大约需要运行24h（可能略有不同）
# 建议最多同时启动6个进程（如果电脑很强大，酌情增加，过多的程序可能会导致程序意外退出或者电脑重启）
# 启动前，每个进程要为其准备存储数据的文件和url文件
# 如果重新启动程序，需要清空存储用文件（如果不想每次清空可以把文件打开模式换成w）
# 为了调试方便，没有使用多线程技术 ，如有需要可以自己增加该功能

# 目前可以爬取率高于80%的标签 ：Order,URL,Name,ASIN,ReleaseTime,Time,Director,Writer,Actor,Type,Comments,Format,Cost,Grade
# 过低爬取率的标签没有被采纳

# 可以新建一个python解释器环境后通过pip快速安装包（import中的）
# 程序中使用了3种浏览器，每种浏览器都需要在电脑中安装相应的driver ，建议阅读blog解决。
# 最少安装两种浏览器，不切换浏览器会在1000余次访问后被识别并反爬，访问时大概率弹出验证码。推荐优先安装chrome和edge，macos也可以尝试安装safari）
# 注意：driver版本需要和浏览器版本对应。

import codecs
import csv
import re
import time
from bs4 import BeautifulSoup as BS
from selenium import webdriver
import signal
import os


def ChromeSet(): # Chrome浏览器驱动
    optC = webdriver.ChromeOptions()
    optC.add_argument('--lang=en')  # 设置网页语言
    optC.add_argument('blink-settings=imagesEnabled=false')  # 不加载图片，提升运行速度
    #optC.add_argument('--headless')  # 不加载浏览器界面
    return webdriver.Chrome(options=optC)

def EdgeSet(): # Edge浏览器驱动
    return webdriver.Edge()

def FireFoxSet(): # FireFox浏览器驱动
    optF=webdriver.FirefoxOptions()
    optF.add_argument('--lang=en') # 设置网页语言
    optF.add_argument('blink-settings=imagesEnabled=false')  # 不加载图片，提升运行速度
    #optF.add_argument('--headless')  # 不加载浏览器界面
    return webdriver.Firefox(options=optF)

def resetUrl(URL):
    bannedNum = bannedUrl.get(URL, 0)
    if (bannedNum == 0):
        bannedUrl[URL] = 1
        URLLIST.append(URL + '\n')
        url = open(Rest, "a+")
        url.write(URL+"\n")
        url.close()
        browser[0] += 1
    elif (bannedNum <= 2):
        bannedUrl[URL] = bannedNum + 1
        URLLIST.append(URL + '\n')
        url = open(Rest, "a+")
        url.write(URL+"\n")
        url.close()
        browser[0] += 1
    else:
        unsolvedUrl = open(UnSOLVED, "a")  # 不可访问的网址
        unsolvedUrl.write("{}\n".format(URL))
        unsolvedUrl.close()
        browser[0] += 1
    #time.sleep(30)

def get_pid(driver):
   # chromepid = int(driver.service.process.pid)
    pid=int(driver.service.process.pid)
    i=0
    return (pid)

def kill_browser(pid):
    try:
        os.kill(pid, signal.SIGTERM)
        return 1
    except:
        return 0
def closeBrowser(driver):
    pid=0
    try:
        pid=get_pid(driver)
        driver.close()
        driver.quit()
    except:
        print("cant close the process!")
    else:
        print("close the process successfully!")
    try:
        kill_browser(pid)
    except:
        print("cant kill the process!")
    else:
        print("kill the process successfully!")


def connect_to_web(URL,order): # 访问并处理数据
    Data=[]
    driver = None
    try: # 浏览器选择，如果某个浏览器未安装，注释相应的函数，下方的调用换成已经安装driver的浏览器（推荐优先安装chrome和edge，firefox返回验证码概率较高）
        if(browser[0]%3==0):
            driver=ChromeSet()
        elif(browser[0]%3==1):
            driver=FireFoxSet()
        else:
            driver=ChromeSet()
    except:
        time.sleep(3)
        print("\nWebsite {} Driver not ready ，jump！".format(order))  # Driver异常处理
        resetUrl(URL)
        try:
            closeBrowser(driver)
        except:
            return
        return

    try:
        driver.get("https://www."+URL)
        #time.sleep(3)
    except: # 访问超时处理
        time.sleep(3)
        print("\nWebsite {} timeout ，jump！".format(order)) # get超时异常处理
        resetUrl(URL)
        try:
            closeBrowser(driver)
        except:
            return
        return

    try: # 判定网站类型
        name=driver.find_element_by_css_selector("[class='_1GTSsh _2Q73m9']").text
    except:  # 爬取Movie&TV网站数据

        try:  # 获取Movie&TV名称
            name=driver.find_element_by_id("productTitle").text
        except:  # 判定非正常页面处理
            time.sleep(3)
            print("\nWebsite {} kill the robot ，jump！".format(order)) # 验证码及无法访问页面异常处理
            resetUrl(URL)
            try:
                closeBrowser(driver)
            except:
                return
            return
        else:
            Data.append(urlOrder[0])
            Data.append(URL)
            Data.append(name)
        try:
            # 获取 html
           data=driver.find_element_by_css_selector("[class='a-unordered-list a-nostyle a-vertical a-spacing-none detail-bullet-list']").get_attribute('innerHTML')
        except: # 未获取到填充空值
            Data.append(URL[-10:])
            Data.append("")
            Data.append("")
            Data.append("")
            Data.append("")
            Data.append("")
        else:

            # html中匹配字符串
            ReleaseTime=re.search(r"(?<=Release date\n:\n</span>\n<span>).*?(?=</)",data)
            Time = re.search(r"(?<=Run time\n:\n</span>\n<span>).*?(?=</)", data)
            Director = re.search(r"(?<=Director\n:\n</span>\n<span>).*?(?=</)", data)
            Writer = re.search(r"(?<=Writers\n:\n</span>\n<span>).*?(?=</)", data)
            Actors = re.search(r"(?<=Actors\n:\n</span>\n<span>).*?(?=</)", data)
            Data.append(URL[-10:])
            if (ReleaseTime == None):
                Data.append("")
            else:
                Data.append(ReleaseTime.group(0))
            if (Time == None):
                Data.append("")
            else:
                Data.append(Time.group(0))
            if (Director == None):
                Data.append("")
            else:
                Data.append(Director.group(0))
            if (Writer == None):
                Data.append("")
            else:
                Data.append(Writer.group(0))
            if (Actors == None):
                Data.append("")
            else:
                Data.append(Actors.group(0))
            # 爬取type
        try:
            type=driver.find_element_by_xpath("/html/body//div[@id='wayfinding-breadcrumbs_feature_div']/ul/li[3]/span/a").text
        except:
            Data.append("")
        else:
            Data.append(type)
            # 爬取comments
        try:
            comments=re.search(r"([+-]?\d+(\.\d+)?)",driver.find_element_by_id("acrCustomerReviewText").text).group(0)
        except:
            Data.append("")
        else:
            Data.append(comments)
            # 爬取format
        formatTag=''
        try:
            soup=BS(driver.find_element_by_id("bylineInfo").get_attribute('innerHTML'),'lxml')
            format = soup.find(text=(u"Format: ")).find_next('span').text
        except:
            Data.append("")
        else:
            formatTag=format
            Data.append(format)
            # 爬取cost
        try:
            soup=BS(driver.find_element_by_id("tmmSwatches").get_attribute("innerHTML"),'lxml')
            cost = soup.find(text=formatTag).find_next('span').text
            cost=re.search(r"([+-]?\d+(\.\d+)?)",cost).group(0)
        except:
            Data.append("")
        else:
            cost='$'+cost
            Data.append(cost)
            # 爬取grade
        try:
            grade=re.search(r"(?<=alt\">)(.+?)(?=out)",driver.find_element_by_css_selector("[class='a-popover-trigger a-declarative']").get_attribute('innerHTML')).group(0)
        except:
            Data.append("")
        else:
            Data.append(grade)

        print("\nWebsite {} successfully downloaded".format(order))

        # 爬取Prime Video数据
    else:
        Data.append(urlOrder[0])
        Data.append(URL)
        Data.append(name)
        Data.append(URL[-10:])
            # 爬取ReleaseTime
        try:
            ReleaseTime=driver.find_element_by_css_selector("[data-automation-id=release-year-badge]").text
        except:
            Data.append("")
        else:
            Data.append(ReleaseTime)
            # 爬取Time
        try:
            Time=driver.find_element_by_css_selector("[data-automation-id=runtime-badge]").text
        except:
            Data.append("")
        else:
            Data.append(Time)

        try:  #  获取部分HTML
            metaInfo=driver.find_element_by_id("meta-info").get_attribute('innerHTML')
        except:
            Data.append("")
            Data.append("")
            Data.append("")
            Data.append("")
        else:
            try:
                # 爬取Director
                soup=BS(metaInfo,'lxml')
                Director=soup.find(text=(u"Directors")).find_parent('dl').find_all(class_="_1NNx6V")
            except:
                Data.append("")
                Data.append("")
            else:
                Dirs = ''
                for i in Director:
                    Dirs += i.text
                    Dirs += ','
                Dirs = Dirs[:-1]
                Data.append(Dirs)
                Data.append("")
            try:
                # 爬取Actor
                soup=BS(metaInfo,'lxml')
                Actor=soup.find(text=(u"Starring")).find_parent('dl').find_all(class_="_1NNx6V")
            except:
                Data.append("")
            else:
                Acts = ''
                for i in Actor:
                    Acts += i.text
                    Acts += ','
                Acts = Acts[:-1]
                Data.append(Acts)
            try:
                # 爬取type
                soup=BS(metaInfo,'lxml')
                Type=soup.find(text=(u"Genres")).find_parent('dl').find_all(class_="_1NNx6V")
            except:
                Data.append("")
            else:
                types = ''
                for i in Type:
                    types += i.text
                    types += ','
                types = types[:-1]
                Data.append(types)

            # 爬取comments
        try:
            HTML=driver.find_element_by_css_selector("[data-automation-id=customer-review-badge]").get_attribute('innerHTML')
            comments = re.search(r"(?<=nbsp;\()(.+?)(?=\)</)", HTML).group(0)
        except:
            Data.append("")
        else:
            Data.append(comments)


            # 爬取format
        try:  # 获取部分HTML
            metaInfo = driver.find_element_by_id("btf-product-details").get_attribute('innerHTML')
        except:
            Data.append("")
        else:
            try:
                soup = BS(metaInfo, 'lxml')
                format = soup.find(text=(u"Format")).find_parent('dt').find_next_sibling('dd')
            except:
                Data.append("")
            else:
                Data.append(format.text)

            # 爬取cost
        try:
            firstHtml=driver.find_element_by_css_selector("[class=_2U1bmy]").get_attribute('innerHTML')
            soup=BS(firstHtml,'lxml')
            text=soup.find(text=(u"Buy ")).find_parent('button').text
            cost=re.search(r'([+-]?\d+(\.\d+)?)',text).group(0)
        except:
            Data.append("")
        else:
            cost = "$"+cost
            Data.append(cost)
            # 爬取grade
        try:
            HTML=driver.find_element_by_css_selector("[data-automation-id=customer-review-badge]").get_attribute('outerHTML')
            grade = re.search(r"(?<=Rated)(.+?)(?=out)", HTML).group(0)
        except:
            Data.append("")
        else:
            Data.append(grade)


        print("\nWebsite {} successfully downloaded".format(order))




    # 实时数据导出csv
    website = codecs.open(CSV, 'a+', 'utf-8')  # 数据集
    writer = csv.writer(website)
    try:
        try:
            closeBrowser(driver)
        except:
            pass
        writer.writerow(Data)
    except:
        print("数据异常，未能写入csv文件")
        unsolvedUrl = open(UnSOLVED, "a")  # 不可访问的网址
        unsolvedUrl.write("{}\n".format(URL))
        unsolvedUrl.close()
    else:
        print("数据成功写入csv文件")
    website.close()

    urlOrder[0] += 1
    return



# 初始化文件路径 ，每个进程都要进行检查配置（当前使用的是样例文件）
CSV='web42000.csv' # 存储data用CSV文件名
UnSOLVED='unsolvedUrl42000.txt' # 存储访问失败url用txt文件名
Rest="url_42000.txt" # 待访问url文件名
Log="log42000.txt" # 记录指针

# 初始化全局变量
url=open(Rest,"r")
log=open(Log,"r")
URLLIST=url.readlines()
bannedUrl={} # 访问失败的url
browser=[0] # 浏览器切换编号
urlOrder=[int(log.readline()[:-1])]
i=int(log.readline()[:-1])
url.close()
log.close()
# Spider is running
while True:
    connect_to_web(URLLIST[i][:-1],i)
    i+=1
    log = open(Log, "w")
    log.write(str(urlOrder[0])+'\n')
    log.write(str(i)+'\n')
    log.close()
    #time.sleep(random.random()*2)
    if(len(URLLIST)==i+1):
        print("Task Complete")
        break





