from django.test import TestCase

# Create your tests here.


import requests
from common.models import Diary


class UserTest(TestCase):

    def setUp(self):
        self.NewDiary_url = 'http://127.0.0.1:8000/NewDiary/'
        self.DiaryLock_url='http://127.0.0.1:8000/DiaryLock/'
        self.LockCancel_url='http://127.0.0.1:8000/LockCancel/'

    #新增日记单元测试
    # def test_NewDiary(self):
    #         headers = {"content-type": "application/json"}
    #
    #         # 成功案例
    #         json_data1 = {'diaryid': '11', 'title': '这是标题', 'content': '这是内容'}
    #         r1 = requests.post(self.NewDiary_url, json=json_data1, headers=headers)
    #         result1 = r1.json()
    #         self.assertEqual(result1['ret'], 0)
    #
    #         # 失败案例
    #         json_data2 = {'diaryid': '123', 'title': '', 'content': '这是内容'}
    #         r2 = requests.post(self.NewDiary_url, json=json_data2, headers=headers)
    #         result2 = r2.json()
    #         self.assertEqual(result2['ret'], 1)

    #日记上锁单元测试
    # def test_DiaryLock(self):
    #     headers = {"content-type": "application/json"}
    #
    #     #成功案例
    #     json_data1 = {'lockid':'11','lockpasswd':'123456'}
    #     r1 = requests.post(self.DiaryLock_url, json=json_data1, headers=headers)
    #     result1 = r1.json()
    #     self.assertEqual(result1['ret'], 0)
    #
    #     # 失败案例
    #     json_data2 = {'lockid':'11','lockpasswd':''}
    #     r2 = requests.post(self.DiaryLock_url, json=json_data2, headers=headers)
    #     result2 = r2.json()
    #     self.assertEqual(result2['ret'], 1)


    #日记解锁单元测试
    # def test_LockCancel(self):
    #     headers = {"content-type": "application/json"}
    #
    #     #成功案例
    #     json_data1 = {'lockid':'11'}
    #     r1 = requests.post(self.LockCancel_url, json=json_data1, headers=headers)
    #     result1 = r1.json()
    #     self.assertEqual(result1['ret'], 0)




