This COM library is built by .Net Framework 4.6.2.

To create and sign a strong name assembly in Visual Studio, 
  1. right-click the project and choose Proerpties
  2. navigate to Signing tab and enable Sign the Assembly option
  3. click the dropdown below "Choose a strong name key file" and choose New...
  4. fill in name, password and etc and click OK.
  5. add [assembly: AssemblyKeyFile("keyfilename")] at the top of the class file
  6. rebuild the solution

To register, open a Command Window as Administrator and run:
  c:\Windows\Microsoft.NET\Framework\v<version>\RegAsm.exe TemperatureConverter.dll /codebase /tlb:TemperatureConverter.tlb

To un-register, run:
  c:\Windows\Microsoft.NET\Framework\v<version>\RegAsm.exe TemperatureConverter.dll /unregister