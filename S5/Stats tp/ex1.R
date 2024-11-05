data = c(
  rep(0, times=43),
  rep(1,times=58),
  rep(2,times=49),
  rep(3, times=25),
  rep(4,times=13),
  rep(5,times=8),
  rep(6, times=4)
  )

tdata = table(data)
plot(tdata)
range(tdata)

fretdata = tdata/sum(tdata)
freqcumdata = cumsum(fretdata)
freqcumdata
# q1=1 q2=1 q3=2
plot(freqcumdata)
summary(data)

# 43*0+1/58+2*49 ... = 1.735
mean(data)
# (0-1.735)²+(1-1.735)²*58
variance = ((0-1.735)**2*43+(1-1.735)**2*58+(2-1.735)**2*49+(3-1.735)**2*25+(4-1.735)**2*13+(5-1.735)**2*8+(6-1.735)**2*4)/200
variance
var(data)
sqrt(variance)
sd(data)


#---------------------------------------------

load('guichet.rda')
guichet
q=guichet$queue
attach(guichet)

summary(queue)
var(queue)
sd(queue)
sqrt(var(queue))
plot(table(queue))
hist(queue)
boxplot(queue)
tqueue= table(queue)
plot(tqueue)
cumsumtqueue = cumsum(tqueue)/sum(tqueue)
plot(cumsumtqueue, type="s")
plot(ecdf(queue))

plot(tqueue/sum(tqueue))
lines(0:9, dpois(0:9,mean(queue), log=FALSE))


#---------------------------------------------

tenfants=matrix(c(1:8,10,510,248,132,55,29,15,2,8,1) ,ncol=2)
tenfant=c(510,248,132,55,29,15,2,8,0,1)
tenfants
enfants=rep(1:10,tenfant)
tenfant
enfants

plot(tenfant, type="h")
which.min(tenfant)
freqtenfants = tenfant/sum(table(enfants))
frecumenfants=cumsum(freqtenfants)
frecumenfants
plot(frecumenfants, type="s")
plot(ecdf(enfants))
var(enfants)
sd(enfants)

plot(freqtenfants, type="h")
plot(tenfant/sum(table(enfants)))
lines(1:10, dgeom(1:10,1/(1+mean(enfants)), log=FALSE))