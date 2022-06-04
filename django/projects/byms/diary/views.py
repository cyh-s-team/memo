import json

from django.http import JsonResponse
from django.shortcuts import render

# Create your views here.


from common.models import Diary,DiaryLock


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
    diaryid=lockid

    if(lockpasswd):
        DiaryLock.objects.create(lockid=lockid,lockpasswd=lockpasswd)
        #将日记表的是否有密码更改为1，即代表有密码
        diarychange = Diary.objects.values("").filter(diaryid=diaryid)
        Diary.iflock=1
        return JsonResponse({'ret': 0, 'msg': '密码创建成功'})
    else:
        return JsonResponse({'ret': 1, 'msg': '密码不能为空'})

