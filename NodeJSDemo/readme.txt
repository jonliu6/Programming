***NOTE*** you need to install all the pre-required software component in either 32- or 64-bit consistently, no mixture; or strange compile errors occur.

Install Node JS 6.9.x 32 bit
Install Python 2.7.x 32 bit
Install Visual C++ 2012+
Download and unzip Oracle instant Client 12.x 32 bit

Launch VC Development Console (32 bit) to set up variables or start
C:\Program Files (x86)\Microsoft Visual Studio 14.0\VC\bin\vcvars32.bat 

Set up environment variable:
*** Set up below environment variables
SET ORACLE_HOME=C:\Oracle\instantclient
SET OCI_LIB_DIR=%ORACLE_HOME%\sdk\lib\msvc
SET OCI_INC_DIR=%ORACLE_HOME%\sdk\include
*** Make sure Oracle Instant Client folder (%ORACLE_HOME%) is at the beginning of PATH if multiple Oracle clients are available
SET PATH=%ORACLE_HOME%;C:\DevApps\nodejs;c:\DevApps\Python27;%PATH%

Install node packages
** npm install -g express
** npm install -g require
** npm install -g oracledb