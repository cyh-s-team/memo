from django.http import HttpResponse
from django.shortcuts import render

# Create your views here.
from User.models import Person
from django.core.exceptions import ObjectDoesNotExist

#用户登录逻辑
def login(request):
    name=request.POST.get('username')
    password=request.POST.get('password')
    try:
        person=Person.objects.get(name=name)
    except ObjectDoesNotExist:
        return HttpResponse('用户不存在')
    else:
        if person.password==password:
            return HttpResponse('登陆成功')
        else:
            return HttpResponse('登录失败')

#用户注册逻辑
def sign_up(request):
    name=request.POST.get('username')
    password=request.POST.get('password')
    try:
        Person.objects.get(name=name)
    except ObjectDoesNotExist:
        Person.objects.create(name=name,password=password)
        return HttpResponse('注册成功')
    else:
        return HttpResponse('用户名重复')