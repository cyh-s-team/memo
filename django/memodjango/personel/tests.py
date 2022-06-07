import requests
from django.test import TestCase

# Create your tests here.



class PersonelTest(TestCase):

    def setUp(self):
        self.NewPersonel_url = 'http://127.0.0.1:8000/NewPersonel/'
        self.GetPersonel_url='http://127.0.0.1:8000/GetPersonel/'
        self.GetPersonelLike_url='http://127.0.0.1:8000/GetPersonelLike/'

    #创建用户详情测试
    # def test_NewPersonel(self):
    #     headers = {"content-type": "application/json"}
    #
    #     # 成功案例
    #     json_data1 = {'personid': '1111', 'personname': 'cyh', 'personsign': '大家好'}
    #     r1 = requests.post(self.NewPersonel_url, json=json_data1, headers=headers)
    #     result1 = r1.json()
    #     self.assertEqual(result1['ret'], 0)
    #
    #     # 失败案例
    #     json_data2 = {'personid': '1111', 'personname': '', 'personsign': '大家好'}
    #     r2 = requests.post(self.NewPersonel_url, json=json_data2, headers=headers)
    #     result2 = r2.json()
    #     self.assertEqual(result2['ret'], 1)

    #获取个人资料单元测试
    # def test_GetPersonel(self):
    #     # 成功实例
    #     r = requests.get(self.GetPersonel_url + '?personid=' + "111")
    #     result1 = r.json()
    #     self.assertEqual(result1['ret'], 0)

    # 获取点赞列表单元测试
    def test_GetPersonelLike(self):
        # 成功实例
        r = requests.get(self.GetPersonelLike_url + '?personid2=' + "11")
        result1 = r.json()
        self.assertEqual(result1['ret'], 0)





