import requests
from django.test import TestCase

# Create your tests here.



class Public_Diary_Test(TestCase):

    def setUp(self):
        self.PersonFollow_url = 'http://127.0.0.1:8000/PersonFollow/'
        self.DeletePersonFollow_url='http://127.0.0.1:8000/DeletePersonFollow/'
        self.DiaryLike_url='http://127.0.0.1:8000/DiaryLike/'
        self.CancelDiaryLike_url='http://127.0.0.1:8000/CancelDiaryLike/'
        self.DiaryComment_url='http://127.0.0.1:8000/DiaryComment/'
        self.DeleteDiaryComment_url='http://127.0.0.1:8000/DeleteDiaryComment/'

    # # 关注用户单元测试
    # def test_PersonFollow(self):
    #     headers = {"content-type": "application/json"}
    #
    #     # 成功案例
    #     json_data1 = {'personid1': '111', 'personid2': '11','followtime':'2022-6-7 21:32'}
    #     r1 = requests.post(self.PersonFollow_url, json=json_data1, headers=headers)
    #     result1 = r1.json()
    #     self.assertEqual(result1['ret'], 0)

    # # 取消关注单元测试
    # def test_DeletePersonFollow(self):
    #     headers = {"content-type": "application/json"}
    #
    #     # 成功案例
    #     json_data1 = {'personid1': '111', 'personid2': '11'}
    #     r1 = requests.post(self.DeletePersonFollow_url, json=json_data1, headers=headers)
    #     result1 = r1.json()
    #     self.assertEqual(result1['ret'], 0)


    #点赞单元测试
    # def test_DiaryLike(self):
    #     headers = {"content-type": "application/json"}
    #
    #     # 成功案例
    #     json_data1 = {'diaryid':'11','personid1': '111', 'personid2': '11','liketime':'2022-2-22 15:30'}
    #     r1 = requests.post(self.DiaryLike_url, json=json_data1, headers=headers)
    #     result1 = r1.json()
    #     self.assertEqual(result1['ret'], 0)

    # 取消点赞单元测试
    # def test_CancelDiaryLike(self):
    #     headers = {"content-type": "application/json"}
    #
    #     # 成功案例
    #     json_data1 = {'diaryid':'11','personid1': '111'}
    #     r1 = requests.post(self.CancelDiaryLike_url, json=json_data1, headers=headers)
    #     result1 = r1.json()
    #     self.assertEqual(result1['ret'], 0)


    #评论日记单元测试
    # def test_DiaryComment(self):
    #     headers = {"content-type": "application/json"}
    #
    #     # 成功案例
    #     json_data1 = {'diaryid':'11','personid1': '111', 'personid2': '11','commenttime':'2022-2-22 15:30','commentcontent':'hello!'}
    #     r1 = requests.post(self.DiaryComment_url, json=json_data1, headers=headers)
    #     result1 = r1.json()
    #     self.assertEqual(result1['ret'], 0)


    #删除日记单元测试
    # def test_DeleteDiaryComment(self):
    #     headers = {"content-type": "application/json"}
    #
    #     # 成功案例
    #     json_data1 = {'diaryid':'11','personid1': '111','commenttime':'2011-1-1 11:11'}
    #     r1 = requests.post(self.DeleteDiaryComment_url, json=json_data1, headers=headers)
    #     result1 = r1.json()
    #     self.assertEqual(result1['ret'], 0)



