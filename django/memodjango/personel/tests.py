import requests
from django.test import TestCase

# Create your tests here.



class PersonelTest(TestCase):

    def setUp(self):
        self.NewPersonel_url = 'http://127.0.0.1:8000/NewPersonel/'

    #创建用户详情测试
    def test_NewPersonel(self):
        headers = {"content-type": "application/json"}

        # 成功案例
        json_data1 = {'personid': '1111', 'personname': 'cyh', 'personsign': '大家好'}
        r1 = requests.post(self.NewPersonel_url, json=json_data1, headers=headers)
        result1 = r1.json()
        self.assertEqual(result1['ret'], 0)

        # 失败案例
        json_data2 = {'personid': '1111', 'personname': '', 'personsign': '大家好'}
        r2 = requests.post(self.NewPersonel_url, json=json_data2, headers=headers)
        result2 = r2.json()
        self.assertEqual(result2['ret'], 1)



