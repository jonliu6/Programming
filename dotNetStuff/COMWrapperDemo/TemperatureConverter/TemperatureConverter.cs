using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;

[assembly: AssemblyKeyFile("strongName.key.pfx")]
namespace TemperatureConverter
{
    [ComVisible(true)]
    [Guid("ca559bba-960b-44af-b637-0ec53b2fa4b9")]
    [InterfaceType(ComInterfaceType.InterfaceIsIDispatch)]
    public interface ITemperatureConverter
    {
        double GetCelsius(double fahrenheit);
        double GetFahrenheit(double celsius);
    }

    [ComVisible(true)]
    [Guid("8b327f11-70a7-47f0-a20d-a524a26847b1")]
    [ClassInterface(ClassInterfaceType.None)]
    public class TemperatureConverter: ITemperatureConverter
    {
        public double GetCelsius(double fahrenheit)
        {
            return (fahrenheit - 32) * 5 / 9;
        }

        public double GetFahrenheit(double celsius)
        {
            return celsius * 9 / 5 + 32;
        }
    }
}
