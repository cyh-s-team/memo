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

# 获取用户详情
def GetPersonel(request):
    personid=request.GET.get('personid')


    personname=Personel.objects.values("personname").filter(personid=personid)
    data1 = list(personname)
    title = data1[0]['personname']
    print(title)

    personsign = Personel.objects.values("personsign").filter(personid=personid)
    data11 = list(personsign)
    title1 = data11[0]['personsign']
    print(title1)

    follownum= Personel.objects.values("follownum").filter(personid=personid)
    data12 = list(follownum)
    title2 = data12[0]['follownum']
    print(title2)

    fansnum = Personel.objects.values("fansnum").filter(personid=personid)
    data13 = list(fansnum)
    title3 = data13[0]['fansnum']
    print(title3)

    commentnum = Personel.objects.values("commentnum").filter(personid=personid)
    data14 = list(commentnum)
    title4 = data14[0]['commentnum']
    print(title4)

    likenum = Personel.objects.values("likenum").filter(personid=personid)
    data142 = list(likenum)
    title42 = data142[0]['likenum']
    print(title42)



    return JsonResponse({'ret': 0,
                         'personname': title,
                         'personsign':title1,
                         'follownum': title2,
                         'fansnum ': title3,
                         'commentnum': title4,
                         'likenum': title42,
                         })