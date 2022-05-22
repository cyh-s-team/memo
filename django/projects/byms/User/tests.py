from django.test import TestCase

import requests
import unittest


class UserTest(TestCase):

    def setUp(self):
        self.user_url = 'http://127.0.0.1:8000/MemoUserLogin/'

    def test_login_user(self):  # 错误用户和错误密码
        form_data = {'username': 'user0016', 'password': 'bbfgb', }
        r = requests.post(self.user_url, data=form_data)
        result = r.json()
        self.assertEqual(result['msg'], '用户不存在')

    def test_login_user(self):  # 正确用户和错误密码
        form_data = {'username': 'cyh', 'password': 'bbfgb', }
        r = requests.post(self.user_url, data=form_data)
        result = r.json()
        self.assertEqual(result['msg'], '登陆失败')

    def test_login_user(self):  # 正确用户和正确密码
        form_data = {'username': 'cyh', 'password': '123', }
        r = requests.post(self.user_url, data=form_data)
        result = r.json()
        self.assertEqual(result['msg'], '登陆成功')