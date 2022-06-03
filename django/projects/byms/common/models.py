from django.db import models

# Create your models here.
from django.db import models


# 用户表
class User(models.Model):
    # 用户id
    userid = models.CharField(max_length=200, primary_key=True)
    # 用户名称
    username = models.CharField(max_length=30)
    # 用户密码
    passwd = models.CharField(max_length=50)


class Diary(models.Model):
    # 日记id
    diaryid = models.CharField(max_length=200, primary_key=True)
    # 日记标题
    title = models.CharField(max_length=50)
    # 日记内容
    content = models.CharField(max_length=2000)
    # 日记是否公开，0代表不公开，1代表公开
    ifpublic = models.IntegerField(default=0)
    # 日记是否上锁，0代表没上锁，1代表上锁
    iflock = models.IntegerField(default=0)
    # 日记是否公开，0代表不公开，1代表公开
    ifremind = models.IntegerField(default=0)


class DiaryLock(models.Model):
    #日记锁id
    lockid= models.CharField(max_length=200, primary_key=True)
    #日记锁密码
    lockpasswd=models.CharField(max_length=2000)