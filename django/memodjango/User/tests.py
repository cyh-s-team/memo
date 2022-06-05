from django.test import TestCase

# Create your tests here.

import requests
from common.models import User


class UserTest(TestCase):

    def setUp(self):
        self.UserLogin_url = 'http://127.0.0.1:8000/UserLogin/'
        self.UserRegister_url = 'http://127.0.0.1:8000/UserRegister/'
        self.UserChange_url = 'http://127.0.0.1:8000/UserChange/'
        self.GetUser_url='http://127.0.0.1:8000/GetUser/'

    #用户登录单元测试
    # def test_UserLogin(self):
    #     # 成功实例
    #     r = requests.get(self.UserLogin_url + '?username=' + "cyh" + '&passwd=' + "123")
    #     result1 = r.json()
    #     self.assertEqual(result1['ret'], 0)
    #
    #     # 失败实例
    #     q = requests.get(self.UserLogin_url + '?username=' + "cyh" + '&passwd=' + "123456")
    #     result2 = q.json()
    #     self.assertEqual(result2['ret'], 1)
    #

    # # 用户注册单元测试
    # def test_UserRegister(self):
    #     headers = {"content-type": "application/json"}
    #
    #     # 成功案例
    #     json_data1 = {'userid': '52488851', 'username': '656', 'passwd': '123'}
    #     r1 = requests.post(self.UserRegister_url, json=json_data1, headers=headers)
    #     result1 = r1.json()
    #     self.assertEqual(result1['ret'], 0)
    #
    #     # 失败案例
    #     json_data2 = {'userid': '9949388491', 'username': '', 'passwd': '123'}
    #     r2 = requests.post(self.UserRegister_url, json=json_data2, headers=headers)
    #     result2 = r2.json()
    #     self.assertEqual(result2['ret'], 1)
    #

    #修改用户信息单元测试
    # def test_UserChange(self):
    #
    #     headers = {"content-type": "application/json"}
    #
    #     #成功案例
    #     json_data1 = {'userid': '1', 'username': 'cyhcyh', 'passwd': '123123'}
    #     r1 = requests.post(self.UserChange_url, json=json_data1, headers=headers)
    #     result1 = r1.json()
    #     self.assertEqual(result1['ret'], 0)
    #
    #     # 失败案例
    #     json_data2 = {'userid': '111', 'username': 'cyhcyh', 'passwd': '123123'}
    #     r2 = requests.post(self.UserChange_url, json=json_data2, headers=headers)
    #     result2 = r2.json()
    #     self.assertEqual(result2['ret'], 1)

    #获取用户详情测试(用于找回密码)
    # def test_GetUser(self):
    #
    #     headers = {"content-type": "application/json"}
    #
    #     # 成功案例
    #     json_data1 = {'userid': '1'}
    #     r1 = requests.post(self.GetUser_url, json=json_data1, headers=headers)
    #     result1 = r1.json()
    #     print(result1)
    #     self.assertEqual(result1['ret'], 0)

        # 失败案例
        # json_data2 = {'userid': '111'}
        # r2 = requests.post(self.GetUser_url, json=json_data2, headers=headers)
        # result2 = r2.json()
        # self.assertEqual(result2['ret'], 1)









