"""byms URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path,include
from User.views import UserLogin,UserRegister,UserChange,GetUser
from diary.views import NewDiary, DiaryLock,LockCancel,DeleteDiary,GetDiary,DiaryRemind,DeleteDiaryRemind,DiarySearch
from personel.views import NewPersonel,GetPersonel



urlpatterns = [
    path('admin/', admin.site.urls),

    #用户管理接口,路由命名符合接口规范
    path('UserLogin/',UserLogin),
    path('UserRegister/',UserRegister),
    path('UserChange/',UserChange),
    path('GetUser/',GetUser),
    path('NewDiary/',NewDiary),
    path('DiaryLock/',DiaryLock),
    path('LockCancel/',LockCancel),
    path('DeleteDiary/',DeleteDiary),
    path('GetDiary/',GetDiary),
    path('DiaryRemind/',DiaryRemind),
    path('DeleteDiaryRemind/',DeleteDiaryRemind),
    path('DiarySearch/',DiarySearch),
    path('NewPersonel/',NewPersonel),
    path('GetPersonel/',GetPersonel)









]
