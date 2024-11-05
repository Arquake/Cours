from django.db import models

# Create your models here.

class Book(models.Model):
    name = models.CharField(max_length=100)
    publisher = models.CharField(max_length=100)
    year = models.IntegerField()
    isbn = models.CharField(max_length=100)
    backCover = models.CharField(max_length=100)
    cover = models.BooleanField()