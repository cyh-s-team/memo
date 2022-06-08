import json

from django.http import JsonResponse
from django.shortcuts import render

# Create your views here.

from common.models import Diary, DiaryLockData, DiaryRemindData,Personel,LikeList,CommentList,FollowList


# 关注用户
def PersonFollow(request):
    request.params = json.loads(request.body)
    #关注人id（主动）
    personid1 = request.params['personid1']
    #被关注人id（被动）
    personid2 = request.params['personid2']
    #关注时间
    followtime=request.params['followtime']

    global Person1Change,Person2Change
    Person1Change = Personel.objects.get(personid=personid1)
    Person1Change.follownum=Person1Change.follownum+1
    Person1Change.save()

    Person2Change=Personel.objects.get(personid=personid2)
    Person2Change.fansnum = Person2Change.fansnum + 1
    Person2Change.save()

    FollowList.objects.create(personid1=personid1,personid2=personid2,followtime=followtime)

    return JsonResponse({'ret': 0, 'msg': '关注成功'})

#取消关注
def DeletePersonFollow(request):
    request.params = json.loads(request.body)
    #关注人id（主动）
    personid1 = request.params['personid1']
    #被关注人id（被动）
    personid2 = request.params['personid2']

    global Person1Change,Person2Change,FollowListChange
    Person1Change = Personel.objects.get(personid=personid1)
    Person1Change.follownum=Person1Change.follownum-1
    Person1Change.save()

    Person2Change=Personel.objects.get(personid=personid2)
    Person2Change.fansnum = Person2Change.fansnum-1
    Person2Change.save()


    FollowListChange = FollowList.objects.get(personid1=personid1)
    FollowListChange.delete()

    return JsonResponse({'ret': 0, 'msg': '取消关注成功'})

#点赞日记
def DiaryLike(request):

    request.params = json.loads(request.body)
    personid1 = request.params['personid1']
    personid2= request.params['personid2']
    diaryid = request.params['diaryid']
    liketime=request.params['liketime']

    LikeList.objects.create(personid1=personid1,personid2=personid2,diaryid=diaryid,liketime=liketime )
    global Person2Change

    #个人资料里的点赞数(主动)加1
    Person2Change = Personel.objects.get(personid=personid1)
    Person2Change.likenum = Person2Change.likenum + 1
    Person2Change.save()

    return JsonResponse({'ret': 0, 'msg': '点赞成功'})

#取消点赞
def CancelDiaryLike(request):
    request.params = json.loads(request.body)
    #点赞人id
    personid1 = request.params['personid1']
    #日记id
    diaryid = request.params['diaryid']

    global LikeListChange
    global Person2Change

    # 个人资料里的点赞数(主动)减1
    Person2Change = Personel.objects.get(personid=personid1)
    Person2Change.likenum = Person2Change.likenum -1
    Person2Change.save()

    LikeListChange = LikeList.objects.get(personid1=personid1,diaryid=diaryid)
    LikeListChange.delete()

    return JsonResponse({'ret': 0, 'msg': '取消点赞成功'})

#评论日记
def DiaryComment(request):

    request.params = json.loads(request.body)
    personid1 = request.params['personid1']
    personid2= request.params['personid2']
    diaryid = request.params['diaryid']
    commenttime=request.params['commenttime']
    commentcontent=request.params['commentcontent']

    CommentList.objects.create(personid1=personid1,personid2=personid2,diaryid=diaryid, commenttime=commenttime,commentcontent=commentcontent)
    global Person2Change

    #个人资料里的评论数(主动)加1
    Person2Change = Personel.objects.get(personid=personid1)
    Person2Change.commentnum = Person2Change.commentnum + 1
    Person2Change.save()

    return JsonResponse({'ret': 0, 'msg': '评论成功'})

#删除评论
def DeleteDiaryComment(request):
    request.params = json.loads(request.body)
    #点赞人id
    personid1 = request.params['personid1']
    #日记id
    diaryid = request.params['diaryid']
    #日记评论时间
    commenttime=request.params['commenttime']

    global CommentListChange
    global Person2Change

    # 个人资料里的评论数(主动)减1
    Person2Change = Personel.objects.get(personid=personid1)
    Person2Change.commentnum = Person2Change.commentnum -1
    Person2Change.save()

    CommentListChange = CommentList.objects.get(personid1=personid1,diaryid=diaryid,commenttime=commenttime)
    CommentListChange.delete()

    return JsonResponse({'ret': 0, 'msg': '删除评论成功'})
