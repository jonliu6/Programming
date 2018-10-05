import math

def calcTime(plantName, meanDistMillKM):
  timePeriod = round(0.2 * math.sqrt(meanDistMillKM ** 3),2)
  print(plantName, "travels", meanDistMillKM, "millions kilometers to sun in about", timePeriod, "Earth days.")

calcTime("Mercury", 58)
calcTime("Venus", 108)
calcTime("Earth", 149)
calcTime("Mars", 228)
calcTime("Jupiter", 778)
calcTime("Saturn", 1420)
calcTime("Uranus", 2880)
calcTime("Neptune", 4520)
