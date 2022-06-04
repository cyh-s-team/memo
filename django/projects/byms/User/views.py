import json

from django.http import JsonResponse
from django.shortcuts import render

# Create your views here.
from common.models import User


# 用户登录
def UserLogin(request):
    username = request.GET.get('username')
    passwd = request.GET.get('passwd')
    type = User.objects.values().filter(username=username, passwd=passwd)
    if type:
        return JsonResponse({'ret': 0, 'msg': '登录成功'})
    else:
        return JsonResponse({'ret': 1, 'msg': '登陆失败'})


# 用户注册
def UserRegister(request):
    request.params = json.loads(request.body)
    userid = request.params['userid']
    username = request.params['username']
    passwd = request.params['passwd']
    if username and passwd:
        User.objects.create(userid=userid, username=username, passwd=passwd)
        return JsonResponse({'ret': 0, 'msg': '注册成功'})
    else:
        return JsonResponse({'ret': 1, 'msg': '用户名或密码为空'})


# 修改用户详情
def UserChange(request):
    global UserChange
    # userid = request.POST.get('userid')
    # username = request.POST.get('username')
    # passwd = request.POST.get('passwd')
    request.params = json.loads(request.body)
    userid = request.params['userid']
    username = request.params['username']
    passwd = request.params['passwd']
    try:
        UserChange = User.objects.get(userid=userid)
    except:
        return JsonResponse({'ret': 1, "msg": "用户不存在，请重新输入"})

    if username:
        UserChange.username = username
    if passwd:
        UserChange.passwd = passwd
    UserChange.save()

    return JsonResponse({'ret': 0, 'msg': '修改成功'})


# 获取用户详情
def GetUser(request):
    request.params = json.loads(request.body)
    userid = request.params['userid']
    try:
        #user = User.objects.get(userid=userid)
        user = User.objects.values("passwd").filter(userid=userid)
        data = list(user)
        print(data)
        s=data[0]['passwd']
    except user.DoesNotExist:
        return {
            'ret': 1,
            'msg': "用户不存在，请重新输入"
        }
    data = list(user)
    print(data)
    return JsonResponse({
        'ret': 0,
        'pwd': s
    })
