# Generated by Django 4.0.4 on 2022-06-04 11:25

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('common', '0011_delete_diarylock'),
    ]

    operations = [
        migrations.CreateModel(
            name='DiaryLockData',
            fields=[
                ('lockid', models.CharField(max_length=200, primary_key=True, serialize=False)),
                ('lockpasswd', models.CharField(max_length=2000)),
            ],
        ),
    ]
