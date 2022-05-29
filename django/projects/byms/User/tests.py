from django.test import TestCase

# Create your tests here.

import requests
class UserTest(TestCase):

    def setUp(self):
        self.UserLogin_url = 'http://127.0.0.1:8000/UserLogin/'
        self.UserRegister_url='http://127.0.0.1:8000/UserRegister/'

    #用户登录单元测试
    def test_UserLogin(self):

        # 成功实例
        r = requests.get(self.UserLogin_url + '?username=' + "cyh" + '&passwd=' + "123")
        result = r.json()
        self.assertEqual(result['ret'], 0)

        # 失败实例
        q = requests.get(self.UserLogin_url + '?username=' + "cyh" + '&passwd=' + "123456")
        result = q.json()
        self.assertEqual(result['ret'], 1)

    def test_UserRegister(self):
        headers = {"content-type": "application/json"}
        #成功案例
        json_data1 = {'userid': '6', 'username': '656', 'passwd': '123'}
        r1= requests.post(self.UserRegister_url, json=json_data1, headers=headers)
        result = r1.json()
        self.assertEqual(result['ret'], 0)

        #失败案例
        json_data2 = {'userid': '6', 'username': '', 'passwd': '123'}
        r2 = requests.post(self.UserRegister_url, json=json_data2, headers=headers)
        result = r2.json()
        self.assertEqual(result['ret'], 1)