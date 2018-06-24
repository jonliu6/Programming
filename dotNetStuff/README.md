Some .Net helper classes facilitate Oracle DB access, MQ transactions, Logging, Property File Reading and etc.

For Oracle 11g, you need the following libraries from Instant Client or ODAC
oci.dll, Oracle.DataAccess.dll, oraociei11.dll and OraOps11w.dll. Oracle.DataAccess.dll needs to be in references of the .Net project

For MQ IBM.WMQ (Native API), you need amqmdnet.dll (put in the project reference) and amqmdxcs.dll. Best way is to install IBM Websphere MQ Client (Custom installation, not default since sometimes missing required *.dll files). For MQ IBM.XMS (JMS-style APis), you need IBM.XMS.dll (in the project references), and other IBM.XMS.* dll files. Again, installing IBM Websphere MQ Client is better.

For Crystal Report, you need to install Crystal Report Runtime libraries (e.g. CRRedist2007_x64.msi or CrystalReports2007.msi bundled with Visual Studio 2010+). add CrystalDecisions.CrystalReports.Engine and CrystalDecisions.CrystalReports.Shared into the project references at least.

SMTP is from Visual Studio, System.Net, System.Net.Mail, System.Net.Mime and etc.