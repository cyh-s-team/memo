# Generated by Django 4.0.4 on 2022-06-05 12:27

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('common', '0012_diarylockdata'),
    ]

    operations = [
        migrations.CreateModel(
            name='DiaryRemindData',
            fields=[
                ('remindid', models.CharField(max_length=200, primary_key=True, serialize=False)),
                ('remindtime', models.CharField(max_length=200)),
            ],
        ),
    ]