from django.contrib import admin

# Register your models here.
from django.contrib import admin

from .models import User,Diary,DiaryLockData,DiaryRemindData,Personel,LikeList

admin.site.register(User)
admin.site.register(Diary)
admin.site.register(DiaryLockData)
admin.site.register(DiaryRemindData)
admin.site.register(Personel)
admin.site.register(LikeList)


