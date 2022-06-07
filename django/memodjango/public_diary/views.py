import json

from django.http import JsonResponse
from django.shortcuts import render

# Create your views here.

from common.models import Diary, DiaryLockData, DiaryRemindData,Personel,LikeList,CommentList


# 关注用户
def PersonFollow(request):
    request.params = json.loads(request.body)
    #关注人id（主动）
    personid1 = request.params['personid1']
    #被关注人id（被动）
    personid2 = request.params['personid2']

    global Person1Change,Person2Change
    Person1Change = Personel.objects.get(personid=personid1)
    Person1Change.follownum=Person1Change.follownum+1
    Person1Change.save()

    Person2Change=Personel.objects.get(personid=personid2)
    Person2Change.fansnum = Person2Change.fansnum + 1
    Person2Change.save()


    return JsonResponse({'ret': 0, 'msg': '关注成功'})

