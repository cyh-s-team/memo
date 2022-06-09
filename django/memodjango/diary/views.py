import json

from django.http import JsonResponse
from django.shortcuts import render

# Create your views here.


from common.models import Diary, DiaryLockData, DiaryRemindData,LikeList


# 新增日记
def NewDiary(request):
    request.params = json.loads(request.body)
    diaryid = request.params['diaryid']
    title = request.params['title']
    content = request.params['content']

    if title and content:
        Diary.objects.create(diaryid=diaryid, title=title, content=content)
        return JsonResponse({'ret': 0, 'msg': '创建日记成功'})
    else:
        return JsonResponse({'ret': 1, 'msg': '日记标题和内容不能为空'})


# 日记上锁
def DiaryLock(request):
    request.params = json.loads(request.body)
    global diarychange
    # lockid和diaryid应始终保持一致
    lockid = request.params['lockid']
    lockpasswd = request.params['lockpasswd']
    diaryid = lockid

    if lockpasswd:
        DiaryLockData.objects.create(lockid=lockid, lockpasswd=lockpasswd)

        # 将日记表的是否有密码更改为1，即代表有密码
        diarychange = Diary.objects.get(diaryid=diaryid)
        diarychange.iflock = 1
        diarychange.save()
        return JsonResponse({'ret': 0, 'msg': '密码创建成功'})
    else:
        return JsonResponse({'ret': 1, 'msg': '密码不能为空'})


# 日记解锁
def LockCancel(request):
    request.params = json.loads(request.body)
    global diarychange, lockdatachange
    # lockid和diaryid应始终保持一致
    lockid = request.params['lockid']

    # 日记的是否上锁改为0
    diaryid = lockid
    diarychange = Diary.objects.get(diaryid=diaryid)
    diarychange.iflock = 0
    diarychange.save()

    lockdatachange = DiaryLockData.objects.get(lockid=lockid)
    lockdatachange.delete()
    return JsonResponse({'ret': 0, 'msg': '密码锁已删除'})


# 获取日记详情
def GetDiary(request):
    diaryid=request.GET.get('diaryid')
    #得到日记标题内容
    titledata = Diary.objects.values("title").filter(diaryid=diaryid)
    data1 = list(titledata)
    title = data1[0]['title']
    print(title)

    #得到日记正文内容
    contentdata = Diary.objects.values("content").filter(diaryid=diaryid)
    data2 = list(contentdata)
    content = data2[0]['content']
    print(content)


    return JsonResponse({'ret': 0,
                         'title': title,
                         'content':content
                         })



# 日记删除
def DeleteDiary(request):
    request.params = json.loads(request.body)
    global diarychange, lockdatachange
    diaryid = request.params['diaryid']
    lockid = diaryid
    # 删除日记
    diarychange = Diary.objects.get(diaryid=diaryid)

    # 如果日记有密码锁,日记删除，密码锁也跟着删除
    print(diarychange.iflock)
    if diarychange.iflock == 1:
        lockdatachange = DiaryLockData.objects.get(lockid=lockid)
        lockdatachange.delete()
    diarychange.delete()
    return JsonResponse({'ret': 0, 'msg': '日记删除成功'})

#创建日记提醒
def DiaryRemind(request):
    request.params = json.loads(request.body)
    global diarychange
    # remindid和diaryid应始终保持一致
    remindid = request.params['remindid']
    remindtime = request.params['remindtime']
    diaryid = remindid

    DiaryRemindData.objects.create(remindid=remindid, remindtime=remindtime)
    diarychange = Diary.objects.get(diaryid=diaryid)
    diarychange.ifremind = 1
    diarychange.save()
    return JsonResponse({'ret': 0,'msg': '设置成功'})

#取消日记提醒时间
def DeleteDiaryRemind(request):
    request.params = json.loads(request.body)
    global diarychange, reminddatachange
    # remindid和diaryid应始终保持一致
    remindid = request.params['remindid']

    # 日记的是否设置提醒时间改为0
    diaryid = remindid
    diarychange = Diary.objects.get(diaryid=diaryid)
    diarychange.ifremind = 0
    diarychange.save()

    reminddatachange = DiaryRemindData.objects.get(remindid=remindid)
    reminddatachange.delete()
    return JsonResponse({'ret': 0, 'msg': '日记提醒已取消'})

#日记按内容搜索
def DiarySearch(request):
    content= request.GET.get('content')
    data = list(Diary.objects.values("diaryid").filter(content__contains=content))
    return JsonResponse({'ret': 0,
                         'data':data})


