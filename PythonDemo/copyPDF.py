from PyPDF2 import PdfReader, PdfWriter
import sys

# get command line arguments
args = sys.argv
scriptName = args[0]
#userParams = args[1:]
userParamCount = len(args[1:])
srcFilename = ''
tgtFilename = ''
startPageNumber = 0
endPageNumber = 0

if userParamCount < 1:
    print(f"{scriptName} <source PDF file> <target PDF file> <start page number> <end page number>")
    sys.exit()
elif userParamCount == 1:
    srcFilename = args[1]
    tgtFilename = args[1] + ".new"
elif userParamCount == 2:
    srcFilename = args[1]
    tgtFilename = args[2]
elif userParamCount == 3:
    srcFilename = args[1]
    tgtFilename = args[2]
    startPageNumber = args[3]
else:
    srcFilename = args[1]
    tgtFilename = args[2]
    startPageNumber = args[3]
    endPageNumber = args[4]

# Create a PDF reader and writer
reader = PdfReader(srcFilename)
writer = PdfWriter()

# Specify the pages you want to copy (e.g., pages 1 and last page)
page_count = len(reader.pages)
if endPageNumber == 0:
    endPageNumber = page_count
elif endPageNumber <= startPageNumber:
    print("please correct the page range (start and end) for PDF copying")
    sys.exit()  

# Add the specified pages to the writer
for page_num in range(startPageNumber, endPageNumber):
    writer.add_page(reader.pages[page_num])

# Write the selected pages to the output PDF
with open(tgtFilename, "wb") as output_file:
    writer.write(output_file)

output_file.close()
print(f"Pages {startPageNumber}-{endPageNumber} copied to {tgtFilename}")
