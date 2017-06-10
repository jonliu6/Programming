#Generate Stub
wsimport -keep -verbose <directory> <WSDL URL>
wsgen -verbose -keep -cp . <class name with package>