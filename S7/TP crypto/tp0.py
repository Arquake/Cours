from PIL import Image

#Conversion d'une image en noir et blanc
def convertIntoBW(im1,im2):
    img = Image.open(im1)
    print(img.getpixel((200,200)))
    tresh = 200
    fn =  lambda x: 255 if x > tresh else 0
    r = img.convert('L').point(fn, mode='1')
    r.save(im2)
    
#convertIntoBW("C:\\Users\\Nicolas\\Downloads\\intruder-lowtiergod.png", "C:\\Users\\Nicolas\\Downloads\\intruder-lowtiergodkysbw.png")

#Conversion d'une image en noir et blanc
def hideImg(hidingString,hiderString , output):
    hiding = Image.open(hidingString)
    hiding.convert('RGB')
    hider = Image.open(hiderString)
    hider.convert('RGB')


    for i in range(hiding.size[0]):
        for j in range(hiding.size[1]):
            # on récupère le pixel de l'image qui cache
            rgbBefore = hider.getpixel((i,j))
            # on récupère le pixel de l'image qui va cacher
            hidingPixel = hiding.getpixel((i,j))
            # On garde uniquement les 2 derniers bits de chaque couleur pour le bit qu'on cache
            hidingAfter = (0.299 * hidingPixel[0] + 0.587 * hidingPixel[0] + 0.114 * hidingPixel[0])
            darken = 0
            if hidingAfter > 200:
                darken = 1

            # On garde les 6 derniers bits de l'image qui cache et les deux premiers de celle caché
            rgbAfter = ((rgbBefore[0]&254) | darken, rgbBefore[1], rgbBefore[2])
            hider.putpixel((i,j), rgbAfter)

    hider.save(output)

#Conversion d'une image en noir et blanc
def hideImgText(hiderString, text , output):
    hider = Image.open(hiderString)
    hider.convert('RGB')
    byteMap = (''.join(map(bin,bytearray(text.encode('ascii'))))).replace('0b', '')
    print(byteMap)

    '''
    for i in range(hider.size[0]):
        for j in range(hider.size[1]):
            # on récupère le pixel de l'image qui cache
            rgbBefore = hider.getpixel((i,j))
            darken = 0
            if hidingAfter > 200:
                darken = 1

            # On garde les 6 derniers bits de l'image qui cache et les deux premiers de celle caché
            rgbAfter = ((rgbBefore[0]&254) | darken, rgbBefore[1], rgbBefore[2])
            hider.putpixel((i,j), rgbAfter)

    hider.save(output)
    '''


#hideImg("C:\\Users\\Nicolas\\Downloads\\intruder-lowtiergod.png", "C:\\Users\\Nicolas\\Downloads\\saul.jpg", "C:\\Users\\Nicolas\\Downloads\\output.png")
hideImgText("C:\\Users\\Nicolas\\Downloads\\intruder-lowtiergod.png", "KILL YOURSELF NOW", "C:\\Users\\Nicolas\\Downloads\\outputText.png")