import json

from django.http import JsonResponse
from django.shortcuts import render

# Create your views here.

from common.models import Diary, DiaryLockData, DiaryRemindData,Personel

# 创建用户详情（用于用户第一次登录时，对有关资料的填写）
def NewPersonel(request):
    request.params = json.loads(request.body)
    personid = request.params['personid']
    personname = request.params['personname']
    personsign = request.params['personsign']
    if personname:
        Personel.objects.create(personid=personid,personname=personname,personsign=personsign)
        return JsonResponse({'ret': 0, 'msg': '创建个人详情成功'})
    else:
        return JsonResponse({'ret': 1, 'msg': '用户名不能为空！'})