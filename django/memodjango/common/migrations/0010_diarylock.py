# Generated by Django 4.0.4 on 2022-06-03 12:32

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('common', '0009_alter_diary_iflock_alter_diary_ifpublic_and_more'),
    ]

    operations = [
        migrations.CreateModel(
            name='DiaryLock',
            fields=[
                ('lockid', models.CharField(max_length=200, primary_key=True, serialize=False)),
                ('lockpasswd', models.CharField(max_length=2000)),
            ],
        ),
    ]