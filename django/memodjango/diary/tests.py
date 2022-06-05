from django.test import TestCase

# Create your tests here.


import requests
from common.models import Diary


class UserTest(TestCase):

    def setUp(self):
        self.NewDiary_url = 'http://127.0.0.1:8000/NewDiary/'
        self.DiaryLock_url = 'http://127.0.0.1:8000/DiaryLock/'
        self.LockCancel_url = 'http://127.0.0.1:8000/LockCancel/'
        self.DeleteDiary_url = 'http://127.0.0.1:8000/DeleteDiary/'
        self.GetDiary_url = 'http://127.0.0.1:8000/GetDiary/'
        self.DiaryRemind_url='http://127.0.0.1:8000/DiaryRemind/'
        self.DeleteDiaryRemind_url='http://127.0.0.1:8000/DeleteDiaryRemind/'

    # 新增日记单元测试
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

    # 日记上锁单元测试
    # def test_DiaryLock(self):
    #     headers = {"content-type": "application/json"}
    #
    #     #成功案例
    #     json_data1 = {'lockid':'111','lockpasswd':'123456'}
    #     r1 = requests.post(self.DiaryLock_url, json=json_data1, headers=headers)
    #     result1 = r1.json()
    #     self.assertEqual(result1['ret'], 0)
    #
    #     # 失败案例
    #     json_data2 = {'lockid':'111','lockpasswd':''}
    #     r2 = requests.post(self.DiaryLock_url, json=json_data2, headers=headers)
    #     result2 = r2.json()
    #     self.assertEqual(result2['ret'], 1)

    # 日记解锁单元测试
    # def test_LockCancel(self):
    #     headers = {"content-type": "application/json"}
    #
    #     #成功案例
    #     json_data1 = {'lockid':'111'}
    #     r1 = requests.post(self.LockCancel_url, json=json_data1, headers=headers)
    #     result1 = r1.json()
    #     self.assertEqual(result1['ret'], 0)

    # 日记删除单元测试
    # def test_DeleteDiary(self):
    #     headers = {"content-type": "application/json"}
    #
    #     #成功案例
    #     json_data1 = {'diaryid':'22'}
    #     r1 = requests.post(self.DeleteDiary_url, json=json_data1, headers=headers)
    #     result1 = r1.json()
    #     self.assertEqual(result1['ret'], 0)

    # # 获得日记详情单元测试
    # def test_GetDiary(self):
    #     # 成功实例
    #     r = requests.get(self.GetDiary_url + '?diaryid=' + "111")
    #     result1 = r.json()
    #     self.assertEqual(result1['ret'], 0)


    #日记提醒时间单元测试
    # def test_DiaryRemind(self):
    #     headers = {"content-type": "application/json"}
    #     #成功案例
    #     json_data1 = {'remindid':'111','remindtime':'2022-6-5 20:24'}
    #     r1 = requests.post(self.DiaryRemind_url, json=json_data1, headers=headers)
    #     result1 = r1.json()
    #     self.assertEqual(result1['ret'], 0)


    #取消日记提醒时间单元测试
    def test_DeleteDiaryRemind(self):
        headers = {"content-type": "application/json"}

        #成功案例
        json_data1 = {'remindid':'111'}
        r1 = requests.post(self.DeleteDiaryRemind_url, json=json_data1, headers=headers)
        result1 = r1.json()
        self.assertEqual(result1['ret'], 0)


