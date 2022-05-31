import json

from django.shortcuts import render

# Create your views here.


from common.models import Diary


# 新增日记
def NewDiary(request):
    request.params = json.loads(request.body)
    diaryid = request.params['diaryid']
    title = request.params['title']
    content = request.params['content']
    ifpublic = request.params['ifpublic']
    iflock = request.params['iflock']
    ifremind = request.params['ifremind']
