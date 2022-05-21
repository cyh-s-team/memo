from django.db import models

# Create your models here.
from django.db import models

class Person(models.Model):
    #用户名称
    name = models.CharField(max_length=30)

    #用户
    password=models.CharField(max_length=50)

    def __str__(self):
        return self.name
