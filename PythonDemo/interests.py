initialAmount = 100
annualAmount = 10
interests = 0.1
asset = 0
#for x in range(0,20):
  # asset = (initialAmount + annualAmount * x) * (1 + interests) ** x
  #asset = (initialAmount) * (1 + interests) ** x # with a fixed initial amount, no annual deposite
  
  #print("year", x, ", asset=" , asset)

# print(asset)

def calcInterests(numOfYear, interestRate, amount, annualDeposit):
    if numOfYear == 0:
        return 1
    else:
        newAmount = (amount + annualDeposit) * (1+interestRate)
        print(numOfYear, " ", newAmount)
        return calcInterests(numOfYear-1, interestRate, newAmount, annualDeposit)
  
calcInterests(25, 0.05, 900, 1000)
