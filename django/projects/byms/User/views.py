import json

from django.http import JsonResponse
from django.shortcuts import render

# Create your views here.
from common.models import User


# 登录
def UserLogin(request):
    username = request.GET.get('username')
    passwd = request.GET.get('passwd')
    type = User.objects.values().filter(username=username, passwd=passwd)
    if type:
        return JsonResponse({'ret': 0, 'msg': '登录成功'})
    else:
        return JsonResponse({'ret': 1, 'msg': '登陆失败'})


# 注册
def UserRegister(request):
    request.params = json.loads(request.body)
    userid = request.params['userid']
    username = request.params['username']
    passwd = request.params['passwd']
    if len(username) == 0 | len(passwd) == 0:
        return JsonResponse({'ret': 1})
    else:
        User.objects.create(userid=userid, username=username, passwd=passwd)
        return JsonResponse({'ret': 0})

#查询用户详情
def User(request):

    userid =request.GET.get("userid")
    qs = User.objects.values()

#修改用户详情
def UserChange(request): #修改


    global modifyUser
    try:
        img = request.FILES['userimage']
    except:
        img = "null"
    #print(img)
    #request.params = json.loads(request_copy)
    userName = request.POST.get('username')
    passWord = request.POST.get('password')
    information = request.POST.get('information')
    briefIntroduction = request.POST.get('introduce')
    userId = request.POST.get('userid')
    #print(userId)
    try:
        modifyUser = User.objects.get(userid=userId)
    except :
        User.objects.create(username=userName,password=passWord,userid=userId,userimage=img)
        userData = User.objects.get(userid=userId)
        PlayListCollection.objects.create(playlistname=userId + 'default', userid=userData)
        PlayList.objects.create(playlistname=userId + 'default',playlistfounder=userId,playlistimage='/img/1255.png')
        return JsonResponse({'ret': 0})

    if userName:
        modifyUser.username = userName
    if passWord:
        modifyUser.password = passWord
    if briefIntroduction:
        modifyUser.briefintroduction = briefIntroduction
    if information:
        modifyUser.information = information
    if img!="null":
        modifyUser.userimage=img
    modifyUser.save()

    return JsonResponse({'ret': 0})