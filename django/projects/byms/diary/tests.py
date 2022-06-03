from django.test import TestCase

# Create your tests here.


import requests
from common.models import Diary

class UserTest(TestCase):

   def setUp(self):
       self.NewDiary_url = 'http://127.0.0.1:8000/NewDiary/'

   def test_NewDiary(self):
           headers = {"content-type": "application/json"}

           # 成功案例
           json_data1 = {'diaryid': '11', 'title': '这是标题', 'content': '这是内容'}
           r1 = requests.post(self.NewDiary_url, json=json_data1, headers=headers)
           result1 = r1.json()
           self.assertEqual(result1['ret'], 0)

           # 失败案例
           json_data2 = {'diaryid': '123', 'title': '', 'content': '这是内容'}
           r2 = requests.post(self.NewDiary_url, json=json_data2, headers=headers)
           result2 = r2.json()
           self.assertEqual(result2['ret'], 1)