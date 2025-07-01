import json
import sys

# get command line arguments
args = sys.argv
scriptName = args[0]
#userParams = args[1:]
userParamCount = len(args[1:])
srcFilename = ''
tgtFilename = ''
indentNumber = 4

#print("user inputs: " + str(userParamCount))
if userParamCount < 1:
    print(f"{scriptName} <source JSON file> <target JSON file> <number of indentations>")
    sys.exit()
elif userParamCount == 1:
    srcFilename = args[1]
    tgtFilename = args[1] + ".new"
elif userParamCount == 2:
    srcFilename = args[1]
    tgtFilename = args[2]
else:
    srcFilename = args[1]
    tgtFilename = args[2]
    indentNumber = args[3]


with open(srcFilename, 'r') as file:
    data = json.load(file)

with open(tgtFilename, 'w') as output_file:
    json.dump(data, output_file, indent=int(indentNumber))

file.close()
output_file.close()
