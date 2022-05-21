from django.urls import path

from . import views

urlpatterns = [
    path('orders/', views.listorders),
    path('orders1/', views.listorders1),
    path('customers/', views.listcustomers),
]