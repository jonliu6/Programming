# updated for Python 3.x
import sys
from xml.etree import ElementTree
from xml.dom import minidom

argCnt = len(sys.argv)
if argCnt < 2:
    print("Usage: " + sys.argv[0] + " <source XML file>")
    sys.exit(1)
	
xmlFName = sys.argv[1]
dom = minidom.parse(xmlFName)
xmlContent = dom.toprettyxml()

print(xmlContent)

xmlNewFName = xmlFName + ".new"
f = open(xmlNewFName, 'w')
f.write(xmlContent)
f.close()
print(xmlFName + " formatted as " + xmlNewFName)
