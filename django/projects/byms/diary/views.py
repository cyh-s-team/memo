import json

from django.http import JsonResponse
from django.shortcuts import render

# Create your views here.


from common.models import Diary


# 新增日记
def NewDiary(request):
    request.params = json.loads(request.body)
    diaryid = request.params['diaryid']
    title = request.params['title']
    content = request.params['content']

    if title and content:
        Diary.objects.create(diaryid=diaryid,title=title,content=content)
        return JsonResponse({'ret': 0, 'msg': '创建日记成功'})
    else:
        return JsonResponse({'ret': 1, 'msg': '日记标题和内容不能为空'})
