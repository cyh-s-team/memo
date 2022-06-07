import requests
from django.test import TestCase

# Create your tests here.



class Public_Diary_Test(TestCase):

    def setUp(self):
        self.PersonFollow_url = 'http://127.0.0.1:8000/PersonFollow/'



##
    # # 关注用户单元测试
    def test_PersonFollow(self):
        headers = {"content-type": "application/json"}

        # 成功案例
        json_data1 = {'personid1': '111', 'personid2': '11'}
        r1 = requests.post(self.PersonFollow_url, json=json_data1, headers=headers)
        result1 = r1.json()
        self.assertEqual(result1['ret'], 0)

