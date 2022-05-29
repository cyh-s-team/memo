from django.db import models

# Create your models here.
from django.db import models

class User(models.Model):
    # 用户id
    userid = models.CharField(max_length=200, primary_key=True)
    #用户名称
    username = models.CharField(max_length=30)
    #用户密码
    passwd=models.CharField(max_length=50)


