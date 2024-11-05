from django.http import HttpResponse
from django.shortcuts import render
from django.template import loader

from bonnes_lectures.models import Book


# Create your views here.
def about(request):
    template = loader.get_template('about.html')
    return HttpResponse(template.render())

def welcome(request):
    template = loader.get_template('welcome.html')
    return HttpResponse(template.render())

def generateModels(request):
    newBook = Book(
        name='nom1',
        publisher='publisher1',
        year=1999,
        isbn='isbn1',
        backCover='backcover1',
        cover='false',

    )
    newBook.save()
    return HttpResponse('generated')