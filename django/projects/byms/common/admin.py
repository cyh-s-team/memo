from django.contrib import admin

# Register your models here.
from django.contrib import admin

from .models import User,Diary,DiaryLock

admin.site.register(User)
admin.site.register(Diary)
admin.site.register(DiaryLock)